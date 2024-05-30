package my.text.algorithm.dp.subsequence;

import java.util.Arrays;
import java.util.Collections;

/**
 * 最长连续递增序列
 * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
 *
 * 示例 1：
 *  输入：nums = [1,3,5,4,7]
 *  输出：3
 *  解释：最长连续递增序列是 [1,3,5], 长度为3。尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
 *
 * 示例 2：
 *  输入：nums = [2,2,2,2,2]
 *  输出：1
 *  解释：最长连续递增序列是 [2], 长度为1。
 * @Title: LongestIncreasingSubsequence
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/4/25 21:11
 * @Version 1.0
 */
public class LongestSuccessiveIncreasingSubsequence {
    public static void main(String[] args) {
//        int[] arr = {10,9,2,5,3,7,101,18};
//        int[] arr = {1,3,5,4,7};
        int[] arr = {2,2,2,2,2};
        System.out.println("最大子序列长度：" + method01_dp(arr));
    }

    /**
     * 方法一：动态规划
     *  思路：similar with last one {@link LongestIncreasingSubsequence}
     *  the different is : successive increase is meaning that num[i] needn't compare with num[j] but num[i-1]
     *  1.dp array definition: dp[i] present the max length of longest successive increase sequence
     *  2.deduce formula :
     *      if(num[i] > num[j]) dp[i] = dp[i-1] + 1;
     *  3.init ap array: all value init 1, because the min length is 1
     *  4.iterate order: traversal order
     *  5.iterate and deduce
     *   空间复杂度：O(N)
     *   时间复杂度：O(N)
     * @param arr
     * @return
     */
    private static int method01_dp(int[] arr){
        if(arr == null || arr.length == 0) return 0;
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i-1]){
                dp[i] = dp[i-1] + 1;
            }
        }

        Arrays.stream(dp).forEach(System.out::println);

        int maxLength = 1;
        for (int i : dp) {
            maxLength = Math.max(maxLength, i);
        }
        return maxLength;
    }
}
