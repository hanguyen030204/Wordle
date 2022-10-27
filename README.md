# Wordle
This is a crossword game based on the rules of Wordle by The New York Times that gives users four times to guess a selected word of a random length.

Rules for this game are: 
1. At the beginning of a game, a new word is randomly selected. The word’s length is randomly chosen to be either 4, 5, or 6 letters.

2. The player has 6 chances to guess the selected word. We do not require that the guess is a valid word in our version.

3. After each guess, the game indicates which guessed letters are in the word and in the correct position, which guessed letters are in the word but not in the correct position, and which guessed letters aren’t in the word at all.
  a. A guessed letter that is not found anywhere in the word is indicated with a question mark: ?
  b. A guessed letter that is in the word but not in the correct position is indicated with the guessed letter in lower-case.
  c. A guessed letter that is in the word and is in the correct position is indicated with the guessed letter in upper-case.
  
4. A player can request a hint which will reveal one randomly selected letter in the word. A player gets no more than 2 hints per game.

5. Win condition: the player wins by correctly guessing the word in 6 or less guesses. Otherwise they lose.

