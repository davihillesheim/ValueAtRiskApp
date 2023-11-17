package com.davihillesheim.varapp;

import com.davihillesheim.varapp.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a collection of financial trades.
 * It allows users to manage a list of trades, including adding trades to the portfolio and retrieving the list of trades.
 */
public class Portfolio {
    private static final String INITIAL_TRADES_NULL_ERROR = "Initial trades cannot be null.";
    private static final String TRADE_NULL_ERROR = "Trade cannot be null.";
    private static final String TRADE_VALUES_EMPTY_ERROR = "Trade cannot have empty historical values.";

    private final List<Trade> trades;

    /**
     * Constructs an empty Portfolio.
     */
    public Portfolio() {
        this.trades = new ArrayList<>();
    }

    /**
     * Constructs a Portfolio with the specified initial list of trades.
     *
     * @param initialTrades The initial list of trades to populate the portfolio.
     * @throws IllegalArgumentException If the initial trades list is null or empty.
     */
    public Portfolio(List<Trade> initialTrades) {
        ValidationUtils.checkNotEmpty(initialTrades, INITIAL_TRADES_NULL_ERROR);
        this.trades = new ArrayList<>(initialTrades);
    }

    /**
     * Adds a trade to the portfolio.
     *
     * @param trade The trade to add to the portfolio.
     * @throws IllegalArgumentException If the trade is null or if the trade has empty historical values.
     */
    public void addTrade(Trade trade) {
        if (trade == null) {
            throw new IllegalArgumentException(TRADE_NULL_ERROR);
        }
        ValidationUtils.checkNotEmpty(trade.getHistoricalValues(), TRADE_VALUES_EMPTY_ERROR);
        trades.add(trade);
    }

    /**
     * Gets an unmodifiable view of the list of trades in the portfolio.
     *
     * @return An unmodifiable list of trades in the portfolio.
     */
    public List<Trade> getTrades() {
        return Collections.unmodifiableList(trades);
    }
}
