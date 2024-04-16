package my.text.algorithm.dp;

/**
 * 完全平方数和
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 *
 * 示例 1：
 *  输入：n = 12
 *  输出：3
 *  解释：12 = 4 + 4 + 4
 *
 * https://programmercarl.com/0279.%E5%AE%8C%E5%85%A8%E5%B9%B3%E6%96%B9%E6%95%B0.html#%E6%80%9D%E8%B7%AF
 * @Title: TotalSumOfSquares
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/4/15 19:57
 * @Version 1.0
 */
public class TotalSumOfSquares {

    public static void main(String[] args) {

        int totalSum = 13;

        System.out.println("最小数量: " + method01_dp(12));
        System.out.println("最小数量: " + numSquares(12));
    }

    /**
     * 方法一：动态规划
     *  思路：可转换成一个完成背包。
     *      1.求出totalSum的平方根，向下取整，假如为 n
     *      2.初始化一个值为[1, 2^2, 3^2, ..., n^2]的整数数组array
     *      3.那题目就变成，求从array数组中取元素（每个元素可以使用无数次），凑成和为totalSum需要最少元素的数量。
     *
     * @param totalSum
     * @return
     */
    public static int method01_dp(int totalSum){
        // 1. dp array meaning: dp[j] present the minimun number of nums sum to totalSum
        // 2. deduce formula: dp[j] = Math.mix(dp[j], dp[j-nums[i]] + 1);
        // 3. dp array init: dp[0] = 1, others init value Integer.max
        // 4. iterate order: transval order
        // 5. iterate and deduce

        // define goods array, and init
        double sqrt = Math.sqrt(totalSum);
        int floor = (int) Math.floor(sqrt);
        int[] goods = new int[floor];
        for (int i = 0; i < floor; i++) {
            goods[i] = (int)Math.pow(i+1, 2);
        }

        // define dp array and init
        int[] dp = new int[totalSum+1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        // iterate and deduce
        for (int i = 0; i < goods.length; i++) {
            for (int j = goods[i]; j <= totalSum; j++) {
                dp[j] = Math.min(dp[j], dp[j-goods[i]] + 1);
            }
        }

        return dp[totalSum];
    }

    public static int numSquares(int n) {
        int max = Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        //初始化
        for (int j = 0; j <= n; j++) {
            dp[j] = max;
        }
        //如果不想要寫for-loop填充數組的話，也可以用JAVA內建的Arrays.fill()函數。
        //Arrays.fill(dp, Integer.MAX_VALUE);

        //当和为0时，组合的个数为0
        dp[0] = 0;
        // 遍历物品
        for (int i = 1; i * i <= n; i++) {
            // 遍历背包
            for (int j = i * i; j <= n; j++) {
                //if (dp[j - i * i] != max) {
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                //}
                //不需要這個if statement，因爲在完全平方數這一題不會有"湊不成"的狀況發生（ 一定可以用"1"來組成任何一個n），故comment掉這個if statement。
            }
        }
        return dp[n];
    }

}

