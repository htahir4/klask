/**
 * @author Hassan Tahir
 * @version 1.0
 *          Class to represent the magnets on the game board.
 */
public class Magnet {
    private Ball ball;

    public Magnet(double x, double y, int diameter, String col) {
        ball = new Ball(x, y, diameter, col);
    }

    /**
     * @return Ball return the gameBall
     */
    public Ball getBall() {
        return ball;
    }

    /**
     * @param magnet the gameBall to set
     */
    public void setGameBall(Ball magnet) {
        this.ball = magnet;
    }

    public void runTrajectory() {
        ball.move(ball.getXVelocity(), ball.getYVelocity());
        ball.setXVelocity(ball.getXVelocity() * 0.995);
        ball.setYVelocity(ball.getYVelocity() * 0.995);
    }
}

