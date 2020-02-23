package edu.uprm.cse.datastructures.cardealer.model;

import java.util.Comparator;

public class LongComparator implements Comparator<Long> {

	@Override
	public int compare(Long l1, Long l2) {
		if(l1==l2) {
			return 0;
		}
		else if(l1>l2) {
			return 1;
		}
		return -1;
	}

}
