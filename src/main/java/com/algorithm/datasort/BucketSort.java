package com.algorithm.datasort;
/**
 * @Author:
 * @Description:
 * 假设有一组长度为N的待排关键字序列K[1....n]。首先将这个序列划分成M个的子区间(桶) 。
 * 然后基于某种映射函数 ，将待排序列的关键字k映射到第i个桶中(即桶数组B的下标 i) ，那么该关键字k就作为B[i]中的元素(每个桶B[i]都是一组大小为N/M的序列)。
 * 接着对每个桶B[i]中的所有元素进行比较排序(可以使用快排)。然后依次枚举输出B[0]....B[M]中的全部内容即是一个有序序列。
 * [桶—关键字]映射函数
 * bindex=f(key)   其中，bindex 为桶数组B的下标(即第bindex个桶), k为待排序列的关键字。
 * 桶排序之所以能够高效，其关键在于这个映射函数，它必须做到：如果关键字k1<k2，那么f(k1)<=f(k2)。
 * 也就是说B(i)中的最小数据都要大于B(i-1)中最大数据。很显然，映射函数的确定与数据本身的特点有很大的关系
 * 比如12 34 56 73 56 34 25 75 分10个桶，num/10分桶
 *
 * 对N个关键字进行桶排序的时间复杂度分为两个部分：
 * (1) 循环计算每个关键字的桶映射函数，这个时间复杂度是O(N)。
 * (2) 利用先进的比较排序算法对每个桶内的所有数据进行排序，其时间复杂度为  ∑ O(Ni*logNi) 。其中Ni 为第i个桶的数据量。
 * @Date: Created in 6:53 PM 8/27/18
 * @Modified by:
 */
public class BucketSort {

    public static void bucketSort(int[] arr){

    }
    public static void main(String[] args){
        int[] arr = new int[]{4,7,6,5,3,1,2,8};
        bucketSort(arr);
        // System.out.println(Arrays.toString(arr));
    }
}
