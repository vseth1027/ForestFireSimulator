
public class Forest {
	
	private int numInitialLivingTrees;	// counts living trees
	private double density;
	private int height, width;
	private Tree[][] forest;
	boolean noLocation = true;

	public Forest(int width, int height) { //initializes forest w/ trees (randomly)
		this.width = width;
		this.height = height;
		forest = new Tree[height][width];
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				forest[i][j] = new Tree((int)(Math.random()*3) + 1);
				if(forest[i][j].isAlive()) numInitialLivingTrees++;
			}
		}
	}

	
	public Forest(int width, int height, double density) { 
		numInitialLivingTrees = 0;
		this.width = width;
		this.height = height;
		forest = new Tree[height][width];
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if((int)(Math.random()*100) < density) {
					forest[i][j] = new Tree(Tree.LIVING);
					numInitialLivingTrees++;
				} else {
					forest[i][j] = null;
				}
			}
		}
		if(density > 0) {		// starts random fire
			while(noLocation) {
				int h = (int)(Math.random()*height);
				int w = (int)(Math.random()*width);
				if(forest[h][w] != null) {
					forest[h][w].setIsBurning();
					noLocation = false;
				}
			}
		}
		
	}

	public double getDensity() {
		return density;
	}
	
	public void setDensity(double d) {
		this.density = d;
	}
	
	public Tree[][] getGrid() {
		return forest;
	}
	
	public double getDensityOfLivingTrees() {	// number of living trees as compared to the entire forest
		return (double)(getNumLivingTrees()) / (height*width);
	}
	
	public int getWidth() {
		return width;
	}
	
	public boolean isInBounds(int r, int c) {
		if(r < 0 || c < 0 || r >= height || c >= width) return false;
		return true;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getNumLivingTrees() {	//gets the number of living trees at a particular time
		int numTrees = 0;
		for(int r = 0; r < getHeight(); r++) {
			for(int c = 0; c < getWidth(); c++) {
				if(forest[r][c] != null) {
					if(forest[r][c].isAlive()) numTrees++;
				}
				
			}
		}
		return numTrees;
	}
	
	public int getNumInitialLivingTrees() {
		return numInitialLivingTrees;
	}

	public void set(int r, int c, Tree nextTree) {
		forest[r][c] = nextTree;
		
	}

	public void clear() {
		for(int r = 0; r < getHeight(); r++) {
			for(int c = 0; c < getWidth(); c++) {
				forest[r][c] = null;
			}
		}
		
	}

}
