# Rabin-Karp指纹字符串查找算法

前一个子字符串和后一个子字符串对应关系
$$
x_{i}=t_{i} R^{M-1}+t_{i+1} R^{M-2}+\ldots+t_{i+M-1} R^{0}
$$

$$
\begin{aligned} x_{i+1} &=t_{i+1} R^{M-1}+t_{i+2} R^{M-2}+\ldots+t_{i+M} R^{0} \\ &=\left(t_{i+1} R^{M-2}+t_{i+2} R^{M-3}+t_{i+M-1} \cdot R^{0}\right) R+t_{i+m} \\ &=\left(x_{i}-t_{i} R^{M-1}\right) R+t_{i+M} \end{aligned}
$$

根据上面的公式可以得到`hash`计算

```java
txtHash = (txtHash + q - RM*txt.charAt(i-m) % q) % q; //减去第一个数字
txtHash = (txtHash*R + txt.charAt(i)) % q;  //加上最后一个数字
```

```java
public class RabinKarp {
    private String pat;      // 模式字符串（仅拉斯维加斯算法需要）
    private long patHash;    // 模式字符串的散列值
    private int m;           // 模式字符串的长度
    private long q;          // 一个很大的素数
    private int R;           // 字母表的大小
    private long RM;         // R^(M-1) % Q

    public RabinKarp(String pat) {
        this.pat = pat;      // save pattern (needed only for Las Vegas)
        R = 256;
        m = pat.length();
        q = longRandomPrime();

        // 计算 R^(m-1) % q 用于减去第一个数字时的计算
        RM = 1;
        for (int i = 1; i <= m-1; i++)
            RM = (R * RM) % q;
        patHash = hash(pat, m);
    } 

    // Compute hash for key[0..m-1]. 
    private long hash(String key, int m) { 
        long h = 0; 
        for (int j = 0; j < m; j++) 
            h = (R * h + key.charAt(j)) % q;
        return h;
    }

    // Las Vegas version: does pat[] match txt[i..i-m+1] ?
    private boolean check(String txt, int i) {
        for (int j = 0; j < m; j++) 
            if (pat.charAt(j) != txt.charAt(i + j)) 
                return false; 
        return true;
    }

    // Monte Carlo version: always return true
    // private boolean check(int i) {
    //    return true;
    //}
 
    /**
     * Returns the index of the first occurrrence of the pattern string
     * in the text string.
     *
     * @param  txt the text string
     * @return the index of the first occurrence of the pattern string
     *         in the text string; n if no such match
     */
    public int search(String txt) {
        int n = txt.length(); 
        if (n < m) return n;
        long txtHash = hash(txt, m); 

        //一开始就匹配成功
        if ((patHash == txtHash) && check(txt, 0))
            return 0;

        // check for hash match; if hash match, check for exact match
        for (int i = m; i < n; i++) {
            txtHash = (txtHash + q - RM*txt.charAt(i-m) % q) % q; //减去第一个数字
            txtHash = (txtHash*R + txt.charAt(i)) % q;  //加上最后一个数字
            // match
            int offset = i - m + 1;
            if ((patHash == txtHash) && check(txt, offset))
                return offset;
        }

        // no match
        return n;
    }


    // a random 31-bit prime
    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    /** 
     * Takes a pattern string and an input string as command-line arguments;
     * searches for the pattern string in the text string; and prints
     * the first occurrence of the pattern string in the text string.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String pat = args[0];
        String txt = args[1];

        RabinKarp searcher = new RabinKarp(pat);
        int offset = searcher.search(txt);

        // print results
        System.out.println("text:    " + txt);

        // from brute force search method 1
        System.out.print("pattern: ");
        for (int i = 0; i < offset; i++)
            System.out.print(" ");
        System.out.println(pat);
    }
}
```

