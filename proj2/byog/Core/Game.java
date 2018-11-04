package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

import static byog.TileEngine.Tileset.FLOOR;
import static byog.TileEngine.Tileset.WALL;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {

    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(int Seed) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        ter.initialize(WIDTH,HEIGHT);
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        Random random = new Random(Seed);
        int NoR = random.nextInt(Seed % 10 + 1);
        //for (int i = 0; i < 10; i ++) {
            addRooms(finalWorldFrame,Seed, NoR,0,0);
        //}
        addNothing(finalWorldFrame);
        ter.renderFrame(finalWorldFrame);
        return finalWorldFrame;
    }

    private void addRooms(TETile[][] world, int Seed, int n, int prevleft, int prevbottom) {
        Random size = new Random(Seed);
        int width = 3 + size.nextInt(6);
        int height = 3 + size.nextInt(6);
        Random startPositions = new Random(Seed);
        int left = startPositions.nextInt(70);
        int bottom = startPositions.nextInt(20);
        for (int i = 0; i < width; i ++) {
            for (int j = 0;j < height; j ++) {
                if (world[i + left][j + bottom] != null) {
                    addRooms(world,Seed - 1,n, prevleft, prevbottom);
                    return;
                }
            }
        }

        for (int i = 0; i < width; i ++) {
            for (int j = 0; j < height; j ++) {
                if (i == 0 || j == 0 || i == width - 1 || j == height - 1) world[i + left][j + bottom] = WALL;
                else world[i + left][j + bottom] = FLOOR;
            }
        }

        if (prevleft != 0) {
            int l = Math.min(left + 1, prevleft);
            int r = Math.max(left + 1, prevleft);
            int b = Math.min(bottom + 1, prevbottom);
            int t = Math.max(bottom + 1, prevbottom);

            for (int i = l + 1; i <= r; i ++) {
                world[i][b] = FLOOR;
                if (world[i][b - 1] != WALL) {
                    world[i][b - 1] = WALL;
                    world[i][b + 1] = WALL;
                    if (i == r) {
                        world[i + 1][b - 1] = WALL;
                        world[i + 1][b] = WALL;
                        world[i + 1][b + 1] = WALL;
                    }
                }
            }

            for (int i = b + 1; i <= t - 1; i ++) {
                world[r][i] = FLOOR;
                if (world[r - 1][i] != WALL) {
                    world[r - 1][i] = WALL;
                    world[r + 1][i] = WALL;
                }
            }
        }

        if (n != 0) {
            addRooms(world, Seed - 10, n - 1, left + 1, bottom + 1);
        }
    }

    private void addNothing(TETile[][] world) {
        for (int i = 0; i < world.length; i ++) {
            for (int j = 0; j < world[0].length; j ++) {
                if (world[i][j] == null) world[i][j] = Tileset.NOTHING;
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playWithInputString(29007);
    }


}
