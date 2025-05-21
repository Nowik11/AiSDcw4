package BST;

public class CountingEvenVisitor<E> implements Visitor<E> {
    private int count = 0;

    public void visit(E e) {
        if (e instanceof Integer && (Integer) e % 2 == 0 || e instanceof Double && (Double) e % 2 == 0 || e instanceof Long && (Long) e % 2 == 0 || e instanceof Float && (Float) e % 2 == 0|| e instanceof Short && (Short) e % 2 == 0 || e instanceof Byte && (Byte) e % 2 == 0 ) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
