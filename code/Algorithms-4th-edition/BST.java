/**
 * BST
 */
public class BST<Key extends Comparable<Key>, Value> {
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N; // 以该节点为根的子树中的节点总数

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    private Node root;// 根几点

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        // 如果找不到直接返回null
        if (x == null)
            return null;
        int cmp = x.key.compareTo(key);
        if (cmp > 0)
            return get(x.left, key);
        else if (cmp < 0)
            return get(x.right, key);
        else
            return x.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    public Node put(Node x, Key key, Value val) {
        if (x == null)
            return new Node(key, val, 1);
        int cmp = x.key.compareTo(key);
        if (cmp > 0)
            return put(x.left, key, val);
        else if (cmp < 0)
            return put(x.right, key, val);
        else
            x.val = val; // 覆盖值
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // 获取最小值
    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null)
            return x;
        return min(x.left);
    }

    // 获取小于等于Key的最大值
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        // 如果给定的键Key小于二叉查找树的根结点的键
        // 那么小于等于key的最大键floor(key)一定在根节点的左子树中
        if (cmp < 0)
            return floor(x.left, key);
        // 如果给定的键key大于二叉查找树的根结点，
        //那么只有当根结点右子树中存在小于等于key的结点时,
        //小于等于key的最大键才会出现在右子树中
        Node t = floor(x.right, key);
        if (t != null)
            return t;
        //否则根结点就是小于等于key的最大键
        return x;

    }

}