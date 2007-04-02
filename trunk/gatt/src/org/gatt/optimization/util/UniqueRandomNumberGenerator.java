package org.gatt.optimization.util;

public class UniqueRandomNumberGenerator {
	private int[] numbers;
	private int uniqueNumbersLeft;
	/**
	 * @param totalRandomNumbers  
	 */
	public UniqueRandomNumberGenerator(int totalRandomNumbers){		
		numbers = new int[totalRandomNumbers];
		for(int i = 0; i < totalRandomNumbers; i++)
			numbers[i] = i;
		uniqueNumbersLeft = totalRandomNumbers;
	}
	public int nextRandom(){
		//1. Generate a random position between 0 and uniqueNumbersLeft
		int position = (int) (Math.random() * uniqueNumbersLeft);
		//2. Decrement the uniqueNumbers left by one
		uniqueNumbersLeft --;
		//3. Exchange the number in the random position and the last uniqueNumber left
		int randomNumber = numbers[position];
		numbers[position] = numbers[uniqueNumbersLeft];
		numbers[uniqueNumbersLeft] = randomNumber;
		//4. Return the randomNumber (the number in the random position)
		return randomNumber;
	}
}
