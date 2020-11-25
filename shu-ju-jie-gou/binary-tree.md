# 二叉树

## 树（Tree）

我们首先来看，什么是“树”？再完备的定义，都没有图直观。

![](../.gitbook/assets/image%20%2846%29.png)

这里面每个元素我们叫做“节点”；用来连接相邻节点之间的关系，我们叫做“父子关系”。

比如下面这幅图，A 节点就是 B 节点的父节点，B 节点是 A 节点的子节点。B、C、D 这三个节点的父节点是同一个节点，所以它们之间互称为兄弟节点。我们把没有父节点的节点叫做根节点，也就是图中的节点 E。我们把没有子节点的节点叫做叶~~_子节点_~~或者叶节点，比如图中的 G、H、I、J、K、L 都是叶子节点。

![](../.gitbook/assets/image%20%2844%29.png)

除此之外，关于“树”，还有三个比较相似的概念：高度（Height）、深度（Depth）、层（Level）。它们的定义是这样的：

![](../.gitbook/assets/image%20%2839%29.png)

![](../.gitbook/assets/image%20%2843%29.png)

## 二叉树的遍历

* 前序遍历是指，对于树中的任意节点来说，先打印这个节点，然后再打印它的左子树，最后打印它的右子树。
* 中序遍历是指，对于树中的任意节点来说，先打印它的左子树，然后再打印它本身，最后打印它的右子树。
* 后序遍历是指，对于树中的任意节点来说，先打印它的左子树，然后再打印它的右子树，最后打印这个节点本身。

![](../.gitbook/assets/image%20%2841%29.png)

### 前序遍历

#### 递归实现

```java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root != null){
             list.add(root.val);
             list.addAll(preorderTraversal(root.left));
             list.addAll(preorderTraversal(root.right));
        }
        return list;
    }
}
```

#### 迭代实现

![](../.gitbook/assets/image%20%2840%29.png)

```java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        LinkedList<TreeNode> stack = new LinkedList();
        stack.push(root);
        while(!stack.isEmpty()){
            root = stack.pop();    
            list.add(root.val);
            if(root.right!=null){
                stack.push(root.right);
            }
            if(root.left!=null){
                stack.push(root.left);
            }
        }
        return list;
    }
}
```

### 中序遍历

#### 递归实现

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root != null){
            list.addAll(inorderTraversal(root.left));
            list.add(root.val);
            list.addAll(inorderTraversal(root.right));
        }
        return list;
    }
}
```

#### 迭代实现

![](../.gitbook/assets/image%20%2847%29.png)

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        while(!stack.isEmpty()||root!=null){
            while(root!=null){
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            list.add(node.val);
            if(node.right!=null){
                root = node.right;
            }
        }
        return list;
    }
}
```

### 后序遍历

#### 递归实现

```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root != null){
            list.addAll(postorderTraversal(root.left));
            list.addAll(postorderTraversal(root.right));
            list.add(root.val);
        }
        return list;
    }
}
```

#### 迭代实现

```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        if(root == null){
            return list;
        }
        LinkedList<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            list.addFirst(node.val);
            if(node.left!=null){
                stack.push(node.left);
            }
            if(node.right!=null){
                stack.push(node.right);
            }
        }
        return list;
    }
}
```

## LeetCode试题

* [144. 二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/)
* [94. 二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)
* [145. 二叉树的后序遍历](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)
* [226. 翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/)

