public class GameBall {
    private Ball ball;

    public GameBall(int x, int y, int diameter, String col) {
        ball = new Ball(x, y, diameter, col);
    }

    /**
     * @return Ball return the gameBall
     */
    public Ball getBall() {
        return ball;
    }

    /**
     * @param gameBall the gameBall to set
     */
    public void setGameBall(Ball gameBall) {
        this.ball = gameBall;
    }

    public void runTrajectory() {
        ball.move(ball.getXVelocity(), ball.getYVelocity());
        ball.setXVelocity(ball.getXVelocity() * 0.995);
        ball.setYVelocity(ball.getYVelocity() * 0.995);
    }
}
