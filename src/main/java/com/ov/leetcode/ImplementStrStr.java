package com.ov.leetcode;

/**
 * No.28 - Return the index of the first occurrence of needle in haystack, or -1
 * if needle is not part of haystack.
 */
public class ImplementStrStr {
	public int strStr(String haystack, String needle) {
		
		if(needle.isEmpty()) return 0;
		if(haystack.isEmpty()) return -1;
		
		char[] h = haystack.toCharArray();
		char[] n = needle.toCharArray();
		
		int i=0;
		while(i <= (h.length-n.length)) {
			if(h[i] == n[0]) {
				for(int l=i, k=0; k <= n.length ; l++, k++) {
					if(k == n.length) return i;
					if(h[l] != n[k]) break;
				}
			}
			i++;
		}
		
		return -1;
	}
}
