package BST;

public class kthElementVisitor <E> implements  Visitor<E>{
    private int counter =0;
    private final int k;
    private boolean stop =false;
    private E result = null;

    public kthElementVisitor(int k) {
        this.k = k;
    }
    public void visit(E e) {
        counter++;
        if(counter == k) {
            stop = true;
            result = e;
        }
    }
    public boolean getStop()
    {
        return stop;
    }
    public E getResult()
    {
        return result;
    }
}
