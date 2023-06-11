import java.util.Iterator;


public class ArrayStack<E extends Cloneable> implements Stack<E>{
    // E extends Cloneable ???
    private int maxCapacity;
    private E[] data;
    private int size;

    public ArrayStack<E>(int maxCapacity) {
        // array size of max capacity
        // negative --> NegativeCapacityException (unmarked)
    }

    class StackIterator implements Iterable<E>{
        @Override
        public Iterator<E> iterator() {
            // go backwards from size to 0
        }
    }

    @Override
    public void push(E element) {
        // if size pre adding == max capacity --> StackOverflowException (unmarked)

        // first in --> data[0]
        // last in --> data[size]
    }

    @Override
    public E pop() {
        // if size == 0 --> EmptyStackException (unmarked)

        // save data[size]
        // change data[size] = null
        // return temp
    }

    @Override
    public E peek() {
        // if size == 0 --> EmptyStackException (unmarked)

        // return data[size]
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        // if size == 0 --> true
    }

    @Override
    public ArrayStack<E> clone() {
        // use try-catch
        // catch --> return null
        // can use invoke
    }
}
