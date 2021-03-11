package bullscows;
import java.security.SecureRandom;
import java.util.*;


public class Main {
    static final String AB = "0123456789abcdefghijklmnopqrstuvwxyz";
    static final String password = "**********";
    static SecureRandom rnd = new SecureRandom();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please, enter the secret code's length:");
            String secretLengthInp = scanner.nextLine();
            if (!secretLengthInp.matches("\\d+")) {
                System.out.println("Error: " + secretLengthInp + " isn't a valid number.");
                break;
            }
            int length = Integer.parseInt(secretLengthInp);
            System.out.println("Input the number of possible symbols in the code:");
            String secretSymbols = scanner.nextLine();
            if (!secretSymbols.matches("\\d+")) {
                System.out.println("Error: " + secretSymbols + " isn't a valid number.");
                break;
            }
                int symbols = Integer.parseInt(secretSymbols);
            if (length > 10) {
                System.out.println("Error");
                return;
            } else if (length > symbols || length <= 0) {
                System.out.println("Error: it's not possible to generate a code with a length of " + length + "with " + symbols + " unique symbols.");
                break;
            } else if (symbols > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                break;
            }
            String result = randomGenerator(length, symbols);
            cowBull(result);
            return;

        }
    }

    private static String randomGenerator(int length, int symbols) {
        String number = AB.substring(0, symbols);
        String lengthCode = password.substring(0, length);
        if (symbols == 1) {
            System.out.println("The secret is prepared: " + lengthCode + " (0).");
        } else if (symbols <= 10) {
            System.out.println("The secret is prepared: " + lengthCode + " (0-" + number.charAt(symbols-1) + ").");
        } else if (symbols == 11) {
            System.out.println("The secret is prepared: " + lengthCode + " (0-9, a).");
        } else {
            System.out.println("The secret is prepared: " + lengthCode + " (0-9, (a-" + number.charAt(symbols-1) + ").");
        }
        System.out.println("let's start a game!");
        char[] array = number.toCharArray();
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            arrayList.add(i, String.valueOf(array[i]));
        }
        Collections.shuffle(arrayList);
        StringBuilder result = new StringBuilder();
        for (var ch : arrayList.subList(0, length)) {
            result.append(ch);
        }
        return result.toString();
    }







    private static void cowBull(String result) {
        Scanner scanner = new Scanner(System.in);
        int cows = 0;
        int bull = 0;
        int turn = 0;
        while (true) {
            String guess = scanner.nextLine();

            for (int j = 0; j < guess.length(); j++) {


                if (guess.charAt(j) == result.charAt(j)) {
                    bull++;
                }
                for (int i = 0; i < result.length(); i++) {
                    if (result.charAt(j) == guess.charAt(i) && j != i) {
                        cows++;
                    }


                }
            }
            ++turn;
            System.out.println("Turn " + turn);
            System.out.println(guess);
            if (guess.equals(result)) {
                System.out.println("Grade: " + bull + " bulls\nCongratulations! You guessed the secret code.");
                return;
            } else if (bull > 0 && cows > 0) {
                System.out.println("Grade: " + bull + " bull and " + cows + " cow");


            } else if (bull > 0) {
                System.out.println("Grade: " + bull + " bull(s).");


            } else if (cows > 0) {
                System.out.println("Grade: " + cows + " cow(s).");


            } else {
                System.out.println("Grade: None.");

            }
            bull = 0;
            cows = 0;
        }

    }
}










