package com.ov.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class BinaryTreeUtil {
	
	/*
	 * create a binary tree from array in pre-order 
	 */
	public static TreeNode fromPreOrder(int[] pre, int size) {
		// preIndex initialed as 0
		return constructBinaryTreePreOrder(pre, 0, size-1, size);
	}
	
	/**
	 * create a binary tree from array in pre-order with leaf-node info
	 */
	public static TreeNode fromPreOrder(int[] pre, char[] preLN, int size) {
		Index preIndex = new Index();
		return constructBinaryTreePreOrder(pre, preLN, preIndex, size);
	}
	
	
	public static int getTreeheight(TreeNode root) {
		if(root == null) return 0;
		
		int lheight = getTreeheight(root.left);
		int rheight = getTreeheight(root.right);
		if(lheight > rheight) {
			return lheight+1;
		}else {
			return rheight+1;
		}
	}
	
	/*
	 * Print Tree 
	 */
	public static void printLevelOrder(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		
		while(!q.isEmpty()) {
			TreeNode node = q.poll();
			System.out.print(node.val + " ");
			if(node.left != null) q.offer(node.left);
			if(node.right != null) q.offer(node.right);
		}
	}
	
	public static void printPreorder(TreeNode root) {
		if (root == null) return;
		System.out.print(root.val + " ");
		printPreorder(root.left);
		printPreorder(root.right);
	}
	
	public static void printInorder(TreeNode root) {
		if (root == null) return;
		printInorder(root.left);
		System.out.print(root.val + " ");
		printInorder(root.right);
	}
	
	
	/*
	 *  Convert tree to level-order array 
	 */
	public static int[] toLevelorderArray(TreeNode node) {
		List<Integer> list = toLevelorderList(node, new ArrayList<Integer>());
		return list.stream()
				   .mapToInt(Integer::intValue)
				   .toArray();
	}
	
	/**
     * Convert binary tree to level-order list 
	 */
	public static List<Integer> toLevelorderList(TreeNode root, List<Integer> list) {
		if (root == null) return null;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		
		q.offer(root);
		
		while(!q.isEmpty()) {
			TreeNode node = q.poll();
			list.add(new Integer(node.val));
			if(node.left != null) q.offer(node.left);
			if(node.right != null) q.offer(node.right);
		}
		
		return list;
	}
	
	
	/*
	 * Convert binary tree to pre-order array  
	 */
	public static int[] toPreorderArray(TreeNode node) {
		List<Integer> list = toPreorderList(node, new ArrayList<Integer>());
		return list.stream()
				   .mapToInt(Integer::intValue)
				   .toArray();
	}
	
	
	/*
	 *  Convert binary tree to pre-order list 
	 */
	public static List<Integer> toPreorderList(TreeNode node, List<Integer> list) {
		if (node == null) return null;
		list.add(new Integer(node.val));
		toPreorderList(node.left, list);
		toPreorderList(node.right, list);
		return list;
	}
	
	public static int[] toInorderArray(TreeNode node) {
		List<Integer> list = toInderList(node, new ArrayList<Integer>());
		return list.stream()
				   .mapToInt(Integer::intValue)
				   .toArray();
	}
	
	/** 
	 * Convert binary tree to in-order list 
	 */
	public static List<Integer> toInderList(TreeNode node, List<Integer> list) {
		if (node == null) return null;
		toInderList(node.left, list);
		list.add(new Integer(node.val));
		toInderList(node.right, list);
		return list;
	}
	
	
	
	/**
	 * Construct a "perfect" binary tree from a pre-order array
	 *
	 * @param pre pre-order array
	 * @param preIndex tracking current recursive index
	 * @param high upper bound of sub-array
	 * @param size size of pre-order array
	 * @return binary tree
	 *
	 * @author Henry.Lin
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
	 *
	 * @author Henry.Lin
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
