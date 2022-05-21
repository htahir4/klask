public class Klask {
    public static void main(String[] args) {
        // create board of 1200x800
        KlaskBoard board = new KlaskBoard(1200, 800);
        // create two players
        Player player1 = new Player(200, 400, board);
        Player player2 = new Player(1000, 400, board);
        board.run();
    }
}