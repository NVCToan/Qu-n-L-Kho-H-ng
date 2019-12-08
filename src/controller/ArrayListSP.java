package controller;

import java.util.Iterator;

public class ArrayListSP<E> {
	public static final int CAPACITY = 20;
	private E[] data;
	private int size = 0;

	public E[] getData() {
		return data;
	}
public E get(int index) {
	return data[index];
}
	public void setData(E[] data) {
		this.data = data;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public static int getCapacity() {
		return CAPACITY;
	}

	public ArrayListSP(int capacity) {
		data = (E[]) new Object[capacity];
	}

	public ArrayListSP() {
		this(CAPACITY);
	}

	public boolean isEmpty() {
		return size == 0;
	}

	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		if (i < 0 || i >= n) {
			throw new IndexOutOfBoundsException("Illegal index : " + i);
		}
	}

	public void Add(int i, E e) throws IndexOutOfBoundsException,IllegalStateException {
		checkIndex(i, size + 1);//kiá»ƒm tra index há»£p lá»‡ 
		if (size == data.length) // danh sÃ¡ch Ä‘Ã£ Ä‘áº§y
		throw new IllegalStateException("Array is full");
		for (int k=size-1; k >= i; k--) //dá»�i cÃ¡c pháº§n tá»­ sang pháº£i
		data[k+1] = data[k];
		data[i] = e; //Ä‘áº·t pháº§n tá»­ má»›i vÃ o danh sÃ¡ch 
		size++;
		}

	
	public void Add(E e) 
	throws IndexOutOfBoundsException, IllegalStateException 
	{
		Add(size, e);
	}
	
	public E Remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E temp = data[i];
		for (int k=i; k < size-1; k++) //dá»�i cÃ¡c pháº§n tá»­ sang trÃ¡i
		data[k] = data[k+1];
		data[size-1] = null; // gÃ¡n pháº§n tá»­ cuá»‘i danh sÃ¡ch báº±ng null, phÃ²ng trÆ°á»�ng há»£p truy xuáº¥t lá»—i
		size--;
		return temp;
		}
	

	public Iterator<E> iterator() {
		return new MyArrayIterator<E>(data,size);
		}
	

}
