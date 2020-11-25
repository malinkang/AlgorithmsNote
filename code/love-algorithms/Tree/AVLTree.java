import java.util.Comparator;

public class AVLTree<E> extends BBST<E> {
    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    // 添加导致失衡
    // LL RL 右旋转
    // RR LR 左旋转
    // 添加可能会导致所有祖先节点都失衡
    // 只要让高度最低的失衡节点恢复平衡，整个树就恢复平衡
    @Override
    protected void afterAdd(Node<E> node) {
        super.afterAdd(node);
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalance(node);
                break;
            }
        }
    }

    // 删除
    // 只可能会导致父节点失衡
    // 让父节点恢复平衡后，可能会导致更高层的祖先节点失衡
    @Override
    protected void afterRemove(BinaryTree.Node<E> node,Node<E> replacement) {
        super.afterRemove(node,replacement);
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalance(node);
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<E>(element, parent);
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    // 恢复平衡
    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                rotateRight(grand);
            } else { // LR
                rotateLeft(parent);// 先左旋转
                rotateRight(grand);
            }
        } else {
            if (node.isLeftChild()) {// RL
                rotateRight(parent);
                rotateLeft(grand);
            } else {// RR
                rotateLeft(grand);

            }
        }

    }

    @Override
    protected void afterRotate(BinaryTree.Node<E> grand, BinaryTree.Node<E> parent, BinaryTree.Node<E> child) {
        super.afterRotate(grand, parent, child);
        // 更新高度
        updateHeight(grand);
        updateHeight(parent);
    }

    // 判断是否平衡
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    private static class AVLNode<E> extends Node<E> {
        // 默认高度为1
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        // 计算平衡因子
        public int balanceFactor() {
            return getLeftHeight() - getRightHeight();
        }

        public void updateHeight() {
            height = Math.max(getLeftHeight(), getRightHeight()) + 1;//
        }

        private int getLeftHeight() {
            return left == null ? 0 : ((AVLNode<E>) left).height;
        }

        private int getRightHeight() {
            return right == null ? 0 : ((AVLNode<E>) right).height;
        }

        // 获取高度高的child
        public Node<E> tallerChild() {
            if (getLeftHeight() > getRightHeight())
                return (AVLNode<E>) left;
            if (getLeftHeight() < getRightHeight())
                return (AVLNode<E>) right;
            // 如果高度相同
            return isLeftChild() ? left : right;
        }

    }

}
