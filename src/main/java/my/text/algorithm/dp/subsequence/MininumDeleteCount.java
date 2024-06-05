package my.text.algorithm.dp.subsequence;

/** 两个字符串的删除操作(求最小删除次数)
 *
 * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 * https://programmercarl.com/0583.%E4%B8%A4%E4%B8%AA%E5%AD%97%E7%AC%A6%E4%B8%B2%E7%9A%84%E5%88%A0%E9%99%A4%E6%93%8D%E4%BD%9C.html#%E6%80%9D%E8%B7%AF
 *
 * 示例：
 *  输入: "sea", "eat"
 *  输出: 2
 * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
 * @Title: MininumDeleteCount
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/6/3 17:30
 * @Version 1.0
 */
public class MininumDeleteCount {

    public static void main(String[] args) {
        String str01 = "sea" , str02 = "eat";

        System.out.println("最小删除次数：" + method01_dp(str01, str02));
    }

    /**
     * 方法1：动态规划
     * 思路：
     *  先求出最大公共子字符串，然后在计算需要删除字符的个数
     *  求最大公告字符串见：
     *
     *  1.dp array definition: let a is the subarray end with str01[i-1], let b is the subarray end with str02[j-1]; dp[i-1][j-1] present the longest common subarray of str01 and str02;
     *  2.deduce formula:
     *      if(str01[i-1]){
     *          dp[i][j] = dp[i-1][j-1] + 1
     *      }else{
     *          dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     *      }
     *  3.dp array init: no need
     *  4.iterate order: transversal sequence
     *  5.iterate and deduce;
     * @see LongestCommonSubarray
     * @param str01
     * @param str02
     * @return
     */
    private static int method01_dp(String str01, String str02){
        if(str01.length() == 0){
            return str02.length();
        }

        if(str02.length() == 0){
            return str01.length();
        }

        int[][] dp = new int[str01.length() + 1][str02.length() + 1];
        for (int i = 1; i < str01.length() + 1; i++) {
            for (int j = 1; j < str02.length() + 1; j++) {
                if(str01.charAt(i-1) == str02.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // 打印动态规划数组结果
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            // 换行
            System.out.println();
        }

        // 需要删除的长度为：str01 + str02的总长度 - 两倍的最大公共子序列长度
        return str01.length() + str02.length() - dp[str01.length()][str02.length()] * 2;
    }
}
