import java.util.*;

/**
* This class defines the methods randomNumber, sort and merge.
* It also contains the main which calls sort on some arraylist.
*
* @author Darragh King (ID 113372871)
*/

public class Sorter{
	private final static int MINIMAL_ARRAY_LENGTH = 1;

	/**
	*	A method that returns a random number betweet two 
	*	specified numbers.
	*
	*	@param min The lower boundary of randomly generated numbers
	*	@param max The Higher boundary of randomly generated numbers
	*	@return A random int 
	**/
	public int randomNumber(int min, int max) {
	Random random = new Random();
	if(max < min){
		int maximum = min;
		int minimum = max;
		max = maximum;
		min = minimum;
	}
	int randomNumber = random.nextInt((max - min) + 1) + min;

    return randomNumber;
	}

	/**
	*	A method that sorts two arrays with respect to each other 
	* 	and returns the sorted final array. 
	*
	*	@param sortedArray The array into which leftArray and rightArray are to finally be sorted
	*	@param leftArray An array of unsorted elements
	*	@param rightArray An array of unsorted elements
	*	@param leftArraySize The array size of the left array
	*	@param rightArraySize The array size of the right array
	*	
	*	@return A sorted Arraylist
	*
	**/
	public final ArrayList<Comparable> merge(ArrayList<Comparable> sortedArray, ArrayList<Comparable> leftArray, ArrayList<Comparable> rightArray, int leftArraySize, int rightArraySize){
			int leftArrayIndex = 0;
			int rightArrayIndex = 0;
			int sortedArrayIndex = 0;
			Integer leftElement;
			Integer rightElement;
			while(leftArrayIndex < leftArraySize && rightArrayIndex < rightArraySize){
				leftElement = (Integer) leftArray.get(leftArrayIndex);
				rightElement = (Integer) rightArray.get(rightArrayIndex);
				if(leftElement.compareTo(rightElement) <= 0){
					sortedArray.add(sortedArrayIndex, leftElement);
					leftArrayIndex++;
				}
				else{
					sortedArray.add(sortedArrayIndex, rightElement);
					rightArrayIndex++;
				}
				sortedArrayIndex++;
			}
			//If all elements in the already sorted final version of rightArray were smaller than the remaining elements in the already sorted
			//final version of leftArray add them to the sortedArray
			while( leftArrayIndex < leftArraySize){
				leftElement = (Integer) leftArray.get(leftArrayIndex);
				sortedArray.add(sortedArrayIndex, leftElement);
				leftArrayIndex++;
				sortedArrayIndex++;
			}
			//If all elements in the already sorted final version of leftArray were smaller than the remaining elements in the already sorted
			//final version of rightArray add them to the sortedArray
			while(rightArrayIndex < rightArraySize){
				rightElement = (Integer) rightArray.get(rightArrayIndex);
				sortedArray.add(sortedArrayIndex, rightElement);
				rightArrayIndex++;
				sortedArrayIndex++;
			}
			return sortedArray;
	}

	/**
	*	My sudo code for my sort algorithm.
	*
	*	Create one temporary array for the final sorted array and two temporary arrays "left" and "right" for processing arrays in the algorithm
	*	while the array is >= the base case split the array into two even or almost even new arrays and place them in left and right
	*	Make recursive calls to sort on the arrays left and right
	*	Once each array has reached its base case return the array for the recursive call to sort.
	*	while there are still elemements to be sorted
	*		sort left and right with respect to each other and combine them into one array
	*	once all arrays have been sorted with respect to each other the array sort is complete and the sorted array is returned
	*	
	**/

	/**
	*
	*	Begin with one large arraylist of comparable objects. Start by spliting this large arraylist
	*	into two even or almost even new arraylists (almost even meaning a difference in size of 1).
	*	Recursively split each of these two new arraylists into two arraylists of equal or almost equal size once again. 
	*	The algorithm should recursively continue to do this until it reaches its base case of an arraylist of size 1 or less than one.
	*	At this point of the algorithm each element of the original array should be in an arraylist on its own.
	*	(ie if the original large array had 8 elements you would now have 8 sperate arrays containing each of these elements)
	*	At this point the algorithm will make a call to merge() and begin merging each of these arrays into sorted arrays. It will continue to do this
	*	until we have a complete, sorted array of all the original large array elemets. It will then return this sorted array.
	*
	*	@param array An arraylist of unsorted Comparable objects.
	*	@return A sorted arraylist consisting of all the comparable objects in the original array.
	**/
	public final ArrayList<Comparable> sort(ArrayList<Comparable> array){

		ArrayList<Comparable> sortedArray = new ArrayList<Comparable>();

		ArrayList<Comparable> left = new ArrayList<Comparable>();
		ArrayList<Comparable> right = new ArrayList<Comparable>();

		int arraySize = array.size();

		//The array is returned if it reaches the base case of size 0 or 1
		if(arraySize <= MINIMAL_ARRAY_LENGTH){
			return array;
		}
		else{
			int firstSplitArray;
			int secondSplitArray;

			//the number of the elements in each array are calculated here
			if(arraySize%2 == 0){
				firstSplitArray = arraySize/2;
				secondSplitArray = firstSplitArray;
			}
			else{
				firstSplitArray = arraySize/2;
				secondSplitArray = arraySize/2 + 1;
			}
			int currentArrayIndex = 0;
			Comparable arrayElement;	
			//create the first split array
			while(currentArrayIndex < firstSplitArray){
				arrayElement = (Integer) array.get(currentArrayIndex);
				left.add(arrayElement);
				currentArrayIndex++;
			}
			//create the second split array
			secondSplitArray = secondSplitArray + currentArrayIndex;
			while(currentArrayIndex < secondSplitArray){
				arrayElement = (Integer) array.get(currentArrayIndex);
				right.add(arrayElement);
				currentArrayIndex++;
			}
			//recursively call sort on the split arrays until they reach ther base case
			left = sort(left);
			right = sort(right);

			int leftArraySize = left.size();
			int rightArraySize = right.size();		
			//merge each all arrays together layer by layer until you get to the original sorted array	
			sortedArray = merge(sortedArray,left,right,leftArraySize,rightArraySize);
			
		return sortedArray;

	}
}


	/**
	*	Creates an arraylist of Comparable objects of size 0-3 as specified. Fills the list with random
	*	integer objects. Prints the unsorted list. Sorts the list, prints the sorted list.
	*
	*	@param args The given command line arguments 
	*/
	public static void main(String[] args){

		Sorter sorter = new Sorter();

		ArrayList<Comparable> listOfNumbers = new ArrayList<Comparable>();
		ArrayList<Comparable> sortedListOfNumbers = new ArrayList<Comparable>();
		
		int arraySize = sorter.randomNumber(0,3);
		System.out.print("Unsorted list: ");
		for(int i = 0 ; i < arraySize; i++){
			int randomInteger = sorter.randomNumber(0,99);
			System.out.print(randomInteger + " ");
			listOfNumbers.add(randomInteger);
		}

		System.out.println("");

		sortedListOfNumbers = sorter.sort(listOfNumbers);

		System.out.print("Sorted list: ");
		for(Comparable iterate : sortedListOfNumbers)
		{
			int integer = (Integer) iterate;
			System.out.print(integer + " ");
		}

	}
}
