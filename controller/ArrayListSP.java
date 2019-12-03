package controller;

public class ArrayListSP<E> {
	public static final int CAPACITY = 20;
	private E[] data;
	private int size = 0;

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

	public void Add(int i, E e) throws IndexOutOfBoundsException, IllegalStateException {
		checkIndex(i, size + 1); // set nhập chỉ số index bắt đầu từ 1
		if (size == data.length) {
			throw new IllegalStateException("Mang Da Full");
		}
		for (int k = size - 1; k >= i; k--) {
			data[k + 1] = data[k];
		}
		
		data[i] = e;
		size ++;
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

}
