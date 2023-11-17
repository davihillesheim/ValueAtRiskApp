package com.davihillesheim.varapp;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class ValueAtRiskApp {
    public static void main(String[] args) {
        Trade trade1 = new Trade(Arrays.asList(-1000D, 2000D, -5000D, 3000D, -2000D, 4000D, -1000D, 3000D, -3000D, 2000D));
        Trade trade2 = new Trade(Arrays.asList(-2000D, 1000D, -3000D, 4000D, -2000D, 1000D, -3000D, 4000D, -1000D, 2000D));

        List<Trade> trades = new ArrayList<>();
        trades.add(trade1);
        trades.add(trade2);
        Portfolio portfolio = new Portfolio(trades);

        HistoricalValueAtRiskCalculator calculator = new HistoricalValueAtRiskCalculator();
        double singleTradeVaR = calculator.calculateValueAtRisk(trade1, 95);
        double portfolioVaR = calculator.calculatePortfolioValueAtRisk(portfolio, 95);

        System.out.println("Single Trade VaR (95% confidence): " + singleTradeVaR);
        System.out.println("Portfolio VaR (95% confidence): " + portfolioVaR);
    }
}
