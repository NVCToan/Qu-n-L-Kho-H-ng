package controller;


public interface MyList<E>{
	void Add(E e);
	void Add(int i, E e);
	boolean isEmpty();
	int getSize();
	E remove(int i);
	E[] interchangeSortAZ(MyComparator<E> x);
	E[] interchangeSortZA(MyComparator<E> x);

}