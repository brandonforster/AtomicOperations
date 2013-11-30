// Brandon Forster
// COP 4600- Operating Systems
// Homework 2- Threading
// 2 December 2013
// HydrogenAtom.java
// The HydrogenAtom object.

import java.util.concurrent.Semaphore;

public class HydrogenAtom implements Runnable {

	private ChemicalBondingCreator cbc;
	private int count;
	
	private Semaphore waitToBond= new Semaphore(0);

	public HydrogenAtom(ChemicalBondingCreator cbc, int count) {
		this.cbc = cbc;
		this.count = count;
	}

	@Override
	public void run() {
		System.out.println("Hydrogen atom no: " + count + " created.");
		// mutex on haList
		synchronized(cbc.haList)
		{
			cbc.haList.add(this);
		}
		
		// add 1 permit to hydrogen semaphore
		cbc.hSemaphore.release(1);
		
		System.out.println("Hydrogen atom no: " + count
				+ " waiting for bonding.");

		// block until CBC tells atom it's ready for it
		try {
			waitToBond.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Hydrogen atom no: " + count + " bonded, done.");
	}
	
	public boolean allowBond()
	{
		waitToBond.release();
		
		return true;
	}

}
