package BST;

public class CountingVisitor<E> implements Visitor<E> {

    private int count =0;

    public void visit(E e) {
        count++;
    }

    public int getCount() {
        return count;
    }
}
