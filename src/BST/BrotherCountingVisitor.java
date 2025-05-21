package BST;

public class BrotherCountingVisitor <E> implements ExtraDataVisitor<E> {
    private int count = 0;

    public void visit(E e, int extraData) {
        if (extraData == 2) {
            count+=2;
        }
    }

    public int getCount() {
        return count;
    }
}
