# 第4章 图

## .4.1 无向图

### 4.1.1 术语

### 4.1.2 表示无向图的数据类型



#### 4.1.2.1 图的几种表示方法

#### 4.1.2.2 邻接表的数据结构

```java
public class Graph {
    private final int V; //顶点数目
    private int E;//边的数目
    private Bag<Integer>[] adj;//邻接表
    //创建一个含有V个顶点但不含有边的图
    public Graph(int V){
        this.V= V;
        this.E = 0;
        adj = (Bag<Integer>[])new Bag[V]; //创建邻接表
        for (int v = 0; v < V; v++) { //将所有链表初始化为空
            adj[v] = new Bag<Integer>();
        }
    }

    //从标准输入流in读入一幅图
    public Graph(In in){
        this(in.readInt()); //读取V并将图初始化
        int E= in.readInt(); //读取E
        for (int i = 0; i < E; i++) {
            int v= in.readInt();//读取一个顶点
            int w = in.readInt();//读取另一个顶点
            addEdge(v,w);//添加一条连接他们的边
        }
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }
    //向图中添加一条边v-w
    void addEdge(int v,int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }
    //和v相邻的所有顶点
    Iterable<Integer> adj(int v){
        return adj[v];
    }
    @Override
    public String toString() {
        String s = V + "vertices," + E + "edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ": ";
            for (int w : this.adj(v)) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }
}
```

![](../.gitbook/assets/image%20%2814%29.png)

#### 4.1.2.3 图的处理算法的设计模式

### 4.1.3 深度优先搜索

#### 4.1.3.1 走迷宫

```java
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;
    public DepthFirstSearch(Graph G,int s){
        marked = new boolean[G.V()];
        dfs(G,s);
    }
    private void dfs(Graph G,int v){
        marked[v]=true;
        count++;
        for (int w : G.adj(v)) {
            if(!marked[w]) 
                dfs(G,w);
        }
    }
    public boolean marked(int w){
        return marked[w];
    }
    public int count(){
        return count;
    }
}
```

```java
public class TestSearch {
    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (depthFirstSearch.marked(v)) {
                StdOut.print(v + " ");
            }
        }
        StdOut.println();
        if (depthFirstSearch.count() != G.V()) {
            StdOut.print("NOT ");
        }
        StdOut.println("connected");
    }
}
```

```bash
% java TestSearch tinyG.txt 0 012345 6
NOT connected
% java TestSearch tinyG.txt 9
9 10 11 12
NOT connected
```

![](../.gitbook/assets/image%20%287%29.png)

#### 4.1.3.2 热身

#### 4.1.3.3 单向通道

#### 4.1.3.4 跟踪深度优化搜索

![](../.gitbook/assets/image%20%2815%29.png)

#### 4.1.3.5 深度优化搜索的详细轨迹

![](../.gitbook/assets/image%20%2830%29.png)

### 4.1.4 寻找路径



