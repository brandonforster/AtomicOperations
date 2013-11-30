// Brandon Forster
// COP 4600- Operating Systems
// Homework 2- Threading
// 2 December 2013
// Atom.java
// The Atom object.

import java.util.concurrent.Semaphore;

public class Atom implements Runnable {

	private ChemicalBondingCreator cbc;
	private int count;
	private int atomicNumber;
	
	private Semaphore waitToBond= new Semaphore(0);

	public Atom(ChemicalBondingCreator cbc, int count, int atomicNumber) {
		this.cbc = cbc;
		this.count = count;
		this.atomicNumber= atomicNumber;
	}

	@Override
	public void run() {
		System.out.println("Element #"+atomicNumber+" atom no: " + count + " created.");
		// mutex on aList
		synchronized(cbc.atomListArray[atomicNumber])
		{
			cbc.atomListArray[atomicNumber].add(this);
		}
		
		// add 1 permit to semaphore
		cbc.atomSemaphoreArray[atomicNumber].release(1);
		
		System.out.println("Element #"+atomicNumber+" atom no: " + count
				+ " waiting for bonding.");

		// block until CBC tells atom it's ready for it
		try {
			waitToBond.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Element #"+atomicNumber+" atom no: " + count + " bonded, done.");
	}
	
	public boolean allowBond()
	{
		waitToBond.release();
		
		return true;
	}

}
