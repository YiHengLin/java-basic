package com.ov.DataStructure;

import java.util.Collection;

/**
 * Singly LinkedList node
 */
public class SinglyListNode<E> {
    E item;
    SinglyListNode<E> next;

    /**
     * Constructs an empty list
     */
    public SinglyListNode(){}

    private SinglyListNode(E item) {
        this.item = item;
    }

    /**
     * Constructs a list containing the elements of the specified collection
     * @param c the collection whose elements are to be placed into this list
     */
    public SinglyListNode(Collection<? extends E> c){
        addAllItems(c);
    }

    private void addAllItems(Collection<? extends E> c) {
        Object[] arr = c.toArray();
        SinglyListNode n = this;

        for(Object o : arr){
            E e = (E) o;
            if(n.item == null){
                n.item = e;
            }else{
                n.next = new SinglyListNode(e);
                n = n.next;
            }
        }
    }

    /**
     * Appends the specified element to the end of this list.
     * @param d the elements to be placed into this list
     */
    public void append(E d){
        SinglyListNode<E> end = new SinglyListNode<>(d);
        SinglyListNode n = this;

        while(n.next != null){
            n = n.next;
        }
        n.next = end;
    }

    /**
     * Appends all of the elements in the specified collection to the end of this list
     * @param c the collection whose elements are to be placed into this list
     */
    public void appendAll(Collection<? extends E> c){
        SinglyListNode n = this;

        while(n.next != null){
            n = n.next;
        }

        Object[] newItems = c.toArray();
        for(Object i : newItems){
            E end = (E) i;
            n.next = new SinglyListNode(end);
            n = n.next;
        }
    }

    /**
     * Retrieves and removes the head (first element) of this list.
     * @param <E>
     * @return
     */
//    public <E> SinglyListNode<E> removeFirst(){
//        SinglyListNode n = this;
//
//        return null;
//    }

    public int indexOf(E e){
        int index = 0;
        SinglyListNode n = this;
        while(n != null){
            if(n.item.equals(e)){
                return index;
            }
            n = n.next;
            index++;
        }
        return -1;
    }

    public boolean contains(E e){
        return indexOf(e) != -1;
    }

    public int size(){
        int size = 1; // head
        SinglyListNode n = this;
        while(n.next != null){
            size++;
            n = n.next;
        }
        return size;
    }
}
