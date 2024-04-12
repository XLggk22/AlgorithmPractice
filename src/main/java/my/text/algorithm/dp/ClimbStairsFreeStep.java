package my.text.algorithm.dp;

/**
 * 爬楼梯-任意步长版
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬至多m (1 <= m < n)个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * https://programmercarl.com/0070.%E7%88%AC%E6%A5%BC%E6%A2%AF%E5%AE%8C%E5%85%A8%E8%83%8C%E5%8C%85%E7%89%88%E6%9C%AC.html#%E6%80%9D%E8%B7%AF
 * @Title: ClimbStairsFreeStep
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/4/12 17:02
 * @Version 1.0
 */
public class ClimbStairsFreeStep {

    public static void main(String[] args) {
        int target = 5;
        System.out.println("方法数：" + method01_dp(target));
    }


    /**
     * 方法一：动态规划
     * 思路：其实就是一个完全背包问题。
     *   剩下n阶台阶，每次都可以选择上 1~n阶，如：剩下3阶，每次都可以选择上[1,2,3]阶，
     *   问题转化为：一个重量为1~n的n个型号物品（重量都是正整数），每个型号件物品有任意多个，求放满一个容量为n的背包的总方法数量。
     * @param target
     * @return
     */
    public static int method01_dp(int target){
        // 1. dp array meaning: dp[j] present the ways of fill up j capacity bag
        // 2. recursion formula: dp[j] += dp[j-arr[i]]
        // 3. dp array init: dp[0] = 1;
        // 4. iterate order: traversal order
        // 5. iterate and deduce

        // define dp array and init
        int[] dp = new int[target+1];
        dp[0] = 1;

        // define and init goods array
        int[] arr = new int[target];
        for (int i = 0; i < target; i++) {
            arr[i] = i + 1;
        }

        // iterate and deduce
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr[i]; j <= target; j++) {
                dp[j] += dp[j-arr[i]];
            }
        }

        return dp[target];
    }

    /**
     * todo
     * 拓展：列举所有组合
     * @param target
     * @return
     */
    public static int method02_dp(int target){
        // 1. dp array meaning: dp[j] present the ways of fill up j capacity bag
        // 2. recursion formula: dp[j] += dp[j-arr[i]]
        // 3. dp array init: dp[0] = 1;
        // 4. iterate order: traversal order
        // 5. iterate and deduce

        // define dp array and init
        int[] dp = new int[target+1];
        dp[0] = 1;

        // define and init goods array
        int[] arr = new int[target];
        for (int i = 0; i < target; i++) {
            arr[i] = i + 1;
        }

        // define combine list
        // todo

        // iterate and deduce
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr[i]; j <= target; j++) {
                dp[j] += dp[j-arr[i]];
            }
        }

        return dp[target];
    }
}
