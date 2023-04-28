package my.text.algorithm.dp;

/**
 * 爬楼梯
 *  假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *  每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *  注意：给定 n 是一个正整数。
 *
 *  https://programmercarl.com/0070.%E7%88%AC%E6%A5%BC%E6%A2%AF.html#%E6%80%9D%E8%B7%AF
 * @Title: ClimbStairs
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/3/20 15:21
 * @Version 1.0
 */
public class ClimbStairs {

    public static void main(String[] args) {

        System.out.println(method03(7));
        System.out.println(method03(8));
        System.out.println(method03(9));
        System.out.println(method03(10));

    }

    /**
     * 方法一：动态规划
     *  思路：上第3个阶梯方式有：
     *      1.上完第1个阶梯 + 一次上2阶
     *      2.上完第2个阶梯 + 一次上1阶
     *   推导公式：
     *   令:  上到第n阶梯的方式数量为pd[n]
     *   则有: dp[n] = dp[n-2] + dp[n-1]
     *
     *  时间复杂度：O(N)
     *  空间复杂度：O(N)
     * @return
     */
    private static int method01(int n){
        if (n <= 2){
            return n;
        }

        int [] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
            System.out.println(String.format("上第 %s 阶梯有 %s 种方式", i, dp[i]));
        }
        return dp[n];
    }

    /**
     * 方法二：类动态规划，压缩方式
     * @param n
     * @return
     */
    private static int method02(int n){
        if (n <=2){
            return n;
        }

        // a为上1级阶梯方法数、b为上两级阶梯方法数，c上级阶梯方法总数
        int a = 1, b = 2, c = 0;

        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
            System.out.println(String.format("上第 %s 阶梯有 %s 种方式", i, c));
        }

        return c;
    }

    /**
     * 方法三：递归法
     * @param n
     * @return
     */
    public static int method03(int n){

        if (n <=2 ){
            return n;
        }else{
            return method03(n - 1) + method03(n - 2);
        }
    }
}
