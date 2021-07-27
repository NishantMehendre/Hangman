import java.io.*;
import java.util.*;

public class Hangman {
    public void createGame() {
        try {
            ArrayList<String> entries = new ArrayList<String>();
            entries.add("");
            Scanner sc = new Scanner(System.in);
            boolean gameOver = false;
            FileReader fr = new FileReader("Words.txt");
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> words = new ArrayList<String>();
            String line = "";
            while ((line = br.readLine()) != null) {
                words.add(line);
            }
            int count = 0;
            String input = "";
            String word = words.get((int) (Math.random() * words.size()));
            String ans[] = new String[word.length()];
            for (int i = 0; i < word.length(); i++) {
                ans[i] = "-";
            }
            System.out.println("  ____");
            System.out.println(" |    |");
            System.out.println("      |");
            System.out.println("      |");
            System.out.println("      |");
            System.out.println("   ___|___");
            // Continues until word is guessed or game is over
            while (gameOver == false) {
                int count1 = 0;
                boolean validInput = false;
                // Continues to receive input from player until a valid input is received
                while (validInput == false) {
                    System.out.println("Guess a letter");
                    input = sc.nextLine();
                    //Checks if a valid input has been entered by the user
                    if (input.length() != 1) {
                        System.out.println("Enter valid input");
                    } 
                    //Checks if the valid input entered by player is repeated
                    else {
                        boolean repeat = false;
                        for (int i = 0; i < entries.size(); i++) {
                            if (input.equals(entries.get(i)))
                                repeat = true;
                        }
                        if (repeat == false) {
                            validInput = true;
                            entries.add(input);
                        } else
                            System.out.println("This letter has already been entered.");
                    }
                }
                //Checks if the guess made by player is a correct one
                for (int i = 0; i < word.length(); i++) {
                    if (input.equals(word.substring(i, i + 1))) {
                        ans[i] = word.substring(i, i + 1);
                    } else
                        count1++;
                }
                if (count1 == word.length())
                    count = count + word.length();
                //Prints the intial game
                if (count == 0) {
                    System.out.println("  ____");
                    System.out.println(" |    |");
                    System.out.println("      |");
                    System.out.println("      |");
                    System.out.println("      |");
                    System.out.println("   ___|___");
                }
                //Checks if the player has made an incorrect guess and prints the head 
                if (count == word.length()) {
                    System.out.println("  ____");
                    System.out.println(" |    |");
                    System.out.println(" o    |");
                    System.out.println("      |");
                    System.out.println("      |");
                    System.out.println("   ___|___");
                }
                //Checks if player has made a second incorrect guess and prints the body
                if (count == (2 * word.length())) {
                    System.out.println("  ____");
                    System.out.println(" |    |");
                    System.out.println(" o    |");
                    System.out.println(" |    |");
                    System.out.println("      |");
                    System.out.println("   ___|___");
                }
                //Checks if the player has made a third incorrect guess and prints the first hand
                if (count == (3 * word.length())) {
                    System.out.println("  ____");
                    System.out.println(" |    |");
                    System.out.println(" o    |");
                    System.out.println("/|    |");
                    System.out.println("      |");
                    System.out.println("   ___|___");
                }
                //Checks if the player has made a fourth incorrect guess and prints the second hand
                if (count == (4 * word.length())) {
                    System.out.println("  ____");
                    System.out.println(" |    |");
                    System.out.println(" o    |");
                    System.out.println("/|\\   |");
                    System.out.println("      |");
                    System.out.println("   ___|___");
                }
                //Checks if the player has made a fifth incorrect guess and prints the first leg
                if (count == (5 * word.length())) {
                    System.out.println("  ____");
                    System.out.println(" |    |");
                    System.out.println(" o    |");
                    System.out.println("/|\\   |");
                    System.out.println("/     |");
                    System.out.println("   ___|___");
                }
                //Checks if the player has made a sixth and final incorrect guess and prints the second leg
                //Ends the game
                if (count == (6 * word.length())) {
                    System.out.println("  ____");
                    System.out.println(" |    |");
                    System.out.println(" o    |");
                    System.out.println("/|\\   |");
                    System.out.println("/ \\   |");
                    System.out.println("   ___|___");
                    System.out.println("The word was \"" + word + "\". Better luck next time.");
                    gameOver = true;
                }
                String check = "";
                for (int i = 0; i < ans.length; i++) {
                    check = check + ans[i];
                }
                if (check.equals(word)) {
                    System.out.println("Congratulations! You have guessed the word \"" + word + "\".");
                    gameOver = true;
                }
                //Prints the correct and incorrect guesses separately as long as the game continues
                if (gameOver == false) {
                    for (int i = 0; i < ans.length; i++) {
                        System.out.print(ans[i]);
                    }
                    System.out.println();
                    System.out.print("Incorrect guesses:");
                    //Goes through all of the entries the player made and checks for incorrect guesses
                    //Prints the incorrect guesses
                    for (int i = 0; i < entries.size(); i++) {
                        int count2 = 0;
                        for (int j = 0; j < word.length(); j++) {
                            if ((entries.get(i).equals(word.substring(j, j + 1))) == false)
                                count2++;
                        }
                        if (count2 == word.length())
                            System.out.print(entries.get(i) + " ");
                    }
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class Driver {
    public static void main(String[] args) {
        Hangman ob = new Hangman();
        ob.createGame();
    }
}