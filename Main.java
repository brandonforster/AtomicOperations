
public class Main {

	
	public static final void main(String args[]) {
		System.out.println("Project 2");
		// create the bonding
		ChemicalBondingCreator cbc = new ChemicalBondingCreator();
		Thread thread = new Thread(cbc);
		thread.start();
		// create the hydrogen atom generator
		HydrogenAtomGenerator hag = new HydrogenAtomGenerator(cbc);
		thread = new Thread(hag);
		thread.start();
		
		// create the carbon atom generator
		CarbonAtomGenerator cag = new CarbonAtomGenerator(cbc);
		thread = new Thread(cag);
		thread.start();
		
		
	}
	
	
}
