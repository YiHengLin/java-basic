package com.ov.datastructure;

import java.util.Collection;

/**
 * Doubly-linked list contains get(), offer(), poll(), peek(), add(), addAll(), remove() methods.
 * @param <E> the type of elements in this list
 */
public class MyLinkedList<E> {

    /**
     * Pointer to first node.
     */
    private Node<E> first;

    /**
     * Pointer to last node.
     */
    private Node<E> last;
    private int size = 0;

    /**
     * Returns the element at the specified position in this list.
     */
    public E get(int index){
        return node(index).item;
    }

    /**
     * Returns the first element in this list.
     */
    public E getFirst(){
        return first.item;
    }

    /**
     * Returns the last element in this list.
     */
    public E getLast(){
        return last.item;
    }

    /**
     * Adds the specified element as the tail (last element) of this list
     * @param e
     * @return
     */
    public boolean offer(E e){
        Node<E> n = new Node<>(last, e, null);
        last.next = n;
        size++;
        return true;
    }

    /**
     * Retrieves and removes the head (first element) of this list
     * @return the head of this list, or null if this list is empty
     */
    public E poll(){
        if(first == null) return null;
        E e = first.item;
        first = first.next;
        size--;
        return  e;
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
     */
    public E peek(){
        if(last == null) return null;
        return first.item;
    }


    public boolean addAll(Collection<? extends E> c){
        return addAll(size, c);
    }

    public boolean addAll(int index, Collection<? extends E> c){
        checkIndex(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        if(numNew == 0) return false;

        Node<E> start, end;
        if(index == size){
            start = last;
            end = null;
        }else{
            end = node(index);
            start = end.prev;
        }

        for(Object o : a){
            E e = (E) o;
            Node<E> newNode = new Node<>(start, e, null);
            if(start == null){
                first = newNode;
            }
            else{
                start.next = newNode;
            }
            start = newNode;
        }

        if(end == null){
            last = start;
        }else{
            start.next = end;

        }
        size+=numNew;
        return true;
    }

    private void checkIndex(int index) {
        if(index < 0 || index > size) throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    /**
     * Return the element at specified index
     */
    private Node<E> node(int index){
       if(index < (size >> 1)){
           Node<E> n = first;
           for(int i = 0; i < index;i++){
               n = n.next;
           }
           return n;
       } else{
            Node<E> n = last;
            for(int i = size-1 ; i > index ;i--){
                n = n.prev;
            }
            return n;
       }
    }

    /**
     * Appends the specified element to the end of this list.
     * @param e the element to be appended
     */
    public boolean add(E e){
        appendToLast(e);
        return true;
    }

    private void appendToLast(E e) {
        final Node<E> l = last;
        Node<E> newNode = new Node<>(l, e, null);
        last = newNode;

        if(last == null)
            first = newNode;
        else
            last.next = newNode;
        size++;
    }

    /**
     * Remove first occurrence of specified element from this list.
     * @param e element to be removed
     * @return true if this list contains the specified element. false if this list contains the specified element.
     */
    public boolean remove(E e){
        for(Node<E> x = first; x != null ; x = x.next){
            if(x.item.equals(e)){
                unlink(x);
                size--;
                return true;

            }
        }
        return false;
    }

    /**
     * Remove the element at specified position
     * @param index at position element to be removed
     * @return the element been removed
     */
    public E remove (int index){
        checkIndex(index);
        return unlink(node(index));
    }

    private E unlink(Node<E> node) {
        final E element = node.item;
        final Node<E> front = node.prev;
        final Node<E> back = node.next;

        if(front == null){
            first = node.next;
        } else if(back == null){
            last = node.prev;
            node.prev.next = null;
        } else{
            front.next = back;
            back.prev = front;
        }

        node.item = null;
        size--;
        return element;
    }

    /**
     * Return true if this list contains specified element
     */
    public boolean contains(E e){
        return !(indexOf(e) == -1);
    }


    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     */
    public int indexOf(E e){
        Node n = first;
        int index = 0;
        while(n.next != null){
            if(n.item.equals(e)){
                return index;
            }
            index++;
            n = n.next;
        }
        return -1;
    }

    private static class Node<E>{
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E e, Node<E> next){
            this.prev = prev;
            this.item = e;
            this.next = next;
        }
    }

    public int size(){ return size;}

}
