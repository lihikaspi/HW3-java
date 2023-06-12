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

    private class StackIterator<T extends Cloneable> implements Iterable<E>{
        private int index;
        private final T[] array;

        public StackIterator(ArrayStack<T> arrayStack) {
            index = arrayStack.size;
            array = arrayStack.data;
        }

        public T next() {
            index--;
            return array[index];
        }

        public boolean hasNext() {
            return index > 0;
        }

        @Override
        public Iterator<E> iterator() {
            return null;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new StackIterator<>(this);
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
        return size == 0;
    }

    @Override
    public ArrayStack<E> clone() {
        // use try-catch
        // catch --> return null
        // can use invoke

        try {
            return (ArrayStack<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
