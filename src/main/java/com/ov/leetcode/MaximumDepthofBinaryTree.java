package com.ov.leetcode;

/**
 * No.104 - Given a binary tree, find its maximum depth
 */
public class MaximumDepthofBinaryTree {

	public int maxDepth(TreeNode root) {
		if(root == null) return 0;
		
		int leftDepth = maxDepth(root.left);
		int rightDepth = maxDepth(root.right);
		
		if(leftDepth > rightDepth) {
			return leftDepth + 1; 
		}else {
			return rightDepth + 1;
		}
	}
}
