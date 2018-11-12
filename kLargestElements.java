package msk180001;


import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * TEAM TASK: SP11
 * 
 * @author Maleeha Shabeer Koul msk180001
 * 		   Shakti Singh 
 * 
 * Purpose of the class : 
 * 				To find the k largest elements in an array 
 * 				Implements two algorithms for performing the task:	
 * 											1. Priority Queue (O(nlogk))
 * 											2. Select (O(n))
 */

public class kLargestElements {
	/**
	 * The method stores all the elements in a priority queue and returns top K elements
	 * 		Time Complexity: O(nlogk)
	 * 
	 * @param a : Array of elements
	 * @param k : # of maximum elements to be found
	 * @param elements : Empty array to store the k elements
	 * @return : Returns an array with k elements
	 */
	private static  <T extends Comparable<? super T>> T[] selectPQ(T[] a, int k, T[] elements ){
		if(k >= a.length){
			return a;
		}
		PriorityQueue<T> pQueue = new PriorityQueue<T>(a.length, Collections.reverseOrder());
		for(int i = 0; i < a.length; i++){
			pQueue.add(a[i]);
		}
		for(int i = 0; i < k; i++){
			elements[i] = pQueue.remove();
		}	
		return elements;
	}
	
	/**
	 * The method finds the K largest elements in the given array using Select
	 * 		Time Complexity: O(n)
	 * @param A: Array of elements
	 * @param k: # of maximum elements to be found
	 * @return Returns an array with k elements
	 */
	   public static Integer[] selectKElements(Integer[] A,int k)
		{
			Integer result[]=new Integer[k];
			
		    if(k<=0)
		    {
		    	return result;
		    }
		    else if(k>=A.length)
		    {
		    	return A;
		    }
		    else
		    {
		    	 select(A,0,A.length,k);
		    int size=A.length;
		     for (int i=0;i<k;i++)
		     {
		    	 result[i]=A[size-k+i];
		     }
		     return result;
		    }
		}
	   //Helper method used for selectKElements
	 	public static Integer select(Integer[] A, int p, int n, int k) {
			int r=p+n-1;
			int q,left,right;
			if(n<17)  //threshold
			{
				insertionSort(A, p, r);
				return A[p+n-k];
			}
		
			else
			{
				q=partition(A, p, r);
				left=q-p;
				right=r-q;
				if(right>=k)
				{
					return select(A, q+1, right, k);
				}
				else if(right+1==k)
				{
					return A[q];
				}
				else
					return select(A,p,left,k-(1+right));
			}
		}

		// Helper method for randomized partition		
		private static int partition(Integer arr[], int low, int high) {
	        Random r = new Random();
	        int size = high - low;
	        int pivot = low + r.nextInt(size+1);

	        swap(arr,pivot,high);
	        Integer x = arr[high];
	        int i = low-1;

	        for(int j = low;j<high;j++){
	            if (arr[j] <= x) {
	                i++;
	                swap(arr, i, j);
	            }
	        }
	        swap(arr,i+1,high);
	        return i+1;
	    }

	// Helper method that swaps two elements
	private static void swap(Integer[] arr, int index1, int index2) {
	    Integer temp = arr[index1];
	    arr[index1] = arr[index2];
	    arr[index2] = temp;
	}
	
	// Helper method for insertion sort, with starting and ending indices
    static void insertionSort(Integer[] arr, int start, int end){
        for(int i=start+1; i<=end; i++){
            int temp = arr[i];
            int j=i-1;

            //finding the correct position to insert the element
            while(j>=start && temp<arr[j])
            {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = temp;
        }
    }
	
	/**
	 * Sample Driver class for testing 
	 * @param args
	 */
	public static void main(String[] args) {
											
		int n = 32000000;							//# of elements in the array
		int k = n/2;								//# of maximum elements (median = n/2)
		Timer t = new Timer();
		Integer[] a1 = new Integer[n];				//Generates a random array
		Integer[] temp = new Integer[k];			//Stores the k maximum elements
		for(int i = 0; i < a1.length; i++){
			a1[i] = i;
		}
		
		t.start();
		//Selection algorithm using Priority queue
		temp = selectPQ(a1, k, temp);
		System.out.println(t.end());
		/*
		 * Printing for testing
		 * for(int i = 0; i < temp.length; i++)
		System.out.print(temp[i] + "\t");	
		System.out.println();*/
		
		
		
		
		t.start();
		//Selection algorithm using select (O(n) algorithm)
		temp = selectKElements(a1, k);
		System.out.println(t.end());
/*		
 * 		Printing for testing 
 * 		for(int i = 0; i < temp.length; i++)
		System.out.print(temp[i] + "\t");	
		
		System.out.println();*/

	}

}
