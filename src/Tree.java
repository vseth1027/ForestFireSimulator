
public class Tree {
	public static final int LIVING = 1, BURNING = 2, ASH = 3;
	private boolean isAlive, isBurning, isAsh;
	private int life = 10;
	private int timeBurning;
	
	public Tree(int state) {
		if(state == LIVING) setIsAlive();
		if(state == BURNING)setIsBurning();
		if(state == ASH) setIsAsh();
	}
	
	public Tree getNextState(int row, int col, Forest current) {
		if(isAsh || life == 0) {
			setIsAsh();
			return new Tree(ASH);
		} else {
			if(isBurning && life > 0) {
				life--;
				timeBurning++;
				return new Tree(BURNING);
			} else {
				if(isAlive) {
					Location loc = new Location(row, col);
					for(int i = 0; i < 360; i+= 45) {
						Location adjLoc = loc.getAdjacentLocation(i);
						if(adjLoc != null && current.isInBounds(adjLoc.getRow(), adjLoc.getCol())) {
							Tree[][] temp = current.getGrid();
							Tree t = temp[adjLoc.getRow()][adjLoc.getCol()];
							if(t != null)  {
								boolean isburning = t.isBurning();
								if(isburning) {
									setIsBurning();
									return new Tree(BURNING);
								}
							}
						}
					}
				}
			}
		}
		return new Tree(LIVING);
	}
	
	public void setIsAlive() {
		isAlive = true;
		isBurning = false;
		isAsh = false;
	}
	public boolean isAlive() {
		return isAlive;
	}
	
	public void setIsBurning() {
		isAlive = false;
		isBurning = true;
		isAsh = false;
	}
	
	public boolean isBurning() {
		return isBurning;
	}
	
	public void setIsAsh() {
		isAlive = false;
		isBurning = false;
		isAsh = true;
	}
	
	public boolean isAsh() {
		return isAsh;
	}

	
	public double getTimeBurning() {	
		return timeBurning;
	}

	public int getTreeState() {
		if(isAlive) return LIVING;
		if(isBurning) return BURNING;
		return ASH;
	}
}
