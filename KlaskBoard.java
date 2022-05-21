/**
 * @author Hassan Tahir
 * @version 1.0
 *          Class to represent the board on which the game is played.
 */
public class KlaskBoard {
    private int width; // board width
    private int height; // board height
    private GameArena arena; // underlying GameArena to draw to
    private Ball[] corners; // corners
    private Ball[] goals; // goals

    /**
     * Creates a board of the specified width and height.
     * 
     * @param w Width of the board.
     * @param h Height of the board.
     */
    public KlaskBoard(int w, int h) {
        this.width = w;
        this.height = h;
        this.arena = new GameArena(w, h, true);
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
        
        arena.run();
    }

    /**
     * Creates the corners for the board
     */
    private void createCorners() {
        this.corners = new Ball[]{
            new Ball(120, 170, 90, "PINK"), // top left
            new Ball(this.width - 120, 170, 90, "PINK"), // top right
            new Ball(120, this.height - 170, 90, "PINK"), // bottom left
            new Ball(this.width - 120, this.height - 170, 90, "PINK") // bottom right
        };
    }

    /**
     * Creates the goals for the board
     */
    private void createGoals() {
        this.goals = new Ball[]{
            new Ball(200, this.height / 2, 80, "PINK"),
            new Ball(this.width - 200, this.height / 2, 80, "PINK")
        };
    }
}
