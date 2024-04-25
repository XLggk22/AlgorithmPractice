package my.text.algorithm.dp.stock;

/**
 * 买股票最大利润
 *  给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *  你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *  返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * 示例 1：
 *  输入：[7,1,5,3,6,4]
 *  输出：5
 * @Title: MaxProfit
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/4/18 11:04
 * @Version 1.0
 */
public class MaxProfit {

    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4};

        System.out.println("最大利润: " + method01_recursion(prices));
        System.out.println("最大利润: " + method02_greedy(prices));
        System.out.println("最大利润: " + method03_dp(prices));
    }

    /**
     * 方法一：双层遍历，暴力破解
     * 思路：求解出 后一个数 - 前一个数 的最大差值
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(1)
     * @param prices
     * @return
     */
    public static int method01_recursion(int[] prices){
        if (null == prices){
            return 0;
        }

        // 最大利润
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i+1; j < prices.length; j++) {
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
        }

        return maxProfit;
    }

    /**
     * 方法二：贪心算法
     * 思路：取左边的最小值和右边的最大值，计算差值就是最大利润
     * 时间复杂度：O(N)
     * 空间复杂度：0(1)
     * @param prices
     * @return
     */
    public static int method02_greedy(int[] prices){
        if (null == prices){
            return 0;
        }

        // 最大利润
        int maxProfit = 0;
        // 最低价格（初始化为最大值，不然会干扰到计算）
        int lowPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            lowPrice = Math.min(lowPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - lowPrice);
        }
        return maxProfit;
    }

    /**
     * // todo 比较难理解（通过手动演示更好理解）
     * 方法三：动态规划
     * 思路：
     *  1.每天无非就两种情况，持有和不持有。
     *  2.每天都算出持有股票 和 不持有股票所得最大现金，dp[i][0]表示持有所得现金，dp[i][1]代表第i天不持有股票的最大现金。
     *  3.最后一天就能推出整个过程能得到的最大现金，即盈利。
     * 思路：详细见资料
     * @param prices
     * @return
     */
    public static int method03_dp(int[] prices){
        if (null == prices || prices.length == 0){
            return 0;
        }

        // dp[i][0]代表第i天持有股票所得最多现金
        // dp[i][1]代表第i天不持有股票的最大现金
        // define dp array and init
        int[][] dp = new int[prices.length][2];
        // todo 这个尤其重要，为什么是-prices[0]？因为这样就可以似的买入价格越高得到的现金越低（即利润越低），为之的动态推导公式服务
        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        for (int i = 1; i < prices.length; i++) {
            /**
             * 第i天持有，有一下两种情况：
             * 1.dp[i-1][0]:前一天持有，今天继续持有；
             * 2.-prices[i]:前一天不持有，今天买入（因为只可以买卖一次，这里必需是-prices[i]才不会受之前影响）
             */
            dp[i][0] = Math.max(dp[i-1][0], -prices[i]);

            /**
             * 第i天持有，有一下两种情况：
             * 1.dp[i-1][0]:前一天持有，今天继续持有；
             * 2.dp[i-1][1]:前一天就不持有
             */
            dp[i][1] = Math.max(dp[i-1][0] + prices[i], dp[i-1][1]);
        }

        return dp[dp.length-1][1];
    }
}
