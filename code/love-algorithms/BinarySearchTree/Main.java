public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        int[] data = {7,4,9,2,5,8,11,3,12,1};
        for(int i = 0;i<data.length;i++){
            bst.add(data[i]);
        }
        BinaryTrees.print(bst);
        // bst.preorderTraversal();
        bst.preorderTraversal(new BinarySearchTree.Visitor<Integer>(){

			@Override
			public void visit(BinarySearchTree.Node<Integer> node) {
				System.out.println("结点："+node.element);
			}
            
        });
    }
}
