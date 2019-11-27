# 希尔排序

```java
public class ShellSort {
    public static void main(String[] args) {
        int[] array = { 3, 5, 4, 1, 2, 6 };
        sort(array);
        System.out.println(Arrays.toString(array));
    }
    private static void sort(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int key = array[i];
                int j = i;
                //普通排序可以看做gap=1
                while (j >= gap && array[j - gap] > key) {
                    array[j] = array[j - gap];
                    j -= gap;
                }
                array[j] = key;
            }
        }
    }
}
```

