package my.text.algorithm.dp;

/**
 * // todo 未全懂
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 * 示例 1：
 *      输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 *      输出：4
 * https://programmercarl.com/0474.%E4%B8%80%E5%92%8C%E9%9B%B6.html#%E7%AE%97%E6%B3%95%E5%85%AC%E5%BC%80%E8%AF%BE
 * @Title: MaxConbineNum
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/9/7 20:14
 * @Version 1.0
 */
public class MaxConbineNum {

    public static void main(String[] args) {
        String[] arr = new String[]{"10", "0001", "111001", "1", "0"};
        int m = 5, n = 3;
        System.out.println("组合数量: " + method01_dp(arr, m, n));
        System.out.println("组合数量: " + method02_dp(arr, m, n));
    }

    /**
     * 方法一: 动态规划
     * 思路：其实这可以转化成一个01背包，每个字符串都可以选择装和不装，自是需要注意的是这个背包是有多个维度容量的背包，即m,n
     * @param arr   字符串数组
     * @param m     m个0
     * @param n     n个1
     */
    private static int method01_dp(String[] arr , int m , int n){
        // dp[i][j]表示最多有i个0，j个1的最大子集
        int[][] dp = new int[m+1][n+1];
        // 当前遍历的元素的1的个数、0的个数
        int oneNum, zeroNum;

        for (String str : arr) {
            oneNum = 0;
            zeroNum=0;
            for (char c : str.toCharArray()) {
                if (c == '0'){
                    zeroNum ++;
                }else{
                    oneNum ++;
                }
            }

            // 倒序遍历，先遍历0容量，再遍历1容量
            for (int i = m; i >= zeroNum ; i--){
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-zeroNum][j-oneNum] + 1);
                }
            }
        }

        return dp[m][n];

    }

    private static int method02_dp(String[] arr, int m, int n){
        // create dp array, dp[i][j] present the ways of i 0 and j 1
        int[][] dp = new int[m+1][n+1];

        // recursion fomula: dp[i][j] = Math.max(dp[i][j], dp[i-zeroNum][j-oneNum] + 1)
        for (int i = 0; i < arr.length; i++) {
            int zeroNum = 0;
            int oneNum = 0;

            String str = arr[i];
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '1'){
                    oneNum += 1;
                }else{
                    zeroNum += 1;
                }
            }

            for (int j = m; j >= zeroNum; j--) {
                for (int k = n; k >= oneNum; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j-zeroNum][k-oneNum] + 1);
                }
            }
        }

        return dp[m][n];
    }
}
