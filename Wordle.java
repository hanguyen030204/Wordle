import java.util.Scanner;
import java.util.Random;
 

/**
 * Name: Ha Nguyen - JHED: hnguy116 
 * Project 2: Wordle
 *
 * This project has you create a text based version of Wordle
 * (https://www.nytimes.com/games/wordle/index.html). Wordle is a word guessing
 * game in which you have 6 tries to guess a 5-letter word. You are told whether
 * each letter of your guess is in the word and in the right position, in the
 * word but in the wrong position, or not in the word at all.
 *
 * Some key differences in our version are:
 *
 * - Text menu based with no grid. Players have to scroll up to see their
 * previous guesses.
 *
 * - Support for 4, 5, or 6 letter words
 *
 * - We don't check for whether a guess is a valid word or not. Players can
 * guess anything they want (of the correct length).
 *
 * Fun facts: The original Wordle was developed by Josh Wardle. Wardle's wife
 * chose the official word list for the game.
 *
 * 500.112 Gateway Computing: Java Spring 2022
 */
public class Wordle {

   /**
    * Defining the only Random variable you may (and must) use. DO NOT CHANGE
    * THIS LINE OF CODE.
    */
   static Random gen = new Random(0);

   /**
    * Defines the number of guesses the player starts with for each word. DO NOT
    * CHANGE THIS LINE OF CODE.
    */
   static final int MAX_GUESSES = 6;
   /**
    * Defines the number of hints the player starts with for each word. DO NOT
    * CHANGE THIS LINE OF CODE.
    */
   static final int MAX_HINTS = 2;
   
   static int usedHints; 
   
   static int usedGuesses; 
   
   static String wordToGuess = ""; 
   
   static String userGuess = ""; 

   /**
    * The main method. This is where most of your menu logic and game logic
    * (i.e. implementation of the rules of the game ) will end up. Feel free to
    * move logic in to smaller subroutines as you see fit.
    *
    * @param args commandline args
    */
   public static void main(String[] args) {
      Scanner kb = new Scanner(System.in);
      String text; //text is user's input to choose an option from menu
                  
      do {
      
         printMenu(); 
         
         System.out.print("Please enter a choice: ");
         text = kb.nextLine(); 
                  
         if ("n".equals(text) || "N".equals(text)) {       
            wordToGuess = newWord();          
         } 
         else if ("h".equals(text) || "H".equals(text)) {
            giveHint(wordToGuess);
            
            int remainingHints = MAX_HINTS - usedHints;
            if (remainingHints == 1) {
               System.out.println("You have 1 hint remaining."); 
            } 
            else {
               System.out.println("You have " + remainingHints + 
                  " hints remaining."); 
            } 
         
         } 
         else if ("g".equals(text) || "G".equals(text)) {
            System.out.println("Enter a guess: ");
            
            //validateGuess(); 
            userGuess = kb.nextLine(); 
                       
            //checkGuess(); 
            checkGuess(wordToGuess, userGuess); 
               
            int remainingGuesses = MAX_GUESSES - usedGuesses;
            if (remainingGuesses == 1) {
               System.out.println("You have 1 guess remaining.");
            } 
            else {
               System.out.println("You have " + remainingGuesses + 
                  " guesses remaining.");
            } 
                   
         } else {
            System.out.println("Invalid option! Try again!"); 
         } 
      } 
      while (!"e".equals(text) || "E".equals(text));    
               
      java.lang.System.exit(0); 
   }

   /**
    * Prints "HINT! The word contains the letter: X" where X is a randomly
    * chosen letter in the word parameter.
    *
    * @param word The word to give a hint for.
    */
   
   static void giveHint(String word) {
      if (usedHints == MAX_HINTS) {
         System.out.println("Sorry, you're out of hints!");
         return; 
      } else {
         int chosenIndex = gen.nextInt(word.length()); 
         usedHints += 1;  
                  
         System.out.println("HINT! The word contains the letter: " 
            + word.charAt(chosenIndex));
         
                  
      } 
   }
            
                    
   /**
    * Checks the players guess for validity. We define a valid guess as one that
    * is the correct length and contains only lower case letters and upper case
    * letters. If either validity condition fails, a message is printed 
    * indicating which condition(s) failed.
    *
    * @param length The length of the current word that the player is trynig to
    *               guess.
    * @param guess  The guess that the player has entered.
    * @return true if the guess is of the correct length and contains only valid
    * characters, otherwise false.
    */
    
