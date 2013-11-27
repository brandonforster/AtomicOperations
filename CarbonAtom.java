public class CarbonAtom implements Runnable {

	private ChemicalBondingCreator cbc;
	private int count;

	public CarbonAtom(ChemicalBondingCreator cbc, int count) {
		this.cbc = cbc;
		this.count = count;
	}

	@Override
	public void run() {
		System.out.println("Carbon atom no: " + count + " created.");
		cbc.caList.add(this);
		System.out.println("Carbon atom no: " + count
				+ " waiting for bonding.");
		try {
			synchronized (cbc.waiton) {
				cbc.waiton.acquire();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Carbon atom no: " + count + " bonded, done.");
	}

}