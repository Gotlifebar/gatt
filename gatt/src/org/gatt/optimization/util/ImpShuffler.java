package org.gatt.optimization.util;

public class ImpShuffler implements Shuffler {

	@Override
	public void shuffle(final Object[] arr) {
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
