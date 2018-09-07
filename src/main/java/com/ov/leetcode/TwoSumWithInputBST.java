package com.ov.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * No. 653 Given a Binary Search Tree and a target number, return true if there
 * exist two elements in the BST such that their sum is equal to the given
 * target.
 * 
 * Time complexity: O(n), space complexity: O(n)
 */
public class TwoSumWithInputBST {

	public boolean findTarget(TreeNode root, int k) {
		Set<Integer> set = new HashSet<>();
		return bfs(root, set, k);
	}

	private boolean bfs(TreeNode root, Set<Integer> set, int k) {
		if(root == null) return false;
		if(set.contains(k - root.val)) return true;
		set.add(root.val);
		return bfs(root.left, set, k) || bfs(root.right, set, k);
	}

}
