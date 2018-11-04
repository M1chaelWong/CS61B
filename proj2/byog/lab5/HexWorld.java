package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public static void addHexagon(int size) {
        TERenderer ter = new TERenderer();
        ter.initialize(30,30,2 * size,2 * size);
        TETile[][] world = new TETile[size + 2 * (size - 1)][2 * size];
        for (int i = 0; i < size; i ++) {
//            for (int j = 0; j < size - 1 - i; j ++) {
//                world[j][i] = Tileset.NOTHING;
//            }
            for (int j = size - 1 - i; j <= size - 1 - i + (size - 1 + 2 * i); j ++) {
                world[j][i] = Tileset.FLOWER;
            }
//            for (int j = size - 1 - i + size  + 2 * i; j < size + 2 * (size - 1); j ++) {
//                world[j][i] = Tileset.NOTHING;
//            }
            for (int j = 0; j < size + 2 * (size - 1); j ++ ) {
                    if (world[j][i] == null) {
                        world[j][i] = Tileset.NOTHING;
                    }
                }
            }
        /* duplicate */
        for (int i = size; i < 2 * size; i ++) {
            for (int j = 0; j < size + 2 * (size - 1); j ++) {
                world[j][i] = world[j][2 * size - 1 - i];
            }
        }
        ter.renderFrame(world);
    }

    public static void main(String[] args) {
        addHexagon(5);
        addHexagon(6);
    }
}
