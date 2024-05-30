package my.text.algorithm.dp.subsequence;

/**
 * 最大子序和
 * https://programmercarl.com/0053.%E6%9C%80%E5%A4%A7%E5%AD%90%E5%BA%8F%E5%92%8C%EF%BC%88%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%EF%BC%89.html#%E7%AE%97%E6%B3%95%E5%85%AC%E5%BC%80%E8%AF%BE
 *
 * 示例:
 *  输入: [-2,1,-3,4,-1,2,1,-5,4]
 *  输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * @Title: MaximunSuborderSum
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/5/29 13:49
 * @Version 1.0
 */
public class MaximunSuborderSum {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println("最大子序和： " + method01_dp(nums));
        System.out.println("最大子序和： " + method02_greedy(nums));
        System.out.println("最大子序和： " + method03_back_track(nums));

    }

    /**
     * 方法一：动态规划
     * 思路：思路和贪心算法基本一样，遍历算出当前累计的最大和，如果前面的累计是负数的时候便丢弃，并重新开始计算
     * 1.dp array definition: dp[i] present the maximum sum of the end with nums[i] subarray
     * 2.deduce formula:
     *  dp[i] = max(dp[i-1] + nums[i], nums[i]);
     *  maxSum = max(dp[i], maxSum);
     * 3.dp array init: dp[0] = nums[0]
     * 4.iterate order: traversal in sequence
     * 5.iterate and deduce:
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    private static int method01_dp(int[] nums){
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }

    /**
     * 方法二：贪心算法
     * 思路：遍历数组，累计求和，如果之前的求和为负数，则重新开始计和
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    private static int method02_greedy(int[] nums){
        int result = 0;
        int maxSum = 0;
        for (int i = 0; i < nums.length; i++) {
            // 因为maxSum再计算过程种会被覆盖，所以需要一个result来记录计算种的最大值。
            maxSum = Math.max(maxSum + nums[i], nums[i]);
            result = Math.max(result, maxSum);
        }

        return result;
    }

    /**
     * 方法三：双重循环遍历破解
     * 思路：遍历数组，计算除分别以nums[i]开始的子序列的最大和
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    private static int method03_back_track(int[] nums){
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            // 设置起始位置
            // 每次遍历都需要重置本轮的最大和
            int maxSum = 0;
            for (int j = i; j < nums.length; j++) {
                maxSum = Math.max(maxSum + nums[j], nums[j]);
                result = Math.max(result, maxSum);
            }
        }
        return result;
    }
}
