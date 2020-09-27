import java.util.List;
import java.util.NoSuchElementException;

public class SimpleLinkedList {
	
	public static int size;
	private static Node start;
	private static Node end;
	
	public SimpleLinkedList() {
		start = null;
		end = null;
		size = 0;
	}
	
	public SimpleLinkedList(List<String> list) {
		start = new Node (null, null, null);
		end = new Node (null, null, null);
		
		Node startNode = start;
		for (int i =  0; i < list.size(); i++) {
			startNode.data = list.get(i);
			Node prevNode = startNode; 
			startNode =  new Node (startNode, null, null);
			prevNode.next = startNode;
		}
		startNode.data = list.get(list.size()-1);
		end = startNode;
		size = list.size();
	}
	
	public void add(int index, String s) {
		checkIdx(index);
		if (index == 0) {
			addFirst(s);
		} else if (index == size - 1) {
			addLast(s);
		} else {
			Node startNode = getNode(index);
			Node newNode = new Node (startNode.prev, s, startNode);
			startNode.prev.next = newNode;
			startNode.prev = newNode;
			size++;
		}
	}
	public void addFirst (String s) {
		Node startNode = start;
		Node newNode = new Node (null, s, startNode);
		start = newNode;
		if (size == 0) {
			end = newNode;
		} else {
			startNode.prev = newNode;
		}
		size++;
	}
	//good
	public void addLast(String s) {
        Node current = end;
        Node newNode = new Node(current, s, null);
        end = newNode;
        if (current == null) {
            start = newNode;
        } else {
            current.next = newNode;
        }
        size++;
    }
//good	
	public void clear () {
		start = new Node (null, null, null);
		end = new Node (null, null, null);
		size = 0;
		}
	//good
	public boolean contains (String s) {
		for(int x = 0; x < size; x++) {
			if (getNode(x).data == s) {
				return true;
			}
		}
		return false;
	}
	
	//good
    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        int count = 0;
        Node current = start;
        while (current != null) {
            if (count++ == index) {
                return current;
            }
            current = current.next;
        }

        return null;
    }
	//good
	private void checkIdx(int idx) {
		if(idx < 0 || idx > size) { 
			throw new IndexOutOfBoundsException("Index: " + idx + ", Size: " + size);
		}
	}
	//good
	public String getFirst () {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return start.data;
	}
	
	public String getLast () {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return end.data;
	}
	public int indexOf (String s) {
		for (int i = 0; i < size; i++) {
			if (getNode(i).data == s) {
				return i;
			}
		}
		return -1; 
	}

	public String get(int index) { 
		Node startNode = getNode(index);
		return startNode.data;
	}
	
	public String remove (int index) { 
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);		
		} else if (index == 0) {
			return removeFirst();
		} else if (index == size -1) {
			return removeLast();
		} else {
			Node deletedNode = getNode(index);
			deletedNode.prev.next = deletedNode.next;
			deletedNode.next.prev = deletedNode.prev;
			String removed = deletedNode.data;
			size = size - 1; 
			return removed;
		}
	}
	
	public boolean remove (String s) {
		boolean isFound = contains(s);
		if (isFound == false) {
			return false;
		} else {
			int index = indexOf(s);
			if (index == 0) {
				removeFirst();
				return true;
			} else if (index == size) {
				removeLast();
				return true;
			} else {
				Node deletedNode = getNode(index);
				deletedNode.prev.next = deletedNode.next;
				deletedNode.next.prev = deletedNode.prev;
				size--;
				return true;
			}
		}
	}
	public String removeFirst () {
		String removed = start.data;
		Node deletedNode = start;
		start = deletedNode.next;
		start.prev = null;
		size--;
		return removed;
	}
	
	public String removeLast () {
		String removed = end.data;
		Node deletedNode = end;
		end = deletedNode.prev;
		end.next = null;
		size--;
		return removed;
	}
	
	public String set(int index, String s) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		Node startNode = getNode(index);
		String element = startNode.data;
		if (s == null) s = "null";
		startNode.data = s;
		return element;
	}
	
	public int size () {
		return size;
	}
	
	public String toString () {
		String str = "[";
		Node startNode = start;
		if (size != 0) {
			for (int i = 0; i < size - 1; i++) {
				str += startNode.data + ", ";
				startNode = startNode.next;
			}
			str += startNode.data + "]";
		} else {
			return "[]";
		}
		return str;
	}
	
	public static class Node {
		Node prev;
		String data;
		Node next;
		public Node(Node prev, String data, Node next) {
			this.prev = prev;
			this.data = data;
			this.next = next;
		}
	}
}
