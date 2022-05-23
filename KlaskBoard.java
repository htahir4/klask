import java.awt.Color;
import java.text.FieldPosition;

/**
 * @author Hassan Tahir
 * @version 1.0
 *          Class to represent the board on which the game is played.
 */
public class KlaskBoard {
    private GameArena arena = new GameArena(1200, 800, true); // underlying GameArena to draw to
    private int width = arena.getArenaWidth(); // board width
    private int height = arena.getArenaHeight(); // board height
    private Ball[] corners;
    private Ball[] goals;
    private Player[] players;
    private GameBall gameBall; // ie ball interacted with by players
    private Magnet[] magnets = new Magnet[]{
        new Magnet(width/2, height/2+100, 10, "WHITE"),
        new Magnet(width/2, height/2, 10, "WHITE"),
        new Magnet(width/2, height/2-100, 10, "WHITE")};
    private int scorep1 = 0;
    private int scorep2 = 0;
    private Text score1 = new Text(String.valueOf(scorep1), 25, 50, height/2, "WHITE", 100);
    private Text score2 = new Text(String.valueOf(scorep2), 25, width-75, height/2, "WHITE", 100);

    /**
     * Creates a board of the specified width and height.
     * 
     * @param w Width of the board.
     * @param h Height of the board.
     */
    public KlaskBoard() {
        // board background
        arena.addRectangle(new Rectangle(100, 150, width - 200, height - 300, "PINK"));
        arena.addRectangle(new Rectangle(120, 170, width - 240, height - 340, "BLUE"));
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
        arena.addLine(new Line(width / 2, 170, width / 2, height - 170, 2, "PINK"));
        // players
        createPlayers();
        // ball
        createGameBall();
        // magnet
        createMagnets();
        arena.addText(score1);
        arena.addText(score2);
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
     * Creates game ball
     */
    public void createGameBall() {
        gameBall = new GameBall(corners[0].getXPosition(), corners[0].getYPosition(), 15, "ORANGE");
        addBall(gameBall.getBall());
    }

    public void createMagnets() {
        for (int i = 0; i < 3; i++){
            addBall(magnets[i].getBall());
        }
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

    public GameBall getGameBall() {
        return gameBall;
    }

    public Magnet getMagnet(int i){
        return magnets[i];
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
                player.getPlayerBall().setXVelocity(0);
            }
            if (xPosition > rightBound) {
                player.setPosition(rightBound, yPosition);
                player.getPlayerBall().setXVelocity(0);
            }
            if (yPosition < topBound) {
                player.setPosition(xPosition, topBound);
                player.getPlayerBall().setYVelocity(0);
            }
            if (yPosition > bottomBound) {
                player.setPosition(xPosition, bottomBound);
                player.getPlayerBall().setYVelocity(0);
            }
        }
    }

    public void runBallBoardCollisions() {
        Ball ball = gameBall.getBall();
        int leftBound = 120;
        int rightBound = width - 120;
        int topBound = 170;
        int bottomBound = height - 170;
        double xPosition = ball.getXPosition();
        double yPosition = ball.getYPosition();
        double xVelocity = ball.getXVelocity();
        double yVelocity = ball.getYVelocity();
        if (xPosition < leftBound) {
            ball.setXPosition(leftBound);
            ball.setXVelocity(-xVelocity);
        }
        if (xPosition > rightBound) {
            ball.setXPosition(rightBound);
            ball.setXVelocity(-xVelocity);
        }
        if (yPosition < topBound) {
            ball.setYPosition(topBound);
            ball.setYVelocity(-yVelocity);
        }
        if (yPosition > bottomBound) {
            ball.setYPosition(bottomBound);
            ball.setYVelocity(-yVelocity);
        }
    }

    public void runMagnetBoardCollisions() {
        for (int i = 0; i < 3; i++){
            Ball ball = magnets[i].getBall();
            int leftBound = 120;
            int rightBound = width - 120;
            int topBound = 170;
            int bottomBound = height - 170;
            double xPosition = ball.getXPosition();
            double yPosition = ball.getYPosition();
            double xVelocity = ball.getXVelocity();
            double yVelocity = ball.getYVelocity();
            if (xPosition < leftBound) {
                ball.setXPosition(leftBound);
                ball.setXVelocity(-xVelocity);
            }
            if (xPosition > rightBound) {
                ball.setXPosition(rightBound);
                ball.setXVelocity(-xVelocity);
            }
            if (yPosition < topBound) {
                ball.setYPosition(topBound);
                ball.setYVelocity(-yVelocity);
            }
            if (yPosition > bottomBound) {
                ball.setYPosition(bottomBound);
                ball.setYVelocity(-yVelocity);
            }
        }
    }

    public void runPlayerBallCollisions() {
        // iterate through players
        for (Player player : players) {
            Ball playerBall = player.getPlayerBall();
            if (playerBall.collides(gameBall.getBall())) {
                gameBall.getBall().deflectWith(playerBall);
            }
        }
    }

    public void runBallMagnetCollisions(){
        for (int i = 0; i < 3; i++){
            if(gameBall.getBall().collides(magnets[i].getBall())){
                gameBall.getBall().deflectWith(magnets[i].getBall());
            }
        }
    }

    public void runMagnetCollisions(){
        if(magnets[0].getBall().collides(magnets[1].getBall())){
            magnets[0].getBall().deflectWith(magnets[1].getBall());
        }
        if(magnets[0].getBall().collides(magnets[2].getBall())){
            magnets[0].getBall().deflectWith(magnets[2].getBall());
        }
        if(magnets[1].getBall().collides(magnets[2].getBall())){
            magnets[1].getBall().deflectWith(magnets[2].getBall());
        }
    }

    public void checkBall() {
        if (gameBall.getBall().getXPosition() > goals[0].getXPosition()-goals[0].getSize()/2 &&
            gameBall.getBall().getXPosition() < goals[0].getXPosition()+goals[0].getSize()/2 &&
            gameBall.getBall().getYPosition() < goals[0].getYPosition()+goals[0].getSize()/2 &&
            gameBall.getBall().getYPosition() > goals[0].getYPosition()-goals[0].getSize()/2){
                reset();
                scorep2++;
                score2.setText(String.valueOf(scorep2));
            }

        if (gameBall.getBall().getXPosition() > goals[1].getXPosition()-goals[1].getSize()/2 &&
            gameBall.getBall().getXPosition() < goals[1].getXPosition()+goals[1].getSize()/2 &&
            gameBall.getBall().getYPosition() < goals[1].getYPosition()+goals[1].getSize()/2 &&
            gameBall.getBall().getYPosition() > goals[1].getYPosition()-goals[1].getSize()/2){
                reset();
                scorep1++;
                score1.setText(String.valueOf(scorep1));
            }
    }

    public void reset(){
        players[0].setPosition(200, height/2);
        players[1].setPosition(width-200, height/2);
        gameBall.getBall().setXVelocity(0);
        gameBall.getBall().setYVelocity(0);
        gameBall.getBall().setYPosition(corners[0].getYPosition());
        gameBall.getBall().setXPosition(corners[0].getXPosition());
        arena.pause();
    }
}
