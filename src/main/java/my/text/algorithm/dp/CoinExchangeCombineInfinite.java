package my.text.algorithm.dp;

/**
 * 零钱兑换(每个面值有无数多个)
 *  给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *  你可以认为每种硬币的数量是无限的。
 *
 * 示例 1：
 *  输入：coins = [1, 2, 5], amount = 11
 *  输出：3
 *  解释：11 = 5 + 5 + 1
 * @Title: CoinExchangeCombineInfinite
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/4/12 20:06
 * @Version 1.0
 */
public class CoinExchangeCombineInfinite {

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        int amount = 11;
        System.out.println("组合数量：" + method01_dp(coins, amount));
    }


    /**
     * 方法一：动态规划
     * 思路：其实就是一个完全背包问题（每个面值任意多个）。
     * 因为求的是最小硬币数量，所以每一枚面值的硬币价值（权重）都是1
     * 递推公式：dp[j] = min(dp[j-coins[i]] + 1, dp[j]);
     * @param coins
     * @param target
     * @return
     */
    public static int method01_dp(int[] coins, int target){
        // 1. dp array meaning: dp[j] present the minimun numbers of coins to convert the amount of J into coins
        // 2. deduce formula: dp[j] = Math.min(dp[j], dp[j-coins[i]] + 1);
        // 3. dp array init: dp[0] = 0; other init Math.max
        // 4. iterate order: tranvsal order
        // 5. iterate and deduce

        // define dp array and init
        int[] dp = new int[target+1];

        // dp array init: dp[0] = 0; other init Integer.MAX_VALUE
        /**
         * 首先凑足总金额为0所需钱币的个数一定是0，那么dp[0] = 0;
         *
         * 其他下标对应的数值呢？
         *
         * 考虑到递推公式的特性，dp[j]必须初始化为一个最大的数，否则就会在min(dp[j - coins[i]] + 1, dp[j])比较的过程中被初始值覆盖。
         *
         * 所以下标非0的元素都是应该是最大值。
         */
        for (int i = 0; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        
        // iterate and deduce
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= target; j++) {
                dp[j] = Math.min(dp[j], dp[j-coins[i]] + 1);
            }
        }

        return dp[target];
    }
}
