package telran.util;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16;
	private int size;
	private T[] array;
	
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	private class ArrayListIterator implements Iterator<T> {
		int currentIndex=0;
		@Override
		public boolean hasNext() {		
			return currentIndex < size;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return array[currentIndex++];
		}
		
	}
	@Override
	/**
	 * adds object at end of array, that is, at index == size
	 */
	public boolean add(T obj) {
		if (size == array.length) {
			allocate();
		}
		array[size++] = obj;
		return true;
	}

	private void allocate() {
		array = Arrays.copyOf(array, array.length * 2);
		
	}
	@Override
	public boolean remove(T pattern) {
		int index = indexOf(pattern);
		boolean res = false;
		if (index > -1) {
			res = true;
			remove(index);
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {		
		return indexOf(pattern) > -1;
	}

	@Override
	public int size() {		
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}

	@Override
	public T get(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("There is no element with this index"); 
		}
		return array[index];
	}

	@Override
	public void add(int index, T obj) {
		// TODO Auto-generated method stub
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException(String.format("Performance the method add: invalid index=%d", index));
		}
		if(index == size) {
			add(obj);
		} else {
				if(size == array.length) {
					allocate();
				}
				System.arraycopy(array, index, array, index+1, size - index);
				array[index] = obj;
				size++;
		}
		
	}

	@Override
	public T remove(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		T removeObj = array[index];
		size--;
		System.arraycopy(array, index+1, array, index, size - index); //????? size - index -1 ??
		return removeObj;
	}

	@Override
	public int indexOf(T pattern) {
		int index = -1;
		for(int i=0; i<size && index==-1; i++) {
			if(pattern==null && array[i]==null || pattern != null 
					&& pattern.equals(array[i])) {
				index = i;
			}
		}
		return index;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int index = -1;
		for(int i=size-1; i>=0 && index==-1; i--) {
			if(pattern==null && array[i]==null || pattern!=null
					&& pattern.equals(array[i])) {
				index = i;
			}
		}
		return index;
	}

}
