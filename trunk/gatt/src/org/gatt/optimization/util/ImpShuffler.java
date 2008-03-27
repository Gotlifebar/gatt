package org.gatt.optimization.util;

/**
 * @author Chucho
 * Mix possition of an array
 */
public class ImpShuffler implements Shuffler{
	
	/* (non-Javadoc)
	 * @see org.gatt.optimization.util.Shuffler#shuffle(java.lang.Object[])
	 */
	public void shuffle(Object[] arr) {
		int n = arr.length;
		int rPos = 0;
		Object tmp;
		while( n > 0 ){
			rPos = (int)(Math.random() * n);
			n--;
			tmp = arr[rPos];
			arr[rPos] = arr[n];
			arr[n] = tmp;
		}		
	}
	
}