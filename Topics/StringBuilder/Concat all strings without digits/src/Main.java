import java.util.Scanner;

class ConcatenateStringsProblem {

    public static String concatenateStringsWithoutDigits(String[] strings) {
        // write your code with StringBuilder here

        StringBuilder builder = new StringBuilder();

        for (String string : strings) {
            char[] chars = string.toCharArray();

            for (char c : chars) {
                if (!Character.isDigit(c)) {
                    builder.append(c);
                }
            }

        }

        return builder.toString();

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split("\\s+");
        String result = concatenateStringsWithoutDigits(strings);
        System.out.println(result);
    }
}