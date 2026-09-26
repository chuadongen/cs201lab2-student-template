import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap(){
        if (size < 2) {
            return;
        }

        // put nodes in arraylist and hashmap (to have o1 time for index lookup)
        List<Node<E>> positionArrayList = new ArrayList<>(size);
        Map<Node<E>, Integer> positionHashMap = new HashMap<>();
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            positionArrayList.add(current);
            positionHashMap.put(current, index);
            index++;
            current = current.getNext();
        }

        // copy arraylist and sort
        List<Node<E>> sortedArrayList = new ArrayList<>(positionArrayList);
        sortedArrayList.sort((a, b) -> a.getElement().compareTo(b.getElement()));

        // in positionArrayList, swap the pairs
        for (int i = 0; i < size / 2; i++) {
            Node<E> smallNode = sortedArrayList.get(i);
            Node<E> largeNode = sortedArrayList.get(size - 1 - i);
            int posSmall = positionHashMap.get(smallNode);
            int posLarge = positionHashMap.get(largeNode);
            Node<E> temp = positionArrayList.get(posSmall);
            positionArrayList.set(posSmall, positionArrayList.get(posLarge));
            positionArrayList.set(posLarge, temp);
        }

        // relink original LL
        for (int i = 0; i < size - 1; i++) {
            positionArrayList.get(i).setNext(positionArrayList.get(i + 1));
        }
        // set last to null
        positionArrayList.get(size - 1).setNext(null);
        head = positionArrayList.get(0);
        tail = positionArrayList.get(size - 1);
    }
   
}

