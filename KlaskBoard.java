/**
 * @author Hassan Tahir
 * @version 1.0
 *          Class to represent the board on which the game is played.
 */
public class KlaskBoard {
    private int width; // board width
    private int height; // board height
    private GameArena arena; // underlying GameArena to draw to
    private Ball[] corners;
    private Ball[] goals;
    private Player[] players;

    /**
     * Creates a board of the specified width and height.
     * 
     * @param w Width of the board.
     * @param h Height of the board.
     */
    public KlaskBoard(int w, int h) {
        width = w;
        height = h;
        arena = new GameArena(w, h, true);
        // board background
        arena.addRectangle(new Rectangle(100, 150, w - 200, h - 300, "PINK"));
        arena.addRectangle(new Rectangle(120, 170, w - 240, h - 340, "BLUE"));

        // corners
        createCorners();
        for (Ball b : corners) {
            arena.addBall(b);
            // create "inner" ball for visual purposes
            arena.addBall(new Ball(b.getXPosition(), b.getYPosition(), b.getSize() - 15, "BLUE"));
        }

        // goals
        createGoals();
        for (Ball b : goals) {
            arena.addBall(b);
        }

        // centreline
        arena.addLine(new Line(w / 2, 170, w / 2, h - 170, 2, "PINK"));

        // players
        createPlayers();
    }

    /**
     * Gets the internal GameArena
     * @return GameArena
     */
    public GameArena getArena() {
        return arena;
    }

    /**
     * Runs the board for 1 frame
     */
    public void renderFrame() {
        arena.pause();
    }

    /**
     * Creates the corners for the board
     */
    private void createCorners() {
        corners = new Ball[]{
            new Ball(120, 170, 90, "PINK"), // top left
            new Ball(width - 120, 170, 90, "PINK"), // top right
            new Ball(120, height - 170, 90, "PINK"), // bottom left
            new Ball(width - 120, height - 170, 90, "PINK") // bottom right
        };
    }

    /**
     * Creates the goals for the board
     */
    private void createGoals() {
        goals = new Ball[]{
            new Ball(200, height / 2, 80, "PINK"),
            new Ball(width - 200, height / 2, 80, "PINK")
        };
    }

    /**
     * Creates players and adds them to the board.
     */
    public void createPlayers() {
        players = new Player[] {
            new Player(200, height / 2, this),
            new Player(width - 200, height / 2, this)
        };
    }

    /**
     * Adds specified Ball to the board.
     * @param b Ball to add.
     */
    public void addBall(Ball b) {
        arena.addBall(b);
    }

    /**
     * Adds specified Rectangle to the board
     * @param r Rectangle to add.
     */
    public void addRectangle(Rectangle r) {
        arena.addRectangle(r);
    }

    /**
     * Gets a Player
     * @param n Number of player to get (1 or 2)
     */
    public Player getPlayer(int n) {
        return players[n - 1];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void runPlayerBoardCollisions() {
        // iterate through players 1 & 2
        for (int i = 1; i <= 2; i++) {
            Player player = players[i - 1];
            // arena.addRectangle(new Rectangle(120, 170, w - 240, h - 340, "BLUE"));
            int leftBound = i == 1 ? 120 : width / 2;
            int rightBound = i == 1 ? width / 2: width - 120;
            int topBound = 170;
            int bottomBound = height - 170;
            double xPosition = player.getPlayerBall().getXPosition();
            double yPosition = player.getPlayerBall().getYPosition();
            if (xPosition < leftBound) {
                player.setPosition(leftBound, yPosition);
            }
            if (xPosition > rightBound) {
                player.setPosition(rightBound, yPosition);
            }
            if (yPosition < topBound) {
                player.setPosition(xPosition, topBound);
            }
            if (yPosition > bottomBound) {
                player.setPosition(xPosition, bottomBound);
            }
        }
    }
}
