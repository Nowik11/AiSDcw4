package BST;

import javax.swing.text.AbstractDocument;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class BST<E> {

    private E classType=null;
    private Node root;
    private final Comparator<E> comparator;

    public BST( Comparator<E> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

     private class Node {
        E data;
        Node left;
        Node right;

        void preOrderWalk(Visitor<E> visitor) {
            visitor.visit(this.data);
            if(left != null) left.preOrderWalk(visitor);
            if(right != null) right.preOrderWalk(visitor);
        }
        void inOrderWalk(Visitor<E> visitor) {
            if(left != null) left.inOrderWalk(visitor);
            visitor.visit(this.data);
            if(right != null) right.inOrderWalk(visitor);
        }
        void postOrderWalk(Visitor<E> visitor) {
            if(left != null) left.postOrderWalk(visitor);
            if(right != null) right.postOrderWalk(visitor);
            visitor.visit(this.data);
        }

        void DFSWalk(ExtraDataVisitor<E> visitor,int depth) {
            visitor.visit(this.data,depth);
            if(left != null) left.DFSWalk(visitor,depth+1);
            if(right != null) right.DFSWalk(visitor,depth+1);

        }
        private class Pair {
            Node node;
            int location;
            Pair(Node node, int location) {
                this.node = node;
                this.location = location;
            }
        }

        void BFSWalk(ExtraDataVisitor<E> visitor) {
            LinkedList<Pair> queue = new LinkedList<>();
            queue.add(new Pair(root,1));
            while (!queue.isEmpty()) {
                Pair current = queue.poll();
                visitor.visit(current.node.data,current.location);
                if (current.node.left != null) {
                    queue.add(new Pair(current.node.left, current.location * 2));
                }
                if (current.node.right != null) {
                    queue.add(new Pair(current.node.right, current.location * 2+1));
                }
            }
        }
        void preOrderWalkButWithCountingChildren(ExtraDataVisitor<E> visitor) {
            int extraData = 0;
            if(left != null) extraData++;
            if(right != null) extraData++;
            visitor.visit(this.data,extraData);
            if(left != null) left.preOrderWalkButWithCountingChildren(visitor);
            if(right != null) right.preOrderWalkButWithCountingChildren(visitor);
        }

        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }


         public Node successor() {
            Node current;
            if(right!=null)
            {
                current = right;

                    while (current.left != null) {
                        current = current.left;
                    }
                return current;
            }
            else {
                current = root;
                Node successor = null;
                while(comparator.compare(current.data , this.data)!=0) {
                    if(comparator.compare(current.data, this.data)>0)
                    {
                        successor = current;
                        current = current.left;

                    }
                    else
                    {
                        current = current.right;
                    }
                }
                return successor;
            }
         }
         public Node remove()
         {
             Node copy=this;
             if(this == root && this.right==null && this.left==null)
             {
                 root = null;
                 return copy;
             }
             Node current = root;
             Node prev = null;
             boolean lastTurnLeft = false;
             while(comparator.compare(current.data, this.data)!=0) {
                 prev = current;
                 if(comparator.compare(current.data, this.data)>0)
                 {
                     current = current.left;
                     lastTurnLeft = true;

                 }
                 else
                 {
                     current = current.right;
                     lastTurnLeft = false;
                 }
             }
             if(this.left == null && this.right == null)
             {
                 if(lastTurnLeft)
                     prev.left = null;
                 else
                     prev.right = null;
             }
             else if(this.left == null ^ this.right == null)
             {
                 if(this.left != null)
                 {
                     if(lastTurnLeft)
                         prev.left = this.left;
                     else
                         prev.right = this.left;
                 }
                 else
                 {
                     if(lastTurnLeft)
                         prev.left = this.right;
                     else
                         prev.right = this.right;
                 }
             }
             else
             {
                 Node successor = this.successor();
                 successor.remove();
                 this.data = successor.data;

             }
            return copy;
         }


     }

    public void preOrderPrint()
    {
       root.preOrderWalk(new BSTDataPrinter<E>());
        System.out.println();
    }
    public void inOrderPrint()
    {
        root.inOrderWalk(new BSTDataPrinter<E>());
        System.out.println();
    }
    public void postOrderPrint()
    {
        root.postOrderWalk(new BSTDataPrinter<E>());
        System.out.println();
    }

    public E successor(E element) {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        Node node = find(element, root);
        if (node == null) {
            throw new IllegalArgumentException("Element not found in the tree");
        }
        Node successor = node.successor();
        if (successor == null) {
            return null;
        }
        return successor.data;

    }

    public void add(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if(find(element)!=null)
        {
            throw new IllegalArgumentException("Element already exists");
        }
        if (root == null) {
            root = new Node(element);
            classType = element;
        } else {
            Node current = root;
            while (true) {
                if(comparator.compare(current.data, element) >0) {
                    if(current.left == null) {
                        current.left = new Node(element);
                        return;
                    } else {
                        current = current.left;
                    }
                }
                else if(comparator.compare(current.data, element) < 0) {
                    if(current.right == null) {
                        current.right = new Node(element);
                        return;
                    } else {
                        current = current.right;
                    }
                }
                else {
                    throw new CannotAssignChildException("Cannot assign child");
                }
            }
        }

    }
    public E max() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return max(root);
    }
    private E max(Node node) {
        if (node == null) {
            return null;
        }
        if(node.right == null) {
            return node.data;
        } else {
            return max(node.right);
        }
    }
    public E min() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return min(root);
    }

    private E min(Node node) {
        if (node == null) {
            return null;
        }
        if(node.left == null) {
            return node.data;
        } else {
            return min(node.left);
        }
    }

    public E kthLowest(int k)
    {
        if(root == null)
            return null;
        if(k==1)
            return min();
        kthElementVisitor<E> visitor  = new kthElementVisitor<E>(k);
        root.inOrderWalk(visitor);
        if(visitor.getResult()==null)
            return null;
        return visitor.getResult();
    }

    public E find(E element) {
        if (root == null) {
            return null;
        }
        Node node = find(element, root);
        return node == null ? null : node.data;
    }


    private Node find(E element, Node node) {
        if (node == null) {
            return null;
        }
        if (comparator.compare(node.data, element) == 0) {
            return node;
        } else if (comparator.compare(node.data, element) > 0) {
            return find(element, node.left);
        } else {
            return find(element, node.right);
        }
    }
    public E remove(E element) {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        Node node = find(element, root);
        if (node == null) {
            return null;
        }
        return node.remove().data;
    }

    public int countNodes()
    {
        if (root == null) {
            return 0;
        }
        CountingVisitor<E> visitor = new CountingVisitor<E>();
        root.preOrderWalk(visitor);
        return visitor.getCount();
    }

    public int countEvenNodes()
    {
        if (root == null) {
            return 0;
        }
        CountingEvenVisitor<E> visitor = new CountingEvenVisitor<E>();
        root.preOrderWalk(visitor);
        return visitor.getCount();
    }

    public int getHeight()
    {
        if (root == null) {
            return 0;
        }
        CountDepthVisitor <E> visitor = new CountDepthVisitor<E>();
        root.DFSWalk(visitor,1);
        return  visitor.getDepth();
    }

    public int getNodesWithOneChildCount()
    {
        if (root == null) {
            return 0;
        }
        CountingSingleChildVisitor<E> visitor = new CountingSingleChildVisitor<E>();
        root.preOrderWalkButWithCountingChildren(visitor);
        return visitor.getCount();
    }

    public int countNodesWithBrother()
    {
        if (root == null) {
            return 0;
        }
        BrotherCountingVisitor<E> visitor = new BrotherCountingVisitor<E>();
        root.preOrderWalkButWithCountingChildren(visitor);
        return visitor.getCount();
    }

    public int getLongestOneChildDynasty()
    {
        if (root == null) {
            return 0;
        }
        LongestOneChildDynastyFinderVisitor<E> visitor = new LongestOneChildDynastyFinderVisitor<E>();
        root.preOrderWalkButWithCountingChildren(visitor);
        return visitor.getLongestDynasty();
    }

    public void exportToFile(String fileName) {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        ToFileExportingVisitor<E> visitor = new ToFileExportingVisitor<E>();
        root.preOrderWalk(visitor);
        String data = visitor.getString();
        try (java.io.FileWriter writer = new java.io.FileWriter(fileName)) {
            writer.write(data);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    public void importFromFile(String fileName)  //gdyby tylko Dr. Konieczny pobłogosławił serializacją to by to 10 razy lepiej działało XD
    {
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String data = scanner.next();
                E element = null;
                if(classType instanceof Integer)
                {
                    element = (E) Integer.valueOf(data);
                }
                else if(classType instanceof String)
                {
                     element = (E) data;
                }
                else
                {
                    throw new IllegalArgumentException("Unsupported type");
                }
                add(element);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found: " + fileName);
        }
    }

    public void LayerPrint()
    {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        LayerPrinter<E> visitor = new LayerPrinter<E>();
        root.BFSWalk(visitor);
    }

    public void clear() {
        root = null;
    }
    public void printNiceTree()
    {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        int height = getHeight();
        NiceTreePrinter<E> visitor = new NiceTreePrinter<E>(height);
        root.BFSWalk(visitor);
        visitor.print();
    }

}
