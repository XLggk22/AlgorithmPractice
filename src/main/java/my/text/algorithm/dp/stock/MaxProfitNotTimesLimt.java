package my.text.algorithm.dp.stock;

/**
 * 买卖股票的最佳时机II(不限制交易次数)
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * https://programmercarl.com/0122.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAII%EF%BC%88%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%EF%BC%89.html#%E5%85%B6%E4%BB%96%E8%AF%AD%E8%A8%80%E7%89%88%E6%9C%AC
 * @Title: MaxProfitNotTimesLimt
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/4/22 21:04
 * @Version 1.0
 */
public class MaxProfitNotTimesLimt {

    public static void main(String[] args) {
//        int[] prices = { 1, 2, 3, 4, 5 };
        int[] prices = {7,1,5,3,6,4};
        System.out.println("最大利润：" + method02_greedy(prices));
        System.out.println("最大利润：" + method01_dp(prices));
    }

    /**
     * // todo 太复杂，暂未理解，详情见资料
     * 方法一：动态规划
     * 思路：同{@link MaxProfit}
     *  1.每天无非就两种情况，持有和不持有。
     *  2.每天都算出持有股票 和 不持有股票所得最大现金，dp[i][0]表示持有所得现金，dp[i][1]代表第i天不持有股票的最大现金。
     *  3.最后一天就能推出整个过程能得到的最大现金，即盈利。
     * @param prices
     * @return
     */
    public static int method01_dp(int[] prices){
        if(prices == null || prices.length == 0) return 0;

        int[][] dp = new int[prices.length][2];
        // 第一天持有股票所得现金，原来现金是0，减去第一天股票价格就是第一天持有的所得现金
        dp[0][0] = -prices[0];
        // 第一天不持有股票所得现金
        dp[0][1] = 0;

        for (int i = 1; i < prices.length; i++) {
            /**
             * 第i天持有，有以下两种情况：
             * 1.前一天持有，今天继续持有：dp[i-1][0]
             * 2.前一天没有，今天买入：dp[i-1][1] - prices[i]
             */
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] - prices[i]);

            /**
             * 第i天不持有，有以下两种情况：
             * 1.前一天不持有，今天继续不持有：dp[i-1][1]
             * 2.前一天持有，今天卖出：dp[i-1][0] + prices[i]
             */
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + prices[i]);
        }

        return dp[dp.length-1][1];
    }

    /**
     * 方法二：贪心算法
     * 思路：要使得最大利润，那只要统计上涨日的利润即可（当天价格比前一天价格高的）
     * @param prices
     * @return
     */
    public static int method02_greedy(int[] prices){
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i-1] > 0){
                maxProfit += prices[i] - prices[i-1];
            }
        }
        return maxProfit;
    }
}
