package edu.uprm.cse.datastructures.cardealer.model;

import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;
import edu.uprm.cse.datastructures.cardealer.util.HashTableOA;

public class CarTable {
	private static final HashTableOA<Long, Car> CarTable = new HashTableOA<Long, Car>(new LongComparator(), new CarComparator());
	//creates the car list which the cars will be added and manipulated
	
	public static HashTableOA<Long, Car> getInstance(){
		//returns the current car list
		return CarTable;
		
	}
	
	public static void resetCars() {
		//clears all cars in the car list
		while(!CarTable.isEmpty()) {
			Long[] temp = new Long[CarTable.getKeys().size()];
			for (int i = 0; i < temp.length; i++) {
				temp[i] = CarTable.getKeys().get(i);
			}
			for (int i = 0; i < temp.length; i++) {
				CarTable.remove(temp[i]);
			}
		}
	}

}
