/**
 * @author Hassan Tahir
 * @version 1.0
 *          Class to represent players in the game.
 */
public class Player {
    private Ball playerBall;

    /**
     * Creates a player at the specified position on the specified board.
     * @param x x position of the player
     * @param y y position of the player
     * @param board board on which to place the ball
     */
    public Player(int x, int y, KlaskBoard board) {
        // create ball
        playerBall = new Ball(x, y, 40, "MAGENTA");
        // add ball to board
        board.addBall(playerBall);
        // create rectangle above ball for "paddle" visual
        board.addRectangle(new Rectangle(x - 8, y - 35, 16, 35, "MAGENTA"));
    }

    /**
     * Gets the Ball that defines position/velocity of this player.
     * @return the Ball.
     */
    public Ball getPlayerBall() {
        return playerBall;
    }
}
