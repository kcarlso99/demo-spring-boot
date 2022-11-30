package com.example.masterclass.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchAlgorithms {

	public static void main(String[] args) {
		String pattern = "AAABAAA";
		String text = "ASBNSAAAAAABAAAAABAAAAAGAHUHDJKDDKSHAAJF";

		List<Integer> foundIndexes = SearchAlgorithms.patternSearch(text, pattern);
		if (foundIndexes.isEmpty()) {
		    System.out.println("Pattern not found in the given text String");
		} else {
		    System.out.println("Pattern found in the given text String at positions: " + 
		    		foundIndexes
		    		.stream()
		    		.map(Object::toString)
		    		.collect(Collectors.joining(", ")));
		}

	}
	
	/**
	 * compiled pattern array stores the pattern of characters in the pattern. T
	 * Finds the prefix and suffix in the pattern. If we know prefix/suffix, we can avoid comparing from the start of the text and just compare next character after the mismatch has occurred.
	 * The compiled array stores the index position of previous occurrence of the current character in the pattern array.
	 * @param pattern
	 * @return
	 */
	public static int[] compilePatternArray(String pattern) {
	    int patternLength = pattern.length();
	    int len = 0;
	    int i = 1;
	    int[] compliedPatternArray = new int[patternLength];
	    compliedPatternArray[0] = 0;

	    while (i < patternLength) {
	        if (pattern.charAt(i) == pattern.charAt(len)) {
	            len++;
	            compliedPatternArray[i] = len;
	            i++;
	        } else {
	            if (len != 0) {
	                len = compliedPatternArray[len - 1];
	            } else {
	                compliedPatternArray[i] = len;
	                i++;
	            }
	        }
	    }
	    System.out.println("Compiled Pattern Array " + Arrays.toString(compliedPatternArray));
	    return compliedPatternArray;
	}
	
	/**
	 * Pattern Search. Determine if input text contains input patter
	 */
	public static List<Integer> patternSearch(String text, String pattern) {
	    int[] compliedPatternArray = compilePatternArray(pattern);

	    int textIndex = 0;
	    int patternIndex = 0;

	    List<Integer> foundIndexes = new ArrayList<>();

	    while (textIndex < text.length()) {
	    	// Same char at patterIndex and textIndex
	        if (pattern.charAt(patternIndex) == text.charAt(textIndex)) {
	            patternIndex++;
	            textIndex++;
	        }
	        
	        if (patternIndex == pattern.length()) {
	            foundIndexes.add(textIndex - patternIndex);
	            patternIndex = compliedPatternArray[patternIndex - 1];
	        }
	        else if (textIndex < text.length() && pattern.charAt(patternIndex) != text.charAt(textIndex)) {
	            if (patternIndex != 0)
	                patternIndex = compliedPatternArray[patternIndex - 1];
	            else
	                textIndex = textIndex + 1;
	        }
	    }
	    return foundIndexes;
	}

}
