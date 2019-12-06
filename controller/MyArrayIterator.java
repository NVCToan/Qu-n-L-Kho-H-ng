package controller;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayIterator<E> implements Iterator<E> {
	private int count;
	private int current;
	private E[] items;

	public MyArrayIterator(E[] collection, int size) {
		items = collection;
		count = size;
		current = 0;
	}
	public boolean hasNext() {
		return (current < count); }
		public E next() {
		if (!hasNext()) throw new NoSuchElementException();
		current++;
		return items[current -1]; }

}
