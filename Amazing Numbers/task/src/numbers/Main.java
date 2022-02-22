package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        write your code here
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!\n");
        printInstructions();

        long number;
        int times;

        do {
            System.out.print("\nEnter a request: ");
            String[] separatedNumbers = scanner.nextLine().split(" ");

            if (separatedNumbers.length == 0) {
                printInstructions();
                number = 1;  // it can be any number except 0
                continue;
            } else {

                try {
                    number = Long.parseLong(separatedNumbers[0]);
                } catch (NumberFormatException e) {
                    printFirstWrongParameterWarning();
                    number = 1;  // it can be any number except 0
                    continue;
                } finally {
                    times = 1; // it can be any number greater than or equal to 1
                }

                if (separatedNumbers.length > 1) {
                    try {
                        times = Integer.parseInt(separatedNumbers[1]);
                    } catch (NumberFormatException e) {
                        printSecondWrongParameterWarning();
                        continue;
                    }
                }
            }

            if (number != 0) {
                if (number < 0 && times >= 1) {
                    printFirstWrongParameterWarning();
                } else if (number >= 1 && times < 1) {
                    printSecondWrongParameterWarning();
                } else if (number < 0) {
                    printFirstWrongParameterWarning();
                    printSecondWrongParameterWarning();
                } else if (separatedNumbers.length == 1) {
                    printSingleNumberProperties(number);
                } else {
                    printMultipleNumberProperties(number, times);
                }
            }

        } while (number != 0);

        System.out.println("\nGoodbye!");
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

    private static boolean isGapfulNumber(long number) {

        if (number > 100) {
            String numberAsString = String.valueOf(number);
            String firstNumberAsString = numberAsString.substring(0, 1);
            String lastNumberAsString = numberAsString.substring(numberAsString.length() - 1);

            long connectedNumbers = Long.parseLong(firstNumberAsString + lastNumberAsString);

            return number % connectedNumbers == 0;

        } else {
            return false;
        }
    }

    private static void printSingleNumberProperties(long number) {

        boolean isEven = isEvenNumber(number);
        boolean isOdd = isOddNumber(number);
        boolean isDuck = isDuckNumber(number);
        boolean isBuzz = isBuzzNumber(number);
        boolean isPalindromic = isPalindromicNumber(number);
        boolean isGapful = isGapfulNumber(number);

        System.out.println("\nProperties of " + number);
        System.out.println("\tbuzz: " + isBuzz);
        System.out.println("\tduck: " + isDuck);
        System.out.println("\tpalindromic: " + isPalindromic);
        System.out.println("\tgapful: " + isGapful);
        System.out.println("\teven: " + isEven);
        System.out.println("\todd: " + isOdd);
    }

    private static void printMultipleNumberProperties(long number, int times) {

        System.out.println();

        for (int i = 0; i < times; i++) {

            boolean isEven = isEvenNumber(number + i);
            boolean isOdd = isOddNumber(number + i);
            boolean isDuck = isDuckNumber(number + i);
            boolean isBuzz = isBuzzNumber(number + i);
            boolean isPalindromic = isPalindromicNumber(number + i);
            boolean isGapful = isGapfulNumber(number + i);

            System.out.print(number + i + " is");

            System.out.print(isBuzz ? " buzz," : "");
            System.out.print(isDuck ? " duck," : "");
            System.out.print(isGapful ? " gapful," : "");
            System.out.print(isPalindromic ? " palindromic," : "");
            System.out.print(isEven ? " even" : "");
            System.out.print(isOdd ? " odd" : "");

            System.out.println();

        }
    }

    private static void printInstructions() {
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
    }

    private static void printFirstWrongParameterWarning() {
        System.out.println("\nThe first parameter should be a natural number or zero.\n");
    }

    private static void printSecondWrongParameterWarning() {
        System.out.println("\nThe second parameter should be a natural number.\n");
    }

}


