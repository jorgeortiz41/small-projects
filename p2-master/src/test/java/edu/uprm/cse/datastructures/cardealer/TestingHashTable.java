package edu.uprm.cse.datastructures.cardealer;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.util.HashTableOA;
import edu.uprm.cse.datastructures.cardealer.util.SortedList;

public class TestingHashTable {

	class StringComparator implements Comparator<String>{

		@Override
		public int compare(String o1, String o2) {
			return o1.compareToIgnoreCase(o2);
		}
		
	}
	@Test
	public void test() {
		
		
		HashTableOA<String,String> hashtable = new HashTableOA<String,String>(new StringComparator(),new StringComparator());
		
		
		assertTrue("Hash table should be empty", hashtable.isEmpty());
		hashtable.put("Alberto", "Cruz");
		hashtable.put("Andrew", "Gonzalez");
		hashtable.put("Francisco", "Bruh");
		hashtable.put("Ye", "Skrt");
		assertFalse("Hash table should be empty", hashtable.isEmpty());
		assertTrue("Should recieve Skrt", hashtable.get("Ye").equals("Skrt"));
		hashtable.put("Roberto", "Olivo");
		hashtable.put("Al", "Varado");
		hashtable.put("Maria", "Soto");
		hashtable.put("Ernesto", "Rio");
		hashtable.put("Raul", "Maldonado");
		assertTrue("It should not be found" , hashtable.get("Remi") == null);
		
		hashtable.remove("Roberto");
		assertTrue("Size should be 8", hashtable.size() == 8);
		hashtable.put("Roberto", "Olivo");
		hashtable.put("Roberto", "Deniro");
		
		hashtable.put("Alexander", "Jimenez");
		hashtable.put("Alondra", "Soto");
		
		String[] inOrder = new String[hashtable.getKeys().size()];
		SortedList<String> results= hashtable.getKeys();
		for (int i = 0; i < results.size(); i++) {
			inOrder[i] = results.get(i);
			
		}
		 assertArrayEquals("CarTable not in order", new String[]{"Al", "Alberto","Alexander", "Alondra", "Andrew", "Ernesto","Francisco","Maria",
				 "Raul" , "Roberto", "Ye" }, inOrder);
	}

	@Test
	public void test2() {
		HashTableOA<String,String> hashtable = new HashTableOA<String,String>(new StringComparator(),new StringComparator());
		
		Random r = new Random();

	    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()";
	    
	    for (int i = 0; i < 10000; i++) {
	    	String rndkey = "";
	    	String rndvlue = "";
	    	for (int j = 0; j < 15; j++) {
	    		rndkey+=alphabet.charAt(r.nextInt(alphabet.length()));
		    }
	    	for (int j = 0; j < 15; j++) {
	    		rndvlue+=alphabet.charAt(r.nextInt(alphabet.length()));
		    }
	    	hashtable.put(rndkey, rndvlue);
		}
	    
	    
	    assertTrue("Size should be 10000", hashtable.size() == 10000);
	    
	    
	    
	    
	    
	    
	}
}
