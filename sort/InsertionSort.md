# 插入排序

```java
public class InsertionSort {
    public static void main(String[] args) {
        int[] array = {3, 5, 4, 1, 2, 6};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i;
            while (j > 0 && array[j - 1] > key) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = key; //插入
        }
    }
}
```