import csv
import mysql.connector
from dotenv import load_dotenv
from urllib.parse import urlparse
import os

def calculate_score(pos, mp, gls, ast, xG, xAG, prgC, prgP, crdY, crdR):
    score = 0.0
    if pos in ["FW", "ST", "CF"]:  # Forward positions
        score = 5 * gls + 3 * ast + 3 * xG + 2 * xAG - 2 * crdY - 5 * crdR
    elif pos in ["MF", "AM", "DM"]:  # Midfielder positions
        score = 4 * ast + 5 * prgP + 3 * xAG + 2 * prgC - 2 * crdY - 5 * crdR
    elif pos == "DF":  # Defender
        score = 3 * gls + 6 * prgC + 2 * prgP - 2 * crdY - 5 * crdR + 0.5 * mp
    else:  # Default, assuming defensive roles like Goalkeeper (GK)
        score = 0.5 * mp - 2 * crdY - 5 * crdR
        
    return score + 10  # Adding base score to ensure no score is zero

def parse_to_int(value):
    try:
        return int(value)
    except ValueError:
        return 0

def parse_to_double(value):
    try:
        return float(value)
    except ValueError:
        return 0.0

load_dotenv()
mysql_url = os.getenv('MYSQL_URL')
parsed_url = urlparse(mysql_url)

# Connect to your database
connection = mysql.connector.connect(
    host=parsed_url.hostname,
    port=parsed_url.port,
    user=os.getenv('MYSQL_USER'),
    password=os.getenv('MYSQL_PASSWORD'),
    database='fantasy_team'
)

cursor = connection.cursor()

# Check if player_score column exists
cursor.execute("SHOW COLUMNS FROM player_statistics LIKE 'player_score'")
result = cursor.fetchone()

# If player_score column does not exist, add it
if not result:
    cursor.execute("ALTER TABLE player_statistics ADD COLUMN player_score DOUBLE")

# Delete all existing records from the table
cursor.execute("DELETE FROM player_statistics")

# Commit the deletion transaction
connection.commit()

# Open the CSV file
with open('premier-player-23-24.csv', 'r', encoding='utf-8') as file:
    reader = csv.reader(file)
    next(reader)  # Skip the header row

    for row in reader:
        # Extracted fields with nation, positions, and remaining fields named 'rest'
        player_name, nation, positions, age, minutes_played, starts, minutes, ninety_s, goals, assists, g_a, g_pk, penalty_kicks, penalty_kick_attempts, yellow_cards, red_cards, expected_goals, non_penalty_expected_goals, expected_assists, npXg_XA, proggresive_carries, proggresive_passes, proggresive_runs, goals_90, assists_90, g_a_90, g_pk_90, g_a_pk_90, xG_90, xA_90, xG_xA_90, npxG_90, npxG_xA_90, team = row

        # Split the positions by comma and strip any whitespace around them
        unique_positions = [position.strip() for position in positions.split(',')]

        # Insert a new row for each position
        for position in unique_positions:
            # Calculate player score
            player_score = calculate_score(
                position, parse_to_int(minutes_played), parse_to_int(goals), 
                parse_to_int(assists), parse_to_double(expected_goals), 
                parse_to_double(expected_assists), parse_to_int(proggresive_carries), 
                parse_to_int(proggresive_passes), parse_to_int(yellow_cards), 
                parse_to_int(red_cards)
            )

            new_row = [
                player_name, nation, position, age, minutes_played, starts, minutes,
                ninety_s, goals, assists, g_a, g_pk, penalty_kicks, penalty_kick_attempts,
                yellow_cards, red_cards, expected_goals, non_penalty_expected_goals,
                expected_assists, npXg_XA, proggresive_carries, proggresive_passes,
                proggresive_runs, goals_90, assists_90, g_a_90, g_pk_90, g_a_pk_90, 
                xG_90, xA_90, xG_xA_90, npxG_90, npxG_xA_90, team, player_score
            ]

            cursor.execute(
                """
                INSERT INTO player_statistics (player_name, nation, position, age, minutes_played, starts, minutes, `90s`, goals, assists, `g+a`, `g+pk`, penalty_kicks, penalty_kick_attempts, yellow_cards, red_cards, expected_goals, non_penalty_expected_goals, expected_assists, `npXg+XA`, proggresive_carries, proggresive_passes, proggresive_runs, goals_90, assists_90, `g+a_90`, `g+pk_90`, `g+a-pk_90`, `xG_90`, `xA_90`, `xG+xA_90`, `npxG_90`, `npxG+xA_90`, team, player_score)
                VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
                """,
                tuple(new_row)
            )

# Commit the insertion transactions
connection.commit()

# Close the connection
cursor.close()
connection.close()