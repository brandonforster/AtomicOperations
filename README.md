#Atomic Operations
##COP 4600 - Homework 2
####Due Dec 2th, 2013


###General description

Create a model of the formation of a methane molecule CH4 using semaphores. Methane is made of one carbon atom and four hydrogen atoms. You need to implement at least three types of processes:
- one process for each hydrogen atom (assume that they are created regularly).
- one process for each carbon atom (assume that they are created regularly)
- a single process for the chemical bonding.
The hydrogen and carbon atoms wait until there are four hydrogen and one carbon atom available. Then, they are combined into a methane molecule and terminate their individual life as an atom. 

Write a Java program which implements this functionality.

###Specific steps:

1.	Start from the posted code. Compile and run it. Understand what it is doing. Look up the documentation of semaphores in Java. 
2. Notice that Carbon atoms are not generated. Add the code to generate carbon atoms. 
3.	Notice that the action creation of Methane molecules is not there. Add this code. You will actually need to remove the corresponding carbon and hydrogen atoms from the lists in ChemicalBondingCreator. You can do this either in ChemicalBondingCreator or in the atoms themselves.
4.	Now, notice that the lists are not protected by mutual exclusion, and if you run the program enough, you will have simultaneous access exceptions. Protect them with mutual exclusion semaphores to prevent this.
5.	Now, notice that by simply signaling 5 times the waiton semaphore does not guarantee that what you will wake up are the four earliest hydrogen atoms and the earliest carbon atom. You need to figure out some different arrangements. Find a solution to this problem (you might need more than one semaphore etc.).
6.	Finally, notice that the ChemicalBondCreator thread is a spin lock: goes to sleep, wakes up, checks if the conditions are satisfied, goes to sleep again. Change the program in such a way that ChemicalBondCreator waits on a semaphore, and it only wakes up if there are sufficient atoms to create a methane molecule. 

###Extra credit (20 points)
Implement a version where the program can take an arbitrary chemical formula. 

###What to submit:
- The Java files (zipped).
- Explanation file.
- If you implemented the extra credit part: a separate zip file for the generic version + a text file describing the syntax of the implementation.

 
Available on GitHub at https://github.com/brandonforster/AtomicOperations
