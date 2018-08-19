package com.ov.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Illustrations of Lambda Expressions and Streams </br>
 * 
 * @see <b>Share mutability:</b> http://henrikeichenhardt.blogspot.com/2013/06/why-shared-mutable-state-is-root-of-all.html
 */
public class LambdaExpressionsAndStream {
	
	public static void main(String[] args) {
		
		/*
		 * Lambda Expressions 
		 */
		
		List<Integer> numbers = Arrays.asList(1, 2, 3);
		
		// External iterator
		// Imperative style, familiar but not simple.
		System.out.println("iteration with for each loop:");
		for(int e : numbers) {
			System.out.println(e);
		}
		
		// External iterator
		// forEach takes function interface Consumer<T>, here passes a anonymous class, may seem unclear.
		System.out.println("\niteration with anonymous class:");
		numbers.forEach(new Consumer<Integer>() {
			@Override
			public void accept(Integer t) {
				System.out.println(t);
			}
		});
		
		// Lambda expressions enable you to treat functionality as method argument and express instances of single-method classes more compactly.
		// Here Consumer<T> is the "target type" of lambda expression
		// Replace anonymous class with lambda expressions
		System.out.println("\niteration with lambda expression:");
		numbers.forEach(value -> System.out.println(value));
		
		// Replace lambda expressions with method reference. 
		// Passing in a function, not invoking a function.
		System.out.println("\niteration with method reference:");
		numbers.forEach(System.out::println);
		
		// ---------------------------------------------------------------------------------------------------------------------------------------
		
		/*
		 * Stream<T> = functional-style + abstraction
		 */
		// Stream is not a data structure that store elements, but conveys elements from source through a pipeline of computational operations.
		// Stream operations are composed into a stream pipeline, consisting of a source, zero or more intermediate operations, and a terminal operation
		
		// Try to: double the even values and put that into a list
		List<Integer> numbers_2 = Arrays.asList(1,2,3,4,5,1,2,3,4,5);
		
		// Wrong way to do this.
		List<Integer> doubleOfEven = new ArrayList<>();
		numbers_2.stream()
				 .filter(e -> e % 2 == 0)
				 .map(e -> e * 2)
				 .forEach(e -> doubleOfEven.add(e)); // numbers_2 and doubleOfEven share mutability, which is bad when performing parallel processing.
		
		// Right way to do this.
		// Note that streams are lazy. Computation on the source data is only performed when the terminal operation is initiated.
		// Tips: if a function returns a stream, a function is lazy-evaluation, if function return a non-stream, it's eager-evaluation
		List<Integer> doubleOfEven_2 = numbers_2.stream()           
											   .filter(e -> e % 2 == 0)  // lazy
											   .map(e -> e * 2)          // lazy
											   .collect(Collectors.toList()); // terminal operation
		System.out.println("\nStream example_1 : doubleOfEven_2= " + doubleOfEven_2);

		
		// Try to: Given a number k and a count n, find the total of double of n even
		// number starting with k, where square root of each number is > 20
		
		// imperative way 
		System.out.printf("\nStream example_2 imperative way : " + computeImperativeWay(20, 11));
		// using stream 
		System.out.println("\nStream example_2 using stream   : " + computeUsingStream(20, 11));
	}
	
	
	public static int computeImperativeWay(int k, int n){
	    int result = 0;
	    int index = k;
	    int count = 0;
	    while(count < n){
	        if(index % 2 == 0 && Math.sqrt(index) > 20){
	            result += index * 2;
	            count++;
	        }
	        index++;
	    }
	    return result;
	}
	
	public static int computeUsingStream(int k, int n){
	    return Stream.iterate(k, e -> e + 1)    // unbounded, lazy
	           .filter(e -> e % 2 == 0)         // unbounded, lazy
	           .filter(e -> Math.sqrt(e) > 20)  // unbounded, lazy
	           .mapToInt(e -> e * 2)            // unbounded, lazy
	           .limit(n)                        // sized, lazy
	           .sum();
	}
}
