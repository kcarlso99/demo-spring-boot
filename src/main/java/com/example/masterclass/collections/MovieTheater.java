package com.example.masterclass.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MovieTheater {
	
	private String theaterName;
	private List<Seat> seats = new ArrayList<Seat>();
	
	// Comparator for Price sort
	// The return value of 0 must mean the objects are equal. In this case, 2 unrelated seats could have same price so breaks this rule.
	static final Comparator<Seat> PRICE_SORT = new Comparator<Seat>() {
		@Override
		public int compare(Seat s1, Seat s2) {
			return Double.valueOf(s1.getPrice()).compareTo( Double.valueOf(s2.getPrice()) );
		}	
	};

	public MovieTheater(String theaterName, int rows, int seatsInRow) {
		this.theaterName = theaterName;

		int lastRow = 'A' + rows-1;
		for (char row = 'A'; row <= lastRow; row++) {
			for (int seatNo = 1; seatNo <= seatsInRow; seatNo++) {
				double price = 12.00;
				if (row >= 'C' && seatNo >=5 && seatNo <= 8) {
					price = 14.50; // more expensive farther back and in middle
				}
				Seat seat = new Seat(row + String.format("%02d", seatNo), price);
				seats.add(seat);
			}
		}
	}
	
	/**
	 * Binary search is the fastest way to find an element in a list.
	 * Starts search in middle of list then continues to break list in half until item found.
	 * @param seatNumber
	 * @return
	 */
	public boolean reserveSeat(String seatNumber) {
		Seat requestedSeat = new Seat(seatNumber, 0);
		int found = Collections.binarySearch(seats, requestedSeat, null);
		if (found >= 0) {
			return seats.get(found).reserve();
		}
		else {
			System.out.println(seatNumber + " does not exist.");
			return false;
		}
	}
	
	public String getTheaterName() {
		return theaterName;
	}
	
	public void setName(String name) {
		this.theaterName = name;
	}
	
	public void printSeats() {
		for (Seat seat : seats) {
			System.out.print(seat.seatNumber + "=$"+seat.getPrice()+" ");
		}
		System.out.println();
	}

	private class Seat implements Comparable<Seat> {
		private String seatNumber;
		private boolean reserved = false;
		double price = 14.00;

		public Seat(String seatNumber, double price) {
			this.seatNumber = seatNumber;
			this.price = price;
		}
		
		public double getPrice() {
			return price;
		}
		
		public boolean reserve() {
			if (reserved) {
				System.out.println(seatNumber + " already reserved.");
				return false;
			}
			else {
				System.out.println(seatNumber + " reserved. Congrats!");
				reserved = true;
			}
			
			return true;
		}

		@Override
		public int compareTo(Seat seat) {
			return this.seatNumber.compareToIgnoreCase(seat.seatNumber);
		}
		
	}

	public List<Seat> getSeats() {
		return seats;
	}
	
	public int getNbrSeats() {
		return seats.size();
	}
	

}
