package controller;

public interface MyComparator<E> {
	public int compare(E e1, E e2);
	public int compare(E e);
}