COMMON_WEIGHTS = {
    "Min": (2 / 90),  # 2 / 90 minutes
    "Ast": 3,
    "CrdY": -1,
    "CrdR": -3,
}

POSITION_WEIGHTS = {
    "FW": {
        "Gls": 5,
    },
    "MF": {
        "Gls": 4,
    },
    "DF": {
        "Gls": 3,
    },
    "GK": {
        "Gls": 2,
        "PrgC": 3,
        "PrgP": 2,
    },
}


def calculate_position_score(row, position):
    """
    Calculates the score for a player based on their position
    """

    # Init score with common weights
    score = sum(row[stat] * weight for stat, weight in COMMON_WEIGHTS.items())

    # Add position-specific weights
    if position in POSITION_WEIGHTS:
        score += sum(
            row[stat] * weight for stat, weight in POSITION_WEIGHTS[position].items()
        )

    return score


def calculate_score(row):
    positions = [position.strip() for position in row["Pos"].split(",")]

    total_score = sum(calculate_position_score(row, position) for position in positions)
    avg_score = total_score / len(positions)  # Average score across all positions

    return max(0.0, round(avg_score, 2))


def calculate_scores(df):
    df["player_score"] = df.apply(calculate_score, axis=1)
