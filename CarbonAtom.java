// Brandon Forster
// COP 4600- Operating Systems
// Homework 2- Threading
// 2 December 2013
// CarbonAtom.java
// The CarbonAtom object.

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
		// mutex on caList
		synchronized(cbc.caList)
		{
			cbc.caList.add(this);
		}
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