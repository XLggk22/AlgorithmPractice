package my.text.algorithm.dp;

/**
 * 推箱子路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 *
 * 示例 1：
 *  输入：m = 2, n = 3
 *  输出：3
 *
 * 示例 2：
 *  输入：m = 7, n = 3
 *  输出：28
 *
 * @Title: MoveBoxPath
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/4/23 9:58
 * @Version 1.0
 */
public class MoveBoxPath {

    public static void main(String[] args) {
        System.out.println(method02_recursive(2,3));
        System.out.println(method02_recursive(7,3));

    }

    /**
     * 方法1：动态规划
     * 思路：因为每次移动只能移动一格，并且只能向下或者向右移动，则移动到指定位置的方位有两种：
     *  一种是从左边往右，一种是从上边往下。总的方法数量为两种相加之和。
     * 即：令移动到m,n 位置的方法数量为：dp[m][n]
     *      则 dp[m][n] = dp[m-1][n] + dp[m][n-1]
     * @param m 长
     * @param n 高
     * @return
     */
    private static int method01_dp(int m, int n){
        int[][] dp = new int[m][n];
        for (int i = 0 ; i < m; i++){
            dp[i][0] = 1;
        }
        for (int j = 0 ; j < n ; j++){
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 左边格子 + 上边格子
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * 方法2：递归
     * @param m
     * @param n
     * @return
     */
    private static int method02_recursive(int m, int n){
        // 第一行全部为1
        if (m == 1){
            return 1;
        }
        // 第一列全部为1
        if (n == 1){
            return 1;
        }
        return method02_recursive(m-1, n) + method02_recursive(m, n-1);
    }
}
