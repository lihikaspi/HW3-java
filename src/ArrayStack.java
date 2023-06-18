import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

public class ArrayStack<E extends Cloneable> implements Stack<E>{
    private final int maxCapacity;
    private Object[] data;
    private int size; // amount of objects in the stack right now

    public ArrayStack(int maxCapacity) {
        if (maxCapacity < 0) throw new NegativeCapacityException();
        this.maxCapacity = maxCapacity;
        this.size = 0;
        this.data = new Object[maxCapacity];
    }

    private class StackIterator<T extends Cloneable>  implements Iterable<T>, Iterator<T> {
        private int index;
        private final Object[] array;

        public StackIterator(ArrayStack<T> arrayStack) {
            index = arrayStack.size;
            array = arrayStack.data;
        }

        @Override
        public T next() {
            index--;
            return (T)array[index];
        }

        @Override
        public boolean hasNext() {
            return index > 0;
        }

        @Override
        public Iterator<T> iterator() {
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
        Object temp = data[size];
        data[size] = null;
        size--;
        return (E)temp;
    }

    @Override
    public E peek(){
        if (isEmpty()) throw new EmptyStackException();
        return (E)data[size];
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
            Method[] ms = ArrayStack.class.getMethods();
            ArrayStack<E> copy = (ArrayStack<E>) super.clone();
            ms[4].invoke(copy);
            copy.data = data.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        } catch (InvocationTargetException e) {
            return null;
        }
    }
}
