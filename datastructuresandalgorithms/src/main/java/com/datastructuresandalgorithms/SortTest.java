package com.datastructuresandalgorithms;

public class SortTest {

    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] arr = {8, 7, 6, 5, 4, 3, 2, 1};
//        int[] arr = {7, 4, 1, 5, 2, 8, 4,0};

//        mpSort(arr);
//        crSort(arr);
//        xzSort(arr);
        gbSort(arr);
    }


    /**
     * 冒泡排序
     * 6位数比较5次，
     * 依次使用数组中相邻的两个数比较，把大的数往后移。
     * 如果某次没有移动，说明比较下来已经全部按顺序排列好了，要不然比较肯定会有数的移动。
     * <p>
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(1)
     * 原地排序算法：是
     * 稳定：是
     *
     * @param arr
     */
    public static void mpSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    /**
     * 插入排序
     * 原理：默认将数组中的下标为0个数当成第一个已经被插入的数。
     * 排序时从下标i=1的数开始，将i=1的数记录到temp，依次与下标<i的数比较，如果小于i的数大于下标为i的数，则将<i的数向前移动一位，然后继续和再前一位数比较。
     * 下标<i的数一定是有序的（所以如果i-1大于temp才有可能i-2也大于temp，如果i-1小于temp那么更前面的数肯定也小于temp，因为i前面的数都是有序的）。
     * 如果小于i的数小于temp，那么终止比较，然后将temp插入到当前比较数的后一位（下标+1位）。
     * 例如：3 2 1 4 5
     * temp=2；
     * 下标为0的3>2 所以将下标为0的3后移一位，移动后 3 3 1 4 5
     * 然后继续走循环后下标减减，得到下标为-1，然后-1是小于0的，所以断开循环，
     * 然后再将temp插入到下标-1+1的位置，也就是下标为0的位置。得到2 3 1 4 5
     * 这样前2位2 3就排序好了，后面的以此类推就可以了。类推后的数组如下
     * temp=1；
     * 3>1，移动后如下：
     * 2 3 3 4 5
     * 2>1，移动后如下：
     * 2 2 3 4 5
     * 将temp插入后如下：
     * 1 2 3 4 5
     * <p>
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(1)
     * 原地排序算法：是
     * 稳定：是
     *
     * @param arr
     */
    public static void crSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > temp) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = temp;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    /**
     * 选择排序
     * 原理：
     * 选择排序第一次优先将数组最小的数插入到下标为i=0的数组下标中。
     * 第二次将除开i=0的数中找出最小的数插入i=1的下标中。
     * 第三次将除开i=0、i=1的数中找出最小的数插入到i=2的下标中。
     * 依次类推，一直到数组长度的最后一个数。
     * <p>
     * 逻辑：
     * 先默认将当前循环下标的数值使用temp记录起来，因为它需要与后续最小的数交换位置。
     * 然后默认当前循环下标的数为最小的数，记录他的下标min。
     * 然后依次用下标最小的数与后面的数比较（注意这里是从数组最后的一个数开始与最小的数比较），
     * 如果遇到比此数更小的数则将其替代min的下标值。
     * 此次外围循环结束后就能找到当前数中的最小的数，然后与当前外围循环的下标位置交换，则已经排好一轮。
     * <p>
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(1)
     * 原地排序算法：是
     * 稳定：否
     *
     * @param arr
     */
    public static void xzSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int min = i;
            int j = arr.length - 1;
            for (; j > i; j--) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            arr[i] = arr[min];
            arr[min] = temp;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    /**
     * 归并排序
     * 归并排序的要点：先拆分，然后合并。
     * （下面的left right mid 都为下标位置。）
     * 拆分：每次将数组拆分为左右两部分，一直拆分到左边的下标等于右边的下标时就不再拆分，开始返回。
     * 合并：将左右两边的数组合并，合并时依次按左右两边下标的首个位置开始比较，数值小的按顺序赋值到temp数组。
     * 一直到左右两边下标达到mid或right的位置则停止，然后将没到达mid或right的下标重新遍历，然后依次加入到
     * temp数组（这里加入时肯定是有序的，因为左右两边的下标表示的区间内的数肯定是有序的），都遍历进temp后，
     * 再依次将temp中的数一一赋值进原数组arr对应的当次递归的left到right的位置，则此次递归的left到right位置就排好序了。
     * 例如：
     * 4,2,3,1
     * 拆分：
     * 4,2      3,1
     * 拆分：
     * 4    2   3   1
     * 合并：
     * 2<4 temp = {2}
     * 左边还剩4，直接加入到temp = {2,4}
     * 1<3 temp = {1}
     * 左边还剩3，直接加入到temp = {1,3}
     * 2,4  1,3
     * 合并：
     * 1<2则  temp = {1}
     * 2,4  3 temp = {1}
     * 2<3则  temp = {1,2}
     * 4    3 temp = {1,2}
     * 3<4则  temp = {1,2,3}
     * 右边都加入完了，左边的2,4中还剩一个4，那么直接加入到temp = {1,2,3,4}
     * 这样就排序完了。
     * 时间复杂度：O(n*logN)
     * 空间复杂度：O(n)
     * 原地排序算法：否
     * 稳定：是
     *
     * @param arr 要排序的数组
     */
    public static void gbSort(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int[] temp = new int[arr.length];
        splitArr(arr, left, right, temp);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    /**
     * 拆分逻辑
     *
     * @param arr   原数组
     * @param left  拆分成两份后的左下标
     * @param right 拆分成两份后的右下标
     * @param temp  用于临时存储数据的数组
     */
    public static void splitArr(int[] arr, int left, int right, int[] temp) {
        // 递归的终止条件。
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        splitArr(arr, left, mid, temp);
        splitArr(arr, mid + 1, right, temp);
        mergeArr(arr, left, mid, right, temp);
    }

    /**
     * 合并逻辑
     * 递归到最深时，第一次合并的必定是数组中只有一个值的数组。
     * 这样就是直接开始比较大小然后合并
     *
     * @param arr   原数组
     * @param left  拆分成两份后的左下标
     * @param mid   中间位置下标
     * @param right 拆分成两份后的右下标
     * @param temp  用于临时存储数据的数组
     */
    public static void mergeArr(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int k = 0;
        // 从left和right的最左边开始比较。更小的放入temp
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        // 上面没完全放入temp的，这里直接放入
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // 上面没完全放入temp的，这里直接放入
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        k = 0;
        // 将temp里面排好序的直接放到arr的left到right的下标位置
        // 增加temp是为了临时存放合并的数值，最后直接插入arr的位置，
        // 只改变了arr中需要合并的数值，对其他下标位置不影响，降低了空间复杂度
        while (left <= right) {
            arr[left++] = temp[k++];
        }
    }


    /**
     * 快速排序
     * @param arr 原数组
     */
    public static void quickSort(int[] arr){

    }

}
