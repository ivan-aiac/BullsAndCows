package bullscows;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        String regex = "[0-9]+";
        Scanner s = new Scanner(System.in);

        System.out.println("Input the length of the secret code:");
        String strLength = s.nextLine();
        if (!strLength.matches(regex)) {
            System.out.println("Error: code length must be a number.");
            return;
        }

        System.out.println("Input the number of possible symbols in the code:");
        String strCharLimit = s.nextLine();
        if (!strCharLimit.matches(regex)) {
            System.out.println("Error: possible symbols must be a number.");
            return;
        }

        int length = Integer.parseInt(strLength);
        int charLimit = Integer.parseInt(strCharLimit);

        boolean errors = validate(length, charLimit);

        if(!errors) {
            BullsCows bullsCows = new BullsCows(length, charLimit);
            String input;
            int turns = 1;

            System.out.printf("The secret is prepared: %s", bullsCows.getCode().replaceAll(".","*"));
            if (charLimit < 11) {
                System.out.printf(" (0-%s).\n", bullsCows.getCurrentSymbols().charAt(charLimit - 1));
            } else {
                System.out.printf(" (0-9, a-%s).\n", bullsCows.getCurrentSymbols().charAt(charLimit - 1));
            }
            System.out.println("Okay, let's start a game!");

            while (!bullsCows.isGameOver()) {
                System.out.printf("Turn %d:\n", turns);
                input = s.next();
                System.out.println(bullsCows.guess(input));
                turns++;
            }
            if (bullsCows.isWinner()) {
                System.out.println("Congratulations! You guessed the secret code.");
            }
        }
    }

    private static boolean validate(int length, int charLimit) {
        boolean errors = true;

        if (length < 1) {
            System.out.println("Error: the length of the code cannot be less than 1.");
        } else if (charLimit < 1) {
            System.out.println("Error: cannot generate a code with less than 1 symbols.");
        } else  if (charLimit < length) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.\n", length, charLimit);
        } else if (charLimit > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
        } else {
            errors = false;
        }

        return errors;
    }

}
