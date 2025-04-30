package org.example;
import java.util.*;
public class Player extends Thread {
    private final String name;
    private final Bag bag;
    private final Board board;
    private List<Tile> tiles;
    private final Scanner scanner;
    private final GameCoordinator coordinator;
    private final int playerIndex;
    private int score = 0;
    private final Dictionary dictionary = new Dictionary("C:\\Users\\Mesul\\IdeaProjects\\word-game\\src\\main\\java\\org\\example\\Words.txt");

    public Player(String name, Bag bag, Board board, GameCoordinator coordinator, int playerIndex, Scanner scanner) {
        this.name = name;
        this.bag = bag;
        this.board = board;
        this.tiles = bag.drawTiles(7);
        this.scanner = scanner;
        this.coordinator = coordinator;
        this.playerIndex = playerIndex;
    }

    @Override
    public void run() {
        try {
            while (!bag.isEmpty() && !Thread.currentThread().isInterrupted()) {
                coordinator.waitForTurn(playerIndex);
                playTurn();
                coordinator.endTurn();
            }
                board.winner();
        }
        catch (InterruptedException e) {
            System.out.println("Player " + name + " was interrupted");
        }
    }

    private void playTurn() {
        System.out.println("===== " + name + " turn =====");
        System.out.print("Your tiles: ");
        for (Tile tile : tiles) {
            System.out.print(tile.getLetter() + " ");
        }
        System.out.println();
        System.out.print("Enter a word or press Enter to skip: ");
        System.out.println();
        String word = scanner.nextLine().trim().toLowerCase();
        if (word.isEmpty()) {
            tiles = bag.drawTiles(7);
            System.out.println(name + " skipped turn.");
            System.out.println();
            synchronized(board){
                board.setName(this);
            }
        }
        else if (isValidWord(word)) {
            int number=0;
            for(Character letter : word.toLowerCase().toCharArray()) {
                Iterator<Tile> iterator = tiles.iterator();
                while (iterator.hasNext()) {
                    Tile tile = iterator.next();
                    if (tile.getLetter() == letter){
                        score+=tile.getPoints();
                        number++;
                        iterator.remove();
                        break;
                    }
                }
            }
            List<Tile> aux = bag.drawTiles(number);
            tiles.addAll(aux);
            synchronized(board){
                board.addWord(word,this);
            }
        }
        else {
            System.out.println("Invalid word");
            playTurn();
        }
    }
    private boolean isValidWord(String word) {
        List<String> submittedWords = board.getWords();
        for (String submittedWord : submittedWords) {
            if(submittedWord.equals(word)) return false;
        }

        Map<Character, Integer> letters = new HashMap<>();
        for(Tile tile : tiles) {
            if(letters.containsKey(tile.getLetter())) letters.put(tile.getLetter(), letters.get(tile.getLetter())+1);
            else letters.put(tile.getLetter(), 1);
        }
        for(Character letter : word.toLowerCase().toCharArray()) {
            if(letters.containsKey(letter)){
                if(letters.get(letter)>=1) letters.put(letter, letters.get(letter)-1);
                else return false;
            }
            else return false;
        }

        if(!dictionary.isValidWord(word)) return false;

        return true;
    }
    public int getScore() {
        return score;
    }
    public int getPlayerIndex() {
        return playerIndex;
    }
    public String getPlayerName(){
        return name;
    }
}
