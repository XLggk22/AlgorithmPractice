package my.text.algorithm.dp.subsequence;

/**
 * 最长公共子序列
 * https://programmercarl.com/1143.%E6%9C%80%E9%95%BF%E5%85%AC%E5%85%B1%E5%AD%90%E5%BA%8F%E5%88%97.html#%E7%AE%97%E6%B3%95%E5%85%AC%E5%BC%80%E8%AF%BE
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 *  示例 1:
 *      输入：text1 = "abcde", text2 = "ace"
 *      输出：3
 *  解释：最长公共子序列是 "ace"，它的长度为 3。
 *
 *  示例 2:
 *      输入：text1 = "abc", text2 = "abc"
 *      输出：3
 *  解释：最长公共子序列是 "abc"，它的长度为 3。
 *
 *  示例 3:
 *      输入：text1 = "abc", text2 = "def"
 *      输出：0
 *  解释：两个字符串没有公共子序列，返回 0。
 *
 * @Title: LongestRepeatSubarray
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/5/27 20:30
 * @Version 1.0
 */
public class LongestCommonSubarray {

    public static void main(String[] args) {
//        String text1 = "abcde", text2 = "ace";
        String text1 = "abc", text2 = "defa";
        System.out.println("最大重复数组长度:" + method01_dp(text1, text2));
    }

    /**
     * 方法一：动态规划
     * 1.dp array definition : A ending with subscript i -1, and b ending with subscript j -1, the longest array of repeats is dp[i][j] .
     * 2.deduce formula :
     *  if(str01.charAt(i-1) == str02(j-1)){
     *      dp[i][j] = dp[i-1][j-1] + 1
     *  }else{
     *      dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     *  }
     * 3.dp array init: dp[i] = 0
     * 4.iterate order : transversal in sequence
     * 5.iterate and deduce
     * @param str01
     * @param str02
     * @return
     */
    private static int method01_dp(String str01, String str02) {
        // definite dp array
        int[][] dp = new int[str01.length() + 1][str02.length() + 1];

        // iterate and deduce
        for (int i = 1; i < str01.length() + 1; i++) {
            for (int j = 1; j < str02.length() + 1; j++) {
                if(str01.charAt(i-1) == str02.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[str01.length()][str02.length()];
    }
}
