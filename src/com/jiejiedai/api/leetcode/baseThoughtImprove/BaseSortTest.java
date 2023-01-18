package com.jiejiedai.api.leetcode.baseThoughtImprove;

import com.sun.org.apache.xpath.internal.operations.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 十种基础排序
 * <p>
 * 稳定排序：两个相同的数，排序之后顺序不变 ，a、b -> a、b
 * 非稳定排序：两个相同的数，排序之后顺序可能变化，a、b ->  b、a
 * <p>
 * 原地排序：不申请多余的数组空间
 * 非原地排序：需要额外数组空间辅助排序
 */
public class BaseSortTest {


    /**
     * 冒泡：稳定排序、原地排序、时间复杂度O(n^2) 、空间复杂度O(1)
     * <p></>
     * 将最大或者最小的数放到数组最后
     *
     * 冒泡排序优化第二版
     */
    private static int[] bubbleSort(int[] arr) {
        int len = arr.length;
        int swapLen = len - 1;
        //开始比较趟数循环
        for (int i = 0; i < len - 1; i++) {
            boolean swapFlag = false;
            int tmpPosition = 0;
            //依次的比较相邻两个数的大小，遍历一次后，把数组中第i小的数放在第i个位置上
            for (int j = 0; j < swapLen; j++) {
                // 比较相邻的元素，如果前面的数小于后面的数，交换
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapFlag = true;
                    tmpPosition = j;
                }
            }
            //但若某一趟中一次元素交换都没有，那么说明数组已经排序完成了。
            //不必再进行趟数比较，外层循环应该结束，即此时if (!flag) break; 跳出循环
            if (!swapFlag) {
                break;
            }
            //把最后一次交换的位置给len，来缩减内循环的次数
            len = tmpPosition;
        }
        return arr;
    }

    /**
     * 选择排序：非稳定排序、原地排序、时间复杂度O(n^2）、空间复杂度O(1)
     * <p></p>
     * 选择出数组中最小或最大的数，放在前边的角标位置，依次排序
     * 每次选出后续数组中最小的数，放在数组最前边。
     *
     * @return
     */
    private static int[] selectSort(int[] arr) {
        int len = arr.length;
        //数组循环次数
        for (int i = 0; i < len - 1; i++) {
            //选出最小值的角标
            int minIndex = i;
            //在后续数组中选出最小值的角标，
            for (int j = i + 1; j < len; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }

        return arr;

    }

    /**
     * 插入排序：稳定排序、原地排序、时间复杂度O(n^2) 、空间复杂度O(1)
     * <p></p>
     * 循环取出数组数据，将该数据默认插入到之前的数组中，默认将之前的数组看做有序数组
     *
     * @param arr
     * @return
     */
    private static int[] insertSort(int[] arr) {
        int len = arr.length;
        //默认从第二个数据开始，作为待插入数据；这样的目的是，将第一个元素看做单元素数组，那么它也一定是有序的。
        for (int i = 1; i < len; i++) {
            //待插入数据
            int sw = arr[i];
            //如果待插入数据小于之前数组的最大值，才需要插入，否则待插入数据就是正确的排序
            if (sw < arr[i - 1]) {
                //将i之前的数据当成有序数组，将sw插入其中
                int j = i - 1;
                while (j >= 0 && sw < arr[j]) {
                    //如果待插入数据小于前数组的最大值，那么将前数据的最大值角标外后移一位，未插入数据留出空位。
                    arr[j + 1] = arr[j];//元素后移
                    j--;//角标前移
                }
                //将sw插入到正确位置
                arr[j + 1] = sw;
            }
        }

        return arr;
    }

    /**
     * 希尔排序：非稳定性排序、原地排序、时间复杂度O(n^1.3 ~ 2)，空间复杂度O(1)
     * <p></p>
     * 插入排序的变种，插入排序计算高效的优势在于：
     * 1、数组基本有序时
     * 2、小规模数据
     * 所以在这个基础上，诞生了希尔排序
     * <p></p>
     * 外层循环：将数组按间隔gap拆分成多个数组，指到gap变成1，也就是等于不拆分数组。
     * 内层循环：对拆分后的数组进行插入排序。
     *
     * @param arr
     */
    private static void shellSort(int[] arr) {
        //[5,1,3,6,10,2]
        int len = arr.length;
        //初始间隔gap为数组的一半
        for (int gap = len / 2; gap > 0; gap /= 2) {
            //对各个分组进行插入排序，从gap开始：和插入排序从1开始一样，默认将每个数组的第一个元素当成有序数组。
            for (int i = gap; i < len; i++) {
                //将arr[i]插入到所在分组的正确位置上。
                shellInsert(arr, gap, i);
            }
        }
    }

    /**
     * 希尔内部的插入排序，只是间隔不再是简单的-1，而是-gap
     *
     * @param arr
     * @param gap
     * @param i
     */
    private static void shellInsert(int[] arr, int gap, int i) {
        int insert = arr[i];
        if (insert < arr[i - gap]) {
            int j = i - gap;
            while (j >= 0 && insert < arr[j]) {
                swap(arr, j, j + gap);
                j -= gap;
            }
            arr[j + gap] = insert;
        }
    }


    /**
     * 快速排序：非稳定排序、平均时间复杂度O(nlog(n))、最差空间复杂度O(log(n))
     * <p></p>
     * 递归方式，将数组中第一个数放在中间位置上，然后将数组根据这个中间数分成两个数组，递归调用两端数组排序，直到两端数组不需要排序。
     *
     * 1、选取第一个元素当成基准数
     * 2、将比基准数大的放后边，基准数小的放前边
     * 3、递归调用，基准数左右两端的数组。直到数组个数为1
     * @param a
     * @param low
     * @param high
     */
    private static void quickSort(int[] a, int low, int high) {
        //[5,1,3,6,10,2]
        //如果低位角标不再小于高位角标时，不需要再进行排序
        if (low >= high) {
            return;
        }
        int first = low;
        int last = high;
        //待排序值，目标：将这个值放到数组low->high的中间位置
        int key = a[low];

        while (first < last) {
            //1、从last往前推，将比key小的值放到前边
            //2、从first往后推，将比key大的值放到后边

            //如果a[last]最后的值大于key，将last--；即角标往前推一位。
            // 直到找到first->last中间小于key的角标。
            // 如果last已经推到first位置仍然没有找到小于key的值，那么说明key往后的值都大于key，就不需要移动了。
            while (first < last && a[last] > key) {
                last--;
            }
            if (first < last) {
                //tips：这注意下，first++是先使用first的值，然后才做+1动作。
                //等同于 a[first] = a[last]; first++;两个语句
                a[first++] = a[last];
            }

            //从first角标往后推，找到比key大的数放到last的位置上。
            // - 因为上边那个循环如果移动了位置，就是将last位置的数据放到了first上，然后将first++了，
            // - 所以现在这个循环就是找到大于key的数补充到last的位置上。
            // - 如果没找到，那么first循环+1，最终就不再满足first<last循环，整个循环体就会结束，那么最后就把key的值赋值给last位置了。
            while (first < last && a[first] < key) {
                first++;
            }
            if (first < last) {
                a[last--] = a[first];
            }
        }
        //最终将key放到last的位置上。
        //因为最终只有last位置上的数据是被移动过的。
        //而last的位置就是key应该放置的中间位置。
        a[last] = key;

        //通过key当前的中间位角标last，拆成两个数组递归调用
        //递归调用后半部分数组
        quickSort(a, last + 1, high);
        //递归调用前半部分数组
        quickSort(a, low, last - 1);
    }



    /**
     * 归并排序（分治法的典型案例）：稳定性排序、非原地排序、时间复杂度O(nlog(n))，空间复杂度O(n)
     * <p></p>
     * 循环将数组拆分为二，一直拆分到单独一个为一组，那么就肯定是有序的了。
     * 然后将每一组进行合并，合并时比较大小后放到合并数组中。
     */
    private static int[] mergeSort(int[] arr, int start, int end) {
        //当开始 == 结束角标，那么说明只剩下一个数据了，不需要再拆分了
        if (start == end) {
            return new int[]{arr[start]};
        }
        //从中间进行拆分
        int mid = start + (end - start) / 2;
        System.out.println(mid);
        int[] leftArr = mergeSort(arr, start, mid);//左边数组
        int[] rightArr = mergeSort(arr, mid + 1, end);//右边数组

        int[] newArr = new int[leftArr.length + rightArr.length];//新数组
        int nIndex = 0;
        int lIndex = 0;
        int rIndex = 0;
        //当两端数组都有数据时，合并时，比较两端数据大小，小的先放到新数组中。
        while (lIndex < leftArr.length && rIndex < rightArr.length) {
            //两个数组中，小的数先放到newArr中。
            newArr[nIndex++] = leftArr[lIndex] < rightArr[rIndex] ? leftArr[lIndex++] : rightArr[rIndex++];
        }
        //左边数组没有合并完，继续合并。如果数组元素是单数的时候，就可能会走到这个条件中。
        while (lIndex < leftArr.length) {
            newArr[nIndex++] = leftArr[lIndex++];
        }
        //右边数组没有合并完，继续合并。如果数组元素是单数的时候，就可能会走到这个条件中。
        while (rIndex < rightArr.length) {
            newArr[nIndex++] = rightArr[rIndex++];
        }
        return newArr;
    }

    /**
     * 计数排序：稳定性排序，非原地排序，时间复杂度O(n + k)，空间复杂度O(n + k) k为整数的范围
     * 只适合整数类型排序，非比较型排序
     * <p></p>
     * 原数组：{4, 1, 3, 0, -1}
     * 1、计算数组最大值到最小值之间有多少个整数，并申请一个计数数组count[]
     * 2、将数据的大小当做count数组的角标插入，如果同一个角标多一个就+1。转换完就等于将原数组的数据当成了count数组的角标存储了 [1, 1, 1, 0, 1, 1]
     * 3、将count数组的值，依次加起来，就是原数组数据的排序了。只是这个排序的角标时从1开始的。[1, 2, 3, 3, 4, 5]
     * 4、遍历原数组，通过count[]查找数据的角标位置。
     *
     * @return
     */
    private static int[] countSort(int[] arr) {
        int[] result = new int[arr.length];
        int max = arr[0], min = arr[0];
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
            if (value < min) {
                min = value;
            }
        }
        int k = max - min + 1;
        int[] count = new int[k];
        for (int value : arr) {
            //将arr数据当成角标
            int countIndex = value - min;
            count[countIndex] += 1;
        }
        System.out.println(Arrays.toString(count));

        //将count数组的值，转换成排序位置
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        System.out.println(Arrays.toString(count));

        for (int i = arr.length - 1; i >= 0; i--) {//倒叙是为了保证稳定性
            //源数据就是count的角标，而count的值对应的就是排序后的顺序，只是从1开始的。所以-1

            //计算count数组的角标，这个角标就是源数据的值-min
            int countIndex = arr[i] - min;
            //通过count角标获取count数组的值，而这个值就是新数组的角标。
            int rIndex = count[countIndex] - 1;
            //这个arr[i]其实就是 countIndex + min
            result[rIndex] = arr[i];
        }

        return result;
    }

    private static void swap(int[] arr, int source, int target) {
        int tmp = arr[source];
        arr[source] = arr[target];
        arr[target] = tmp;
    }


    /**
     * 选择排序-堆排序
     *
     * @param array 待排序数组
     * @return 已排序数组
     */
    public static int[] heapSort(int[] array) {
        //这里元素的索引是从0开始的,所以最后一个非叶子结点array.length/2 - 1
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);  //调整堆
            System.out.println(Arrays.toString(array));
        }

        // 上述逻辑，建堆结束
        // 下面，开始排序逻辑
        for (int j = array.length - 1; j > 0; j--) {
            // 元素交换,作用是去掉大顶堆
            // 把大顶堆的根元素，放到数组的最后；换句话说，就是每一次的堆调整之后，都会有一个元素到达自己的最终位置
            swap(array, 0, j);
            // 元素交换之后，毫无疑问，最后一个元素无需再考虑排序问题了。
            // 接下来我们需要排序的，就是已经去掉了部分元素的堆了，这也是为什么此方法放在循环里的原因
            // 而这里，实质上是自上而下，自左向右进行调整的
            adjustHeap(array, 0, j);
        }
        return array;
    }

    /**
     * 整个堆排序最关键的地方
     *
     * @param array  待组堆
     * @param i      起始结点
     * @param length 堆的长度
     */
    public static void adjustHeap(int[] array, int i, int length) {
        // 先把当前元素取出来，因为当前元素可能要一直移动
        int temp = array[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {  //2*i+1为左子树i的左子树(因为i是从0开始的),2*k+1为k的左子树
            // 让k先指向子节点中最大的节点
            if (k + 1 < length && array[k] < array[k + 1]) {  //如果有右子树,并且右子树大于左子树
                k++;
            }
            //如果发现结点(左右子结点)大于根结点，则进行值的交换
            if (array[k] > temp) {
                swap(array, i, k);
                // 如果子节点更换了，那么，以子节点为根的子树会受到影响,所以，循环对子节点所在的树继续进行判断
                i = k;
            } else {  //不用交换，直接终止循环
                break;
            }
        }
    }


    public static void basket(int[] data)//data为待排序数组
    {
        int n = data.length;
        int[][] bask = new int[10][n];
        int[] index = new int[10];
        int max = Integer.MIN_VALUE;
        for (int datum : data) {
            max = max > (Integer.toString(datum).length()) ? max : (Integer.toString(datum).length());
        }
        String str;
        for (int i = max - 1; i >= 0; i--) {
            for (int datum : data) {
                str = "";
                if (Integer.toString(datum).length() < max) {
                    for (int k = 0; k < max - Integer.toString(datum).length(); k++)
                        str += "0";
                }
                str += Integer.toString(datum);
                bask[str.charAt(i) - '0'][index[str.charAt(i) - '0']++] = datum;
            }
            int pos = 0;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < index[j]; k++) {
                    data[pos++] = bask[j][k];
                }
            }

            for (int l = 0; l < 10; l++)
                System.out.println(l + " : " + Arrays.toString(bask[l]));
            System.out.println("  " + Arrays.toString(index));
            System.out.println("----------------------");

            for (int x = 0; x < 10; x++) index[x] = 0;


        }

    }

    public static void bucketSort(int[] arr) {
//        {4, 1, 3, 0, -1}
        // 计算最大值与最小值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        // 计算桶的数量
        int bucketNum = (max - min) / arr.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketArr.add(new ArrayList<Integer>());
        }

        // 将每个元素放入桶
        for (int i = 0; i < arr.length; i++) {
            int num = (arr[i] - min) / (arr.length);
            bucketArr.get(num).add(arr[i]);
        }

        // 对每个桶进行排序
        for (int i = 0; i < bucketArr.size(); i++) {
            Collections.sort(bucketArr.get(i));
        }

        // 将桶中的元素赋值到原序列
        int index = 0;
        for (int i = 0; i < bucketArr.size(); i++) {
            for (int j = 0; j < bucketArr.get(i).size(); j++) {
                arr[index++] = bucketArr.get(i).get(j);
            }
        }
    }


