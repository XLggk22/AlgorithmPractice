package my.text.algorithm.dp.subsequence;

/**
 * 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 *  https://programmercarl.com/0392.%E5%88%A4%E6%96%AD%E5%AD%90%E5%BA%8F%E5%88%97.html#%E7%AE%97%E6%B3%95%E5%85%AC%E5%BC%80%E8%AF%BE
 * 示例 1：
 *  输入：s = "abc", t = "ahbgdc"
 *  输出：true
 *
 * 示例 2：
 *  输入：s = "axc", t = "ahbgdc"
 *  输出：false
 * @Title: JudgeWetherSubArray
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/5/30 19:41
 * @Version 1.0
 */
public class JudgeWetherSubArray {

    public static void main(String[] args) {

//        String str1 = "abc", str2 = "ahbgdc";
        String str1 = "axc", str2 = "ahbgdc";
        System.out.println(method01_dp(str1, str2));
    }

    /**
     * 方法一：动态规划
     * 思路：求最大公共子序列长度，如果长度等于较小的那个字符串的长度，那么较段的那个字符串就是较长字符串的子序列
     * 1.dp array definition: A end with str01 index i-1 , B end with str02 index j-1 , dp[i][j] present the common array length of A and B;
     * 2.deduce formula:
     *  if(str01.chatAt(i-1) == str02.chatAt(j-1)){
     *      dp[i][j] = dp[i-1][j-1] + 1;
     *  }else{
     *      dp[i][j] = dp[i-1][j] + dp[i][j-1];
     *  }
     * 3.dp array init: dp[0][0] = 0
     * 4.iterate order: traversal sequence
     * 5.iterate and deduce:
     *
     * @param str1
     * @param str2
     * @return
     */
    private static boolean method01_dp(String str1, String str2){
        int length1 = str1.length();
        int length2 = str2.length();

        int[][] dp = new int[length1 +1][length2 +1];

        for (int i = 1; i < length1 + 1 ; i++) {
            for (int j = 1; j < length2 + 1; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        for (int i = 0; i < length1 + 1; i++) {
            for (int j = 0; j < length2 + 1; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }

        // 较长字符串的长度是否等于最大公共子序列长度
        int shoterStringLength = length1 < length2 ? length1 : length2;

        System.out.println("更短字符串长度：" + shoterStringLength);

        return dp[length1][length2] == shoterStringLength;
    }


}
