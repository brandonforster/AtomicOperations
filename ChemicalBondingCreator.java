// Brandon Forster
// COP 4600- Operating Systems
// Homework 2- Threading
// 2 December 2013
// ChemicalBondingCreator.java
// Bonds atoms together.

import java.util.concurrent.*;

public class ChemicalBondingCreator implements Runnable {

	public Semaphore hWaiton = new Semaphore(0);
	public Semaphore cWaiton = new Semaphore(0);
	
	public Semaphore hSemaphore = new Semaphore(0);
	public Semaphore cSemaphore = new Semaphore(0);
	
	public ConcurrentLinkedQueue<HydrogenAtom> haList = 
			new ConcurrentLinkedQueue<>();
	public ConcurrentLinkedQueue<CarbonAtom> caList = 
			new ConcurrentLinkedQueue<>();

	@Override
	public void run() {
		while (true) {
			System.out.println("Chemical bonding creator: looking for bonding");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// block until enough permits to create the molecule
			try {
				hSemaphore.acquire(4);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				cSemaphore.acquire(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			System.out.println("Chemical bonding creator: "
					+ "enough atoms to create a methane molecule");
			
			// signal that we need 4 hydrogens and 1 carbon to stop blocking
			hWaiton.release(4);
			cWaiton.release(1);
			
			synchronized (haList)
			{
				synchronized (haList)
				{
					System.out.println("HS: "+ hSemaphore+ " "+ haList+ "\nCS: "+cSemaphore+" " + caList);
				}
			}
		}
	}

}
