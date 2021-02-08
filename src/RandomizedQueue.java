import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;         // number of elements on queue
    private Item[] items;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        n = 0;
    };
    // is the randomized queue empty?
    public boolean isEmpty() {

        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {

        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException("Object to add is NULL!");
        if (n == items.length) {
            resize(2*items.length);
        }
        items[n] = item;
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int rand = StdRandom.uniform(n);
        Item elem = items[rand];
        if (n-1 == rand) {
            items[rand] = null;
        }
        else {
            items[rand] = items[n-1];
            items[n-1] = null;
        }
        if (n == items.length/4) {
            resize(items.length/2);
        }
        n--;
        return elem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (n == 0) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(n);
        return items[index];
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {
        private int randNum = n;
        public boolean hasNext() {
            return getRandNum() != 0;
        }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int rand = StdRandom.uniform(getRandNum());
            Item itemtoreturn = items[rand];
            if (rand == getRandNum() - 1) {
                setRandNum(getRandNum() - 1);
                return itemtoreturn;
            }
            else {
                items[rand] = items[getRandNum() -1];
                items[getRandNum() -1] = itemtoreturn;
                setRandNum(getRandNum() - 1);
                return itemtoreturn;
            }

        }
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public int getRandNum() {
            return randNum;
        }

        public void setRandNum(int randNum) {
            this.randNum = randNum;
        }
    }
    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rqueue = new RandomizedQueue<Integer>();

        rqueue.enqueue(1);
        rqueue.enqueue(2);
        rqueue.enqueue(3);
        rqueue.enqueue(4);
        System.out.println(rqueue.size() + " size ");
        for (int elem : rqueue) {
            System.out.println(elem);
        }
        rqueue.dequeue();
        System.out.println(rqueue.sample() + " sample");
        System.out.println(rqueue.size() + " size ");

    }

}