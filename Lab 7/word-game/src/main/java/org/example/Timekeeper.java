package org.example;
import java.time.Duration;
import java.time.Instant;

public class Timekeeper extends Thread {
    private final long timeLimitMillis;
    private final Player[] players;
    private final Instant startTime;
    public Timekeeper(int timeLimitMinutes, Player[] players) {
        this.timeLimitMillis = timeLimitMinutes * 60 * 1000L;
        this.players = players;
        this.startTime = Instant.now();
    }
    @Override
    public void run(){
        while(!allPlayersFinished()){
            try {
                Duration elapsed=Duration.between(startTime,Instant.now());
                System.out.printf("[TimeKeeper] Elapsed time: %02d:%02d%n", elapsed.toMinutes(), elapsed.toSecondsPart());
                if (elapsed.toMillis() >= timeLimitMillis) {
                    System.out.println("[TimeKeeper] Time limit exceeded. Stopping game.");
                    interruptPlayers();
                    break;
                }
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                interruptPlayers();
            }
        }
    }
    private boolean allPlayersFinished(){
        for(Player player : players){
            if(player.isAlive()) return false;
        }
        return true;
    }
    private void interruptPlayers(){
        for(Player player : players){
            player.interrupt();
        }
    }
}
