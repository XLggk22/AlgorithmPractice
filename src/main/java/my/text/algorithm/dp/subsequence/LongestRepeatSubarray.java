package my.text.algorithm.dp.subsequence;

/**
 * 最长重复子数组
 * 比较难理解，详细见教程
 * https://programmercarl.com/0718.%E6%9C%80%E9%95%BF%E9%87%8D%E5%A4%8D%E5%AD%90%E6%95%B0%E7%BB%84.html#%E7%AE%97%E6%B3%95%E5%85%AC%E5%BC%80%E8%AF%BE
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * 示例：
 *  输入：
 *      A: [1,2,3,2,1]
 *      B: [3,2,1,4,7]
 *  输出：3
 * 解释：长度最长的公共子数组是 [3, 2, 1] 。
 *
 * @Title: LongestRepeatSubarray
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/5/27 20:30
 * @Version 1.0
 */
public class LongestRepeatSubarray {

    public static void main(String[] args) {
//        int[] arr01 = {1,2,3,2,1};
//        int[] arr02 = {3,2,1,1,1};
        int[] arr01 = {1,2,3,2,1};
        int[] arr02 = {1,1,1,1,1};

        System.out.println("最大重复数组长度:" + method01_dp(arr01, arr02));
    }

    /**
     * 方法一：动态规划
     * 1.dp array definition : A ending with subscript i -1, and b ending with subscript j -1, the longest array of repeats is dp[i][j] .
     * 2.deduce formula :
     *  if(arr01[i-1] == arr02[j-1]){
     *      dp[i][j] = dp[i-1][j-1] + 1
     *  }
     * 3.dp array init: dp[i] = 0
     * 4.iterate order : transversal in sequence
     * 5.iterate and deduce
     * @param arr01
     * @param arr02
     * @return
     */
    private static int method01_dp(int[] arr01, int[] arr02) {
        // definite dp array
        int[][] dp = new int[arr01.length + 1][arr02.length + 1];

        int result = 0;
        // iterate and deduce
        for (int i = 1; i < arr01.length + 1; i++) {
            for (int j = 1; j < arr02.length + 1; j++) {
                if(arr01[i-1] == arr02[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        return result;
    }
}
