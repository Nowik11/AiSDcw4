package BST;

public class NiceTreePrinter <E> implements ExtraDataVisitor<E>{
    private int height;
    private E[] array;
    private int format=1;


    public NiceTreePrinter(int height) {
        this.height = height;
        this.array = (E[]) new Object[1<<height];
    }
    public void visit(E e, int extraData) {
        array[extraData] = e;
        String temp = e.toString();
        if(temp.length()>format)
            format = temp.length();
    }

    public void print() {
        int width = (1 << height) * (format + 1);  // szerokość całej linii
        int index = 1;

        for (int i = 0; i < height; i++) {
            int nodesInLevel = 1 << i;
            int spacingBetween = width / nodesInLevel;

            for (int j = 0; j < nodesInLevel; j++) {
                int pos = j * spacingBetween + spacingBetween / 2;

                if (j == 0) {
                    printSpaces(pos - format / 2);
                } else {
                    printSpaces(spacingBetween - format);
                }

                String toPrint = " ".repeat(format);
                if (index < array.length && array[index] != null) {
                    String data = array[index].toString();
                    toPrint = data + toPrint.substring(data.length());
                }

                System.out.print(toPrint);
                index++;
            }
            System.out.println();
        }
    }

    private void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }
}
