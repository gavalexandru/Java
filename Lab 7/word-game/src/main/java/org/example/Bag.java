package org.example;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class Bag {
    private List<Tile>tiles = new ArrayList<>();
    private final Random random = ThreadLocalRandom.current();
    public Bag() {
        initializeTiles();
    }
    private void initializeTiles() {
        for(char c = 'a'; c <= 'z'; c++) {
            for(int i=0;i<=10;i++){
                tiles.add(new Tile(c,random.nextInt(10)+1));
            }
        }
        Collections.shuffle(tiles);
    }
    public synchronized List<Tile> drawTiles(int count) {
        List<Tile>drawn = new ArrayList<>();
        int actualCount = Math.min(count, tiles.size());
        for(int i=0;i<actualCount;i++) {
            drawn.add(tiles.remove(0));
        }
        return drawn;
    }
    public synchronized boolean isEmpty(){
        return tiles.isEmpty();
    }
}
