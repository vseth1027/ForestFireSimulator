
public class Simulator {

	private static Forest currentForest, nextForest;
	private double density;
	
	public Simulator(double d) {
		density = d;
		currentForest = new Forest(100, 100, density);
		nextForest = new Forest(currentForest.getHeight(), currentForest.getWidth());
	}
	
	public double getDensity() {
		return density;
	}
	
	public void startFire(int row, int col) { // sets one tree on fire
		if(outOfBounds(row, col)) return;
		Tree[][] forest = currentForest.getGrid();
		forest[row][col].setIsBurning();
	}
	
	public boolean outOfBounds(int row, int col) {
		if(row < 0 || row >= currentForest.getHeight() || col < 0 
				|| col >= currentForest.getWidth())	return false;
		return true;
	}
	
	public double calcPercentBurned() { // fix this
		int a = currentForest.getNumInitialLivingTrees();
		if(a == 0) return 0;
		int b = currentForest.getNumLivingTrees();
		return ((double)(a - b) / a) * 100;
	}

	public Forest getSimForest() {
		return currentForest;
	}

	public void simulateOneStep() {
		for(int r = 0; r < currentForest.getHeight(); r++) {
			for(int c = 0; c < currentForest.getWidth(); c++) {
				Tree[][] forest = currentForest.getGrid();
				Tree tree = forest[r][c];
				if(tree != null) {
					Tree nextTree = tree.getNextState(r, c, currentForest);
					nextForest.set(r, c, nextTree);
				} else {
					nextForest.set(r, c, null);
				}
				
				
			}
		}
		
		Forest temp = currentForest;
		currentForest = nextForest;
		nextForest = temp;
		nextForest.clear();
	}

	public void run() {
		for(int i = 0; i < 1000; i++) {
			simulateOneStep();
		}
		
	}

}
