package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        write your code here
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!\n" +
                "\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter 0 to exit.");

        long number;

        do {
            System.out.println("\nEnter a request: ");
            number = scanner.nextLong();

            if (number >= 1) {

                boolean isEven = isEvenNumber(number);
                boolean isOdd = isOddNumber(number);
                boolean isDuck = isDuckNumber(number);
                boolean isBuzz = isBuzzNumber(number);
                boolean isPalindromic = isPalindromicNumber(number);

                System.out.println("Properties of " + number);
                System.out.println("\teven: " + isEven);
                System.out.println("\todd: " + isOdd);
                System.out.println("\tbuzz: " + isBuzz);
                System.out.println("\tduck: " + isDuck);
                System.out.println("\tpalindromic: " + isPalindromic);

            } else {
                System.out.println("The first parameter should be a natural number or zero.");
            }

        } while (number != 0);
    }

    public static boolean isEvenNumber(long number) {
        return number % 2 == 0;
    }

    private static boolean isOddNumber(long number) {
        return !isEvenNumber(number);
    }

    private static boolean isDuckNumber(long number) {
        return String.valueOf(number).contains("0");
    }

    private static boolean isBuzzNumber(long number) {
        return number % 7 == 0 || number % 10 == 7;
    }

    private static boolean isPalindromicNumber(long number) {

        String numberAsString = String.valueOf(number);
        StringBuilder builder = new StringBuilder(numberAsString);

        String reversed = builder.reverse().toString();

        return numberAsString.equals(reversed);
    }
}


