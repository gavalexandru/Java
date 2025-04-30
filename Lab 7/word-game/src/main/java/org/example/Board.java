package org.example;
import java.util.*;
public class Board {
private final List<String> words = new ArrayList<>();
private List<Integer> scores = new ArrayList<>();
private List<String> playersNames = new ArrayList<>();
private final int playerNumber;
public Board(int playerNumber){
    for(int i=0;i<playerNumber;i++){
        scores.add(0);
        playersNames.add("");
    }
    this.playerNumber = playerNumber;
}
public synchronized void addWord(String word, Player player) {
    words.add(word);
    scores.set(player.getPlayerIndex(), player.getScore());
    if(playersNames.get(2).isEmpty()) playersNames.set(player.getPlayerIndex(),player.getPlayerName());
    printStatus();
 }
 public synchronized void setName(Player player){
     if(playersNames.get(2).isEmpty()) playersNames.set(player.getPlayerIndex(),player.getPlayerName());
 }
 public synchronized void printStatus(){
    System.out.println("Words submitted: " + words);
    System.out.println("Names: " + playersNames);
    System.out.println("Scores: " + scores);
 }
 public synchronized void winner(){
    int index=0;
    int max=-1;
    for(int i=0;i<playerNumber;i++){
        if(i==0){
            max = scores.get(i);
        }
        else if(max < scores.get(i)){
            max = scores.get(i);
            index = i;
        }
    }
    System.out.println("Winner: " + playersNames.get(index));
 }
 List<String> getWords(){
    return words;
 }
}
