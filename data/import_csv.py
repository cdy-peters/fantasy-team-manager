"""
To install packages run:
    pip install -r requirements.txt
"""

import os
from urllib.parse import urlparse
import mysql.connector
from dotenv import load_dotenv
import pandas as pd

load_dotenv()


def connect():
    mysql_url = os.getenv("MYSQL_URL")
    parsed_url = urlparse(mysql_url)

    conn = mysql.connector.connect(
        host=parsed_url.hostname,
        port=parsed_url.port,
        user=os.getenv("MYSQL_USER"),
        password=os.getenv("MYSQL_PASSWORD"),
        database="fantasy_team",
    )

    return conn


def calculate_score(positions, _minutes, gls, ast, prgC, prgP, crdY, crdR):
    unique_positions = [position.strip() for position in positions.split(",")]

    score = 0.0

    for pos in unique_positions:
        score += 2 * _minutes / 90
        score += 3 * ast
        score -= 1 * crdY
        score -= 3 * crdR

        if pos == "FW":
            score += 5 * gls
        elif pos == "MF":
            score += 4 * gls
        elif pos == "DF":
            score += 3 * gls
        elif pos == "GK":
            score += 2 * gls + 3 * prgC + 2 * prgP
        else:
            return 0.0

    score /= len(unique_positions)
    score = round(score, 2)

    return max(0.0, score)


def main():
    df = pd.read_csv("premier-player-23-24.csv")

    df["player_score"] = df.apply(
        lambda row: calculate_score(
            row["Pos"],
            row["Min"],
            row["Gls"],
            row["Ast"],
            row["PrgC"],
            row["PrgP"],
            row["CrdY"],
            row["CrdR"],
        ),
        axis=1,
    )

    connection = connect()
    cursor = connection.cursor()

    # Check if the player_statistics table exists
    cursor.execute("SHOW COLUMNS FROM player_statistics LIKE 'player_score'")
    if not cursor.fetchone():
        cursor.execute("ALTER TABLE player_statistics ADD COLUMN player_score DOUBLE")

    # Delete existing player statistics
    cursor.execute("DELETE FROM player_statistics")
    connection.commit()

    # Insert the data into the table
    for _, row in df.iterrows():
        cursor.execute(
            """
            INSERT INTO player_statistics (player_name, nation, position, age, minutes_played, starts, minutes, `90s`, goals, assists, `g+a`, `g+pk`, penalty_kicks, penalty_kick_attempts, yellow_cards, red_cards, expected_goals, non_penalty_expected_goals, expected_assists, `npXg+XA`, proggresive_carries, proggresive_passes, proggresive_runs, goals_90, assists_90, `g+a_90`, `g+pk_90`, `g+a-pk_90`, `xG_90`, `xA_90`, `xG+xA_90`, `npxG_90`, `npxG+xA_90`, team, player_score)
            VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
            """,
            tuple(row),
        )
    connection.commit()

    # Close the connection
    cursor.close()
    connection.close()


if __name__ == "__main__":
    main()
