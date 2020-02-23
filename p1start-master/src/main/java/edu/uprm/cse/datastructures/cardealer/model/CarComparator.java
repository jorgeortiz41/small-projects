package edu.uprm.cse.datastructures.cardealer.model;

import java.util.Comparator;

public class CarComparator implements Comparator<Car> {

	@Override
	public int compare(Car o1, Car o2) {
		//compares cars based on brand, model and model option in that exact order
		if(o1.equals(o2)) {
			return 0;
		}
		else if(o1.getCarBrand().compareTo(o2.getCarBrand()) == 0) {
			if(o1.getCarModel().compareTo(o2.getCarModel()) == 0) {
				return o1.getCarModel().compareTo(o2.getCarModelOption());
			}
			else {
				return o1.getCarModel().compareTo(o2.getCarModel());
			}
		}
		else {
			return o1.getCarBrand().compareTo(o2.getCarBrand());
		}
	}

}
