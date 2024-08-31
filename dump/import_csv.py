import csv
import mysql.connector
from dotenv import load_dotenv
from urllib.parse import urlparse
import os

load_dotenv()

mysql_url = os.getenv('MYSQL_URL')
parsed_url = urlparse(mysql_url)

# Connect to your database
connection = mysql.connector.connect(
    host = parsed_url.hostname,
    port = parsed_url.port,
    user= os.getenv('MYSQL_USER'),
    password= os.getenv('MYSQL_PASSWORD'),
    database='fantasy_team'
)
cursor = connection.cursor()

# Open the CSV file
with open('dump\\premier-player-23-24.csv', 'r', encoding='utf-8') as file:
    reader = csv.reader(file)
    next(reader)  # Skip the header row
    for row in reader:
        cursor.execute(
            """
            INSERT INTO player_statistics (player_name, nation, position, age, minutes_played, starts, minutes, `90s`, goals, assists, `g+a`, `g+pk`, penalty_kicks, penalty_kick_attempts, yellow_cards, red_cards, expected_goals, non_penalty_expected_goals, expected_assists, `npXg+XA`, proggresive_carries, proggresive_passes, proggresive_runs, goals_90, assists_90, `g+a_90`, `g+pk_90`, `g+a-pk_90`, `xG_90`, `xA_90`, `xG+xA_90`, `npxG_90`, `npxG+xA_90`, team)
            VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
            """, 
            tuple(row),
        )

# Commit the transaction
connection.commit()

# Close the connection
cursor.close()
connection.close()