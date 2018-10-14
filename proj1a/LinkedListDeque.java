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

    @Override
    public void addFirst(T item){
        ItemNode first = sentinel.next;
        sentinel.next = new ItemNode(sentinel,item,sentinel.next);
        first.prev = sentinel.next;
        size ++;
    }

    @Override
    public void addLast(T item){
        ItemNode last = sentinel.prev;
        sentinel.prev = new ItemNode(last, item,sentinel);
        last.next = sentinel.prev;
        size ++;
    }

    @Override
    public boolean isEmpty(){
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque(){
        ItemNode node = sentinel.next;
        for (int i = 0; i < size;i ++) {
            System.out.println(node.Item);
            node = node.next;
        }
    }

    @Override
    public T removeFirst(){
        T item = sentinel.next.Item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size --;
        return item;
    }

    @Override
    public T removeLast(){
        T item = sentinel.prev.Item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size --;
        return item;
    }

    @Override
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

    }
}
