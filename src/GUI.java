import processing.core.*;

public class GUI extends PApplet {
	int c;
	Simulator sim;
	Display display;

	public void setup() {
		size(640, 550); // set the size of the screen.

		// Create a simulator
		sim = new Simulator(40);

		// Create the display
		// parameters: (10,10) is upper left of display
		// (620, 530) is the width and height
		display = new Display(this, 10, 10, 620, 530);

		// Set different grid values to different colors
		display.setColor(Tree.LIVING, color(0, 255, 0)); 
		display.setColor(Tree.BURNING, color(250, 0, 0));
		display.setColor(Tree.ASH, color(192, 192, 192));
		display.setColor(null, color(0, 0, 0));

		// You can use classes instead, for example:
		// display.setColor(Tree.class, color(0, 255, 0));
		// display.setColor(Ash.class, color(0, 255, 0));
		
		display.setNumCols(100);
		display.setNumRows(100);
	}

	@Override
	public void draw() {
		background(200);

		sim.simulateOneStep();
		
		display.drawGrid(sim.getSimForest()); // display the game
	}
}