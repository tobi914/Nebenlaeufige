package u1.a2;

import java.math.BigDecimal;

public class Job implements Runnable
{
    private static BigDecimal MINUS_ONE = BigDecimal.valueOf(-1);
    private static BigDecimal TWO = BigDecimal.valueOf(2);

    private int start, end;
    private BigDecimal result;

    public Job(int start, int end)
    {
        this.start = start;
        this.end = end;
        this.result = BigDecimal.ZERO;
    }

    @Override
    public void run()
    {
        BigDecimal denominator, numerator;
        for (int i = start; i <= end; ++i)
        {
            denominator = MINUS_ONE.pow(i);
            numerator = TWO.multiply(BigDecimal.valueOf(i)).add(BigDecimal.ONE);
            result = result.add(denominator.divide(numerator, 100, BigDecimal.ROUND_HALF_EVEN));
        }
    }

    public BigDecimal getResult()
    {
        return result;
    }
}