//    public static void main(String[] args) {
//        int[] arr = {9, 1, 0, 10, -1, 8, 5, 100, 1};
        //1、冒泡
//        System.out.println(Arrays.toString(bubbleSort3(arr)));
        //2、选择
//        System.out.println(Arrays.toString(selectSort3(arr)));
        //3、插入
//        System.out.println(Arrays.toString(insertSort3(arr)));
        //4、快排
//        int[] arr = {4, 1, 3, 0, -1};
//        quickSort3(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));
        //5、希尔排序
//        System.out.println(Arrays.toString(shellSort3(arr)));
        //6、归并
//        System.out.println(Arrays.toString(mergeSort3(arr, 0, arr.length - 1)));
        //7、堆排序
//        int[] arr1 = {5, 2, 7, 3, 6, 1, 4};
//        System.out.println(Arrays.toString(heapSort3(arr1)));
        //8、计数排序
//        System.out.println(Arrays.toString(countSort3(arr)));
        //9、桶排序
//        int[] arr = {4, 1, 3, 0, 10, 999, -1};
//        basket(arr);
//        System.out.println(Arrays.toString(arr));

//        String str = "a";
//        System.out.println(str.substring(0, 1));
//    }

    /**
     * 1、冒泡：稳定排序、原地排序、时间复杂度O(n^2) 、空间复杂度O(1)
     * <p></>
     * 将最大或者最小的数放到数组最后
     */
    private static int[] bubbleSort3(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        return arr;
    }

    /**
     * 2、选择：非稳定排序、原地排序、时间复杂度O(n^2) 、空间复杂度O(1)
     * <p></>
     */
    private static int[] selectSort3(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
        return arr;
    }

    /**
     * 3、快排：非稳定排序、原地排序、时间复杂度O(nlogN) 、空间复杂度O(1)
     * <p></>
     */
    private static void quickSort3(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int start = low;
        int end = high;

        int sw = arr[low];
        while (start < end) {
            while (start < end && arr[end] > sw) {
                end--;
            }
            if (start < end) {
                arr[start++] = arr[end];
            }
            while (start < end && arr[start] < sw) {
                start++;
            }
            if (start < end) {
                arr[end--] = arr[start];
            }

        }
        arr[start] = sw;

        quickSort3(arr, low, start - 1);
        quickSort3(arr, start + 1, end);

    }

    /**
     * 4、插入：稳定排序、原地排序、时间复杂度O(n^2) 、空间复杂度O(1)
     * <p></>
     */
    private static int[] insertSort3(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            int sw = arr[i];
            int j = i - 1;
            if (arr[j] > sw) {
                while (j >= 0 && arr[j] > sw) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = sw;
            }
        }
        return arr;
    }

    /**
     * 5、希尔：稳定排序、原地排序、时间复杂度O(n ^ 1.3 ) 、空间复杂度O(1)
     * <p></>
     */
    private static int[] shellSort3(int[] arr) {
        int len = arr.length;
        for (int gap = len / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < len; i++) {
                int sw = arr[i];
                int j = i - gap;
                if (arr[j] > sw) {
                    while (j >= 0 && arr[j] > sw) {
                        arr[j + gap] = arr[j];
                        j -= gap;
                    }
                    arr[j + gap] = sw;
                }
            }
        }
        return arr;
    }

    /**
     * 6、归并：非稳定排序、非原地排序、时间复杂度O(nlogN ) 、空间复杂度O(n)
     * <p></>
     */
    private static int[] mergeSort3(int[] arr, int low, int high) {
        if (low == high) {
            return new int[]{arr[low]};
        }
        int mid = low + (high - low) / 2;

        int[] leftArr = mergeSort3(arr, low, mid);
        int[] rightArr = mergeSort3(arr, mid + 1, high);

        int[] newArr = new int[leftArr.length + rightArr.length];
        int newIndex = 0;
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < leftArr.length && rightIndex < rightArr.length) {
            newArr[newIndex++] = leftArr[leftIndex] < rightArr[rightIndex] ? leftArr[leftIndex++] : rightArr[rightIndex++];
        }
        while (leftIndex < leftArr.length) {
            newArr[newIndex++] = leftArr[leftIndex++];
        }
        while (rightIndex < rightArr.length) {
            newArr[newIndex++] = rightArr[rightIndex++];
        }

        return newArr;

    }

    /**
     * 7、堆排序：非稳定排序、非原地排序、时间复杂度O(nlogN ) 、空间复杂度O(n)
     * <p></>
     */
    private static int[] heapSort3(int[] arr) {
        //1、建立大顶堆，从最后一个非叶子结点，往前循环，直到最顶端index = 0
        int len = arr.length;
        for (int i = len / 2 - 1; i >= 0; i--) {
            adjustHeap3(arr, i, len);
        }
        //2、将顶端和最后一位交换，然后用除去最后一位的数组作为新数组，重新调整堆
        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            adjustHeap3(arr, 0, i);
        }


        //调整堆
        //1、获取左子树k = 2 * i + 1，
        //2、如果右子树k+1不超过len，那么就比较两个大小
        //3、用大的子节点和父节点交换
        //4、如果发生了交换，那么就将这个数作为新的父节点i，继续调整。
        //5、如果不需要交换，就结束调整


