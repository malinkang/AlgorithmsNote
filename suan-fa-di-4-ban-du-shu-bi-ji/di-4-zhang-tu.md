# 第4章 图

## 4.1 无向图

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

![](../.gitbook/assets/image%20%2812%29.png)

