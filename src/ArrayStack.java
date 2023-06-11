import java.util.Iterator;

public class ArrayStack<E extends Cloneable> implements Stack<E>{
    // E extends Cloneable ???
    private int maxCapacity;
    private E[] data;
    private int size;

    public ArrayStack(int maxCapacity) {
        if (maxCapacity < 0) throw new NegativeCapacityException();
        this.maxCapacity = maxCapacity;
        this.size = 0;
        this.data = new <E>[maxCapacity];
    }

    class StackIterator implements Iterable<E>{
        @Override
        public Iterator<E> iterator() {
            // go backwards from size to 0
        }
    }

    @Override
    public void push(E element) {
        if (size == maxCapacity) throw new StackOverflowException();
        data[size] = element;
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) throw new EmptyStackException();
        E temp = data[size];
        data[size] = null;
        size--;
        return temp;
    }

    @Override
    public E peek(){
        if (isEmpty()) throw new EmptyStackException();
        return data[size];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
         if (size == 0) return true;
         return false;
    }

    @Override
    public ArrayStack<E> clone() {
        // use try-catch
        // catch --> return null
        // can use invoke
    }
}
