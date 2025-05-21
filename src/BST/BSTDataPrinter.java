package BST;

public class BSTDataPrinter<E> implements Visitor<E> {
   public void visit(E e) {
       System.out.print(e+" ");
   }
}
