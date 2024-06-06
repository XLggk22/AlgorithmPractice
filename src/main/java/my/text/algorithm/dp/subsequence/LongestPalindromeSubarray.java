package my.text.algorithm.dp.subsequence;

/**最长回文子序列
 *
 * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
 *  示例 1: 输入: "bbbab" 输出: 4 一个可能的最长回文子序列为 "bbbb"。
 *  示例 2: 输入:"cbbd" 输出: 2 一个可能的最长回文子序列为 "bb"。
 *
 * 区别于{@link PalindromeStringCount},本题要求的是回文子序列（子数组）：回文子串是要连续的，回文子序列可不是连续的！
 * 和上一题类似，重点都在于递推公式，在纸面上演算一下会更好理解一点。
 *
 * @Title: LongestPalindromeSubarray
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/6/5 16:46
 * @Version 1.0
 */
public class LongestPalindromeSubarray {

    public static void main(String[] args) {

        char[] arr = "bbbab".toCharArray();

        System.out.println("最长回文子序列长度为：" + method01_dp(arr));

    }

    /**
     * 方法一：动态规划
     * 思路：和{@link PalindromeStringCount}基本一样，详细见资料
     * 1. dp array definition: dp[i][j] present the max length of the subarray that start with arr[i], and end with arr[j]
     * 2. deduce formula:
     *  if(arr[i] == arr[j]){
     *      dp[i][j] = dp[i+1][j-1] + 2
     *  }else{
     *      dp[i][j] = max(dp[i+1][j], dp[i][j-1]);
     *  }
     * 3. dp array init: no need
     * 4. iterate order : in reverse order outer, transversal sequence inner
     * 5. iterate and deduce:
     *
     * @param arr
     * @return
     */
    private static int method01_dp(char[] arr) {
        int[][] dp = new int[arr.length][arr.length];

        for (int i = arr.length-1; i >= 0; i--) {
            // init dp[i][i]
            dp[i][i] = 1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else{
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }

        return dp[0][arr.length-1];
    }
}
