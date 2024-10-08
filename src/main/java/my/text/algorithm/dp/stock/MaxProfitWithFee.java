package my.text.algorithm.dp.stock;

import java.util.Arrays;

/**
 * 买卖股票的最佳时机含手续费
 *  给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 *  你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *  返回获得利润的最大值。
 *  注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 *  示例 1:
 *  输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 *  输出: 8
 *
 *  解释: 能够达到的最大利润:
 *      在此处买入 prices[0] = 1
 *      在此处卖出 prices[3] = 8
 *      在此处买入 prices[4] = 4
 *      在此处卖出 prices[5] = 9
 *      总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * @Title: MaxProfitWithFee
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/4/24 16:19
 * @Version 1.0
 */
public class MaxProfitWithFee {

    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        System.out.println("max profit: " + method01_dp(prices));
    }

    /**
     * 方法一：动态规划
     * 思路：
     *  basically the same with {@link MaxProfit}，the different is need pay trade fee
     *  1.dp array meaning ：
     *      dp[i][0]: present the cash amount when day i hold stock
     *      dp[i][1]: present the cash amount when day i not hold stock
     *  2.dp array init:
     *      dp[0][0] = -prices[0]
     *      dp[0][1] = 0
     *  3.deduce formula :
     *      dp[i][0] = max(dp[i-1][0], dp[i-1][1] - prices[i]);
     *      dp[i][1] = max(dp[i-1][1], dp[i-1][0] + prices[i] - fee);
     * @param prices    stock price array
     * @return
     */
    private static int method01_dp(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        // define dp array and init
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        // fee
        int fee = 2;

        // iterate prices
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + prices[i] - fee);
        }

        // iterate dp array and print
        for (int[] ints : dp) {
            System.out.println(ints[0] + "  " + ints[1]);
        }

        return Math.max(dp[prices.length-1][0], dp[prices.length-1][1]);
    }

}
