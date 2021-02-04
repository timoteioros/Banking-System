package utils;
import java.util.Random;

public class CreditCardGenerator {

    private static Random random = new Random(System.currentTimeMillis());

    public static String generateExpDate(){
        return String.valueOf(random.nextInt(12) + 1) + "/" + String.valueOf(random.nextInt(10) + 2021);
    }

    public static String generateCCV(){
        return String.valueOf(random.nextInt(999 - 100) + 100);
    }

    public static String generateNumber() {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            int digit = random.nextInt(10);
            builder.append(digit);
        }

        // Do the Luhn algorithm to generate the check digit.
        int checkDigit = getCheckDigit(builder.toString());
        builder.append(checkDigit);

        return builder.toString();
    }

    private static int getCheckDigit(String number) {

        int sum = 0;
        for (int i = 0; i < number.length(); i++) {

            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }

        int mod = sum % 10;
        return ((mod == 0) ? 0 : 10 - mod);
    }
}
