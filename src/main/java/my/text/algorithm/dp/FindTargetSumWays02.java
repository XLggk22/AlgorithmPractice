package my.text.algorithm.dp;

/**
 * 组合总和 Ⅳ（每个数可以使用任意多次）
 *
 * 难度：中等
 * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
 *  示例:
 *  nums = [1, 2, 3]
 *  target = 4
 *
 * 所有可能的组合为： (1, 1, 1, 1) (1, 1, 2) (1, 2, 1) (1, 3) (2, 1, 1) (2, 2) (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合(其实是求排列数)。
 *
 * 因此输出为 7。
 * @title: targetnumcombine
 * @description:
 * @author deepexi-raobinghua
 * @date 2024/4/12 15:26
 * @version 1.0
 */
public class FindTargetSumWays02 {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        int target = 4;
        System.out.println("组合数量：" + method01_dp(arr, target));
    }

    /**
     * 方法一：动态规划
     * @param arr   数组
     * @param target    目标数
     * @return
     */
    public static int method01_dp(int[] arr, int target){
        // 1. dp array meaning: dp[j] present the ways of target num j combine
        // 2. recursion formula: dp[j] += dp[j-arr[i]]
        // 3. dp array init: dp[0] = 1
        // 4. iterate order: order
        // 5. iterate and deduce

        // dp array define and init
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 0; i < arr.length; i++) {
            // order iterate(suit for all this complete combine type subject)
            for (int j = arr[i]; j <= target; j++) {
                dp[j] += dp[j-arr[i]];
            }
        }

        return dp[target];
    }

}
