public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(10);
        bst.add(14);
        bst.add(6);
        bst.add(9);
        bst.add(4);
        bst.add(12);
        bst.add(32);
        BinaryTrees.print(bst);
    }
}
