public class Klask {
    public static void main(String[] args) {
        // create board of 1200x800
        KlaskBoard board = new KlaskBoard();

        // main game loop
        while (true) {
            // player-board collisions run twice to avoid movement out of bounds between frames
            board.getArena().pause();
            board.runPlayerBoardCollisions();
            board.renderFrame();
            Input.handleInputs(board);
            board.getGameBall().runTrajectory();
            board.getMagnet(0).runTrajectory();
            board.getMagnet(1).runTrajectory();
            board.getMagnet(2).runTrajectory();
            board.runPlayerBallCollisions();
            board.runPlayerBoardCollisions();
            board.runBallBoardCollisions();
            board.runBallMagnetCollisions();
            board.runMagnetCollisions();
            board.runMagnetBoardCollisions();
            board.checkBall();
        }
    }
}