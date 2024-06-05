package my.text.algorithm.dp.subsequence;

/**
 * 最短编辑距离
 * 难度等级：5颗星
 * https://programmercarl.com/0072.%E7%BC%96%E8%BE%91%E8%B7%9D%E7%A6%BB.html#%E6%80%9D%E8%B7%AF
 *
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 *  插入一个字符
 *  删除一个字符
 *  替换一个字符
 *
 * 示例 1：
 *  输入：word1 = "horse", word2 = "ros"
 *  输出：3
 *  解释： horse -> rorse (将 'h' 替换为 'r') rorse -> rose (删除 'r') rose -> ros (删除 'e')
 *
 * 示例 2：
 *  输入：word1 = "intention", word2 = "execution"
 *  输出：5
 *  解释： intention -> inention (删除 't') inention -> enention (将 'i' 替换为 'e') enention -> exention (将 'n' 替换为 'x') exention -> exection (将 'n' 替换为 'c') exection -> execution (插入 'u')
 * @Title: ShortestEditDistance
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/6/4 15:58
 * @Version 1.0
 */
public class ShortestEditDistance {

    public static void main(String[] args) {
        String str01 = "ros", str02 = "horse";
        System.out.println("要使得两个字符串相等，需要最小的编辑次数为：" + method01_dp(str01, str02));
    }

    /**
     * 方法一：动态规划
     * 难度等级：5颗星
     * 思路：比较难理解，详细见资料，记住地推公式
     * 1. dp array definition : let a is the string that end with str01[i-1] , b is the string that end with str02[j-1]; dp[i][j] present the at least edit count to make a equal b;
     *
     * 2. deduce formula:
     *  if(str01.charAt(i-1) == str02.charAt(j-1)){
     *      dp[i][j] = dp[i-1][j-1];
     *  }else{
     *      dp[i][j] = min(min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
     *  }
     *
     * 3. dp array init (think about the case of blank str01 or str02):
     *  case 1: str01 is blank, need delete str02   ->  dp[0][j] = j
     *  case 2: str02 is blank, need delete str01   ->  dp[i][0] = i
     *
     * 4. iterate order : transversal sequence;
     * 5. iterate and deduce:
     *   		h	o	r	s	e
     *  	0	1	2	3	4	5
     *  r   1   1	2	2	3	4
     *  o   2   2	1	2	3	4
     *  s	3   3	2	2	2	3
     *
     * @param str01
     * @param str02
     * @return
     */
    private static int method01_dp(String str01, String str02){
        int length01 = str01.length();
        int length02 = str02.length();

        // dp array definition
        int[][] dp = new int[length01 + 1][length02 + 1];

        // init dp array
        for (int i = 0; i <= length01; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= length02; j++) {
            dp[0][j] = j;
        }

        // iterate and deduce
        for (int i = 1; i <= length01; i++) {
            for (int j = 1; j <= length02; j++) {
                if (str01.charAt(i - 1) == str02.charAt(j - 1)) {
                    // 相等情况不操作
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    // 不相等的情况下有三种情况，增/删/换，取最小值即可（可纸上演算推到得出）
                    dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;
                }
            }
        }

        // print dp array
        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }

        return dp[length01][length02];
    }
}
