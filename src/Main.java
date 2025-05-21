import BST.BST;

import java.util.*;

import static java.util.Collections.swap;

public class Main {

    public static <E> LinkedList<E> merge(List<E> list1, List<E> list2, Comparator<? super E> comparator) {
        LinkedList<E> mergedList = new LinkedList<E>();
        ListIterator<E> iterator1 = list1.listIterator();
        ListIterator<E> iterator2 = list2.listIterator();
        E element1 = iterator1.hasNext() ? iterator1.next() : null;
        E element2 = iterator2.hasNext() ? iterator2.next() : null;
        while (element1 != null || element2 != null) {
            if (element1 == null) {
                mergedList.add(element2);
                element2 = iterator2.hasNext() ? iterator2.next() : null;
            } else if (element2 == null) {
                mergedList.add(element1);
                element1 = iterator1.hasNext() ? iterator1.next() : null;
            } else if (comparator.compare(element1, element2) <= 0) {
                mergedList.add(element1);
                element1 = iterator1.hasNext() ? iterator1.next() : null;
            } else {
                mergedList.add(element2);
                element2 = iterator2.hasNext() ? iterator2.next() : null;
            }
        }
        return mergedList;
    }

    public static void MergeListTest()
    {
        LinkedList<Integer> list1 = new LinkedList<>();
        list1.add(1);
        list1.add(3);
        list1.add(5);
        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(2);
        list2.add(4);
        list2.add(6);
        list2.add(7);
        list2.add(8);
        LinkedList<Integer> mergedList = merge(list1, list2, Integer::compareTo);
        System.out.println("Merged List: " + mergedList);
    }

    public static<E> void printTab(E[] tab)
    {
        System.out.println("Array:" + Arrays.toString(tab));
    }

    public static<E>  void heapSort(E[] tab, Comparator<E> comparator) {
        ArrayHeap<E> heap = new ArrayHeap<>(tab, comparator);
        for(int i=0;i<tab.length-1;i++) {
            printTab(tab);
            heap.maximum();
        }
        printTab(tab);

    }


    public static void HeapSortTest()
    {
        Integer[] tab = {76,21, 5, 57,12,50,20,93,20};
        heapSort(tab,Integer::compareTo);
        for (Integer i : tab) {
            System.out.print(i + " ");
        }
    }

    public static  void countingSort(int[] tab,int k) {
        int n = tab.length;
        int[] count = new int[k + 1];
        for (int value : tab) {
            count[value]++;
        }
        int index = 0;
        for(int i = 0; i <= k; i++) {
           for(int j = 0; j<count[i];j++ ){
                tab[index++] = i;
           }
        }
    }

    public static void countingSortTest()
    {
        int[] tab = {0,2,1,0,4,4,2,1,1,1};
        int k = 4;
        countingSort(tab,k);
        System.out.println("Sorted array: " + Arrays.toString(tab));
    }


    public static <E> List<E> quickSort(List<E> list , Comparator<E> comparator) {
        quickSort(0, list.size() - 1, list, comparator);
        return list;
    }

    public static <E> void quickSort(int left, int right, List<E> list, Comparator<E> comparator) {
        if (left < right) {
            int pivotIndex = partition(left, right, list,comparator);
            quickSort(left, pivotIndex - 1, list,comparator);
            quickSort(pivotIndex + 1, right, list,comparator);
        }
    }

    public static<E>int partition(int left, int right, List<E> list, Comparator<E> comparator) {
        int pivotIndex = choosePivot(left,right,list,comparator);
        E pivotValue = list.get(pivotIndex);
        if (pivotIndex != left) {
            swap(list, pivotIndex, left);
        }
        int indexLeft = left + 1;
        int indexRight = right;

        ListIterator<E> iteratorLeft = list.listIterator(left+1);
        ListIterator<E> iteratorRight = list.listIterator(right);
        E leftValue = iteratorLeft.next();
        E rightValue = iteratorRight.next();
        iteratorRight.previous();

        do{
            while (indexLeft < right && comparator.compare(leftValue, pivotValue) <= 0) {
                indexLeft++;
                leftValue = iteratorLeft.next();

            }
            while (indexRight > left && comparator.compare(rightValue, pivotValue) > 0) {
                indexRight--;
                rightValue = iteratorRight.previous();
            }
            if (indexLeft < indexRight) {
                swap(list, indexLeft, indexRight);
                iteratorLeft.previous();
                leftValue = iteratorLeft.next();


                rightValue = iteratorRight.next();
                iteratorRight.previous();
            }
        }while (indexLeft < indexRight);
        swap(list, left, indexRight);
        return indexRight;
    }

    public static <E> int  choosePivot(int left, int right, List<E> list, Comparator<E> comparator) {
        if (right - left < 100) {
            return left + (int) (Math.random() * (right - left + 1));
        } else {
            int firstIndex = (left + (int) (Math.random() * (right - left + 1)));
            int secondIndex = (left + (int) (Math.random() * (right - left + 1)));
            int thirdIndex = (left + (int) (Math.random() * (right - left + 1)));

            E first = list.get(firstIndex);
            E second = list.get(secondIndex);
            E third = list.get(thirdIndex);
            int firstCompare = comparator.compare(first, second);
            int secondCompare = comparator.compare(second, third);
            int thirdCompare = comparator.compare(third, first);
            if (firstCompare < 0 && secondCompare < 0 || secondCompare > 0 && firstCompare > 0) {
                return secondIndex;
            } else if (thirdCompare < 0 && secondCompare < 0 || secondCompare > 0 && thirdCompare > 0) {
                return thirdIndex;
            } else {
                return firstIndex;
            }
        }
    }

    public static void quickSortTest()
    {
        List<Integer> list = new ArrayList<>();
       for (int i = 0; i < 300; i++) {
              list.add((int) (Math.random() * 100));
       }
        System.out.println("Before sorting: " + list);
        quickSort(list, Integer::compareTo);
        System.out.println("After sorting: " + list);
    }



    public static void BSTTests()
    {
        BST<Integer> bst = new BST<>(Integer::compareTo);
        bst.add(20); bst.add(7); bst.add(10); bst.add(25);
        bst.add(4); bst.add(1); bst.add(2); bst.add(12); bst.add(30);
        bst.remove(12);bst.remove(1);bst.remove(20);
        System.out.println("Node count: " + bst.countNodes());
        System.out.println("Even node count: " + bst.countEvenNodes());
        System.out.println("Height: " + bst.getHeight());
        System.out.println("Nodes with one child: " + bst.getNodesWithOneChildCount());
        System.out.println("Nodes with one brother: " + bst.countNodesWithBrother());
        System.out.println("Longest one child dynasty: " + bst.getLongestOneChildDynasty());
        bst.exportToFile("output.txt");
        System.out.println("Exported to file.");
        bst.preOrderPrint();
        bst.clear();
        System.out.println("Cleared BST.");
        bst.importFromFile("output.txt");
        bst.preOrderPrint();
        BST<Integer> bst2 = new BST<>(Integer::compareTo);


        bst2.add(8);
        bst2.add(4);
        bst2.add(12);
        bst2.add(2);
        bst2.add(6);
        bst2.add(10);
        bst2.add(14);
        bst2.add(1);
        bst2.add(3);
        bst2.add(5);
        bst2.add(7);
        bst2.add(9);
        bst2.add(11);
        bst2.add(13);
        bst2.add(15);


        bst2.printNiceTree();

    }
    public static void main(String[] args) {

      BSTTests();
    }
}