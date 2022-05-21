/**
 * Models a simple solid sphere. 
 * This class represents a Ball object. When combined with the GameArena class,
 * instances of the Ball class can be displayed on the screen.
 */
public class Ball 
{
	// The following instance variables define the
	// information needed to represent a Ball
	// Feel free to more instance variables if you think it will 
	// support your work... 
	
	private double xPosition;			// The X coordinate of this Ball
	private double yPosition;			// The Y coordinate of this Ball
	private double xVelocity;			// The X velocity of this Ball
	private double yVelocity;			// The Y velocity of this Ball
	private double size;				// The diameter of this Ball
	private int layer;					// The layer of this ball is on.
	private String colour;				// The colour of this Ball

										// Permissable colours are:
										// BLACK, BLUE, CYAN, DARKGREY, GREY,
										// GREEN, LIGHTGREY, MAGENTA, ORANGE,
										// PINK, RED, WHITE, YELLOW or #RRGGBB 

	/**
	 * Constructor. Creates a Ball with the given parameters.
	 * @param x The x co-ordinate of centre of the Ball (in pixels)
	 * @param y The y co-ordinate of centre of the Ball (in pixels)
	 * @param diameter The diameter of the Ball (in pixels)
	 * @param col The colour of the Ball (Permissable colours are: BLACK, BLUE, CYAN, DARKGREY, GREY, GREEN, LIGHTGREY, MAGENTA, ORANGE, PINK, RED, WHITE, YELLOW or ##RRGGBB)
	 */
	public Ball(double x, double y, double diameter, String col)
	{
		this.xPosition = x;
		this.yPosition = y;
		this.size = diameter;
		this.colour = col;
		this.layer = 0;
	}	

	/**
	 * Constructor. Creates a Ball with the given parameters.
	 * @param x The x co-ordinate of centre of the Ball (in pixels)
	 * @param y The y co-ordinate of centre of the Ball (in pixels)
	 * @param diameter The diameter of the Ball (in pixels)
	 * @param col The colour of the Ball (Permissable colours are: BLACK, BLUE, CYAN, DARKGREY, GREY, GREEN, LIGHTGREY, MAGENTA, ORANGE, PINK, RED, WHITE, YELLOW or ##RRGGBB)
	 * @param layer The layer this ball is to be drawn on. Objects with a higher layer number are always drawn on top of those with lower layer numbers.
	 */
	public Ball(double x, double y, double diameter, String col, int layer)
	{
		this.xPosition = x;
		this.yPosition = y;
		this.size = diameter;
		this.colour = col;
		this.layer = layer;
	}	

	/**
	 * Obtains the current position of this Ball.
	 * @return the X coordinate of this Ball within the GameArena.
	 */
	public double getXPosition()
	{
		return xPosition;
	}

	/**
	 * Obtains the current position of this Ball.
	 * @return the Y coordinate of this Ball within the GameArena.
	 */
	public double getYPosition()
	{
		return yPosition;
	}

	/**
	 * Moves the current position of this Ball to the given co-ordinates
	 * @param x the new x co-ordinate of this Ball
	 */
	public void setXPosition(double x)
	{
		this.xPosition = x;
	}

	/**
	 * Moves the current position of this Ball to the given co-ordinates
	 * @param y the new y co-ordinate of this Ball
	 */
	public void setYPosition(double y)
	{
		this.yPosition = y;
	}

	/**
	 * Gets the current x velocity of this Ball
	 * @return the x velocity of the ball.
	 */
	public double getXVelocity() {
		return this.xVelocity;
	}

	/**
	 * Sets the x velocity of this Ball to the given velocity.
	 * @param x the new x velocity of this Ball
	 */
	public void setXVelocity(double x) {
		this.xVelocity = x;
	}

	/**
	 * Gets the current y velocity of this Ball
	 * @return the y velocity of the ball.
	 */
	public double getYVelocity() {
		return this.yVelocity;
	}

	/**
	 * Sets the y velocity of this Ball to the given velocity.
	 * @param y the new y velocity of this Ball
	 */
	public void setYVelocity(double y) {
		this.yVelocity = y;
	}

	/**
	 * Obtains the size of this Ball.
	 * @return the diameter of this Ball,in pixels.
	 */
	public double getSize()
	{
		return size;
	}
	
	/**
	 * Sets the diameter of this Ball to the given size.
	 * @param s the new diameter of this Ball, in pixels.
	 */
	public void setSize(double s)
	{
		size = s;
	}

	/**
	 * Obtains the layer of this Ball.
	 * @return the layer of this Ball.
	 */
	public int getLayer()
	{
		return layer;
	}

