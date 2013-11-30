// Brandon Forster
// COP 4600- Operating Systems
// Homework 2- Threading
// 2 December 2013
// ChemicalBondingCreator.java
// Bonds atoms together.

import java.util.concurrent.*;

public class ChemicalBondingCreator implements Runnable {
	private int numElements;
	private int numBonds[];

	public Semaphore atomWaitArray[] = new Semaphore[numElements];	
	public Semaphore atomSemaphoreArray[] = new Semaphore[numElements];
	public ConcurrentLinkedQueue<Atom> atomListArray[]= new ConcurrentLinkedQueue[numElements];

	public ChemicalBondingCreator(int numElements, int[] numBonds) {
		this.numElements= numElements;
		this.numBonds= numBonds;
		
		System.out.println(atomWaitArray.length);
		
		for (int i = 0; i < numElements; i++)
		{
			// set up the array of queues per element
			atomListArray[i]= new ConcurrentLinkedQueue<Atom>();
			
			// set up the array of waitons per element
			atomWaitArray[i]= new Semaphore(0);
			
			// set up the array of semaphores per element
			atomSemaphoreArray[i]= new Semaphore(0);
		}
	}

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
			
			for (int i = 0; i < numElements; i++)
			{
				// block until enough permits to create the molecule
				try {
					atomSemaphoreArray[i].acquire(numBonds[i]);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
			System.out.println("Chemical bonding creator: "
					+ "enough atoms to create a methane molecule");
			
			
			for (int element = 0; element < numElements; element++)
			{
				// mutex on aList
				synchronized(atomListArray[element])
				{
					// remove the first atom numBonds number of times and ublock that atom
					for (int i=0; i < numBonds[element]; i++)
						atomListArray[i].poll().allowBond();
				}
			}
		}
	}

}
