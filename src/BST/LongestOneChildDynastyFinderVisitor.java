package BST;

public class LongestOneChildDynastyFinderVisitor <E> implements ExtraDataVisitor<E> {
    private int longestDynasty = 0;
    private int currentDynasty = 0;

    @Override
    public void visit(E e, int extraData) {
        if (extraData == 1) {
            currentDynasty++;
        } else {
            longestDynasty = Math.max(longestDynasty, currentDynasty);
            currentDynasty = 0;
        }
    }

    public int getLongestDynasty() {
        return Math.max(longestDynasty, currentDynasty);
    }
}
