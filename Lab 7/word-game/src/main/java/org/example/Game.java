package org.example;
import java.util.*;
public class Game {
    public static void main(String[] args) {
        int timeLimit = 1;
        Bag bag = new Bag();
        Board board = new Board(3);
        Scanner sharedScanner = new Scanner(System.in);
        GameCoordinator coordinator = new GameCoordinator(3);
        Player[] players = {
         new Player("Alex", bag, board,coordinator,0,sharedScanner),
                new Player("Alice", bag, board,coordinator,1,sharedScanner),
                new Player("Dan", bag, board,coordinator,2,sharedScanner),
        };

        Timekeeper timekeeper = new Timekeeper(timeLimit, players);
        timekeeper.setDaemon(true);
        timekeeper.start();

        for(Player player : players) {
            player.start();
        }
        for(Player player : players) {
            try{
                player.join();
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        board.printStatus();
        System.out.println("Game over");
    }
}
