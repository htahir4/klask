/**
 * @author Hassan Tahir
 * @version 1.0
 *          Class to represent players in the game.
 */
public class Player {
    private Ball playerBall;
    private Rectangle playerRectangle;

    /**
     * Creates a player at the specified position on the specified board.
     * 
     * @param x     x position of the player
     * @param y     y position of the player
     * @param board board on which to place the ball
     */
    public Player(int x, int y, KlaskBoard board) {
        // create ball
        playerBall = new Ball(x, y, 40, "MAGENTA");
        // create rectangle above ball for "paddle" visual
        playerRectangle = new Rectangle(x - 8, y - 35, 16, 35, "MAGENTA");

        // add to board
        board.addBall(playerBall);
        board.addRectangle(playerRectangle);
    }

    /**
     * Gets the Ball that defines position/velocity of this player.
     * 
     * @return the Ball.
     */
    public Ball getPlayerBall() {
        return playerBall;
    }

    /**
     * Changes the position of the Player.
     * 
     * @param x X to change
     * @param y Y to change
     */
    public void changePosition(double x, double y) {
        playerBall.move(x, y);
        playerRectangle.move(x, y);
    }
}
