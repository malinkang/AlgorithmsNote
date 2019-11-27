# 冒泡排序

```java
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = { 3, 5, 4, 1, 2, 6 };
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] array) {
        //一共需要进行n-1趟比较 因为从0开始所以n = length-1
        for (int i = 0; i < array.length - 1; i++) {
            //设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已然完成。
            boolean flag = true;
            //每趟需要n-i次计较，因为从0开始所以n = length-1
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    flag = false;
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            if (flag) {
                break;
            }
        }
    }
}
```