	/**
	 * Sets the layer of this Ball.
	 * @param l the new layer of this Ball. Higher layer numbers are drawn on top of low layer numbers.
	 */
	public void setLayer(int l)
	{
		layer = l;
	}

	/**
	 * Obtains the colour of this Ball.
	 * @return a textual description of the colour of this Ball.
	 */
	public String getColour()
	{
		return colour;
	}

	/**
	 * Sets the colour of this Ball.
	 * @param c the new colour of this Ball, as a String value. Permissable colours are: BLACK, BLUE, CYAN, DARKGREY, GREY, GREEN, LIGHTGREY, MAGENTA, ORANGE, PINK, RED, WHITE, YELLOW or #RRGGBB.
	 */
	public void setColour(String c)
	{
		colour = c;
	}

	/**
	 * Moves this Ball by the given amount.
	 * 
	 * @param dx the distance to move on the x axis (in pixels)
	 * @param dy the distance to move on the y axis (in pixels)
	 */
	public void move(double dx, double dy)
	{
		xPosition += dx;
		yPosition += dy;
	}

	/**
	 * Determines if this Ball is overlapping the given ball.
	 * 
	 * @param b the ball to test for collision
	 * @return true of this ball is overlapping the ball b, false otherwise.
	 */
	public boolean collides(Ball b)
	{
		double dx = b.xPosition - xPosition;
		double dy = b.yPosition - yPosition;
		double distance = Math.sqrt(dx*dx+dy*dy);

		return distance < size/2 + b.size/2;
	}

	/**
	 * Deflects this Ball with the given Ball, altering the velocities of both.
	 * 
	 * @param otherBall the other ball with which to deflect.
	 */
	public void deflectWith(Ball otherBall) {
        // Calculate initial momentum of the balls... We assume unit mass here.
        double p1InitialMomentum = Math.sqrt(this.xVelocity * this.xVelocity + this.yVelocity * this.yVelocity);
        double p2InitialMomentum = Math.sqrt(otherBall.getXVelocity() * otherBall.getXVelocity() + otherBall.getYVelocity() * otherBall.getYVelocity());
        // calculate motion vectors
        double[] p1Trajectory = { this.xVelocity, this.yVelocity };
        double[] p2Trajectory = { otherBall.getXVelocity(), otherBall.getYVelocity() };
        // Calculate Impact Vector
        double[] impactVector = { otherBall.getXPosition() - this.xPosition, otherBall.getYPosition() - this.yPosition };
        double[] impactVectorNorm = Utils.normalizeVector(impactVector);
        // Calculate scalar product of each trajectory and impact vector
        double p1dotImpact = Math.abs(p1Trajectory[0] * impactVectorNorm[0] + p1Trajectory[1] * impactVectorNorm[1]);
        double p2dotImpact = Math.abs(p2Trajectory[0] * impactVectorNorm[0] + p2Trajectory[1] * impactVectorNorm[1]);
        // Calculate the deflection vectors - the amount of energy transferred from one
        // ball to the other in each axis
        double[] p1Deflect = { -impactVectorNorm[0] * p2dotImpact, -impactVectorNorm[1] * p2dotImpact };
        double[] p2Deflect = { impactVectorNorm[0] * p1dotImpact, impactVectorNorm[1] * p1dotImpact };
        // Calculate the final trajectories
        double[] p1FinalTrajectory = { p1Trajectory[0] + p1Deflect[0] - p2Deflect[0],
                p1Trajectory[1] + p1Deflect[1] - p2Deflect[1] };
        double[] p2FinalTrajectory = { p2Trajectory[0] + p2Deflect[0] - p1Deflect[0],
                p2Trajectory[1] + p2Deflect[1] - p1Deflect[1] };
        // Calculate the final energy in the system.
        double p1FinalMomentum = Math
                .sqrt(p1FinalTrajectory[0] * p1FinalTrajectory[0] + p1FinalTrajectory[1] * p1FinalTrajectory[1]);
        double p2FinalMomentum = Math
                .sqrt(p2FinalTrajectory[0] * p2FinalTrajectory[0] + p2FinalTrajectory[1] * p2FinalTrajectory[1]);
        // Scale the resultant trajectories if we've accidentally broken the laws of
        // physics.
        double mag = (p1InitialMomentum + p2InitialMomentum) / (p1FinalMomentum + p2FinalMomentum);
        // Calculate the final x and y speed settings for the two balls after collision.
        this.xVelocity = p1FinalTrajectory[0] * mag;
        this.yVelocity = p1FinalTrajectory[1] * mag;
		otherBall.setXVelocity(p2FinalTrajectory[0] * mag);
		otherBall.setYVelocity(p2FinalTrajectory[1] * mag);
    }
}
