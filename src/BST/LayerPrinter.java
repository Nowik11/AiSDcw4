package BST;

public class LayerPrinter <E> implements ExtraDataVisitor<E>{
    public void visit(E e, int extraData) {
        System.out.println(e + " " );
    }
}
