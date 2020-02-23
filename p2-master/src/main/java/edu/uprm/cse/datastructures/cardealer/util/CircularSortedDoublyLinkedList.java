package edu.uprm.cse.datastructures.cardealer.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularSortedDoublyLinkedList<E> implements SortedList<E> {

	private static class Node<E>{
		private E element;
		private Node<E> next;
		private Node<E> prev;
		
		
		//constructor node class with getters and setters for the elements and previous or next
		public Node() {
			this.element = null;
			this.next = this.prev = null;
		}
		public E getElement() {
			return element;
		}
		public void setElement(E element) {
			this.element = element;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
		public Node<E> getPrev() {
			return prev;
		}
		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}
	}

	private Node<E> header;
	private int currentSize;
	private Comparator<E> comp;
	
	
	//constructor for list
	public CircularSortedDoublyLinkedList(Comparator<E> cmp) {
		this.currentSize = 0;
		this.header = new Node<>();

		this.header.setNext(this.header);
		this.header.setPrev(this.header);

		this.comp = cmp;

	}
	
	//iterator for the list
	public Iterator<E> iterator(int index) {
		return new CircularSortedDoublyLinkedListIterator<E>(index);
	}

	@Override
	public Iterator<E> iterator() {
		return new CircularSortedDoublyLinkedListIterator<E>();
	}
	public Iterator<E> reverseiterator() {
		return new CircularSortedDoublyLinkedListReverseIterator<E>();
	}
	public Iterator<E> reverseiterator(int index) {
		return new CircularSortedDoublyLinkedListReverseIterator<E>(index);
	}

	private class  CircularSortedDoublyLinkedListIterator<E> implements Iterator<E>{
		private Node<E> nextNode;
		private Node<E> previousNode;
		private int indx;

		public  CircularSortedDoublyLinkedListIterator() {
			this.nextNode = (Node<E>) header.getNext();
			this.indx = 0;

		}
		public  CircularSortedDoublyLinkedListIterator(int index) {
			int count = 0;
			this.nextNode = (Node<E>) header;
			while(count <= index) {
				this.nextNode = this.nextNode.getNext();
				count++;
			}
			this.indx = index;

		}
		@Override
		public boolean hasNext() {
			return nextNode.element != null;
		}


		@Override
		public E next() {
			if (this.hasNext()) {
				E result = this.nextNode.getElement();
				this.nextNode = this.nextNode.getNext();
				return result;
			}
			else {
				throw new NoSuchElementException();
			}
		}


	}
	private class  CircularSortedDoublyLinkedListReverseIterator<E> implements Iterator<E>{
		private Node<E> nextNode;
		private Node<E> previousNode;
		private int indx;

		public  CircularSortedDoublyLinkedListReverseIterator() {
			this.nextNode = (Node<E>) header.getPrev();
			this.indx = 0;

		}
		public  CircularSortedDoublyLinkedListReverseIterator(int index) {
			int count = 0;
			this.nextNode = (Node<E>) header;
			while(count <= index) {
				this.nextNode = this.nextNode.getNext();
				count++;
			}

			this.indx = index;

		}
		@Override
		public boolean hasNext() {
			return nextNode.element != null;
		}


		@Override
		public E next() {
			if (this.hasNext()) {
				E result = this.nextNode.getElement();
				this.nextNode = this.nextNode.getPrev();
				return result;
			}
			else {
				throw new NoSuchElementException();
			}
		}


	}

	@Override
	public boolean add(E obj) {
		
		//adds object to the list
		if(obj == null) {
			return false;
		}
		Node<E> newNode = new Node<E>();
		newNode.setElement(obj);
		
		//if list is empty it adds element next to header
		if(this.isEmpty()) {
			this.header.setNext(newNode);
			this.header.setPrev(newNode);
			newNode.setNext(this.header);
			newNode.setPrev(this.header);
			this.currentSize++;
			return true;
		}
		Node<E> temp = this.header.getNext();
		newNode.setElement(obj);
		boolean x = true;
		while(x) {
			
			//if the first element is same as new element then add it right next to first element
			if(this.comp.compare(temp.getElement(),  newNode.getElement())== 0) {
				newNode.setNext(temp);
				newNode.setPrev(temp.getPrev());
				temp.getPrev().setNext(newNode);
				temp.setPrev(newNode);
				this.currentSize++;
				return true;
			}
			
			//if first element is less than new element then add it after the first element
			else if(this.comp.compare(temp.getElement(), newNode.getElement())<0 && temp.getNext().getElement() == null){
				newNode.setNext(header);
				this.header.setPrev(newNode);
				temp.setNext(newNode);
				newNode.setPrev(temp);
				this.currentSize++;
				return true;

			}
			
			//if first element is greater than new element then add it before the first element
			else if(temp.getPrev().getElement() == null && this.comp.compare(temp.getElement(), newNode.getElement())>0) {
				newNode.setNext(temp);
				this.header.setNext(newNode);
				newNode.setPrev(header);
				temp.setPrev(newNode);
				this.currentSize++;
				return true;
			}
			
			//if new element is greater than first element but smaller than the following element then add in between
			else if(this.comp.compare(temp.getElement(), newNode.getElement())<0 && this.comp.compare(temp.getNext().getElement(), newNode.getElement())>0) {
				newNode.setNext(temp.getNext());
				temp.getNext().setPrev(newNode);
				temp.setNext(newNode);
				newNode.setPrev(temp);
				this.currentSize++;
				return true;
			}
			
			//in case it doesn't meet the previous requirements just add after first element
			else if(temp.getNext().getElement() == null) {
				temp.setNext(newNode);
				newNode.setPrev(temp);
				newNode.setNext(header);
				this.header.setPrev(newNode);
				this.currentSize++;
				return true;
			}

			temp = temp.getNext();
		}
		return false;
	}

	@Override
	public int size() {
		
		//return list size
		return this.currentSize;
	}

	@Override
	public boolean remove(E obj) {
		
		//remove object from list,given an object, by iterating through the list and checking each element to see if it matches with the input
		if(obj == null) {
			return false;
		}
		Node<E> temp = this.header.getNext();

		while(true) {
			if(this.comp.compare(temp.getElement(), obj)==0) {
				temp.getPrev().setNext(temp.getNext());
				temp.getNext().setPrev(temp.getPrev());
				temp.setNext(null);
				temp.setPrev(null);
				temp.setElement(null);
				this.currentSize--;
				return true;
			}
			else if(temp.getNext().getElement() == null) {
				return false;
			}
			temp = temp.getNext();
		}
	}

	@Override
	public boolean remove(int index) {
		
		//remove object from list given an index
		if(index <0 || this.size() <= index) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> temp = this.header.getNext();
		while(index != 0) {
			temp = temp.getNext();
			index--;
		}
		temp.getPrev().setNext(temp.getNext());
		temp.getNext().setPrev(temp.getPrev());
		temp.setNext(null);
		temp.setPrev(null);
		temp.setElement(null);
		this.currentSize--;
		return true;

	}

	@Override
	public int removeAll(E obj) {
		
		//remove all objects same as the given one
		int count = 0;
		while(this.remove(obj)) {
			count++;
		}
		return count;
	}

	@Override
	public E first() {
		
		//show first element in list
		return this.header.getNext().getElement();
	}

	@Override
	public E last() {
		
		//show last element in list
		return this.header.getPrev().getElement();
	}

	@Override
	public E get(int index) {
		
		//return object given an index
		if(index <0 || this.size() <= index) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> result = this.header;

		for(int i=0; i<=index; i++) {
			result = result.getNext();
		}
		return result.getElement();
	}

	@Override
	public void clear() {
		
		//clear the list
		while(!this.isEmpty()) {
			this.remove(0);
		}

	}

	@Override
	public boolean contains(E e) {
		
		//check if object is in list
		return this.firstIndex(e)!= -1;
	}

	@Override
	public boolean isEmpty() {
		
		//check if list is empty
		return this.size() == 0;
	}

	@Override
	public int firstIndex(E e) {
		
		//return the first index of a given object by iterating through the list 
		Node<E> temp = this.header.getNext();
		int count = 0;
		while(true) {
			if(this.comp.compare(temp.getElement(), e)==0) {
				return count;
			}
			else if(temp.getNext().getElement() == null) {
				return -1;
			}
			count++;
			temp = temp.getNext();
		}
	}

	@Override
	public int lastIndex(E e) {
		
		//return the last index of a given object by iterating through the list
		Node<E> temp = this.header.getPrev();
		int count = this.currentSize-1;
		while(true) {
			if(this.comp.compare(temp.getElement(), e)==0) {
				return count;
			}
			else if(temp.getPrev().getElement() == null) {
				return -1;
			}
			count--;
			temp = temp.getPrev();
		}
	}

}
