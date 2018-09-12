package com.ov.datastructure;


/**
 * Singly linked list contains get(), add(), remove(), poll() methods
 * @param <E> type of elements in this list
 */
public class MySinglyLinkedList<E> {

    private int size = 0;

    /**
     * Pointer to first node.
     */
    private Node<E> head;

    /**
     * Append the specified element to the end of this list
     */
    public boolean offer(E e){
        return add(e);
    }

    /**
     * Append the specified element to the end of this list
     */
    public boolean add(E e){
        add(size, e);
        return true;
    }

    /**
     * Insert the specified element at the specified position in this list
     */
    public boolean add(int index, E e){
        checkAddIndex(index);
        Node<E> newNode = new Node<>(e);
        Node<E> n = head;

        if(n == null){ // this list is empty
            head = newNode;
            size++;
            return true;
        }

        if(index == 0){ // insert specified element at the first position
            newNode.next = n;
            head = newNode;
            size++;
            return true;
        }

        for (int i = 0; i < index-1; i++) {
            n = n.next;
        }

        Node front = n; // the front element(at position index-1)
        Node back = n.next;

        if(back !=  null){
            newNode.next = back;
        }

        front.next = newNode;
        size++;
        return true;
    }

    private void checkAddIndex(int index) {
        if(index < 0 || index > size ) throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    /**
     * Retrieves but not remove the first element of this list
     */
    public E peek(){
        return get(0);
    }

    /**
     * Retrieves and remove the first element of this list
     * @return the first element
     */
    public E poll(){
        return (head == null) ? null : removeFirst();
    }

    private E removeFirst(){
        Node<E> newHead = this.head.next;
        E elementRemoved = head.item;
        head.item = null;
        this.head = newHead;
        size--;
        return elementRemoved;
    }

    /**
     * Remove element at specified position in this list
     */
    public E remove(int index){
        checkIndex(index);

        if(index == 0) return removeFirst();

        Node<E> n = head;

        for(int i = 0 ; i < index -1 ; i++){
            n = n.next;
        }
        Node<E> front = n; // the front element (at position index-1)
        Node<E> back = n.next.next;
        Node<E> nodeRemoved = n.next;
        E itemRemoved = nodeRemoved.item;
        nodeRemoved.item = null;
        front.next = back;

        size--;
        return itemRemoved;
    }

    /**
     * Returns the element at the specified position in this list.
     */
    public E get(int index){
        checkIndex(index);

        if(index == 0) return head.item;

        Node<E> n = head;

        for(int i = 0; i < index; i++){
            n = n.next;
        }
        return n.item;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list
     * @return -1 if this list does not contain the specified element
     */
    public int indexOf(E e){
        Node<E> n = head;
        int index = 0;
        while (n != null){
            if(n.item.equals(e)){
                return index;
            }
            n = n.next;
            index++;
        }
        return -1;
    }

    private void checkIndex(int index) {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    public int size(){return size;}


    private static class Node<E>{
        private E item;
        private Node<E> next;

        public Node(E e){
            this.item = e;
        }
    }

}
