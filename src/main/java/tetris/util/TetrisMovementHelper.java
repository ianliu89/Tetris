package tetris.util;

import tetris.InactiveBlocks;
import tetris.Location;
import tetris.tetrimino.Tetrimino;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static tetris.GameConfig.BLOCK_SIZE;
import static tetris.util.TetrisCreator.randomlyCreateTetrimno;

public class TetrisMovementHelper {

    public static Set<Location> moveVerticalTetris(int movement, Tetrimino tetrimino) {
        Set<Location> newLocations = new HashSet<>();
        for(Location location: tetrimino.getLocations()) {
            newLocations.add(new Location(location.getxLocation(), location.getyLocation()));
        }

        return newLocations.stream().map(newLocation -> {
            newLocation.setyLocation(newLocation.getyLocation() + movement);
            return newLocation;
        }).collect(Collectors.toSet());
    }

    public static Set<Location> moveHorizonalTetris(int movement, Tetrimino tetrimino) {
        Set<Location> newLocations = new HashSet<>();
        for(Location location: tetrimino.getLocations()) {
            newLocations.add(new Location(location.getxLocation(), location.getyLocation()));
        }

        return newLocations.stream().map(newLocation -> {
            newLocation.setxLocation(newLocation.getxLocation() + movement);
            return newLocation;
        }).collect(Collectors.toSet());
    }
}