   static boolean validateGuess(int length, String guess) {
      boolean checkLength = true;    
      boolean checkAlphabet = true; 
      
      if (guess.length() != length) {
         System.out.println("You must enter a guess of length " + length); 
         checkLength = false;
      } 
   
      for (int i = 0; i < guess.length(); ++i) {
         if (!Character.isAlphabetic(guess.charAt(i))) {
            System.out.println("Your guess must only contain upper case"
               + " letters and lowercase letters."); 
            checkAlphabet = false;  
         }            
      } 
      
      return !(!checkLength || !checkAlphabet);
      
      // if (!checkLength || !checkAlphabet) {
//          return false; 
//       } else {        
//          return true;
//       } 
    
   }

   /**
    * Checks the player's guess against the current word. Capitalization is
    * IGNORED for this comparison. This function also prints a string
    * corresponding to the player's guess. An X indicates a letter that isn't in
    * the word at all. A lower case letter indicates that the letter is in the
    * word but not in the correct position. An upper case letter indicates a
    * correct letter in the correct position. Example:
    *
    * SPLINE (the correct word)
    *
    * SPEARS (the player's guess)
    *
    * SPeXXs (the output printed by this function)
    *
    * Suggestion 1: Convert guess to upper case before doing anything else. This
    * can help simplify later logic.
    *
    * Suggestion 2: Consider using String.indexOf
    *
    * @param word  The current word the player is trying to guess.
    * @param guess The guess that a player has entered.
    * @return true if the word and guess match IGNORING CASE, otherwise false.
    */
   static boolean checkGuess(String word, String guess) { 
      if (validateGuess(word.length(), guess)) {
         usedGuesses += 1;
      } else {
         return false;
      }
      
      guess = guess.toUpperCase(); 
      word = word.toUpperCase(); 
      
      if (guess.equals(word) && usedGuesses <= 6) {
         System.out.println(guess); 
         System.out.println("Congrats! You won!");
        
      } else {
         if (usedGuesses == MAX_GUESSES) {
            System.out.println("Sorry, you're out of guesses! The word was " 
               + word + "." + " Use the \"n\"/\"N\" command to play again."); 
            return false; 
         } else {                    
            String newGuessed = ""; 
         
                       
            for (int i = 0; i < guess.length(); ++i) {
               guess = guess.toUpperCase(); 
               char guessedCharacter = guess.charAt(i); 
                              
               if (!word.contains(Character.toString(guessedCharacter))) {
                  guessedCharacter = '?';
                  newGuessed += guessedCharacter; 
               } 
               else {
                  if (guessedCharacter == word.charAt(i)) {
                     guessedCharacter = Character.toUpperCase(guessedCharacter);
                     newGuessed += guessedCharacter;  
                  } 
                  else {
                     guessedCharacter = Character.toLowerCase(guessedCharacter);
                     newGuessed += guessedCharacter;
                  } 
               }
            }
            System.out.println(newGuessed); 
            
         }
      }   
      return false; 
   } 
      
   /**
    * Chooses a random word using WordProvider.getWord(int length). This should
    * print "New word length: X" where x is the length of the word.
    *
    * @return the randomly chosen word
    */
   static String newWord() {
      resetNewGame();
      int givenWordLength = gen.nextInt(3) + 4; 
      String givenWord = WordProvider.getWord(givenWordLength);       
      System.out.println("New word length: " + givenWordLength); 
      return givenWord; 
   }

   /**
    * Prints menu options.
    */
   static void printMenu() {
      System.out.println("n/N: New word");
      System.out.println("h/H: Get a hint");
      System.out.println("g/G: Make a guess");
      System.out.println("e/E: Exit");
      System.out.println("-------------");
   }
   
   static void resetNewGame() {
      usedHints = 0; 
      usedGuesses = 0; 
      wordToGuess = "";
      userGuess = ""; 
   } 
}
