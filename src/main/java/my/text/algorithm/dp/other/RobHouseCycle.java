package my.text.algorithm.dp.other;

/**
 * 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋【首尾相连】。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * https://programmercarl.com/0198.%E6%89%93%E5%AE%B6%E5%8A%AB%E8%88%8D.html#%E7%AE%97%E6%B3%95%E5%85%AC%E5%BC%80%E8%AF%BE
 *
 * 示例 1：
 *  输入：nums = [2,3,2]
 *  输出：3
 *
 * 示例 2：
 *  输入：nums = [1,2,3,1]
 *  输出：4
 * @Title: RobHouse
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/4/16 15:56
 * @Version 1.0
 */
public class RobHouseCycle {

    public static void main(String[] args) {
        int[] houses = new int[]{2,3,2};
        System.out.println("最大金额：" + method01_dp(houses));
        System.out.println("最大金额：" + method02_greedy(houses));
        System.out.println("最大金额：" + method03_dp(houses));
    }

    /**
     * 方法一：动态规划
     * 思路：和打家劫舍题目一样(my.text.algorithm.dp.other.RobHouse)，区别就是首尾不能相连。
     *  要使得首尾不能相连，那就两种方式：
     *    1.让第一个选不到：舍弃第一个，遍历求最大
     *    2.让最后一个选不到：舍弃最后一个，遍历求最大
     *   求两种方式的最大数。
     * @param houses    house价值数组
     * @return  最大金额
     */
    public static int method01_dp(int[] houses){
        /**
         * 1. drop the first one
         */
        // define dp array and init
        int[] dp = new int[houses.length];

        // index 0 droped
        dp[0] = 0;
        dp[1] = houses[1];

        // transval and iterate
        for (int i = 2; i < houses.length; i++) {
            dp[i] = Math.max(houses[i] + dp[i-2], dp[i-1]);
        }

        /**
         * 1. drop the last one
         */
        // define dp array and init
        int[] dp02 = new int[houses.length];
        dp02[0] = houses[0];
        dp02[1] = Math.max(houses[0], houses[1]);

        // transval and iterate(i < houses.length)
        for (int i = 2; i < houses.length-1; i++) {
            dp02[i] = Math.max(houses[i] + dp02[i-2], dp02[i-1]);
        }

        return Math.max(dp[houses.length-1], dp02[houses.length-2]);
    }


    /**
     * 方法三：动态规划（元素复用方式）
     * @param houses
     * @return
     */
    public static int method03_dp(int[] houses){
        return Math.max(robAction(houses, 0, houses.length - 1), robAction(houses, 1, houses.length));
    }

    /**
     *
     * @param houses    houses
     * @param start 开始下标
     * @param end   结束下标
     * @return  最大金额
     */
    public static int robAction(int[] houses, int start, int end){
        int a = 0, b = 0 ,c = 0;

        for (int i = start; i < end; i++) {
            c = Math.max(a + houses[i], b);
            a = b;
            b = c;
        }
        return c;
    }



    /**
     * 方法二：贪心算法
     * 因为不能偷相邻的两家，那其实就是隔着一家偷，起始无非是两种方式，又因为房屋是首尾相接的：
     * 一：舍弃第一个元素做以下计算
     *  1.从第0家开始偷，偷 0, 2, 4, 6
     *  2.从第1家开始偷，偷 1, 3, 5, 7
     *
     * 二：舍弃最后个元素做以下计算
     *  1.从第0家开始偷，偷 0, 2, 4, 6
     *  2.从第1家开始偷，偷 1, 3, 5, 7
     *
     * 然后比较一、二两种方式结果即可
     * @param houses    house价值数组
     * @return 最大金额
     */
    public static int method02_greedy(int[] houses){
        return 1;
    }

}
