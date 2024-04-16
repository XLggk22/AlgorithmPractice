package my.text.algorithm.dp.other;

/**
 * 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * https://programmercarl.com/0198.%E6%89%93%E5%AE%B6%E5%8A%AB%E8%88%8D.html#%E7%AE%97%E6%B3%95%E5%85%AC%E5%BC%80%E8%AF%BE
 *
 * 示例 1：
 *  输入：[1,2,3,1]
 *  输出：4
 *
 * 示例 2：
 *  输入：[2,7,9,3,1]
 *  输出：2 + 9 + 1 = 12
 * @Title: RobHouse
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/4/16 15:56
 * @Version 1.0
 */
public class RobHouse {

    public static void main(String[] args) {
        int[] houses = new int[]{1,2,3,1};
        System.out.println("最大金额：" + method01_dp(houses));
        System.out.println("最大金额：" + method02_greedy(houses));
    }

    /**
     * 方法一：动态规划
     * 1.数组下标及含义：dp[i]：考虑下标i（包括i）以内的房屋，最多可以偷窃的金额为dp[i]。
     * 2.递推公式：dp[i] = max(dp[i] + dp[i-2], dp[i-1])
     * 3.数组初始化：dp[0] = nums[0]; dp[1] = max(dp[0], dp[1]), 通过自行推演可得
     * 4.遍历顺序：没什么特别的，从前到后就可以了
     * 5.推演，举例
     * @param houses    house价值数组
     * @return  最大金额
     */
    public static int method01_dp(int[] houses){
        // define dp array and init
        int[] dp = new int[houses.length];
        dp[0] = houses[0];
        dp[1] = Math.max(houses[0], houses[1]);

        // transval iterate
        for (int i = 2; i < houses.length; i++) {
            dp[i] = Math.max(houses[i] + dp[i-2], dp[i-1]);
        }

        for (int i = 0; i < dp.length; i++) {
            System.out.println("dp [" + i + "] = " + dp[i]);
        }

        return dp[dp.length-1];
    }

    /**
     * 方法二：贪心算法
     * 因为不能偷相邻的两家，那其实就是隔着一家偷，起始无非是两种方式：
     *  1.从第0家开始偷，偷 0, 2, 4, 6
     *  2.从第1家开始偷，偷 1, 3, 5, 7
     *  然后比较两种方式结果即可
     * @param houses    house价值数组
     * @return 最大金额
     */
    public static int method02_greedy(int[] houses){
        int sum1 = 0;
        for (int i = 0; i < houses.length; i += 2) {
            sum1 += houses[i];
        }

        int sum2 = 0;
        for (int i = 1; i < houses.length; i += 2) {
            sum2 += houses[i];
        }

        return Math.max(sum1, sum2);
    }

}
