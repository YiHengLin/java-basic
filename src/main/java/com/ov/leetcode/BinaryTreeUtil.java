package com.ov.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class BinaryTreeUtil {
	
	/**
	 * Create a binary tree from array with elements arranged in pre-order
	 * @param pre source array
	 * @param size size of source array
	 */
	public static TreeNode fromPreOrder(int[] pre, int size) {
		// preIndex initialed as 0
		return constructBinaryTreePreOrder(pre, 0, size-1, size);
	}

	/**
	 * Create a binary tree from array with elements arranged in pre-order, with leaf-node info
	 * @param pre source array
	 * @param preLN leaf-node info: 'L' = leaf node, 'N' = non leaf node
	 * @param size size of source array
	 */
	public static TreeNode fromPreOrder(int[] pre, char[] preLN, int size) {
		Index preIndex = new Index();
		return constructBinaryTreePreOrder(pre, preLN, preIndex, size);
	}

	/**
	 * Return the depth of specified tree
	 * @param root
	 */
	public static int getTreeHeight(TreeNode root) {
		if(root == null) return 0;
		
		int lHeight = getTreeHeight(root.left);
		int rHeight = getTreeHeight(root.right);
		if(lHeight > rHeight) {
			return lHeight+1;
		}else {
			return rHeight+1;
		}
	}
	
	/**
	 * Print specified tree in level-order
	 */
	public static void printLevelOrder(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		
		while(!q.isEmpty()) {
			TreeNode node = q.poll();
			System.out.print(node.val + " ");
			if(node.left != null) q.offer(node.left);
			if(node.right != null) q.offer(node.right);
		}
	}

	/**
	 * Print specified tree in pre-order
	 */
	public static void printPreorder(TreeNode root) {
		if (root == null) return;
		System.out.print(root.val + " ");
		printPreorder(root.left);
		printPreorder(root.right);
	}

	/**
	 * Print specified tree in in-order
	 */
	public static void printInorder(TreeNode root) {
		if (root == null) return;
		printInorder(root.left);
		System.out.print(root.val + " ");
		printInorder(root.right);
	}
	
	
	/**
	 *  Convert specified tree to array with element arranged in level-order
	 */
	public static int[] toLevelOrderArray(TreeNode node) {
		List<Integer> list = toLevelOrderList(node, new ArrayList<Integer>());
		return list.stream()
				   .mapToInt(Integer::intValue)
				   .toArray();
	}
	
	/**
     * Convert specified tree to list with element arranged in level-order
	 */
	public static List<Integer> toLevelOrderList(TreeNode root, List<Integer> list) {
		if (root == null) return null;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		
		q.offer(root);
		
		while(!q.isEmpty()) {
			TreeNode node = q.poll();
			list.add(node.val);
			if(node.left != null) q.offer(node.left);
			if(node.right != null) q.offer(node.right);
		}
		
		return list;
	}
	
	
	/**
	 * Convert specified tree to array with element arranged in pre-order
	 */
	public static int[] toPreOrderArray(TreeNode node) {
		List<Integer> list = toPreOrderList(node, new ArrayList<Integer>());
		return list.stream()
				   .mapToInt(Integer::intValue)
				   .toArray();
	}


	/**
	 * Convert specified tree to list with element arranged in pre-order
	 */
	public static List<Integer> toPreOrderList(TreeNode node, List<Integer> list) {
		if (node == null) return null;
		list.add(node.val);
		toPreOrderList(node.left, list);
		toPreOrderList(node.right, list);
		return list;
	}

	/**
	 * Convert specified tree to array with element arranged in in-order
	 */
	public static int[] toInOrderArray(TreeNode node) {
		List<Integer> list = toInOrderList(node, new ArrayList<Integer>());
		return list.stream()
				   .mapToInt(Integer::intValue)
				   .toArray();
	}
	
	/** 
	 * Convert specified tree to list with element arranged in in-order
	 */
	public static List<Integer> toInOrderList(TreeNode node, List<Integer> list) {
		if (node == null) return null;
		toInOrderList(node.left, list);
		list.add(node.val);
		toInOrderList(node.right, list);
		return list;
	}

	
	/**
	 * Construct a "perfect" binary tree from a pre-order array
	 *
	 * @param pre pre-order array
	 * @param preIndex tracking index for recursion
	 * @param high upper bound of sub-array
	 * @param size size of pre-order array
	 * @return binary tree
	 */
	private static TreeNode constructBinaryTreePreOrder(int[] pre, int preIndex, int high, int size) {
		
		if(preIndex > high ) return null; // rightmost leaf node
		if(preIndex == high) return new TreeNode(pre[preIndex]); // leaf node
		
		TreeNode root = new TreeNode(pre[preIndex]);
		
		// find the first element that larger than root,
		// it's index would be the root of right-subtree
		// and index-1 would be the last element of left-subtree
		int i;
		for(i=preIndex;i<high;++i) {
			if(pre[i] > root.val)
				break;
		}
		
		root.left = constructBinaryTreePreOrder(pre, preIndex+1, i-1, size);
		root.right = constructBinaryTreePreOrder(pre, i, high, size);
		return root;
	}
	
	/**
	 * Construct a binary tree (might be non-perfect) from a pre-order array with leaf-node info "preLN"
	 *
	 * @param pre pre-order array
	 * @param preLN 'N'= non-leaf node; 'L' = leaf-node
	 * @param preIndex tracking index in recursive calls
	 * @param size
	 * @return
	 */
	private static TreeNode constructBinaryTreePreOrder(int[] pre, char[] preLN, Index preIndex, int size) {
		// base case
		if(preIndex.index >= size ) return null;
		
		int currentIndex = preIndex.index; // we need to store current index, for creating node
		
		TreeNode root = new TreeNode(pre[currentIndex]);
		preIndex.index ++; // keep tracking recursive index for further recursive calls
		if(preLN[currentIndex] == 'N') {
			root.left = constructBinaryTreePreOrder(pre, preLN, preIndex, size);
			root.right = constructBinaryTreePreOrder(pre, preLN, preIndex, size);
		}
		return root;
	}

}
