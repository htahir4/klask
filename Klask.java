public class Klask {
    public static void main(String[] args) {
        // create board of 1200x800
        KlaskBoard board = new KlaskBoard(1200, 800);

        // main game loop
        while (true) {
            // collisions run twice to avoid movement out of bounds between frames
            board.runPlayerBoardCollisions();
            board.renderFrame();
            Input.handleInputs(board);
            board.runPlayerBoardCollisions();
        }
    }
}