package my.text.algorithm.dp.subsequence;

import java.util.Arrays;

/**
 * https://programmercarl.com/0300.%E6%9C%80%E9%95%BF%E4%B8%8A%E5%8D%87%E5%AD%90%E5%BA%8F%E5%88%97.html#%E7%AE%97%E6%B3%95%E5%85%AC%E5%BC%80%E8%AF%BE
 * 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * 示例 1：
 *  输入：nums = [10,9,2,5,3,7,101,18]
 *  输出：4
 *  解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *
 * 示例 2：
 *  输入：nums = [0,1,0,3,2,3]
 *  输出：4
 * @Title: LongestIncreasingSubsequence
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/4/25 21:11
 * @Version 1.0
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] arr = {10,9,2,5,3,7,101,18};
        System.out.println("最大子序列长度：" + method01_dp(arr));
    }

    /**
     * 方法一：动态规划
     *  思路：
     *      1.dp array definetion: dp[i] present the longest increasing subsequence length when end with number[i]
     *      2.deduce formula:
     *          if(num[i] > [j]) dp[i] = max(dp[i], dp[j] + 1);
     *      3.init dp array: dp[i] = 1; (the min length is 1)
     *      4.iterate order: traversal in sequence
     *      5.iterate and deduce
     *   空间复杂度：O(N^2)
     *   时间复杂度：O(N)
     * @param arr
     * @return
     */
    private static int method01_dp(int[] arr){
        if(arr == null || arr.length == 0) return 0;
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[dp.length-1];
    }
}
