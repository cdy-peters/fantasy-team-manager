from scipy.stats import zscore

POSITION_WEIGHTS = {
    "FW": {
        "Gls": 0.7,
        "Ast": 0.3,
        "npxG": 0.1,
        "xAG": 0.1,
        "Min": 0.1,
        "CrdY": -0.2,
        "CrdR": -0.5,
    },
    "MF": {
        "PrgP": 0.4,
        "Ast": 0.3,
        "xAG": 0.2,
        "npxG": 0.1,
        "Min": 0.1,
        "CrdY": -0.2,
        "CrdR": -0.5,
    },
    "DF": {
        "PrgC": 0.4,
        "PrgP": 0.3,
        "Min": 0.1,
        "CrdY": -0.2,
        "CrdR": -0.5,
    },
    "GK": {  # Adding cards causes NaN scores
        "PrgC": 0.4,
        "PrgP": 0.3,
        "Min": 0.1,
    },
}


def has_position(positions_str, position):
    """
    Check if a player has a specific position
    """

    return position in map(str.strip, positions_str.split(","))


def calculate_weighted_rating(group, stats, weights):
    """
    Calculate the weighted rating for players in the group using z-scores of stats.
    """

    group[stats] = group[stats].apply(zscore)

    weighted_sum = sum(group[stat] * weight for stat, weight in weights.items())
    group["player_rating"] = round(weighted_sum, 2)

    return group


def calculate_ratings(df):
    """
    Assign player ratings based on their positions using position-specific weights.
    """

    data = df.copy()  # Avoid modifying the original DataFrame

    for position, weights in POSITION_WEIGHTS.items():
        stats = list(weights.keys())

        # Filter players by position
        position_group = data[data["Pos"].apply(has_position, args=(position,))].copy()

        # Calculate ratings for this position group
        position_group = calculate_weighted_rating(position_group, stats, weights)

        # Update the original DataFrame with the new ratings
        df.loc[position_group.index, "player_rating"] = position_group["player_rating"]
