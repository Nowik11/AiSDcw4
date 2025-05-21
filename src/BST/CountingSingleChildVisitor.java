package BST;

public class CountingSingleChildVisitor<E> implements ExtraDataVisitor<E>{
    private int count = 0;

    public void visit(E e, int extraData) {
        if (extraData == 1) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
