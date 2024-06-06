package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> implements List<T> {
	Node<T> head;
	Node<T> tail;
	int size;

	private static class Node<T> {
		T data;
		Node<T> prev;
		Node<T> next;

		Node(T data) {
			this.data = data;
		}
	}

	// O[1]
	@Override
	public boolean add(T obj) {
		Node<T> node = new Node<>(obj);
		addNode(size, node);
		return true; // always true for List
	}

	// O[N]
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

	// O[N]
	@Override
	public boolean contains(T pattern) {
		return indexOf(pattern) > -1;
	}

	// O[1]
	@Override
	public int size() {
		return size;
	}

	// O[1]
	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator<T> {
		Node<T> current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T data = current.data;
			current = current.next;
			return data;
		}

	}

	// O[1]
	@Override
	public T get(int index) {
		List.checkIndex(index, size, true);
		return getNode(index).data;
	}

	// O[N]
	@Override
	public void add(int index, T obj) {
		List.checkIndex(index, size, false);
		Node<T> node = new Node<>(obj);
		addNode(index, node);
	}

	// O[N]
	@Override
	public T remove(int index) {
		List.checkIndex(index, size, true);
		Node<T> nodeToRemove = getNode(index);
		Node<T> prevNode = nodeToRemove.prev;
		Node<T> nextNode = nodeToRemove.next;
		if (prevNode != null) {
			prevNode.next = nextNode;
		} else {
			head = nextNode;
		}
		if (nextNode != null) {
			nextNode.prev = nextNode;
		} else {
			tail = prevNode;
		}
		size--;
		return nodeToRemove.data;
	}

	// O[N]
	@Override
	public int indexOf(T pattern) {
		Node<T> current = head;
		int index = 0;
		while (index < size && !Objects.equals(current.data, pattern)) {
			index++;
			current = current.next;
		}
		return index < size ? index : -1;
	}

	// O[N]
	@Override
	public int lastIndexOf(T pattern) {
		int index = size - 1;
		Node<T> current = tail;
		while (index >= 0 && !Objects.equals(current.data, pattern)) {
			index--;
			current = current.prev;
		}
		return index;
	}

	// O[1]
	private Node<T> getNode(int index) {
		return index < size / 2 ? getNodeFromHead(index) : getNodeFromTail(index);
	}

	// O[1]
	private Node<T> getNodeFromTail(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	// O[1]
	private Node<T> getNodeFromHead(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	// O[N]
	private void addNode(int index, Node<T> node) {
		if (index == 0) {
			addHead(node);
		} else if (index == size) {
			addTail(node);
		} else {
			addMiddle(node, index);
		}
		size++;
	}

	// O[N]
	private void addMiddle(Node<T> node, int index) {
		Node<T> nodeNext = getNode(index);
		Node<T> nodePrev = nodeNext.prev;
		nodeNext.prev = node;
		nodePrev.next = node;
		node.prev = nodePrev;
		node.next = nodeNext;
	}

	// O[N]
	private void addTail(Node<T> node) {
		// head cannot be null
		tail.next = node;
		node.prev = tail;
		tail = node;
	}

	// O[N]
	private void addHead(Node<T> node) {
		if (head == null) {
			head = tail = node;
		} else {
			node.next = head;
			head.prev = node;
			head = node;
		}
	}
}
