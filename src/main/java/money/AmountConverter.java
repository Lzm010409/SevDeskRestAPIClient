package money;

public class AmountConverter {
    public long euroToCents(double number) {
        long returnValue = (long) (number * 100);
        return returnValue;
    }

    public long euroToCents(long number) {
        return number * 100;
    }

    public double centsToEuro(long amount) {
        double returnValue = amount / 100;
        return returnValue;
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
