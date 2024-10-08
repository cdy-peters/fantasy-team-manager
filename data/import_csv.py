"""
To install packages run:
    pip install -r requirements.txt
"""

import os
from urllib.parse import urlparse
import mysql.connector
from dotenv import load_dotenv
import pandas as pd
from scores import calculate_scores
from ratings import calculate_ratings

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


def main():
    df = pd.read_csv("premier-player-23-24.csv")
    calculate_ratings(df)
    calculate_scores(df)

    connection = connect()
    cursor = connection.cursor()

    # Delete existing player statistics
    cursor.execute("DELETE FROM player_statistics")
    connection.commit()

    # Insert the data into the table
    for _, row in df.iterrows():
        cursor.execute(
            """
            INSERT INTO player_statistics (player_name, nation, position, age, minutes_played, starts, minutes, `90s`, goals, assists, `g+a`, `g+pk`, penalty_kicks, penalty_kick_attempts, yellow_cards, red_cards, expected_goals, non_penalty_expected_goals, expected_assists, `npXg+XA`, proggresive_carries, proggresive_passes, proggresive_runs, goals_90, assists_90, `g+a_90`, `g+pk_90`, `g+a-pk_90`, `xG_90`, `xA_90`, `xG+xA_90`, `npxG_90`, `npxG+xA_90`, team, player_rating, player_score)
            VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
            """,
            tuple(row),
        )
    connection.commit()

    # Close the connection
    cursor.close()
    connection.close()


if __name__ == "__main__":
    main()
