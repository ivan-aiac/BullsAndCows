package bullscows;

import java.util.Random;

public class BullsCows {

    private static final String[] SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyz".split("");
    private final String currentSymbols;
    private final String code;
    private boolean gameOver;
    private boolean winner;

    public BullsCows (int length, int limit) {
        code = generateCode(length, limit);
        currentSymbols = setCurrentSymbols(limit);
        gameOver = false;
        winner = false;
    }

    public String getCode() {
        return code;
    }

    public String getCurrentSymbols() {
        return currentSymbols;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isWinner() {
        return winner;
    }

    public String guess(String input) {
        String[] inputArr = input.split("");

        if (input.length() != code.length()) {
           gameOver = true;
           return "Error: The length of your guess doesn't match the length of the code.";
        }

        for (String in: inputArr) {
            if (!currentSymbols.contains(in)) {
                gameOver = true;
                return String.format("Error: %s isn't a valid guess.", input);
            }
        }

        int cows = 0;
        int bulls = 0;

        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == input.charAt(i)) {
                bulls++;
            } else if (code.contains(inputArr[i])){
                cows++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Grade: ");
        String bs = bulls > 1 ? " bulls" : " bull";
        String cs = cows > 1 ? " cows" : " cow";

        if (bulls == 0 && cows == 0) {
            sb.append("None.");
        } else if (bulls == 0) {
            sb.append(cows).append(cs);
        } else if (cows == 0) {
            sb.append(bulls).append(bs);
        } else {
            sb.append(bulls).append(bs).append(" and ")
                    .append(cows).append(cs);
        }

        gameOver = bulls == code.length();
        winner = gameOver;

        return sb.toString();
    }

    private String generateCode(int length, int limit) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        String nextSymbol;

        while (sb.length() != length) {
            nextSymbol = SYMBOLS[random.nextInt(limit)];
            if (!sb.toString().contains(nextSymbol)) {
                sb.append(nextSymbol);
            }
        }

        return sb.toString();
    }

    private String setCurrentSymbols(int limit) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < limit; i++) {
            sb.append(SYMBOLS[i]);
        }
        return sb.toString();
    }
}
