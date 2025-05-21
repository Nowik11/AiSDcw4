

import java.util.Comparator;

public class ArrayHeap<E>  {
    private int height;
    private int size;
    E[] array;
    int arraySize;
    private Comparator<E> comparator;

    private int findPowerOf2(int number)
    {
        int power = 0;
        while (number > 0) {
            number /= 2;
            power++;
        }
        return power;
    }

    public ArrayHeap(E[] array, Comparator<E> comparator) {
        this.height = findPowerOf2(array.length);
        this.array = array;
        this.size = array.length;
        this.comparator = comparator;
        for(int i = (array.length-1)/2 ; i >= 0; i--)
        {
            sink(i);
        }
    }

    public ArrayHeap(int height, Comparator<E> comparator) {
        this.height = height;
        arraySize = (int)Math.pow(2,height);
        array = (E[]) new Object[arraySize];
        this.comparator = comparator;

    }

    public boolean isFull()
    {
        return arraySize<=(size);
    }

    public void clear()
    {
        size = 0;
        array = (E[]) new Object[arraySize];
    }
    public void add(E e)
    {

        array[size] = e;
        repairHeap(size);
        size++;

    }
    int sink(int index)
    {
        while(index <size ) {
            int left = 2 * index +1;
            int right = 2 * index + 2;
            if (left<size &&comparator.compare(array[left], array[index]) > 0 && (right>=size ||  comparator.compare(array[right], array[left]) <= 0)) {
                swap(index, left);
                index = left;
            }
            else if(right< size && comparator.compare(array[right], array[index]) > 0)
            {
                swap(index, right);
                index = right;
            }
            else{
                break;
            }
        }
        return index;
    }

    private void swap(int i, int j)
    {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    void repairHeap(int index)
    {
        while(index >1)
        {
            if(comparator.compare(array[index], array[(index-1)/2]) >0)
            {
                swap(index, (index-1)/2);
                index = (index-1)/2;
            }
            else
                return;
        }
    }

    public E maximum() {
        E max = array[0];
        swap(0, --size);
        sink(0);
        return max;
    }


    public int size() {
        return size;
    }
    public int getHeight()
    {
        return height;
    }
    public E[] getArray(){
        return array;
    }
}
