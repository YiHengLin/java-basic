package com.ov.leetcode;

/**
 * No.167 Given an array of integers that is already sorted in ascending order,
 * find two numbers such that they add up to a specific target number.
 */
public class TwoSumWithInputSortedArray {

	public int[] twoSum(int[] numbers, int target) {
		
		int i = 0;
		int j = numbers.length -1;
		
		while(numbers[i] + numbers[j] != target) {
			if((numbers[i] + numbers[j]) < target) {
				i++;
			}else {
				j--;
			}
		}
		
		return new int[] {i+1, j+1}; // since not zero-based
	}

}
