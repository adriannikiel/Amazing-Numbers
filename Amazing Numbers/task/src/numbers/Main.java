package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        write your code here
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a natural number:");
        int number = scanner.nextInt();

        boolean isEven = false;
        boolean isOdd = false;
        boolean isBuzz = false;
        boolean isDuck = false;

        if (number >= 1) {
            if (number % 2 == 0) {
                isEven = true;
            } else {
                isOdd = true;
            }

            if (String.valueOf(number).contains("0")) {
                isDuck = true;
            }

            if (number % 7 == 0 || number % 10 == 7) {
                isBuzz = true;
            }

            System.out.println("Properties of " + number);
            System.out.println("\teven: " + isEven);
            System.out.println("\todd: " + isOdd);
            System.out.println("\tbuzz: " + isBuzz);
            System.out.println("\tduck: " + isDuck);

        } else {
            System.out.println("This number is not natural!");
        }
    }
}
