//public class ArrayDeque<T> {
//    private T[]  array = (T[]) new Object[8];
//    private int size;
//    private int First = 4, Last = 5;
//
//    public void resize() {
//        T[] newArray = (T[]) new Object[array.length * 4];
//        int newFirst = array.length;
//        for (int i = newFirst; i < newFirst + size; i ++) {
//            newArray[i] = array[First % array.length];
//            First ++;
//        }
//        First = newFirst;
//        Last = First + size;
//    }
//
//    public void addFirst(T item) {
//         array[First] = item;
//         First --;
//         size ++;
//         if (size == array.length) this.resize();
//    }
//
//    public void addLast(T item) {
//         array[Last % array.length] = item;
//         Last ++;
//    }
//
//    public boolean isEmpty() {
//
//    }
//
//    public int size() {
//
//    }
//
//    public void printDeque() {
//
//    }
//
//    public T removeFirst() {
//
//    }
//
//    public T removeLast() {
//
//    }
//
//    public T get(int index) {
//
//    }
//
//
//}
