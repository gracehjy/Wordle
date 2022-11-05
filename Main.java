import java.io.*;
import java.util.Scanner;


import java.lang.StringBuffer;
import java.io.FileReader; 

class Main {    
  //GenFilerates a random word given an array of words
  public static String randomWord(String[] listOfWords){
    int randomIndex = (int)(Math.random()*listOfWords.length);
    return (listOfWords[randomIndex]);
  }

  public static void checkForCorrectChars(String userGuess, String chosenWord){
    String result = "";

    //Checks for words in correct spot
    for(int i = 0; i < chosenWord.length(); i++){
      if(userGuess.substring(i,i+1).equals(chosenWord.substring(i,i+1))){
        result = result + userGuess.substring(i,i+1).toUpperCase()+"";
      }
      else{
        result = result + userGuess.substring(i,i+1)+"";
      }
    }
    
    StringBuffer newResult = new StringBuffer(result);
    int counter = 0;
    
    for(int j = 0; j < result.length(); j++){
      if(result.substring(j,j+1).equals(userGuess.substring(j,j+1))){
        for(int k = 0; k < chosenWord.length(); k++){
          if(result.substring(j,j+1).equals(chosenWord.substring(k,k+1))){
            //inserts an asterick next to the letter
            newResult = newResult.insert(counter+1, "*");
            counter++;
            k = chosenWord.length();
          }
        }
      }
      counter++;
    }
    System.out.println(newResult);
  }

  //Checks to see if the guess is valid
  public static String checkForValidGuess(String userGuess){
    Scanner in = new Scanner(System.in);
    while(userGuess.length() != 5){
      System.out.println("Invalid Guess. Word must be 5 letters long. Try again: ");
      userGuess = in.nextLine();
    }
    return userGuess;
  }
  
  public static void runGame(String wordList[]){
    Scanner in = new Scanner(System.in);
    final int NUM_OF_ROUNDS = 5;
    int guessCount = 1;
    
    String[] listOfWords = wordList;

    String chosenWord = randomWord(listOfWords);

    String userGuess = in.nextLine();
    userGuess = checkForValidGuess(userGuess);
    
    while((guessCount < NUM_OF_ROUNDS) && !(userGuess.equals(chosenWord))){
      checkForCorrectChars(userGuess, chosenWord);
      userGuess = in.nextLine();
      userGuess = checkForValidGuess(userGuess);
      guessCount++;
    }

    if(userGuess.equals(chosenWord)){
      System.out.println("Congrats! The word was: "+chosenWord+". You got it in "+guessCount+" rounds.");
    }
    else{
      System.out.println("Sorry, you did not get the word. The word was: "+chosenWord+". You used up all "+guessCount+" rounds.");
    }

    System.out.println("Play again? (Y/N) ");
    String playAgain = in.nextLine();
    if(playAgain.equals("Y")){
      runGame(listOfWords);
    }
    else{
      System.out.println("Okay, thanks for playing!");
    }
  }
  
  public static void main(String[] args) throws Exception{
    System.out.println("Welcome to Wordle! Rules are simple, guess a five-letter word. If a letter is in the correct spot, it will be capitalized. If a letter is in the chosen word, but not the correct spot, an asterick will after it. Good luck, have fun, and let's start guessing: ");
    FileReader fr = new FileReader("words.txt");    
    BufferedReader br = new BufferedReader(fr);
    String listOfWords[] = new String[5757];   
    for(int i = 0; i < 5757; i++){
        listOfWords[i] = br.readLine();
    }    
         
    fr.close();     
    runGame(listOfWords);
  }
}