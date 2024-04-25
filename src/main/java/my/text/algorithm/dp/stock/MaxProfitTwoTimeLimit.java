package my.text.algorithm.dp.stock;

/**
 * 买卖股票的最佳时机III（限定卖两次）
 * @Title: MaxProfitNTimeLimit
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/4/23 15:45
 * @Version 1.0
 */
public class MaxProfitTwoTimeLimit {

    public static void main(String[] args) {

        int[] prices = {3, 5, 7, 4, 5, 2, 5, 8};

        System.out.println("最大利润：" + method02_greedy(prices));

    }

    /**
     * 方法一：动态规划
     * 思路：// todo 有点复杂，见资料，大体上和 {@link MaxProfitNotTimesLimt} 一样
     * @return
     */
    public static int method01_dp(){
        return 0;
    }

    /**
     * 方法二：贪心算法
     * 思路：
     *  1.用一个数组保存真个过程中正收益的交易利润；
     *  2.最后倒序排序取前两个之和即为最大利润
     * @return
     */
    public static int method02_greedy(int[] prices){
        if(prices == null || prices.length == 0) return 0;

        // 第一次、第二次买入的价格
        int buy01 = Integer.MAX_VALUE;
        int buy02 = Integer.MAX_VALUE;

        // 第一、第二次交易的利润
        int profit01 = 0;
        int profit02 = 0;

        //  iterate prices array
        for(int i = 0; i < prices.length; i++){
            buy01 = Math.min(buy01, prices[i]);
            profit01 = Math.max(profit01, prices[i] - buy01);

             // 第二次买入价格更新，基于第一次卖出后的资金。prices[i] - profit01 如果大于buy02说还是价格上升，如果小于buy02说明价格下降，是第二段中一个新的低点
            buy02 = Math.min(buy02, prices[i] - profit01);
            profit02 = Math.max(profit02, prices[i] - buy02);
        }

        System.out.println("第1次买入价格：" + buy01);
        System.out.println("第2次买入价格：" + buy02);
        System.out.println("第1次交易利润：" + profit01);
        System.out.println("第2次交易利润：" + profit02);

        return profit02;
    }
}
