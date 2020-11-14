import java.util.Comparator;
import java.util.LinkedList;

/**
 * BinarySearchTree
 */
public class BinarySearchTree<E> implements BinaryTreeInfo{
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;
    public BinarySearchTree(){
        this(null);
    }
    public BinarySearchTree(Comparator<E> comparator){
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {

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

    // 添加方法
    public void add(E element) {
        elementNotNullCheck(element);
        if (root == null) {
            root = new Node<E>(element, null);
            size++;
            return;
        }
        Node<E> parent = root;// 父节点
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            parent = node;
            cmp = compare(element,node.element);
            if(cmp < 0){ //插入的元素小于节点
                node = node.left;
            }else if(cmp > 0){
                node = node.right;
            }else{ //相等
                node.element = element;
                return;
            }
        }
        //创建新节点
        Node<E> newNode = new Node<E>(element,parent);
        if(cmp < 0){
            parent.left = newNode;
        }else{
            parent.right = newNode;
        }
        size++;
    }

    // 删除
    public void remove(E element) {

    }

    public boolean contains(E element) {
        return false;
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    // 比较方法
    private int compare(E e1, E e2) {
        if(comparator!=null){
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>)e1).compareTo(e2);
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

    }

    public interface Visitor<E>{
        public void visit(Node<E> node);
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