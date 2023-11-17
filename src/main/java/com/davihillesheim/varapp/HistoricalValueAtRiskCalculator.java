package com.davihillesheim.varapp;

import com.davihillesheim.varapp.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Responsible for calculating Value at Risk (VaR) using historical values for individual trades and portfolios.
 */
public class HistoricalValueAtRiskCalculator implements ValueAtRiskCalculator {

    private static final String CONFIDENCE_LEVEL_ERROR = "Confidence level must be between 1 and 100.";
    private static final String PORTFOLIO_EMPTY_ERROR = "Portfolio cannot be null or empty.";
    private static final String INCONSISTENT_TRADE_SIZES_ERROR = "All trades must have the same size.";

    /**
     * Calculates the Value at Risk (VaR) for an individual trade at the specified confidence level.
     *
     * @param trade           The trade for which VaR is calculated.
     * @param confidenceLevel The confidence level (in percentage) for VaR calculation.
     * @return The VaR for the given trade at the specified confidence level.
     * @throws IllegalArgumentException If the confidence level is not between 1 and 100.
     */
    public double calculateValueAtRisk(Trade trade, int confidenceLevel) {
        ValidationUtils.checkConfidenceLevel(confidenceLevel, CONFIDENCE_LEVEL_ERROR);

        List<Double> historicalValues = new ArrayList<>(trade.getHistoricalValues());
        Collections.sort(historicalValues);
        int position = (int) Math.round((1 - (confidenceLevel / 100.0)) * historicalValues.size());
        position = Math.max(0, position - 1);
        return historicalValues.get(position);
    }

    /**
     * Calculates the Value at Risk (VaR) for a portfolio of trades at the specified confidence level.
     *
     * @param portfolio       The portfolio for which VaR is calculated.
     * @param confidenceLevel The confidence level (in percentage) for VaR calculation.
     * @return The VaR for the given portfolio at the specified confidence level.
     * @throws IllegalArgumentException If the portfolio is empty, confidence level is not between 1 and 100,
     *                                  or if the trades in the portfolio have inconsistent sizes.
     */
    public double calculatePortfolioValueAtRisk(Portfolio portfolio, int confidenceLevel) {
        ValidationUtils.checkNotEmpty(portfolio.getTrades(), PORTFOLIO_EMPTY_ERROR);
        ValidationUtils.checkConfidenceLevel(confidenceLevel, CONFIDENCE_LEVEL_ERROR);
        ValidationUtils.checkConsistentTradeSizes(portfolio.getTrades(), INCONSISTENT_TRADE_SIZES_ERROR);

        List<Trade> trades = portfolio.getTrades();
        int numValues = trades.get(0).getHistoricalValues().size();
        List<Double> aggregatedValues = new ArrayList<>(Collections.nCopies(numValues, 0.0));

        for (Trade trade : trades) {
            List<Double> tradeValues = trade.getHistoricalValues();
            for (int i = 0; i < numValues; i++) {
                aggregatedValues.set(i, aggregatedValues.get(i) + tradeValues.get(i));
            }
        }

        Collections.sort(aggregatedValues);
        int position = (int) Math.round((1 - (confidenceLevel / 100.0)) * aggregatedValues.size());
        position = Math.max(0, position - 1);
        return aggregatedValues.get(position);
    }
}
