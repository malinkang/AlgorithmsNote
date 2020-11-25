import java.util.Comparator;

public class RBTree<E> extends BBST<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element,parent);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        super.afterAdd(node);
        Node<E> parent = node.parent;
        // 添加的根节点
        if (parent == null) {
            black(node); // 染色成黑色
            return;
        }
        // 如果父节点是黑色，直接返回
        if (isBlack(parent))
            return;
        // 获取叔父节点
        Node<E> uncle = parent.sibling();
        // 获取祖父节点
        Node<E> grand = red(parent.parent);
        // 叔父节点时红色
        if (isRed(uncle)) {
            //parent uncle 染成黑色
            black(parent);
            black(uncle);
            // 把祖父接地那当做是新添加的节点
            afterAdd(grand);
            return;
        }
        // 叔父节点不是红色
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {//R
            if (node.isLeftChild()) {// RL
                black(node);
                rotateRight(parent);
            } else {// RR
                black(parent);
            }
            rotateLeft(grand);
        }

    }

    //删除结点
    //在B树中，最后真正被删除的元素都在叶子节点中

    //删除红节点
    //直接删除，不用做任何操作
    //删除黑节点
    // 1. 拥有两个RED节点的黑节点 不能直接被删除
    // 2. 拥有1个红色节点的黑节点
    @Override
    protected void afterRemove(Node<E> node,Node<E> replacement) {
        super.afterRemove(node,replacement);
        //如果删除的节点是红色 不用做任何处理
        if(isRed(node)) return;
        //黑色
        //用以取代node的子节点是红色
        if(isRed(replacement)){
            black(replacement);
            return;
        }
        Node<E> parent = node.parent;
        if(parent == null) return;
        //删除的是黑色叶子节点
        //判断被删除的node是左还是右 已经被删除了所以是null
        boolean left = parent.left == null;
        //获取兄弟节点
        Node<E> sibling = left? parent.right:parent.left;
        //被删除的节点在左边，兄弟节点在右边
        if(left){

        }else{//被删除的节点在右边，兄弟节点在左边
            if(isRed(sibling)){//兄弟节点是红色
                black(sibling); //兄弟变成黑色
                red(parent);//父节点变成红色
                rotateRight(parent); //右旋转
                sibling = parent.left;//更换兄弟
            }
            //统一处理黑色
            //兄弟节点是黑色
            if(isBlack(sibling.left)&&isBlack(sibling.right)){ 
                //兄弟节点没有红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if(parentBlack){
                    afterRemove(parent, null);
                }
            }else{
                //兄弟节点至少有一个红色子节点，向兄弟节点借元素
                //兄弟节点的左边是黑色，兄弟要先旋转
                if(isBlack(sibling.left)){
                    rotateLeft(sibling);
                    sibling = parent.left;
                }
                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }

        }
       
        //删除的是黑色叶子节点
        //兄弟节点是黑色

        //兄弟节点是红色



    }

    // 染色
    private Node<E> color(Node<E> node, boolean color) {
        if (node == null)
            return null;
        ((RBNode<E>) node).color = color;
        return node;
    }


    // 染成黑色
    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    // 染成红色
    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    // 判断颜色
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private static class RBNode<E> extends Node<E> {
        boolean color = RED;

        public RBNode(E element, BinaryTree.Node<E> parent) {
            super(element, parent);
        }

    }

}
