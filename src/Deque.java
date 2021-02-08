import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int n;         // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;     // end of queue

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // construct an empty deque
    public Deque() {
        this.first = null;
        this.last = null;
        n = 0;

    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (size() == 0) {
            first.prev = null;
            n++;
        }
        else {
            oldFirst.prev = first;
            first.prev = null;
            n++;
        }
        if (size() == 1) {
            last = first;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (size() == 0 ) {
            last.prev = oldLast;
            n++;

        } else {
            oldLast.next = last;
            last.prev = oldLast;
            n++;
        }
        if (size() == 1) {
            first = last;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (first == null) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (size() == 1 ) {
            n--;
        } else {
            first.prev = null;
            n--;
        }
        if (size() == 0)
            last = first;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (last == null) throw new NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        if (size() == 1 ) {
            n--;
        } else {
            last.next = null;
            n--;
        }
        if (size() == 0)
            first = last;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {

        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.removeFirst();    // ==> 2
        deque.iterator();    // ==> [1]
        deque.addFirst(5);
        deque.removeFirst(); //     ==> 5
        deque.addFirst(7);
        deque.removeFirst() ; //    ==> 7
        deque.removeFirst();
        // Deque<Integer> deq = new Deque<Integer>();
        // System.out.println(deq.isEmpty() + " is deque empty ");
        // System.out.println(deq.size() + " deque size ");
        // deq.addFirst(3);
        // deq.addFirst(2);
        // deq.addFirst(1);
        // deq.addFirst(0);
        // deq.addLast(4);
        // deq.addLast(5);
        // System.out.println(deq.isEmpty() + " is deque empty ");
        // System.out.println(deq.size() + " deque size ");
        // Iterator<Integer> it = deq.iterator();
        // System.out.println(deq.removeLast());
        // System.out.println(deq.removeFirst());
        // System.out.println(deq.first.item + " first item");
        // System.out.println(deq.last.item + " last item");
        // System.out.println(deq.size() + " deque size ");
        // while (it.hasNext()) {
        //     System.out.println(it.next());
        // }
    }
}