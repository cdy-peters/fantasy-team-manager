import pandas as pd
import random
from sqlalchemy import create_engine, exc, text
from sqlalchemy.orm import sessionmaker
from faker import Faker
import bcrypt

# Initialize Faker
fake = Faker()

# Create a mock player_statistics dataframe
player_ids = list(range(3063, 3643))
player_statistics_mock = pd.DataFrame({
    'id': player_ids,
    'player_price': [random.uniform(1, 10) for _ in player_ids],
    'player_score': [random.uniform(1, 100) for _ in player_ids]
})

def generate_mock_users(num_users):
    """Generate mock users."""
    users = set()  # Use a set to ensure unique emails
    i = 0
    while len(users) < num_users:
        i += 1
        name = fake.first_name()
        email = f"{name.lower()}{i}@example.com"
        username = f"{name.lower()}{i}"
        password_hash = bcrypt.hashpw('password'.encode('utf-8'), bcrypt.gensalt()).decode('utf-8')
        users.add((i, name, email, username, password_hash))
    return list(users)

def select_team(player_statistics, max_price=100, min_price=95):
    """Select a team of 15 players with a total price between min_price and max_price."""
    attempts = 0
    while attempts < 10000:
        attempts += 1
        selected_players = []
        accumulated_price = 0

        # Shuffle player statistics to pick players randomly
        player_statistics_shuffled = player_statistics.sample(frac=1).reset_index(drop=True)

        for _, player in player_statistics_shuffled.iterrows():
            if accumulated_price + player['player_price'] <= max_price:
                selected_players.append((player['id'], player['player_price'], player['player_score']))
                accumulated_price += player['player_price']
            if len(selected_players) == 15:
                break
        
        if len(selected_players) == 15 and accumulated_price >= min_price:
            break

        # If the price is below the minimum threshold, try replacing some players
        if accumulated_price < min_price:
            for i, (player_id, player_price, player_score) in enumerate(selected_players):
                candidate_player = player_statistics.sample().iterrows()
                _, candidate_stats = next(candidate_player)
                
                # Attempt to swap players to increase the team price to at least min_price
                if candidate_stats['player_price'] - player_price + accumulated_price <= max_price:
                    selected_players[i] = (candidate_stats['id'], candidate_stats['player_price'], candidate_stats['player_score'])
                    accumulated_price += candidate_stats['player_price'] - player_price
                    if accumulated_price >= min_price:
                        break

    return selected_players, accumulated_price

def generate_user_rosters(session, user_ids, player_statistics, max_price=100, min_price=95):
    """Generate user rosters."""
    print(f"Creating rosters for {len(user_ids)} users...")
    rosters_created = 0
    total_users = len(user_ids)
    try:
        for index, user_id in enumerate(user_ids, start=1):
            selected_players, accumulated_price = select_team(player_statistics, max_price, min_price)
            roster_score = sum(player[2] for player in selected_players)

            # Insert roster into database
            session.execute(text(
                """
                INSERT INTO user_roster (
                    user_id, position1_player_id, position2_player_id, position3_player_id, 
                    position4_player_id, position5_player_id, position6_player_id, position7_player_id, 
                    position8_player_id, position9_player_id, position10_player_id, position11_player_id,
                    sub1_player_id, sub2_player_id, sub3_player_id, sub4_player_id,
                    roster_score, roster_price
                ) VALUES (
                    :user_id, :p1, :p2, :p3, :p4, :p5, :p6, :p7, :p8, :p9, :p10, :p11,
                    :sub1, :sub2, :sub3, :sub4, :roster_score, :roster_price
                )
                """
            ), {
                "user_id": user_id,
                "p1": selected_players[0][0], "p2": selected_players[1][0], "p3": selected_players[2][0],
                "p4": selected_players[3][0], "p5": selected_players[4][0], "p6": selected_players[5][0],
                "p7": selected_players[6][0], "p8": selected_players[7][0], "p9": selected_players[8][0],
                "p10": selected_players[9][0], "p11": selected_players[10][0],
                "sub1": selected_players[11][0], "sub2": selected_players[12][0],
                "sub3": selected_players[13][0], "sub4": selected_players[14][0],
                "roster_score": float(roster_score), "roster_price": float(accumulated_price)
            })
            rosters_created += 1

            # Progress indicator
            print(f"Processed {index}/{total_users} users. Rosters created: {rosters_created}")

        # Commit the transaction if all statements executed successfully
        session.commit()
        print(f"Finished creating {rosters_created} rosters and committed to the database.")

    except exc.SQLAlchemyError as e:
        # Rollback the transaction in case of an error
        session.rollback()
        print(f"Error creating rosters: {e}")

# MySQL Database connection details
username = 'root'
password = 'root'
hostname = 'localhost'
port = '3306'  # Usually 3306 for MySQL
database_name = 'fantasy_team'

# Establish the database connection
engine = create_engine(f'mysql+mysqldb://{username}:{password}@{hostname}/{database_name}', echo=True)

# Create a configured "Session" class
Session = sessionmaker(bind=engine)

# Create a session
session = Session()

# Create the user table and populate it with mock data
with engine.connect() as connection:
    connection.execute(text("""CREATE TABLE IF NOT EXISTS user (
        id INT PRIMARY KEY, name VARCHAR(100), email VARCHAR(255), username VARCHAR(100), password VARCHAR(255))"""))
    users_data = generate_mock_users(100)  # Number of mock users
    connection.execute(text("DELETE FROM user"))  # Clear existing data

    # Insert users data using executemany pattern
    connection.execute(
        text("INSERT INTO user (id, name, email, username, password) VALUES (:id, :name, :email, :username, :password)"),
        [dict(id=user[0], name=user[1], email=user[2], username=user[3], password=user[4]) for user in users_data]
    )

    # Create the user_roster table
    connection.execute(text("""CREATE TABLE IF NOT EXISTS user_roster (
        id INT AUTO_INCREMENT PRIMARY KEY, user_id INT, position1_player_id INT, 
        position2_player_id INT, position3_player_id INT, position4_player_id INT, 
        position5_player_id INT, position6_player_id INT, position7_player_id INT, 
        position8_player_id INT, position9_player_id INT, position10_player_id INT, 
        position11_player_id INT, sub1_player_id INT, sub2_player_id INT, sub3_player_id INT, 
        sub4_player_id INT, roster_score FLOAT, roster_price FLOAT)"""))

# Generate and insert mock data for user_rosters
user_ids = [user[0] for user in users_data]
generate_user_rosters(session, user_ids, player_statistics_mock)
