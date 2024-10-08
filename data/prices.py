import pandas as pd

MIN_PRICE = 4
MAX_PRICE = 15
NUM_BINS = 100


def calculate_prices(df):
    """
    Assign player prices based on their ratings.
    """

    # Create bins for player ratings and calculate the price for each bin
    price_range = MAX_PRICE - MIN_PRICE
    df["player_price"] = MIN_PRICE + pd.cut(
        df["player_rating"], bins=NUM_BINS, labels=False
    ) * price_range / (NUM_BINS - 1)
