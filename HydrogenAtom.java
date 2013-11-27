public class HydrogenAtom implements Runnable {

	private ChemicalBondingCreator cbc;
	private int count;

	public HydrogenAtom(ChemicalBondingCreator cbc, int count) {
		this.cbc = cbc;
		this.count = count;
	}

	@Override
	public void run() {
		System.out.println("Hydrogen atom no: " + count + " created.");
		cbc.haList.add(this);
		System.out.println("Hydrogen atom no: " + count
				+ " waiting for bonding.");
		try {
			synchronized (cbc.waiton) {
				cbc.waiton.acquire();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Hydrogen atom no: " + count + " bonded, done.");
	}

}
