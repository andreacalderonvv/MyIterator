
/* Name: Andrea Calderon
 * Email: a6calderon@ucsd.edu
 * PID: A17303613
 * Sources used: Write-Up, class slides
 */

 import java.util.AbstractList;
 import java.util.NoSuchElementException;
 import java.util.ListIterator;
 import java.util.Iterator;

 /*
  * Implements a doubly link list and all the methods
  */
 public class MyLinkedList<E> extends AbstractList<E> {
 
     int size;
     Node head;
     Node tail;
 
     /**
      * A Node class that holds data and references to previous and next Nodes.
      */
     protected class Node {
         E data;
         Node next;
         Node prev;
 
         /** 
          * Constructor to create singleton Node 
          * @param element Element to add, can be null	
          */
         public Node(E element) {
             // Initialize the instance variables
             this.data = element;
             this.next = null;
             this.prev = null;
         }
 
         /** 
          * Set the parameter prev as the previous node
          * @param prev new previous node
          */
         public void setPrev(Node prev) {
             this.prev = prev;		
         }
 
         /** 
          * Set the parameter next as the next node
          * @param next new next node
          */
         public void setNext(Node next) {
             this.next = next;
         }
 
         /** 
          * Set the parameter element as the node's data
          * @param element new element 
          */
         public void setElement(E element) {
             this.data = element;
         }
 
         /** 
          * Accessor to get the next Node in the list 
          * @return the next node
          */
         public Node getNext() {
             return this.next;
         }
         /** 
          * Accessor to get the prev Node in the list
          * @return the previous node  
          */
         public Node getPrev() {
             return this.prev;
         } 
         /** 
          * Accessor to get the Nodes Element 
          * @return this node's data
          */
         public E getElement() {
             return this.data;
         } 
     }
 
     //  Implementation of the MyLinkedList Class
     public MyLinkedList() {
         /* Add your implementation here */
         // TODO
         head = new Node(null);
         tail = new Node(null);
         head.next = tail;
         head.prev = null;
         tail.next = null;
         tail.prev = head;
     }
 
     /**
      * @return the size of the list
      * @param
      */
 
     @Override
     public int size() {
         // need to implement the size method
         return size; // TODO
     }
 
     /**
      * @return the data of the node at the wanted index
      * @param index
      */
 
     @Override
     public E get(int index) {
         if (index < 0 || index >= size){
             throw new IndexOutOfBoundsException();
         }
         Node temp = getNth(index);
         E other = temp.data;
 
         return (E) other;  // TODO
     }
     /**
      * adds the element at the wanted index
      * @param index
      */
     @Override
     public void add(int index, E data) {
         if (data == null){
             throw new NullPointerException();
         }
         if(index < 0 || index > size){
             throw new IndexOutOfBoundsException();
         }
         if (size == 0){
             Node temp = new Node(data);
             head.next = temp;
             tail.prev = temp;
             temp.prev = head;
             temp.next = tail;
             size++;
         }
         else{
             Node temp = new Node(data);
             Node nRight = getNth(index);
             Node nLeft = getNth(index).prev;
             temp.prev = nLeft;
             temp.next = nRight;
             nRight.prev = temp;
             nLeft.next =  temp;
             size++;
         }
     }
 
     /**
      * @param data
      * adds data to the end of the list
      */
     @Override
     public boolean add(E data) {
         if (data == null){
             throw new NullPointerException();
         }
         if( size == 0){
             Node temp = new Node(data);
             head.next = temp;
             tail.prev = temp;
             temp.prev = head;
             temp.next = tail;
             size++;
         }
         else{
             Node temp = new Node(data);
             temp.next = tail;
             tail.prev = temp;
             getNth(size-1).next = temp;
             temp.prev = getNth(size-1);
             size++;
         }
         return true; // TODO
     }
     /***
      * @param data
      * @param index
      * @return data that is replaced
      * old data to the new data of the node at the wanted index
      */
     @Override
     public E set(int index, E data) {
         if (data == null){
             throw new NullPointerException();
         }
         if (index < 0 || index >= size){
             throw new IndexOutOfBoundsException();
         }
 
         Node temp = getNth(index);
 
         E other = temp.data;
 
         temp.data = data;
 
         return (E) other; // TODO
     }
     /**
      * @return data that is replaced
      * removes node at wanted index
      * @param index
      */
     @Override
     public E remove(int index) {
         if( index < 0 || index >= size){
             throw new IndexOutOfBoundsException();
         }
         Node temp = getNth(index);
         E other = temp.data;
         Node nRight = getNth(index).next;
         Node nLeft = getNth(index).prev;
         nRight.prev = nLeft;
         nLeft.next = nRight;
         size--;
         return (E) other; // TODO
    }
    /**
    * clears list of every non-sentinel node
    * @param
    */
    @Override
    public void clear() {
        /* Add your implementation here */
        if (size==0){
            size=0;
        }
        else{
        getNth(0).prev= null;
        getNth(size-1).next=null;
        head.next = tail;
        tail.prev = head;
        size=0;
        }
    }
 
    /**
    * checks if list is empty or not
    * @param
    */
    @Override
    public boolean isEmpty() {
         if (this.size == 0){
             return true;
         }
         return false;  // TODO
    }
 
     /**
      * @returns returns node, not the data
      * @param index
      */
    protected Node getNth(int index) {
         if(index < 0 || index >= size){// may not be size.
             throw new IndexOutOfBoundsException();
         }
         Node temp= head;
         for ( int i = 0; i <= index; i++){
 
             temp = temp.next;
         }
         return (Node) temp;  // TODO
    }

    /* this class implements all the methods needed for an iterator */
    /* Instance variables: left, right, idx, forward, canRemoveOrSet */
    protected class MyListIterator implements ListIterator<E> {
        Node left,right;
        int idx;
        boolean forward;
        boolean canRemoveOrSet;

        /**
         * intitializes the instance variables
         */
        public MyListIterator(){
            left = head;
            right = head.next;
            idx = 0;
            forward = true;
            canRemoveOrSet = false;
        }

        /**
         * @return true if the iterator is able to move to the right
         */
        public boolean hasNext() {
            if(idx < size){
                return true;
            }
            return false;
        }

        /**
         * @return the element of the list when iterator moves to 
         * the right by one
         */
        public E next() {
            if(hasNext()==false){
                throw new NoSuchElementException();
            }
            E temp = right.data;
            idx++;
            left = right;
            right = right.next;
            forward = true;
            canRemoveOrSet = true;
            return temp;
        }

        /**
         * @return true if the iterator has an element one node
         * behind
         */
        public boolean hasPrevious() {
            if ( idx == 0){
                return false;
            }
            return true;
        }

        /**
         * @return the element of the list when the iterator
         * moves backward by one node
         */
        public E previous() {
            if (hasPrevious()== false){
                throw new NoSuchElementException();
            }
            E temp = left.data;
            idx--;
            right = left;
            left = left.prev;
            forward = false;
            canRemoveOrSet = true;
            return temp;
        }

  
        /**
         * @return index of element if method next() was called
         */
        public int nextIndex() {
            if ( idx == size){
                return size;
            }
            return idx;
        }


        /**
         * @return the index of the element that would be returned 
         * by a call to previous() method or return -1 if it is at the start
         * of the list
         */
        public int previousIndex() {
            if ( idx == 0){
                return -1;
            }
            return idx-1;
        }


        /**
         * removes correct node based on previous/next call
         */
        public void remove() {
            if (canRemoveOrSet == false){
                throw new IllegalStateException();
            }
            if (forward == true){

                left.prev.setNext(right);
                right.setPrev(left.prev);
                left = left.prev;
                left.prev = null;
                left.next = null;
                size--;
                idx--;
                canRemoveOrSet= false;
                forward = true;
            }
            else if(forward == false){
                // removes right node
                left.setNext(right.next);
                right.next.setPrev(left);
                right = right.next;
                right.next = null;
                right.prev = null;
                //right = right.next;
                size--;
                forward = false;
                canRemoveOrSet = false;
            }

        }

        /**
         * sets correct node to inputted element based on previous/next call
         * @param e 
         */
        public void set(E e) {
            if (e == null){
                throw new NullPointerException();
            }
            if (canRemoveOrSet == false){
                throw new IllegalStateException();
            }
            if (forward ==  false){
                right.setElement(e);
            }
            else if (forward == true){
                left.setElement(e);
            }

        }


        /**
         * adds element into the list and adjusts instance variables
         * @param e
         */
        public void add(E e) {
            if ( e == null){
                throw new NullPointerException();
            }
            if (size == 0){ // here
                Node temp = new Node(e);
                temp.prev = left;
                temp.next = right;
                left = temp;
                right = tail;
                size++;
                idx++;
                canRemoveOrSet = false;
            }
            else{
                Node temp = new Node(e);
                temp.prev = left;
                temp.next = right;
                left = temp;
                right = temp.next;
                size++;
                idx++;
                canRemoveOrSet = false;
        }
        }
    }
    // overrides AbstractList implementations
    public ListIterator<E> listIterator() {
        return new MyListIterator();
    }
    
    public Iterator<E> iterator() {
        return new MyListIterator();
    }
     
 }
