package my.text.algorithm.dp;

/**
 * 零钱兑换组合
 *  给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 *
 * 示例 1:
 *  输入: amount = 5, coins = [1, 2, 5]
 *  输出: 4
 *  解释: 有四种方式可以凑成总金额:
 *      5=5
 *      5=2+2+1
 *      5=2+1+1+1
 *      5=1+1+1+1+1
 *
 *  示例 2:
 *      输入: amount = 3, coins = [2]
 *      输出: 0
 *      解释: 只用面额2的硬币不能凑成总金额3。
 *      示例 3:
 *
 *  输入: amount = 10, coins = [10]
 *  输出: 1
 *
 * @Title: CoinExchangeCombine
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/4/11 11:46
 * @Version 1.0
 */
public class CoinExchangeCombine {

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        int target = 5;
        System.out.println("兑换的方法数:" + method01_dp(coins, target));
    }

    public static int method01_dp(int[] coins, int target){
        // 1. means of dp array: dp[j] present the ways of target monny exchange to coins
        // 2. deduce formula: dp[j] += dp[j-coins[i]]
        // 3. dp array init: dp[0] = 1
        // 4. goods array iterate order
        // 5. iterate and recursion

        // define dp array
        int[] dp = new int[target+1];

        // init dp array, present combines count of amount 0 exchange to coins combine is 1, that {0}
        // dp[0]=1还说明了一种情况：如果正好选了coins[i]后，也就是j-coins[i] == 0的情况表示这个硬币刚好能选，此时dp[0]为1表示只选coins[i]存在这样的一种选法。
        dp[0] = 1;

        // order iterate(for ways caculateall algorithm problems)
        // must iterate goods, detail refer to links of information
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= target; j++) {
                dp[j] += dp[j-coins[i]];
            }
        }

        return dp[target];
    }
}
