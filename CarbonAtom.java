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
		
		// add 1 permit to carbon semaphore
		cbc.cSemaphore.release(1);
		
		System.out.println("Carbon atom no: " + count
				+ " waiting for bonding.");
		try {
			synchronized (cbc.cWaiton) {
				cbc.cWaiton.acquire();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Carbon atom no: " + count + " bonded, done.");
		
		// mutex on caList
		synchronized(cbc.caList)
		{
			// remove 1 carbon
			cbc.caList.remove(this);
		}
	}

}