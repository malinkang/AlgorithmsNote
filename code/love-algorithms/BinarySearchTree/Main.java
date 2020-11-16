public class Main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<Integer>();
        int[] data = {7,4,9,2,5,8,11,3,12,1};
        for(int i = 0;i<data.length;i++){
            bst.add(data[i]);
        }
        BinaryTrees.print(bst);
        bst.remove(7);
        System.out.println("\n删除后");
        BinaryTrees.print(bst);
    }
}
