
import java.util.LinkedList;

public class BinaryTree<E> implements BinaryTreeInfo{
    protected  int size;
    protected Node<E> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }
    public void preorderTraversal(Visitor<E> visitor){
        preorderTraversal(root,visitor);
    }
    //前序遍历
    private void preorderTraversal(Node<E> node,Visitor<E> visitor){
        if(node == null || visitor == null) return;
        visitor.visit(node);
        preorderTraversal(node.left,visitor);
        preorderTraversal(node.right,visitor);
    }
    public void inorderTraversal(Visitor<E> visitor){
        inorderTraversal(root,visitor);
    }
    //中序遍历
    private void inorderTraversal(Node<E> node,Visitor<E> visitor){
        if(node == null) return;
        inorderTraversal(node.left,visitor);
        visitor.visit(node);
        inorderTraversal(node.right,visitor);
    }
    public void postorderTraversal(Visitor<E> visitor){
        postorderTraversal(root,visitor);
    }
    //中序遍历
    private void postorderTraversal(Node<E> node,Visitor<E> visitor){
        if(node == null) return;
        postorderTraversal(node.left,visitor);
        visitor.visit(node);
        postorderTraversal(node.right,visitor);
    }
    public void levelOrderTraversal(){
        if(root == null) return;
        LinkedList<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            Node<E> node = queue.poll();
            System.out.println(node.element);
            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
        }
    }
    //前驱节点：中序遍历时的前一个节点
    //如果是二叉搜索树，前驱节点就是前一个比它小的节点
    //如果左子树不为空 node.left!=null前驱节点是左子树的最大节点
    //predecessor = node.left.right.right... 
    //终止条件：right为null

    //如果左子树为空 node.left == null && node.parent !=null 
    //predecessor = node.parent.parent.parent
    //终止条件：node在parent的右子树

    //node.left == null && node.parent == null 没有前驱
    protected Node<E> predecessor(Node<E> node){
        if(node == null) return null;
        //前驱节点在左子树当中
        if(node.left!=null){
            node = node.left;
            while(node.right!=null){
                node = node.right;
            }
            return node;
        }
        while(node.parent!=null&&node.parent.left == node){
            node = node.parent;
        }
        return node.parent;

    }
    //后继节点：中序遍历时的后一个节点
    //如果是二叉搜索树，后继节点就是后一个比它打的节点
    //如果左子树不为空 node.right != null
    //successor = node.right.left.left
    //终止条件 left为null

    //node.right == null && node.parent!=null
    //successor = node.parent。parent.parent 
    //终止条件：node在parent的左子树中  
    protected Node<E> successor(Node<E> node){
        if(node == null) return null;

        if(node.right!=null){
            node = node.right;
            while(node.left!=null){
                node = node.left;
            }
            return node;
        }
        while(node.parent!=null&&node.parent.right == node){
            node = node.parent;
        }
        return node.parent;
    }

    public static interface Visitor<E>{
        public void visit(Node<E> node);
    }
    public static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean hasTwoChildren(){
            return left!=null && right!=null;
        }

    }


    //=====================
    // 实现方法
    //=======================

    

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
       return  ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        return  ((Node<E>)node).element;
    }
}
