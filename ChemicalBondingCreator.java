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
			
			// mutex on haList
			synchronized(haList)
			{
				// remove the first hydrogen from the list four times and unblock it
				for (int i=0; i < 4; i++)
					haList.poll().allowBond();
			}
			
			// mutex on caList
			synchronized(caList)
			{
				// remove the first carbon from the list and unblock it
				caList.poll().allowBond();
			}
		}
	}

}
