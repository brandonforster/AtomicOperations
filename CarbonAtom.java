// Brandon Forster
// COP 4600- Operating Systems
// Homework 2- Threading
// 2 December 2013
// CarbonAtom.java
// The CarbonAtom object.

import java.util.concurrent.Semaphore;

public class CarbonAtom implements Runnable {

	private ChemicalBondingCreator cbc;
	private int count;

	public CarbonAtom(ChemicalBondingCreator cbc, int count) {
		this.cbc = cbc;
		this.count = count;
	}
	
	private Semaphore waitToBond= new Semaphore(0);

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
		
		// block until CBC tells atom it's ready for it
		try {
			waitToBond.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Carbon atom no: " + count + " bonded, done.");
	}
	
	public boolean allowBond()
	{
		waitToBond.release();
		
		return true;
	}

}