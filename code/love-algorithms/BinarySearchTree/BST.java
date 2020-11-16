import java.util.Comparator;

/**
 * BinarySearchTree
 */
public class BST<E> extends BinaryTree<E> {
    private Comparator<E> comparator;

    public BST() {
        this(null);
    }

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
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
            cmp = compare(element, node.element);
            if (cmp < 0) { // 插入的元素小于节点
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else { // 相等
                node.element = element;
                return;
            }
        }
        // 创建新节点
        Node<E> newNode = new Node<E>(element, parent);
        if (cmp < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;
    }

    // 删除
    // 如果删除的是叶子节点 直接删除
    // 删除度为1的节点（只有一个子节点的结点）用子节点替代原节点的位置
    // 删除度为2的结点 先用前驱或者后继节点的值覆盖原节点的值
    // 然后删除相应的前驱或者后继节点

    // 如果一个节点的度为2，
    // 那么它的前驱、后继节点度只可能为1或0
    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null)
            return;
        size--;
        // 优先删除度为2的节点
        if (node.hasTwoChildren()) {
            // 找到后继节点
            Node<E> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.element = s.element;
            // 将s赋值给node然后删除node来删除后继节点
            node = s;
        }
        // node的度必然为1或者0，所以可以执行度为1或者0的删除操作
        Node<E> replacement = node.left != null ? node.left : node.right;

        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            if (node.parent == null) {// 根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
        } else if (node.parent == null) { // node是叶子节点 并且是根节点
            root = null;
        } else { // node是叶子节点 不是根节点
            if (node == node.parent.right) { // node是右子节点
                node.parent.right = null;
            } else {
                node.parent.left = null; // node是左子节点
            }

        }

    }

    // 获取节点
    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0)
                return node;
            else if (cmp > 0)
                node = node.right;
            else if (cmp < 0)
                node = node.left;
        }
        return null;

    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    // 比较方法
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

}