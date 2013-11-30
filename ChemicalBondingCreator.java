// Brandon Forster
// COP 4600- Operating Systems
// Homework 2- Threading
// 2 December 2013
// ChemicalBondingCreator.java
// Bonds atoms together.

import java.util.*;
import java.util.concurrent.*;

public class ChemicalBondingCreator implements Runnable {
	private int numElements;
	private int numBonds[];

	// forms 2D lists to keep track of these per element
	public ArrayList<ConcurrentLinkedQueue<Atom>> atomListArray=
			new ArrayList<ConcurrentLinkedQueue<Atom>>();
	public ArrayList<Semaphore> atomSemaphoreArray= new ArrayList<Semaphore>();

	public ChemicalBondingCreator(int numElements, int[] numBonds) {
		this.numElements= numElements;
		this.numBonds= numBonds;
		
		for (int i = 0; i < numElements; i++)
		{
			// set up the array of queues per element
			atomListArray.add(new ConcurrentLinkedQueue<Atom>());

			// set up the array of semaphores per element
			atomSemaphoreArray.add(new Semaphore(0));
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
					atomSemaphoreArray.get(i).acquire(numBonds[i]);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
			System.out.println("Chemical bonding creator: "
					+ "enough atoms to create a molecule");
			
			
			for (int element = 0; element < numElements; element++)
			{
				// mutex on aList
				synchronized(atomListArray.get(element))
				{
					// remove the first atom numBonds number of times and ublock that atom
					for (int i=0; i < numBonds[element]; i++)
						atomListArray.get(element).poll().allowBond();
				}
			}
		}
	}

}
