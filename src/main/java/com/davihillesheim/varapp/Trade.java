package com.davihillesheim.varapp;

import com.davihillesheim.varapp.utils.ValidationUtils;

import java.util.List;

/**
 * Represents a financial trade with historical values.
 * It stores a list of historical values and provides methods for accessing them.
 */
public class Trade {
    private static final String HISTORICAL_VALUES_EMPTY_ERROR = "Historical values cannot be null or empty.";

    private List<Double> historicalValues;

    /**
     * Constructs a Trade object with the specified list of historical values.
     *
     * @param historicalValues The list of historical values associated with this trade.
     * @throws IllegalArgumentException If the historical values list is null or empty.
     */
    public Trade(List<Double> historicalValues) {
        ValidationUtils.checkNotEmpty(historicalValues, HISTORICAL_VALUES_EMPTY_ERROR);
        this.historicalValues = historicalValues;
    }

    /**
     * Gets the list of historical values associated with this trade.
     *
     * @return The list of historical values.
     */
    public List<Double> getHistoricalValues() {
        return historicalValues;
    }
}
