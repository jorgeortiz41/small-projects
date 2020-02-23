package edu.uprm.cse.datastructures.cardealer.util;

import java.util.Comparator;
import java.util.List;

public class HashTableOA<K, V> implements Map<K, V> {

	public static class MapEntry<K,V> {
		private K key;
		private V value;
		public K getKey() {
			return key;
		}
		public void setKey(K key) {
			this.key = key;
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
		public MapEntry(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}


	}

	private int currentSize;
	private Object[] buckets;
	private Comparator<K> keycmp;
	private Comparator<V> valcmp;

	private static final int DEFAULT_BUCKETS = 10;

	private int hashFunction(K key, int size) {
		return Math.abs(key.hashCode() % size);
	}

	private int hashFunction2(K key, int size) {
		return Math.abs((int) (Math.pow(key.hashCode(), 2) % size));
	}

	public HashTableOA(int numBuckets, Comparator<K> keyComp, Comparator<V> valComp) {
		this.currentSize  = 0;//not sure
		this.buckets = new Object[numBuckets];
		this.keycmp = keyComp;
		this.valcmp = valComp;
	}

	public HashTableOA(Comparator<K> keyComp, Comparator<V> valComp) {
		this(DEFAULT_BUCKETS, keyComp, valComp);
	}
	@Override
	public int size() {
		return this.currentSize;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public V get(K key) {
		int targetBucket = this.hashFunction(key, this.buckets.length);
		MapEntry<K,V> target = (MapEntry<K, V>) this.buckets[targetBucket];
		if(this.buckets[targetBucket] == null || !key.equals(((MapEntry<K, V>) this.buckets[targetBucket]).getKey()) ) {
			target = (MapEntry<K, V>) this.buckets[targetBucket];
			targetBucket = this.hashFunction2(key, this.buckets.length);
			if(this.buckets[targetBucket] == null || !key.equals(((MapEntry<K, V>) this.buckets[targetBucket]).getKey()) ) {
				for(int i = (this.hashFunction(key, this.buckets.length)+1)% this.buckets.length; 
						i != this.hashFunction(key, this.buckets.length); i = (i+1)%this.buckets.length) {
					target = (MapEntry<K, V>) this.buckets[i];
					if(this.buckets[i] != null && 
							((MapEntry<K, V>) this.buckets[i]).getKey().equals(key)) {
						return ((MapEntry<K, V>) this.buckets[i]).getValue();
					}
				}
				return null;
			}
		}
		return ((MapEntry<K, V>) buckets[targetBucket]).getValue();

	}

	@Override
	public V put(K key, V value) {
		V oldValue = this.get(key);
		if (oldValue != null) {
			this.remove(key);
		}
		float porcentage  = ((float)this.currentSize)/this.buckets.length;
		//if((((float)this.currentSize)/this.buckets.length) >= 0.7) {
		if(porcentage >= 0.7) {
			reAllocate();
		}
		int targetBucket = this.hashFunction(key, this.buckets.length);	
		if(buckets[targetBucket] != null) {
			targetBucket = this.hashFunction2(key, this.buckets.length);
			if(buckets[targetBucket] != null) {
				for(int i = (this.hashFunction(key, this.buckets.length)+1)% this.buckets.length; 
						i != this.hashFunction(key, this.buckets.length); i = (i+1)%this.buckets.length) {
					if(this.buckets[i] == null) {
						targetBucket = i;
						break;
					}
				}
			}		
		}
		this.currentSize++;
		this.buckets[targetBucket] = new MapEntry<K, V>(key, value);
		return oldValue;

	}

	private void reAllocate() {
		Object[] L = new Object[this.buckets.length*2];
		for (int i = 0; i < this.buckets.length; i++) {
			if(this.buckets[i]!=null) {
				int targetBucket = this.hashFunction(((MapEntry<K, V>) this.buckets[i]).getKey(), L.length);	
				if(L[targetBucket] != null) {
					targetBucket = this.hashFunction2(((MapEntry<K, V>) this.buckets[i]).getKey(), L.length);
					if(L[targetBucket] != null) {
						for(int j = (this.hashFunction(((MapEntry<K, V>) this.buckets[i]).getKey(), L.length)+1)% L.length; 
								j != this.hashFunction(((MapEntry<K, V>) this.buckets[i]).getKey(), L.length); j = (j+1)%L.length) {
							if(L[j] == null) {
								targetBucket = j;
								break;
							}
						}
					}		
				}
				L[targetBucket]= this.buckets[i];
			}
		}
		this.buckets = L;
	}

	@Override
	public V remove(K key) {
		int targetBucket = this.hashFunction(key, this.buckets.length);
		if(this.buckets[targetBucket] != null && !key.equals(((MapEntry<K, V>) this.buckets[targetBucket]).getKey()) ) {
			targetBucket = this.hashFunction2(key, this.buckets.length);
			if(this.buckets[targetBucket] != null && !key.equals(((MapEntry<K, V>) this.buckets[targetBucket]).getKey()) ) {
				for(int i = (this.hashFunction(key, this.buckets.length)+1)% this.buckets.length; 
						i != this.hashFunction(key, this.buckets.length); i = (i+1)%this.buckets.length) {
					if(((MapEntry<K, V>) this.buckets[i]).getKey().equals(key)) {
						V val =  ((MapEntry<K, V>) buckets[targetBucket]).getValue();
						this.buckets[targetBucket] = null;
						currentSize--;
						return val;	
					}
				}
				return null;
			}
		}
		V val =  ((MapEntry<K, V>) buckets[targetBucket]).getValue();
		this.buckets[targetBucket] = null;
		currentSize--;
		return val;	
	}

	@Override
	public boolean contains(K key) {
		return this.get(key) != null;
	}

	@Override
	public SortedList<K> getKeys() {
		SortedList<K> result = new CircularSortedDoublyLinkedList<K>(keycmp);
		for (int i = 0; i < buckets.length; i++) {
			if(this.buckets[i] != null) {
				result.add(((MapEntry<K, V>) this.buckets[i]).getKey());
			}
		} 
		return result;
	}

	@Override
	public SortedList<V> getValues() {
		SortedList<V> result = new CircularSortedDoublyLinkedList<V>(valcmp);
		for (int i = 0; i < buckets.length; i++) {
			if(this.buckets[i] != null) {
				result.add(((MapEntry<K, V>) this.buckets[i]).getValue());
			}
		} 
		return result;
	}

}
