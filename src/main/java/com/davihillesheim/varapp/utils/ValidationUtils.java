package com.davihillesheim.varapp.utils;

import com.davihillesheim.varapp.Trade;

import java.util.List;

public class ValidationUtils {

    public static void checkNotEmpty(List<?> list, String message) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkConfidenceLevel(int confidenceLevel, String message) {
        if (confidenceLevel <= 0 || confidenceLevel > 100) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkConsistentTradeSizes(List<Trade> trades, String message) {
        int expectedSize = trades.get(0).getHistoricalValues().size();
        for (Trade trade : trades) {
            if (trade.getHistoricalValues().size() != expectedSize) {
                throw new IllegalArgumentException(message);
            }
        }
    }
}
