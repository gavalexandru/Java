package org.example;

public class GameCoordinator {
    private int currentPlayerIndex=0;
    private final int totalPlayers;
    public GameCoordinator(int totalPlayers) {
        this.totalPlayers = totalPlayers;
    }
    public synchronized void waitForTurn(int playerIndex) throws InterruptedException {
        while(playerIndex!=currentPlayerIndex){
            wait();
        }
    }
    public synchronized void endTurn() throws InterruptedException {
        currentPlayerIndex=(currentPlayerIndex+1)%totalPlayers;
        notifyAll();
    }
}
