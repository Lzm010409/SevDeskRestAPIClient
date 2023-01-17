package money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountConverter {
    public double euroToCents(double number) {
        long returnValue = (long) (number * 100);
        return returnValue;
    }

    public long euroToCents(long number) {
        return number * 100;
    }

    public double centsToEuro(long amount) {
        double dAmount = amount;
        double returnValue = dAmount / 100;
        return returnValue;
    }
    public double centsToEuro(double amount) {
        double returnValue = amount / 100;
        return returnValue;
    }
    public double roundDoubleNumber(double v) {
        BigDecimal bd = new BigDecimal(v).setScale(2, RoundingMode.HALF_UP);
        double roundedValue = bd.doubleValue();
        return roundedValue;
    }


    public double roundFloatNumber(float number) {
        BigDecimal bd = new BigDecimal(number).setScale(2, RoundingMode.HALF_UP);
        double roundedValue = bd.doubleValue();
        return roundedValue;
    }

    public double removeComma(double d) {
        String newString = String.valueOf(d);
        StringBuilder builder = new StringBuilder();
        if (newString.contains(",")) {
            char[] chars = newString.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != ',') {
                    builder.append(chars[i]);
                }
                if (chars[i] == ',') {
                    builder.append(".");
                }
            }
        }
        return Double.valueOf(builder.toString());
    }
}
