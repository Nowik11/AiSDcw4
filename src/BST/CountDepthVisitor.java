package BST;

public class CountDepthVisitor <E> implements ExtraDataVisitor<E>{
    private int depth=0;

    public void visit(E e, int extraData) {
        if (extraData > depth) {
            depth = extraData;
        }
    }
    public int getDepth() {
        return depth;
    }
}
