/**
 * Defines methods for calculating Value at Risk (VaR).
 */
package com.davihillesheim.varapp;

public interface ValueAtRiskCalculator {
    /**
     * Calculates the Value at Risk (VaR) for an individual trade at the specified confidence level.
     *
     * @param trade           The trade for which VaR is calculated.
     * @param confidenceLevel The confidence level (in percentage) for VaR calculation.
     * @return The VaR for the given trade at the specified confidence level.
     * @throws IllegalArgumentException If the confidence level is not between 1 and 100.
     */
    double calculateValueAtRisk(Trade trade, int confidenceLevel);

    /**
     * Calculates the Value at Risk (VaR) for a portfolio of trades at the specified confidence level.
     *
     * @param portfolio        The portfolio for which VaR is calculated.
     * @param confidenceLevel The confidence level (in percentage) for VaR calculation.
     * @return The VaR for the given portfolio at the specified confidence level.
     * @throws IllegalArgumentException If the portfolio is empty, confidence level is not between 1 and 100,
     *                                  or if the trades in the portfolio have inconsistent sizes.
     */
    double calculatePortfolioValueAtRisk(Portfolio portfolio, int confidenceLevel);
}