//        //1、建大顶堆
//        //2、将堆顶数据放到数组最后一位，重新调整堆，直到堆只剩下一个数据
//
////        arr.length / 2 - 1 等于 最后一个非叶子节点
//        for (int i = arr.length / 2 - 1; i >= 0; i--) {
//            adjustHeap3(arr, i, arr.length);
//        }
//
//        for (int i = arr.length - 1; i >= 0; i--) {
//            swap(arr, 0, i);
//            adjustHeap3(arr, 0, i);
//        }
//
//
//        //调整堆：
//        //1、循环获取左子节点
//        //2、获取左子节点、右子节点中较大的角标
//        //3、将这个角标和父节点比较，是否大于父节点，如果大于就和父节点换换
//        //4、换过之后该子节点设置成循环体的父节点，重新给这个子节点下边的子节点排序。
//        //5、如果子节点和父节点不需要交换，就退出循环


        return arr;
    }

    private static int[] adjustHeap3(int[] arr, int parentIndex, int len) {
        //父节点
        int parent = arr[parentIndex];
        //k 为左子节点
        for (int k = 2 * parentIndex + 1; k < len; k = 2 * k + 1) {
            //右节点大于左节点
            if (k + 1 < len && arr[k + 1] > arr[k]) {
                k++;
            }
            if (arr[k] > parent) {
                //将子节点中大的数和父节点交换位置
                swap(arr, k, parentIndex);
                //换完位置之后，被换的子节点可能就需要调整了，所以将k当成父节点继续调整
                parentIndex = k;
            } else {
                break;
            }
        }
        return arr;

    }

    /**
     * 8、计数排序：稳定性排序、非原地排序、时间复杂度O(n + k)、O(k) k是取值范围
     * 不是比较排序
     * 1、取出最大值，生成相应的count数组
     * 2、遍历数组，将值当成count数组角标存入。
     * 3、遍历count数组，将其中有数据的角标拿出来放回arr数组中。
     *
     * @param arr
     * @return
     */
    private static int[] countSort3(int[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }
        int len = arr.length;
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < len; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        int[] count = new int[max - min + 1];
        for (int i = 0; i < len; i++) {
            count[arr[i] - min] += 1;
        }
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                arr[index++] = i + min;
                count[i] -= 1;
            }
        }
        return arr;
    }


