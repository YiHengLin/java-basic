package com.ov.leetcode;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


class LeetCodeTest {
	

	@Test
	@Disabled
	public void invertBinaryTree() {
		
		// given
		int[] pre = {4, 2, 1, 3, 7, 6, 9};
		int[] answer = {4,7,2,9,6,3,1};
		TreeNode root = BinaryTreeUtil.fromPreOrder(pre, pre.length);
		InvertBinaryTree obj = new InvertBinaryTree();
		
		// then
		TreeNode invertedTree = obj.invertTree(root);
		assertArrayEquals(BinaryTreeUtil.toLevelorderArray(invertedTree), answer);	
	}
	
	@Test
	@Disabled
	public void sumOfLeftLeaves() {
		
		// given 
		// test-case-1
		int[] pre = {3,9,20,15,7};
		char[] preLN = {'N', 'L', 'N', 'L', 'L'}; 
		TreeNode root = BinaryTreeUtil.fromPreOrder(pre, preLN, pre.length);
		// test-case2
		int[] pre_2 = {1};
		char[] preLN_2 = {'L'}; 
		TreeNode root_2 = BinaryTreeUtil.fromPreOrder(pre_2, preLN_2, pre_2.length);
		
		// then 
		SumOfLeftLeaves obj = new SumOfLeftLeaves();
		assertEquals(24, obj.sumOfLeftLeaves(root));
		assertEquals(0, obj.sumOfLeftLeaves(root_2));
	}
	
	@Test
	@Disabled
	public void twoSumWithInputBST() {
		
		// given 
		// test-case1
		int[] pre = {5, 3, 2, 4, 6, 7};
		char[] preLN = {'N', 'N', 'L', 'L', 'N', 'L'};
		int k = 9;
		TreeNode root1 = BinaryTreeUtil.fromPreOrder(pre, preLN, pre.length);
		// test-case2
		int[] pre2 = {2,1,3};
		int k2 = 4;
		TreeNode root2 = BinaryTreeUtil.fromPreOrder(pre2, pre2.length);
		// test-case3
		int[] pre3 = {2, 1, 3};
		int k3 = 3;
		TreeNode root3 = BinaryTreeUtil.fromPreOrder(pre3, pre3.length);
		// then 
		TwoSumWithInputBST obj = new TwoSumWithInputBST();
		assertTrue(obj.findTarget(root1, k));
		assertTrue(obj.findTarget(root2, k2));
		assertTrue(obj.findTarget(root3, k3));
	}
	
	@Test
	@Disabled
	public void twoSumWithInputSortedArray() {
		
		// given 
		int[] numbers = {2,7,11,15};
		int target = 9;
		int[] ans = {1,2};
		
		// then
		TwoSumWithInputSortedArray obj = new TwoSumWithInputSortedArray();
//		int[] result = obj.twoSum(numbers, target);
//		Arrays.stream(result)
//		      .forEach(System.out::println);
		assertArrayEquals(ans, obj.twoSum(numbers, target));
		
	}
	
	@Test
	@Disabled
	public void implementStrStr() {
		// given 
		//test-case-1
		String haystack = "mississippi";
		String needle = "pi";
		int ans = 9;
		//test-case-2
		String haystack2 = "a";
		String needle2 = "a";
		int ans2 = 0;
		
		
		// then 
		ImplementStrStr obj = new ImplementStrStr();
		assertEquals(ans, obj.strStr(haystack, needle));
//		assertEquals(ans2, obj.strStr(haystack2, needle2));
		
		
	}
	
	@Test
	public void maximumDepthofBinaryTree() {
		
		// given 
		int[] pre = {3, 9, 20, 15, 7};
		char[] preLN = {'N', 'L', 'N', 'L', 'L'};
		int ans = 3;
		
		// then
		TreeNode root = BinaryTreeUtil.fromPreOrder(pre, preLN, pre.length);
		MaximumDepthofBinaryTree obj = new MaximumDepthofBinaryTree();
		assertEquals(ans, obj.maxDepth(root));
		
	}
	
	@Test
	@Disabled
	public void moveZeroes() {
		
		// given 
		int[] input = {0, 1, 0, 3, 12};
		int[] output = {1, 3, 12, 0, 0};
		
		// given 
		MoveZeroes obj = new MoveZeroes();
		obj.moveZeroes(input);
		assertArrayEquals(output, input);
		
		
	}

}
