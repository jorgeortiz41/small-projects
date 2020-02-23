package edu.uprm.cse.datastructures.cardealer.model;

import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;

public class CarList {
	private static final CircularSortedDoublyLinkedList<Car> CarList = new CircularSortedDoublyLinkedList<Car>(new CarComparator());
	//creates the car list which the cars will be added and manipulated
	
	public static CircularSortedDoublyLinkedList<Car> getInstance(){
		//returns the current car list
		return CarList;
		
	}
	
	public static void resetCars() {
		//clears all cars in the car list
		CarList.clear();
	}

}
