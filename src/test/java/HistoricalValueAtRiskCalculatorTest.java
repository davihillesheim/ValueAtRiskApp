import com.davihillesheim.varapp.HistoricalValueAtRiskCalculator;
import com.davihillesheim.varapp.Portfolio;
import com.davihillesheim.varapp.Trade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class HistoricalValueAtRiskCalculatorTest {

    private static Trade trade1;
    private static Portfolio portfolio;
    private static HistoricalValueAtRiskCalculator calculator;

    @BeforeAll
    public static void setup() {
        trade1 = new Trade(Arrays.asList(
                -2000000D, 1000000D, -3000000D, 4000000D, -2000000D, 1000000D, -3000000D, 4000000D, -1000000D, 2000000D,
                -5000000D, 3000000D, -2000000D, 4000000D, -1000000D, 3000000D, -3000000D, 2000000D, -4000000D, 1000000D));
        Trade trade2 = new Trade(Arrays.asList(
                -1000000D, 2000000D, -5000000D, 3000000D, -2000000D, 4000000D, -1000000D, 3000000D, -3000000D, 2000000D,
                -4000000D, 1000000D, -2000000D, 1000000D, -3000000D, 4000000D, -2000000D, 1000000D, -3000000D, 4000000D));
        portfolio = new Portfolio(Arrays.asList(trade1, trade2));
        calculator = new HistoricalValueAtRiskCalculator();
    }

    @Test
    public void testCalculateValueAtRisk() {
        double var = calculator.calculateValueAtRisk(trade1, 95);
        assertEquals(-5000000D, var);
    }

    @Test
    public void testCalculatePortfolioValueAtRisk() {
        double portfolioVaR = calculator.calculatePortfolioValueAtRisk(portfolio, 95);
        assertEquals(-9000000D, portfolioVaR);
    }

    @Test
    public void testCalculateValueAtRiskInvalidConfidenceLevel() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateValueAtRisk(trade1, 101));
    }

    @Test
    public void testCalculatePortfolioValueAtRiskEmptyPortfolio() {
        Portfolio emptyPortfolio = new Portfolio();
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculatePortfolioValueAtRisk(emptyPortfolio, 95)
        );
    }
}
