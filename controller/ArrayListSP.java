package controller;

import java.util.Iterator;

public class ArrayListSP<E> {
	public static final int CAPACITY = 20;
	private E[] data;
	private int size = 0;

	public E[] getData() {
		return data;
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
		checkIndex(i, size + 1);//kiểm tra index hợp lệ 
		if (size == data.length) // danh sách đã đầy
		throw new IllegalStateException("Array is full");
		for (int k=size-1; k >= i; k--) //dời các phần tử sang phải
		data[k+1] = data[k];
		data[i] = e; //đặt phần tử mới vào danh sách 
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
		for (int k=i; k < size-1; k++) //dời các phần tử sang trái
		data[k] = data[k+1];
		data[size-1] = null; // gán phần tử cuối danh sách bằng null, phòng trường hợp truy xuất lỗi
		size--;
		return temp;
		}
	

	public Iterator<E> iterator() {
		return new MyArrayIterator<E>(data,size);
		}
	

}
