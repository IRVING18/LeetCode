package com.jiejiedai.api.leetcode;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例 1：
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 */
public class Test {
    public static void main(String[] args) {


        int[] arr = {9, 1, 0, 10, -1, 8, 5, 100, 1};
//        System.out.println(Arrays.toString(bubbleSort(arr)));
//        System.out.println(Arrays.toString(selectSort(arr)));
//        System.out.println(Arrays.toString(insertSort(arr)));
//        System.out.println(Arrays.toString(shellSort(arr)));
//        System.out.println(Arrays.toString(quickSort(arr, 0, arr.length - 1)));
//        System.out.println(Arrays.toString(heapSort(arr)));

    }

    private static int[] bubbleSort(int[] arr) {

        int len = arr.length - 1;
        for (int i = 0; i < len; i++) {
            int swapPosition = 0;
            for (int j = 0; j < len; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    swapPosition = j;
                }
            }
            if (swapPosition == 0) {
                break;
            } else {
                len = swapPosition;
            }
        }

        return arr;
    }


    private static int[] selectSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            int tmp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = tmp;
        }

        return arr;

    }


    private static int[] insertSort(int[] arr) {
        //1、从1开始，因为将第0个元素看成一个数组，并且他就是有序的
        for (int i = 1; i < arr.length; i++) {
            int sw = arr[i];
            //2、如果当前数据小于，arr数组的最后一个元素，那么就说明需要进行排序。否则不需要
            int j = i - 1;
            if (sw < arr[j]) {
                //3、循环和数组的值进行比较。
                while (j >= 0 && sw < arr[j]) {
                    //4、如果sw小于arr最后的值，就交换一次，给sw腾出位置
                    arr[j + 1] = arr[j];
                    //5、并且将数组往前挪一位，继续比较
                    j--;
                }
                //6、当比较完成时，将sw放在当前的j + 1位置上。
                arr[j + 1] = sw;
            }
        }
        return arr;
    }


    private static int[] shellSort(int[] arr) {
        int len = arr.length;
        for (int gap = len / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < len; i++) {
                shellSortInside(arr, i, gap);
            }
        }
        return arr;
    }

    private static void shellSortInside(int[] arr, int i, int gap) {
        int sw = arr[i];
        int j = i - gap;
        if (sw < arr[j]) {
            while (j >= 0 && sw < arr[j]) {
                arr[j + gap] = arr[j];
                j -= gap;
            }
            arr[j + gap] = sw;
        }
    }


    /**
     * 1、选取第一个元素当成基准数
     * 2、将比基准数大的放后边，基准数小的放前边
     * 3、递归调用，基准数左右两端的数组。直到数组个数为1
     */
    private static int[] quickSort(int[] arr, int low, int high) {

        if (low >= high) {
            return arr;
        }
        int first = low;
        int last = high;
        int sw = arr[first];

        while (first < last) {
            //1、从last往前推，找到比基准数小的，放到基准数的first位置上
            //2、从first往后推，找到比基准数大的，放到刚才移动过的last上
            while (first < last && arr[last] > sw) {
                last--;
            }
            if (first < last) {
                arr[first++] = arr[last];
            }

            while (first < last && arr[first] < sw) {
                first++;
            }
            if (first < last) {
                arr[last--] = arr[first];
            }
        }

        arr[last] = sw;

        quickSort(arr, last + 1, high);
        quickSort(arr, low, last - 1);
        return arr;
    }


    private static int[] heapSort(int[] arr) {
        //1、建立大顶堆，从len/2-1 最后一个非叶子节点开始构建堆。直到i = 0
        int len = arr.length;
        for (int i = len / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, len);
        }

        //2、每次从堆顶拿最大的数，放到最后一个。然后重新建堆
        for (int i = len - 1; i >= 0; i--) {
            int tmp = arr[i];
            arr[i] = arr[0];
            arr[0] = tmp;

            adjustHeap(arr, 0, i);
        }


        return arr;
    }

    private static void adjustHeap(int[] arr, int parentIndex, int len) {
        //1、获取要排序的堆顶数据
        int parent = arr[parentIndex];
        //2、堆顶和 左右子节点 比较大小。获取左子树角标：2 * 堆顶坐标 + 1；
        //3、将比堆顶大的子节点换到堆顶位置。
        //4、将交换过的子节点，继续和它的子节点进行比较，看是否需要重建
        //5、不需重建后停止
        for (int left = parentIndex * 2 + 1; left < len; left = 2 * left + 1) {
            //求出左右子树中最大的角标
            //left + 1是右子树
            //右子树 < len && 右子树 > 左子树。那么就用右子树去和堆顶比较
            int max = left;
            if (left + 1 < len && arr[left + 1] > arr[left]) {
                max = left + 1;
            }

            //将大的子树和堆顶比较，
            // 如果大于堆顶，交换数据，并将交换的子树当成堆顶角标，继续循环
            // 如果小于堆顶，说明不用重建堆
            if (arr[max] > parent) {
                arr[parentIndex] = arr[max];
                arr[max] = parent;

                parentIndex = max;
            } else {
                break;
            }
        }


    }


    private static void heapSort1(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap1(arr, i, arr.length);
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;

            adjustHeap1(arr, 0, i);
        }
    }

    private static void adjustHeap1(int[] arr, int parentIndex, int len) {
        int parent = arr[parentIndex];
        for (int left = parentIndex * 2 + 1; left < len; left = left * 2 + 1) {
            int maxIndex = left;
            if (left + 1 < len && arr[left + 1] > arr[left]) {
                maxIndex = left + 1;
            }

            if (arr[maxIndex] > parent){
                arr[parentIndex] = arr[maxIndex];
                arr[maxIndex] = parent;

                parentIndex = maxIndex;
            }else {
                break;
            }
        }
    }
}
