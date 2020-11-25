# 第3章 查找

## 3.1 符号表

### 3.1.1 API

### 3.1.2 有序符号表

### 3.1.3 用例举例

### 3.1.4 无序链表中的顺序查找

### 3.1.5 有序数组中的二分查找

```java
import edu.princeton.cs.algs4.BinarySearch;

/**
 * Created by malk on 2018/12/25.
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public void delete(Key key) {
        put(key, null);
    }

    boolean contains(Key key) {
        return get(key) != null;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return values[i];
        else return null;
    }

    //基于有序数组的二分查找
    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        //如果未找到则返回lo，lo是一个临界值，lo位置的数据比key大，lo+1位置的数据比key小
        //可以看下图的二分查找排名
        return lo;
    }

    public void put(Key key, Value value) {
        //通过二分查找查找key
        int i = rank(key);
        //存在key
        if (i < N && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        //插入新元素前将所有较大的键向后移动一格
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }

}
```

![&#x4E8C;&#x5206;&#x67E5;&#x627E;&#x6392;&#x540D;&#x8F68;&#x8FF9;](../.gitbook/assets/image%20%289%29.png)

### 3.1.6 对二分查找的分析

### 3.1.7 预览

## 3.2 二叉查找树

```java
import edu.princeton.cs.algs4.Queue;

/**
 * Created by malk on 2018/12/25.
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root; //二叉查找树的根节点

    private class Node {
        private Key key; //键
        private Value value; //值
        private Node left, right; //指向子树的链接
        private int N; //以该 节点为跟的子树中的结点总数

        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }

    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.value;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        //如果key存在于以x为根节点的子树中则更新它的值;
        //否则将一key和value为键值对的新节点插入到该子树中
        if (x == null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;

    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

}
```

### 3.2.1 基本实现

#### 3.2.1.1 数据表示

#### 3.2.1.2 查找

## 3.3 平衡查找树

### 3.3.1 2-3查找树

#### 3.3.1.1 查找

#### 3.3.1.2 向2-结点中插入新键

### 3.3.2 红黑二叉查找树

### 3.3.3 实现

### 3.3.4 删除操作

### 3.3.5 红黑树的性质

## 3.4 散列表

