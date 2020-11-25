
public class Main {
    public static void main(String[] args) {
        // BST<Integer> bst = new BST<Integer>();
        // int[] data = {7,4,9,2,5,8,11,3,12,1};
        // for(int i = 0;i<data.length;i++){
        //     bst.add(data[i]);
        // }
        // BinaryTrees.print(bst);
        // bst.remove(7);
        // System.out.println("\n删除后");
        // BinaryTrees.print(bst);

        // AVLTree<Integer> avlTree = new AVLTree<>();
        // avlTree.add(10);
        // avlTree.add(5);
        // avlTree.add(3);
        // BinaryTrees.print(avlTree);
        test4();
    }

    private static void test3(){
        int data[] = {
            55,87,56,74,96,22,62,20,70,68,90,50
        };
        RBTree<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
        }
        BinaryTrees.println(rb);
    }
    //测试红黑树删除
    private static void test4(){
        int data[] = {
            55,87,56,74,96,22,62,20,70,68,90,50
        };
        RBTree<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
        }
        BinaryTrees.println(rb);
        for (int i = 0; i < data.length; i++) {
            rb.remove(data[i]);
            System.out.println("["+data[i]+"]");
            BinaryTrees.println(rb);
        }
    }
}
