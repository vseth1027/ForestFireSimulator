public class NonGUI {
	public static void main(String[] args) {
		// A non-graphical runner for doing a lot
		// of simulations and displaying the results
		//Simulator sim = new Simulator();
		
		// put in info for simulator (like dimensions) 
		
		double numTrials = 100;
		
		for(int density = 50; density <= 100; density++) {
			int sum = 0;
			for (int trial = 0; trial < numTrials; trial++) {
				Simulator sim = new Simulator(density);
				sim.run();
				sum += sim.calcPercentBurned();
				//sim.displayInfo();	// decide how to display the info (write method)
			}
			System.out.println(density + "\t" + (sum / numTrials));
		}
			

	}
}
