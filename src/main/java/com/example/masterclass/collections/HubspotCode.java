package com.example.masterclass.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HubspotCode {

	public static void main(String[] args) {
		sortArray();
	}
	
	/**
	 * a = [1, 3, 5]
b = [2, 6, 8, 9]
max_array_length = 3

=> [1, 2, 3]

	 */
	
	public static void sortArray() {
		List<Integer> list1 = Arrays.asList(1, 1, 1);
		List<Integer> list2 = Arrays.asList(2, 5, 6, 6);
		
		int maxSize = 5;
		
		List<Integer> newList = new ArrayList<Integer>();
		
		int idx2 = 0;
		int idx1 = 0;
		
		int x = 0;
		int y = 0;
		
		while ( newList.size() <= maxSize ) {
			
			if ( list1.size() > idx1)
				x = list1.get(idx1);	
			else {
				// fill up the list with list2 items
				System.out.println("list1 exhausted. filling with list2");
				while (newList.size() < maxSize)
					newList.add(list2.get(idx2++));
				break;
			}
						
			if ( list2.size() > idx2 )
				y = list2.get(idx2);
			else {
				// fill up the list with list1 items
				System.out.println("list2 exhausted. filling with list1");
				while (newList.size() < maxSize)
					newList.add(list1.get(idx1++));
				break;
			}
			
			System.out.println("Comparing " + x + " to " + y + " list1 index=" + idx1 + " list2 index="+idx2);

			if (x < y) {
				newList.add(x);
				idx1++;
			} else {
				newList.add(y);
				idx2++;
			}

		}
		
		/**
		for (Integer x : list1) {
			int y = 0;
			
			if (idx2 < list2.size())
				y = list2.get(idx2);
			
			System.out.println("Comparing " + x + " to " + y + " list1 index=" + idx1 + " list2 index="+idx2);
		
			if ( x <= y) {
				newList.add(x);
				x = list1.get(idx1++);
			}
			else {
				newList.add(y);
				if (idx2 < list2.size())
					y = list2.get(idx2++);
			}
			
			if (newList.size() > maxSize)
				break;
		}
		
		if (newList.size() < maxSize) {

			while (newList.size() < maxSize)
				// See which idx is larger
				if (idx1 < idx2)
					newList.add(list1.get(idx1++));
		}
		*/
		
		newList.forEach( z -> System.out.println( z ));
		
	}

}
