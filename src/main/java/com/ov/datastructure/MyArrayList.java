package com.ov.datastructure;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Resizable-array implementation which contains add(), addAll(), get(), remove() methods.
 * @param <E> the type of elements in this list
 */
public class MyArrayList<E> implements Iterable<E> {

    /**
     * The number of elements arrayList contains
     */
    private int size;

    /**
     * Used for empty instances
     */
    private static final Object[] EMPTY_ELEMENT_DATA = {};

    /**
     * The array buffer which the elements of the MyArrayList are stored.
     * The capacity of MyArrayList is the length of this array buffer.
     * Any empty MyArrayList will expend to with length of DEFAULT_CAPACITY
     * when the first element is added.
     */
    private Object[] elementData;

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 3;

    public MyArrayList(int initialCapacity){
        if(initialCapacity == 0){
            this.elementData = EMPTY_ELEMENT_DATA;
        }else if(initialCapacity > 0){
            this.elementData = new Object[initialCapacity];
        }else{
            throw new IllegalArgumentException("Illegal initCapacity: " + initialCapacity);
        }
    }

    /**
     * Constructs an empty list
     */
    public MyArrayList(){ this.elementData = EMPTY_ELEMENT_DATA; }

    /**
     * Append all of the elements in the specified collection into this list
     * @param c collection containing all elements to insert into this list
     * @return {@code true} if insert success
     */
    public boolean addAll(Collection<? extends E> c){
        Object[] a = c.toArray();
        int numNew = a.length;
        if(numNew == 0) return false;
        final int l = elementData.length;
        final int s = size;
        if(numNew + s > l){
            elementData = grow(s + numNew);
        }

        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return true;
    }

    /**
     * Insert all of the elements in the specified collection into this list,
     * starting at the specified position.
     * @param index index at which to insert the first element from the specified collection.
     * @param c collection containing all elements to insert into this list
     * @return {@code true} if insert success
     */
    public boolean addAll(int index, Collection<? extends E> c) {
        checkRange(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        if(numNew == 0) return false;

        if(numNew > elementData.length - size){
            grow(size + numNew);
        }
        final int numMoved = size - index;
        if(numMoved > 0){
            System.arraycopy(elementData, index, elementData, index+numNew, numMoved);
        }
        System.arraycopy(a, 0, elementData, index, numNew);
        size += numNew;
        return true;
    }

    private void checkRange(int index) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
    }

    /**
     * Appends the specified element to the end of this list.
     * Dynamic array will increase its size when number of elements in array is equal to length of array.
     * @param e
     */
    public boolean add(E e){
        if(size == elementData.length)
            elementData = grow();
        elementData[size] = e;
        size++;
        return true;
    }

    private Object[] grow() {
        return grow(size+1);
    }

    /**
     * Increases the capacity to ensure that it can hold at least the
     * number of elements specified by the minimum capacity argument.
     * @param minCapacity
     */
    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData, newCapacity(minCapacity));
    }

    /**
     * Returns a capacity at least as large as the given minimum capacity.
     * Returns the current capacity increased by 50% if that suffices.
     * @param minCapacity
     */
    private int newCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if(newCapacity < minCapacity){
            if(elementData == EMPTY_ELEMENT_DATA){
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            }
            return minCapacity;
        }
        return newCapacity;
    }

    /**
     * Returns the element at the specified position in this list
     * @param index index of the element to return
     */
    public E get(int index){
        return elementData(index);
    }

    private E elementData(int index) {
        if(index >= size)
            throw new IndexOutOfBoundsException("illegal index: " + index);
        return (E) elementData[index];
    }

    /**
     * Remove the element at specified position in this list
     * @param index the index of element to be removed
     * @return the element that was remove from this list
     */
    public E remove(int index){
        if(index >= size){
            throw new IndexOutOfBoundsException("illegal index: " + index);
        }
        E oldValue = elementData(index);
        int numMoved = size - index - 1;
        if(numMoved > 0){
            System.arraycopy(elementData, index+1, elementData, index, numMoved);
        }
        elementData[--size] = null;
        return oldValue;
    }

    /**
     * Remove the first occurrence of the specified element in this list.
     * If the list does not contain the specified element, it is unchanged.
     * @param o element to be removed from this list, if present
     * @return {@code true} if this list contain the specified element
     */
    public boolean remove(Object o){
        for (int i = 0; i < size; i++) {
            if(o.equals(elementData[i])){
                remove(i);
                return true;
            }
        }
        return false;
    }

    public int size(){
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public E next() {
            if(!hasNext()) throw new NoSuchElementException();
            return (E) elementData[current++];
        }
    }

}
