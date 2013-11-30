// Brandon Forster
// COP 4600- Operating Systems
// Homework 2- Threading
// 2 December 2013
// Main.java
// The main function class for AtomicOperations.

import java.util.*;
		
public class Main {

	
	public static final void main(String args[]) {
		System.out.println("Project 2 by Brandon Forster");
		
		System.out.println("Please enter how many elements you want to bond, followed by how many atoms of each your bond needs.");
		
		Scanner stdin= new Scanner(System.in);		
		int numElements= stdin.nextInt();
		
		while (numElements <= 0)
		{
			System.out.println("Please enter how many elements you want to bond, followed by how many atoms of each your bond needs.");
			numElements= stdin.nextInt();
		}
		
		int numBonds[]= new int[numElements];
		
		boolean validInput= false;
		while (!validInput)
		{
			try
			{
				for (int i= 0; i< numElements; i++)
				{
					numBonds[i]= stdin.nextInt();
				}
				validInput= true;
			} catch (InputMismatchException e)
			{
				System.out.println("Please enter the bond numbers for the number of elements you specified.");
				
				// clear the Scanner's buffer by telling it to read the next line
				stdin.nextLine();
			}
		}
		
		stdin.close();
		
		// create the bonding
		ChemicalBondingCreator cbc = new ChemicalBondingCreator(numElements, numBonds);
		Thread thread = new Thread(cbc);
		thread.start();
		
		// the atomic number, counts up from 0
		int number= 0;
		
		// create the appropriate number of atom generators
		for (int i= 0; i< numElements; i++)
		{
			AtomGenerator ag = new AtomGenerator(cbc, number);
			number++;
			thread = new Thread(ag);
			thread.start();
		}
		

	}
}
