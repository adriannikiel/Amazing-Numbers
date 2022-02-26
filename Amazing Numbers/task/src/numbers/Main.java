package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to Amazing Numbers!\n");
        printInstructions();

        do {
            String[] inputs = initInputs();

            int nArgs = Integer.parseInt(inputs[0]);
            long number = Long.parseLong(inputs[1]);
            int times = Integer.parseInt(inputs[2]);
            String[] propertiesAsString = Arrays.copyOfRange(inputs, 3, inputs.length);

            List<NumberProperty> expectedProperties = new ArrayList<>();
            List<NumberProperty> excludedProperties = new ArrayList<>();

            for (String property : propertiesAsString) {
                if (property.startsWith("-")) {
                    property = property.substring(1);
                    excludedProperties.add(NumberProperty.valueOf(property));
                } else {
                    expectedProperties.add(NumberProperty.valueOf(property));
                }
            }

            if (number == 0) {
                break;
            }

            if (nArgs == 1) {
                if (number < 0) {
                    printFirstWrongParameterWarning();
                } else {
                    printSingleNumberProperties(number);
                }
            }

            if (nArgs == 2) {
                if (number < 0 && times < 1) {
                    printFirstWrongParameterWarning();
                    printSecondWrongParameterWarning();
                } else if (number < 0) {
                    printFirstWrongParameterWarning();
                } else if (times < 1) {
                    printSecondWrongParameterWarning();
                } else {
                    printMultipleNumberProperties(number, times);
                }
            }

            if (nArgs >= 3) {
                if (number < 0 && times < 1) {
                    printFirstWrongParameterWarning();
                    printSecondWrongParameterWarning();
                } else if (number < 0) {
                    printFirstWrongParameterWarning();
                } else if (times < 1) {
                    printSecondWrongParameterWarning();
                } else {
                    printSearchNumberProperties(number, times, expectedProperties, excludedProperties);
                }
            }
        } while (true);

        System.out.println("\nGoodbye!");
    }

    private static String[] initInputs() {

        String[] inputs;

        do {
            long number = -1;
            int times = -1;  // it can be any number greater than or equal to 1
            String properties[] = new String[0];

            System.out.print("\nEnter a request: ");
            String[] separatedNumbers = scanner.nextLine().split(" ");

            if (separatedNumbers.length == 0) {
                printInstructions();
                continue;
            }

            if (separatedNumbers.length >= 1) {

                try {
                    number = Long.parseLong(separatedNumbers[0]);
                } catch (NumberFormatException e) {
                    printFirstWrongParameterWarning();
                    continue;
                }
            }

            if (separatedNumbers.length >= 2) {

                try {
                    times = Integer.parseInt(separatedNumbers[1]);
                } catch (NumberFormatException e) {
                    printSecondWrongParameterWarning();
                    continue;
                }
            }

            if (separatedNumbers.length >= 3) {

                properties = Arrays.copyOfRange(separatedNumbers, 2, separatedNumbers.length);

                if (!validateProperties(properties)) {
                    continue;
                }
            }

            inputs = new String[3 + properties.length];

            inputs[0] = String.valueOf(separatedNumbers.length);
            inputs[1] = String.valueOf(number);
            inputs[2] = String.valueOf(times);

            for (int i = 3; i <= separatedNumbers.length; i++) {
                inputs[i] = properties[i - 3].toUpperCase();
            }

            break;

        } while (true);

        return inputs;
    }

    private static boolean validateProperties(String[] properties) {

        if (!checkWrongProperty(properties)) {
            return false;
        }

        if (!checkMutualExclusivity(properties)) {
            return false;
        }

        return true;
    }

    private static boolean checkWrongProperty(String[] propertiesAsString) {
        List<String> wrongProperties = new ArrayList<>();

        for (String property : propertiesAsString) {

            boolean foundProperty = false;

            for (NumberProperty value : NumberProperty.values()) {

                if (property.startsWith("-")) {
                    property = property.substring(1).toUpperCase();
                } else {
                    property = property.toUpperCase();
                }

                if (property.equals(value.name())) {
                    foundProperty = true;
                    break;
                }
            }

            if (!foundProperty) {
                wrongProperties.add(property);
            }
        }

        if (wrongProperties.size() > 0) {
            printWrongPropertyParameterWarning(wrongProperties);
            return false;
        }

        return true;
    }

    private static boolean checkMutualExclusivity(String[] propertiesAsString) {
        List<String> properties = new ArrayList<>();

        for (String p : propertiesAsString) {
            properties.add(p.toUpperCase());
        }

        if (!checkMutualExclusivityProperty(properties, "EVEN", "ODD")) {
            return false;
        }

        if (!checkMutualExclusivityProperty(properties, "-EVEN", "-ODD")) {
            return false;
        }

        if (!checkMutualExclusivityProperty(properties, "DUCK", "SPY")) {
            return false;
        }

        if (!checkMutualExclusivityProperty(properties, "-DUCK", "-SPY")) {
            return false;
        }

        if (!checkMutualExclusivityProperty(properties, "SUNNY", "SQUARE")) {
            return false;
        }

        if (!checkMutualExclusivityProperty(properties, "-SUNNY", "-SQUARE")) {
            return false;
        }

        for (NumberProperty value : NumberProperty.values()) {
            if (!checkMutualExclusivityProperty(properties, value.name(), "-" + value.name())) {
                return false;
            }
        }


        return true;
    }

    private static boolean checkMutualExclusivityProperty(List<String> properties, String prop1, String prop2) {
        if (properties.contains(prop1) && properties.contains(prop2)) {
            printMutuallyExclusivePropertyParameterWarning(prop1, prop2);
            return false;
        }

        return true;
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

        if (number >= 100) {
            String numberAsString = String.valueOf(number);
            String firstNumberAsString = numberAsString.substring(0, 1);
            String lastNumberAsString = numberAsString.substring(numberAsString.length() - 1);

            long connectedNumbers = Long.parseLong(firstNumberAsString + lastNumberAsString);

            return number % connectedNumbers == 0;

        } else {
            return false;
        }
    }

    private static boolean isSpyNumber(long number) {
        int sum = 0;
        int product = 1;

        long tempNumber = number;

        while (tempNumber > 0) {
            sum += tempNumber % 10;
            product *= tempNumber % 10;

            tempNumber /= 10;
        }

        return product == sum;
    }

    private static boolean isSunnyNumber(long number) {
        return isSquareNumber(number + 1);
    }

    private static boolean isSquareNumber(long number) {
        double square = Math.sqrt(number);
        return ((square - Math.floor(square)) == 0);
    }

    private static boolean isJumpingNumber(long number) {

        while (number > 9) {
            int previous = (int) (number % 10);
            number /= 10;
            int current = (int) (number % 10);

            if ((previous - 1 != current) && (previous + 1 != current)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isHappyNumber(long number) {

        long result = number;

        while (result != 1 && result != 4) {
            result = countHappyNumber(result);
        }

        if (result == 1) {
            return true;
        } else {
            return false;
        }

    }

    public static long countHappyNumber(long number) {
        long rem = 0;
        long sum = 0;

        while (number > 0) {
            rem = number % 10;
            sum = sum + (rem * rem);
            number = number / 10;
        }
        return sum;
    }


    private static boolean isSadNumber(long number) {
        return !isHappyNumber(number);
    }

    private static List<NumberProperty> checkNumbers(long number) {

        List<NumberProperty> properties = new ArrayList<>();

        if (isEvenNumber(number)) properties.add(NumberProperty.EVEN);
        if (isOddNumber(number)) properties.add(NumberProperty.ODD);
        if (isDuckNumber(number)) properties.add(NumberProperty.DUCK);
        if (isBuzzNumber(number)) properties.add(NumberProperty.BUZZ);
        if (isPalindromicNumber(number)) properties.add(NumberProperty.PALINDROMIC);
        if (isGapfulNumber(number)) properties.add(NumberProperty.GAPFUL);
        if (isSpyNumber(number)) properties.add(NumberProperty.SPY);
        if (isSunnyNumber(number)) properties.add(NumberProperty.SUNNY);
        if (isSquareNumber(number)) properties.add(NumberProperty.SQUARE);
        if (isJumpingNumber(number)) properties.add(NumberProperty.JUMPING);
        if (isHappyNumber(number)) properties.add(NumberProperty.HAPPY);
        if (isSadNumber(number)) properties.add(NumberProperty.SAD);

        return properties;
    }

    private static void printSingleNumberProperties(long number) {

        List<NumberProperty> properties = checkNumbers(number);

        System.out.println("\nProperties of " + number);
        System.out.println("\teven: " + properties.contains(NumberProperty.EVEN));
        System.out.println("\todd: " + properties.contains(NumberProperty.ODD));
        System.out.println("\tduck: " + properties.contains(NumberProperty.DUCK));
        System.out.println("\tbuzz: " + properties.contains(NumberProperty.BUZZ));
        System.out.println("\tpalindromic: " + properties.contains(NumberProperty.PALINDROMIC));
        System.out.println("\tgapful: " + properties.contains(NumberProperty.GAPFUL));
        System.out.println("\tspy: " + properties.contains(NumberProperty.SPY));
        System.out.println("\tsunny: " + properties.contains(NumberProperty.SUNNY));
        System.out.println("\tsquare: " + properties.contains(NumberProperty.SQUARE));
        System.out.println("\tjumping: " + properties.contains(NumberProperty.JUMPING));
        System.out.println("\thappy: " + properties.contains(NumberProperty.HAPPY));
        System.out.println("\tsad: " + properties.contains(NumberProperty.SAD));
    }

    private static void printMultipleNumberProperties(long number, int times) {

        for (int i = 0; i < times; i++) {

            List<NumberProperty> properties = checkNumbers(number + i);

            System.out.print(number + i + " is");

            System.out.print(properties.contains(NumberProperty.DUCK) ? " duck," : "");
            System.out.print(properties.contains(NumberProperty.BUZZ) ? " buzz," : "");
            System.out.print(properties.contains(NumberProperty.PALINDROMIC) ? " palindromic," : "");
            System.out.print(properties.contains(NumberProperty.GAPFUL) ? " gapful," : "");
            System.out.print(properties.contains(NumberProperty.SPY) ? " spy," : "");
            System.out.print(properties.contains(NumberProperty.SUNNY) ? " sunny," : "");
            System.out.print(properties.contains(NumberProperty.SQUARE) ? " square," : "");
            System.out.print(properties.contains(NumberProperty.JUMPING) ? " jumping," : "");
            System.out.print(properties.contains(NumberProperty.HAPPY) ? " happy," : "");
            System.out.print(properties.contains(NumberProperty.SAD) ? " sad," : "");
            System.out.print(properties.contains(NumberProperty.EVEN) ? " even" : "");
            System.out.print(properties.contains(NumberProperty.ODD) ? " odd" : "");

            System.out.println();
        }
    }

    private static void printSearchNumberProperties(long number, int times, List<NumberProperty> expectedProperties, List<NumberProperty> excludedProperties) {

        for (int i = 0; i < times; i++) {
            number = findNextNumberWithProperties(expectedProperties, excludedProperties, number);
            printMultipleNumberProperties(number, 1);
            number++;
        }

    }

    private static long findNextNumberWithProperties(List<NumberProperty> expectedProperties, List<NumberProperty> excludedProperties, long number) {

        while (true) {
            List<NumberProperty> properties = checkNumbers(number);

            if (properties.containsAll(expectedProperties) &&
                    (excludedProperties.size() == 0 || !properties.containsAll(excludedProperties))) {
                break;
            } else {
                number++;
            }

        }

        return number;
    }

    private static void printInstructions() {
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");
    }

    private static void printFirstWrongParameterWarning() {
        System.out.println("The first parameter should be a natural number or zero.");
    }

    private static void printSecondWrongParameterWarning() {
        System.out.println("The second parameter should be a natural number.");
    }

    private static void printWrongPropertyParameterWarning(List<String> properties) {
        if (properties.size() == 1) {
            System.out.println("The property " + properties + " is wrong.");
        } else {
            System.out.println("The properties " + properties + " are wrong.");
        }

        System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
    }

    private static void printMutuallyExclusivePropertyParameterWarning(String property1, String property2) {
        System.out.printf("The request contains mutually exclusive properties: [%s, %s]", property1, property2);
        System.out.println("\nThere are no numbers with these properties.\n");
    }

}