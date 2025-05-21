package BST;

public class ToFileExportingVisitor <E> implements Visitor<E> {
    private final StringBuilder stringBuilder = new StringBuilder();


    @Override
    public void visit(E e) {
        stringBuilder.append(e).append(" ");
    }

    public String getString() {
        return stringBuilder.toString();
    }


}
