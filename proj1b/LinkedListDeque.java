import com.sun.corba.se.spi.ior.TaggedProfileTemplate;

public class LinkedListDeque<T> implements Deque<T>{
    private ItemNode sentinel;
    private int size;

    public class ItemNode {
        private T Item;
        private ItemNode next;
        private ItemNode prev;

        public ItemNode(ItemNode prev, T Item, ItemNode next) {
            this.prev = prev;
            this.next = next;
            this.Item = Item;
        }
    }

    public LinkedListDeque() {
//        sentinel = new ItemNode(sentinel,null,sentinel);
        sentinel = new ItemNode(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item){
        ItemNode first = sentinel.next;
        sentinel.next = new ItemNode(sentinel,item,sentinel.next);
        first.prev = sentinel.next;
        size ++;
    }

    public void addLast(T item){
        ItemNode last = sentinel.prev;
        sentinel.prev = new ItemNode(last, item,sentinel);
        last.next = sentinel.prev;
        size ++;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque(){
        ItemNode node = sentinel.next;
        for (int i = 0; i < size;i ++) {
            System.out.println(node.Item);
            node = node.next;
        }
    }

    public T removeFirst(){
        T item = sentinel.next.Item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size --;
        return item;
    }

    public T removeLast(){
        T item = sentinel.prev.Item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size --;
        return item;
    }

    public T get(int index){
        ItemNode node = sentinel;
        for (int i = -1; i < index; i ++) {
            node = node.next;
        }
        return node.Item;
    }

    public T getRecursive(int index) {
        ItemNode node = sentinel.next;
        return helper(index, node);
    }

    public T helper(int index, ItemNode node) {
        if (index == 0) return node.Item;
        else {
            return helper(index - 1, node.next);
        }
    }



    public static void main(String[] args) {
        LinkedListDeque<String> L = new LinkedListDeque<>();
        L.addLast("happy");
        L.addLast("happy_birthday");
        L.addFirst("happyNewyear");
        L.removeFirst();
        System.out.println(L.getRecursive(1));
        L.printDeque();
    }
}
