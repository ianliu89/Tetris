package tetris;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static tetris.GameConfig.*;

public class InactiveBlocks {

    private Set<Location> inactiveBlockSet = new HashSet<>();
    private List<Set<Integer>> inactiveBlockList = new ArrayList<>();

    public InactiveBlocks() {
        for(int i = 0; i<Y_SIZE; i++) {
            inactiveBlockList.add(i, null);
        }
    }

    public void addAllInactiveBlockSet(Set<Location> locations) {
        inactiveBlockSet.addAll(locations);
        addAllInactiveBlockList(locations);
    }

    public int calculateDeletedBlockRow() {
       int row = cleanBlockList();
       int score;

       switch (row) {
           case 1:
               score = 40;
               break;
           case 2:
               score = 100;
               break;
           case 3:
               score = 300;
               break;
           case 4:
               score = 1200;
               break;
           default:
               score = 0;
               break;
       }
       return score;
    }

    public void updateInactiveBlockSet() {
        inactiveBlockSet = updateBlockSet();
    }

    public Set<Location> getInactiveBlockSet() {
        return inactiveBlockSet;
    }

    private void addAllInactiveBlockList(Set<Location> locations) {
        for(Location location: locations) {
            int index = (Y_MAX_LOCATION - location.getyLocation())/Y_SIZE;
            if(inactiveBlockList.get(index) == null) {
                Set<Integer> blockSet = new HashSet<>();
                blockSet.add(location.getxLocation());
                inactiveBlockList.set(index, blockSet);
            }
            else {
                Set<Integer> blockSet = inactiveBlockList.get(index);
                blockSet.add(location.getxLocation());
            }
        }
    }

    private int cleanBlockList() {
        int counter = 0;
        for(int i = 0; i< Y_SIZE; i++) {
            if(inactiveBlockList.get(i) != null) {
                if(inactiveBlockList.get(i).size() == X_SIZE) {
                    inactiveBlockList.set(i, null);
                    counter++;
                }
            }
        }

        for(int i = 0; i< Y_SIZE; i++) {
            if(inactiveBlockList.get(i) == null) {
                int right = i + 1;
                while(right < Y_SIZE && inactiveBlockList.get(right) == null ) {
                    right++;
                }
                if(right < Y_SIZE) {
                    inactiveBlockList.set(i, inactiveBlockList.get(right));
                    inactiveBlockList.set(right, null);
                }
            }
        }
        return counter;
    }

    private Set<Location> updateBlockSet() {
        Set<Location> newInactiveBlocks = new HashSet<>();
        for(int i=0; i<Y_SIZE; i++) {
            if(inactiveBlockList.get(i) != null) {
                for(Integer xLocation : inactiveBlockList.get(i)) {
                    Location location = new Location(xLocation, Y_MAX_LOCATION - BLOCK_SIZE*i);
                    newInactiveBlocks.add(location);
                }
            }
        }
        return newInactiveBlocks;
    }
}
