import java.util.Comparator;

public class BBST<E> extends BST<E> {

    public BBST(){
        this(null);
    }


    public BBST(Comparator<E> comparator) {

    }

    // 左旋转
    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    // 右旋转
    protected void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    // 旋转之后的操作
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // 更新parent父节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            parent.parent.left = parent;
        } else if (grand.isRightChild()) {
            parent.parent.right = parent;
        } else {
            root = parent; // parent是根几点
        }
        // 更新child的父节点
        if (child != null) {
            child.parent = grand;
        }
        // 更新grand的parent
        grand.parent = parent;
    }
}
