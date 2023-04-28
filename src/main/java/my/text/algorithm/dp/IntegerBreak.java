package my.text.algorithm.dp;

/**
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * 示例 1:
 *  输入: 2
 *  输出: 1
 *  解释: 2 = 1 + 1, 1 × 1 = 1。
 *
 *  示例 2:
 *      输入: 10
 *      输出: 36
 *      解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 *
 * 说明: 你可以假设 n 不小于 2 且不大于 58
 *
 * https://programmercarl.com/0343.%E6%95%B4%E6%95%B0%E6%8B%86%E5%88%86.html#%E6%80%9D%E8%B7%AF
 * @Title: IntegerBreak
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/4/25 15:45
 * @Version 1.0
 */
public class IntegerBreak {


    public static void main(String[] args) {
        System.out.println(method01_dp(10));
        System.out.println(methond02_greedy(10));
        System.out.println(method03_reccursive(10));

        System.out.println(method01_dp(11));
        System.out.println(methond02_greedy(11));
        System.out.println(method03_reccursive(11));
        System.out.println(method03_reccursive(4));
    }

    private static int method01_dp(int n) {
        //dp[i] 为正整数 i 拆分后的结果的最大乘积
        int[] dp = new int[n+1];
        dp[2] = 1;
        for(int i = 3; i <= n; i++) {
            for(int j = 1; j <= i-j; j++) {
                // 这里的 j 其实最大值为 i-j,再大只不过是重复而已，
                //并且，在本题中，我们分析 dp[0], dp[1]都是无意义的，
                //j 最大到 i-j,就不会用到 dp[0]与dp[1]
                dp[i] = Math.max(dp[i], Math.max(j*(i-j), j*dp[i-j]));
                // j * (i - j) 是单纯的把整数 i 拆分为两个数 也就是 i,i-j ，再相乘
                //而j * dp[i - j]是将 i 拆分成两个以及两个以上的个数,再相乘。
            }
        }
        return dp[n];
    }

    /**
     * 方法二：贪心算法
     * 思路：
     *  如果要使拆分后的数字乘积最大，则需要拆成足够多的3，推导：
     *    1.fun(1) = 1 * 0 = 0
     *      fun(2) = 1 * 1 = 1
     *      fun(3) = 2 * 1 = 2
     *      fun(4) = 2 * 2 = 4
     *      fun(5) = 2 * 3 = 6
     *      fun(6) = 3 * 3 = 9
     *
     *      以上可知：
     *          大于3的时候，拆出来数字乘积一定大于等于它本身；
     *          小于等于3的时候，继续拆出来的数字乘积小于它本身；
     *          所以拆分最小粒度是3
     *
     *      由因为：
     *          大于等于4的数字，均可以拆分成任意多个2或3的组合，
     *          并且数字不能有1，因为1相当于损失了（任何数乘与1都为原数）
     *
     *      所以，需要拆分后的数字组合乘积最大，需要拆成足够多的3
     *
     * @param n
     * @return
     */
    private static int methond02_greedy(int n){
        if (n <=3 ){
            return n -1;
        }
        int r = n / 3;
        int m = n % 3;
        if (m == 0){
            return (int) Math.pow(3, r);
        }else if (m == 1) {
            return (int) (Math.pow(3, r - 1) * 4);
        }else {
            return (int) (Math.pow(3, r) * 2);
        }
    }

    /**
     * 方法三：递归
     * 思路：和贪心算法一样
     * @param n
     * @return
     */
    private static int method03_reccursive(int n){
        if (n <= 3){
            return n -1;
        }
        if (n == 4) {
            return 4;
        }
        //大于5才进行拆分
        return recursive(n-3) * recursive(3);
    }

    private static int recursive(int n){
        //入参只可能为2或3
        if (n == 2){
            return 2;
        }else if (n == 3) {
            return 3;
        }else if (n == 4) {
            return 4;
        }
        return recursive(n-3) * recursive(3);
    }
}