//    /**
//     * 1、冒泡：稳定排序、原地排序、时间复杂度O(n^2) 、空间复杂度O(1)
//     * <p></>
//     * 将最大或者最小的数放到数组最后
//     */
//    private static int[] bubbleSort3(int[] arr) {
//        int len = arr.length;
//        for (int i = 0; i < len - 1; i++) {
//            boolean flag = true;
//            for (int j = 0; j < len - i - 1; j++) {
//                if (arr[j] > arr[j + 1]) {
//                    int tmp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = tmp;
//                    flag = false;
//                }
//            }
//            if (flag) {
//                break;
//            }
//        }
//        return arr;
//    }
//
//    /**
//     * 2、选择：非稳定排序、原地排序、时间复杂度O(n^2) 、空间复杂度O(1)
//     * <p></>
//     */
//    private static int[] selectSort3(int[] arr) {
//        int len = arr.length;
//        for (int i = 0; i < len - 1; i++) {
//            int minIndex = i;
//            for (int j = i + 1; j < len; j++) {
//                if (arr[minIndex] > arr[j]) {
//                    minIndex = j;
//                }
//            }
//            if (minIndex != i) {
//                int tmp = arr[minIndex];
//                arr[minIndex] = arr[i];
//                arr[i] = tmp;
//            }
//        }
//        return arr;
//    }
//
//    /**
//     * 3、快排：非稳定排序、原地排序、时间复杂度O(nlogN) 、空间复杂度O(1)
//     * <p></>
//     */
//    private static void quickSort3(int[] arr, int low, int high) {
//        if (low >= high) {
//            return;
//        }
//        int mid = low + (high - low) / 2;
//        int start = low;
//        int end = high;
//
//        int sw = arr[mid];
//        while (start < end) {
//            while (start < end && sw < arr[end]) {
//                end--;
//            }
//            if (start < end) {
//                arr[start++] = arr[end];
//            }
//            while (start < end && sw > arr[start]) {
//                start++;
//            }
//            if (start < end) {
//                arr[end--] = arr[start];
//            }
//        }
//
//        arr[start] = sw;
//
//        quickSort3(arr, low, mid - 1);
//        quickSort3(arr, mid + 1, high);
//    }
//
//    /**
//     * 4、插入：稳定排序、原地排序、时间复杂度O(n^2) 、空间复杂度O(1)
//     * <p></>
//     */
//    private static int[] insertSort3(int[] arr) {
//        int len = arr.length;
//        for (int i = 1; i < len; i++) {
//            int sw = arr[i];
//            if (sw < arr[i - 1]) {
//                int j = i - 1;
//                while (j >= 0 && sw < arr[j]) {
//                    arr[j + 1] = arr[j];
//                    j--;
//                }
//                arr[j + 1] = sw;
//            }
//        }
//        return arr;
//    }
//
//    /**
//     * 5、希尔：稳定排序、原地排序、时间复杂度O(n ^ 1.3 ) 、空间复杂度O(1)
//     * <p></>
//     */
//    private static int[] shellSort3(int[] arr) {
//        int len = arr.length;
//        for (int gap = arr.length / 2; gap >= 1; gap /= 2) {
//            for (int i = gap; i < len; i++) {
//                int sw = arr[i];
//                if (sw < arr[i - gap]) {
//                    int j = i - gap;
//                    while (j >= 0 && sw < arr[j]) {
//                        arr[j + gap] = arr[j];
//                        j -= gap;
//                    }
//                    arr[j + gap] = sw;
//                }
//            }
//        }
//        return arr;
//    }
//
//    /**
//     * 6、归并：非稳定排序、非原地排序、时间复杂度O(nlogN ) 、空间复杂度O(n)
//     * <p></>
//     */
//    private static int[] mergeSort3(int[] arr, int low, int high) {
//        if (low == high) {
//            return new int[]{arr[low]};
//        }
//        int mid = low + (high - low) / 2;
//
//        int[] leftArr = mergeSort3(arr, low, mid);
//        int[] rightArr = mergeSort3(arr, mid + 1, high);
//
//        int[] newArr = new int[leftArr.length + rightArr.length];
//
//        int leftIndex = 0;
//        int rightIndex = 0;
//        int newIndex = 0;
//
//        while (leftIndex < leftArr.length && rightIndex < rightArr.length) {
//            newArr[newIndex++] = leftArr[leftIndex] < rightArr[rightIndex] ? leftArr[leftIndex++] : rightArr[rightIndex++];
//        }
//        while (leftIndex < leftArr.length) {
//            newArr[newIndex++] = leftArr[leftIndex++];
//        }
//        while (rightIndex < rightArr.length) {
//            newArr[newIndex++] = rightArr[rightIndex++];
//        }
//
//        return newArr;
//    }
//
//    /**
//     * 7、堆排序：非稳定排序、非原地排序、时间复杂度O(nlogN ) 、空间复杂度O(n)
//     * <p></>
//     */
//    private static int[] heapSort3(int[] arr) {
//        //1、建大顶堆
//        //2、将堆顶数据放到数组最后一位，重新调整堆，直到堆只剩下一个数据
//
////        arr.length / 2 - 1 等于 最后一个非叶子节点
//        for (int i = arr.length / 2 - 1; i >= 0; i--) {
//            adjustHeap3(arr, i, arr.length);
//        }
//
//        for (int i = arr.length - 1; i >= 0; i--) {
//            swap(arr, 0, i);
//            adjustHeap3(arr, 0, i);
//        }
//
//
//        //调整堆：
//        //1、循环获取左子节点
//        //2、获取左子节点、右子节点中较大的角标
//        //3、将这个角标和父节点比较，是否大于父节点，如果大于就和父节点换换
//        //4、换过之后该子节点设置成循环体的父节点，重新给这个子节点下边的子节点排序。
//        //5、如果子节点和父节点不需要交换，就退出循环
//
//
//        return arr;
//    }
//
//    private static int[] adjustHeap3(int[] arr, int i, int len) {
//        int parent = arr[i];
//        //循环做子节点
//        for (int k = 2 * i + 1; k < len; k = 2 * k + 1) {
//            if (k + 1 < len && arr[k] < arr[k + 1]) {
//                k++;
//            }
//            if (parent < arr[k]) {
//                swap(arr, i, k);
//
//                i = k;
//            } else {
//                break;
//            }
//
//        }
//        return arr;
//    }
//
//    /**
//     * 8、计数排序：稳定性排序、非原地排序、时间复杂度O(n + k)、O(k) k是取值范围
//     * 不是比较排序
//     * 1、取出最大值，生成相应的count数组
//     * 2、遍历数组，将值当成count数组角标存入。
//     * 3、遍历count数组，将其中有数据的角标拿出来放回arr数组中。
//     *
//     * @param arr
//     * @return
//     */
//    private static int[] countSort3(int[] arr) {
//        if (arr == null || arr.length < 1) {
//            return null;
//        }
//        int len = arr.length;
//        int max = arr[0];
//        int min = arr[0];
//        for (int i = 1; i < len; i++) {
//            if (arr[i] > max) {
//                max = arr[i];
//            }
//            if (arr[i] < min) {
//                min = arr[i];
//            }
//        }
//        int[] count = new int[max - min + 1];
//        for (int i = 0; i < len; i++) {
//            count[arr[i] - min] += 1;
//        }
//        int index = 0;
//        for (int i = 0; i < count.length; i++) {
//            while (count[i] > 0) {
//                arr[index++] = i + min;
//                count[i] -= 1;
//            }
//        }
//        return arr;
//    }


    /**
     * 面试题61. 扑克牌中的顺子
     * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，
     * 而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
     *
     *
     * 限制：
     * 数组长度为 5
     * 数组的数取值为 [0, 13] .
     *
     * 链接：https://leetcode.cn/problems/bu-ke-pai-zhong-de-shun-zi-lcof
     * @param nums
     * @return
     */
    public boolean isStraight(int[] nums) {
        quickSort1(nums, 0, nums.length - 1);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                min = Math.min(nums[i],min);
                if (i+1 < nums.length &&nums[i] == nums[i + 1]) {
                    return false;
                }
            }
        }

        if (nums[nums.length - 1] - min < 5){
            return true;
        }
        return false;

    }

    /**
     * 递归：
     * 1、结束条件 low >= high
     * 1、取start角标的数n，放到他应该在的中间位置上，大于n的放右边，小于n的放左边
     * 2、分成两半：排这个low -> n的新角标， n的新角标 -> high
     */
    public void quickSort1(int[] arr, int low, int high){
        if (low >= high){
            return;
        }
        int start = low;
        int end = high;

        int sw = arr[low];
        while (start < end) {

            //从end王前推，找到第一个小于sw的数
            while (start < end && sw < arr[end]) {
                end--;
            }

            if (start < end) {
                arr[start++] = arr[end];
            }

            //从start往后推，找到第一个大于sw的数
            while (start < end && sw > arr[start]) {
                start ++;
            }
            if (start < end) {
                arr[end --] = arr[start];
            }
        }

        arr[start] = sw;

        quickSort1(arr,start + 1, high);
        quickSort1(arr,low, start - 1);
    }


    /**
     * 面试题45. 把数组排成最小的数
     *
     * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     *
     * 示例 1:
     * 输入: [10,2]
     * 输出: "102"
     *
     * 示例 2:
     * 输入: [3,30,34,5,9]
     * 输出: "3033459"
     *
     * 链接：https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof
     *
     * 思路：
     * 1、xy 如何判断 谁该排前边？ xy < yx =》 就说明 x 该排在前边。例如10，2 ：102 < 210 所以应该返回[10,2]
     * 2、写个快排，只是判断条件换成 如上。
     */
    public static String minNumber(int[] nums) {
        quickSortSpecial(nums,0,nums.length -1);
        StringBuilder sb = new StringBuilder();
        for(int n: nums){
            sb.append(n);
        }
        return sb.toString();
    }

    private static void quickSortSpecial(int[] nums, int low, int high) {
        if (low >= high){
            return;
        }

        int start=low;
        int end =high;
        int sw = nums[low];

        while (start < end){
            while (start < end && isLeftBig(nums[end], sw)) {
                end--;
            }
            if (start<end){
                nums[start++] = nums[end];
            }
            while (start<end && isLeftBig(sw,nums[start])){
                start++;
            }
            if (start<end){
                nums[end--] = nums[start];
            }
        }

        nums[start] = sw;

        quickSortSpecial(nums, low, start - 1);
        quickSortSpecial(nums, start+1, high);
    }

    /**
     * 1、int + "" + int ：字符要在中间，否则会先计算int相加再拼接
     * 2、compareTo()比较的每个char的ASCII码的大小，0-9的ASCII正好是顺序的，所以可直接用。
     */
    private static boolean isLeftBig(int left,int right) {
        return (left + "" + right).compareTo(right + "" + left) > 0;
//        return Long.parseLong(left + "" + right) > Long.parseLong(right + "" + left);
    }

    public static void main(String[] args) {
        int arr[] = new int[]{10,2};
        System.out.println(minNumber(arr));
    }
}
