import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialSolver {

    public static void main(String[] args) throws IOException {

        String content = new String(Files.readAllBytes(Paths.get("test.json")));

        int k = extractK(content);

        List<BigInteger> xValues = new ArrayList<>();
        List<BigInteger> yValues = new ArrayList<>();

        extractPoints(content, k, xValues, yValues);

        BigDecimal constant = computeConstant(xValues, yValues);
        if (xValues.size() < k)
    throw new RuntimeException("Insufficient roots provided");

        System.out.println("Constant term (c) = " + constant.toPlainString());
    }

    private static int extractK(String content) {

        Pattern pattern = Pattern.compile("\"k\"\\s*:\\s*(\\d+)");
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }

        throw new RuntimeException("k not found in JSON");
    }

    private static void extractPoints(String content, int k,
                                      List<BigInteger> xValues,
                                      List<BigInteger> yValues) {

        Pattern pattern = Pattern.compile(
                "\"(\\d+)\"\\s*:\\s*\\{\\s*\"base\"\\s*:\\s*\"(\\d+)\"\\s*,\\s*\"value\"\\s*:\\s*\"([0-9a-zA-Z]+)\"",
                Pattern.MULTILINE);

        Matcher matcher = pattern.matcher(content);

        while (matcher.find() && xValues.size() < k) {

            String xStr = matcher.group(1);
            int base = Integer.parseInt(matcher.group(2));
            String value = matcher.group(3);

            BigInteger x = new BigInteger(xStr);
            BigInteger y = new BigInteger(value, base);

            xValues.add(x);
            yValues.add(y);
        }
    }

    private static BigDecimal computeConstant(List<BigInteger> x,
                                              List<BigInteger> y) {

        MathContext mc = new MathContext(100);
        BigDecimal result = BigDecimal.ZERO;

        int k = x.size();

        for (int i = 0; i < k; i++) {

            BigDecimal numerator = BigDecimal.ONE;
            BigDecimal denominator = BigDecimal.ONE;

            for (int j = 0; j < k; j++) {
                if (i != j) {

                    numerator = numerator.multiply(
                            new BigDecimal(x.get(j).negate()), mc);

                    denominator = denominator.multiply(
                            new BigDecimal(x.get(i).subtract(x.get(j))), mc);
                }
            }

            BigDecimal term = new BigDecimal(y.get(i))
                    .multiply(numerator.divide(denominator, mc), mc);

            result = result.add(term, mc);
        }

        return result;
    }
}