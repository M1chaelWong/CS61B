// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import DebugPractice.In;
import synthesizer.AbstractBoundedQueue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;
    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = last = 0;
        this.capacity = capacity;
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */

    public void enqueue(T x) {
        if (isFull()) throw new RuntimeException("Ring Buffer Overflow");
        rb[last] = x;
        fillCount ++;
        if (last + 1 == capacity) {
            last = 0;
        }else last ++;
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        try {
            if(isEmpty()) throw new RuntimeException();
        }catch (Exception e) {
            System.out.println("Ring Buffer Underflow");
        }
        T rtn = rb[first];
        rb[first] = null;
        fillCount --;
        if (first + 1 == capacity) {
            first = 0;
        }else first ++;
        return rtn;
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        return rb[first];
        // TODO: Return the first item. None of your instance variables should change.
    }


    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Iterator<Integer> iter = list.iterator();
        ArrayRingBuffer<Integer> ring = new ArrayRingBuffer<>(2);
        ring.dequeue();
        ring.enqueue(3);
        ring.enqueue(2);
        ring.enqueue(5);
        ring.enqueue(2);
    }

    private class Itr implements Iterator<T> {
        int index = 0;
        @Override
        public boolean hasNext() {
            return (index != capacity);
        }

        @Override
        public T next() {
            return rb[index ++];
        }
    }




    public Iterator<T> iterator() {
        return new Itr();
    }
}
