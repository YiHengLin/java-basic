package com.ov.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * No. 404: Find the sum of all left leaves in a given binary tree.
 */
public class SumOfLeftLeaves {

	public int sumOfLeftLeaves(TreeNode root) {
		
		if(root == null) return 0;
		int result = 0;
		
		if(root.left == null && root.right == null) {
			return 0;
		}
		
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);

		while(!q.isEmpty()) {
			TreeNode node = q.poll();
			
			if(node.left != null) {
				q.offer(node.left);
				if(node.left.left == null && node.left.right == null) {
					result += node.left.val;
				}
			}
			
			if(node.right != null) {
				q.offer(node.right);
			}
		}
		
		return result;
	}
}
