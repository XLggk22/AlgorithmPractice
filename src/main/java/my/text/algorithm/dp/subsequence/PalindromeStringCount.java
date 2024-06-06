package my.text.algorithm.dp.subsequence;

/**
 * 回文子串——回文子字符串个数
 * 难度5颗星
 *  给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *  具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * https://programmercarl.com/0647.%E5%9B%9E%E6%96%87%E5%AD%90%E4%B8%B2.html#%E6%80%9D%E8%B7%AF
 *
 * 示例 1：
 *  输入："abc"
 *  输出：3
 *  解释：三个回文子串: "a", "b", "c"
 *
 * 示例 2：
 *  输入："aaa"
 *  输出：6
 *  解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * @Title: PalindromeStringCount
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/6/5 14:54
 * @Version 1.0
 */
public class PalindromeStringCount {

    public static void main(String[] args) {
//        String str = "aba";
//        String str = "abc";
        String str = "aabaa";
        System.out.println("回文子字符串个数：" + method01_dp(str));
    }

    /**
     * 方法一：动态规划
     * 难度5颗星
     * 思路：
     *     主要难点主要在于推导公式，概述如下（纸上演示推到一下会比较好理解一点点）：
     *      我们在判断字符串S是否是回文，那么如果我们知道 s[1]，s[2]，s[3] 这个子串是回文的，那么只需要比较 s[0]和s[4]这两个元素是否相同，如果相同的话，这个字符串s 就是回文串。
     *
     *      在确定递推公式时，就要分析如下几种情况。
     *          整体上是两种，就是s[i]与s[j]相等，s[i]与s[j]不相等这两种。
     *          当s[i]与s[j]不相等，那没啥好说的了，dp[i][j]一定是false。
     *          当s[i]与s[j]相等时，这就复杂一些了，有如下三种情况：
     *              情况一：下标i 与 j相同，同一个字符例如a，当然是回文子串
     *              情况二：下标i 与 j相差为1，例如aa，也是回文子串
     *              情况三：下标：i 与 j相差大于1的时候，例如cabac，此时s[i]与s[j]已经相同了，我们看i到j区间是不是回文子串就看aba是不是回文就可以了，那么aba的区间就是 i+1 与 j-1区间，这个区间是不是回文就看dp[i + 1][j - 1]是否为true。
     *
     *  1.dp array definition: dp[i][j] present whether the substring that start with s[i], end with s[j] is palindrome string
     *  2.deduce formula:
     *      int result = 0;
     *      if(s[i] == s[j]){
     *          if(j-i <= 1){
     *              dp[i][j] = true;
     *              result ++;
     *          }else if(s[i+1][j-1]){
     *              dp[i][j] = true;
     *              result ++;
     *          }
     *      }
     *   3.dp array init: no need
     *   4.iterate order : in reverse order outer, int transversal sequence inner, because deduce dp[i][j] need use dp[i+1][j-1] value
     *   5.iterate and deduce:
     *  aabaa
     * 	    a	a	b	a	a
     *  a	1	1	0	0	1
     *  a		1	0	1	0
     *  b			1	0	0
     *  a				1	1
     *  a					1
     *
     *  @param str
     * @return
     */
    private static int method01_dp(String str){
        char[] chars = str.toCharArray();

        boolean[][] dp = new boolean[chars.length][chars.length];

        // record palindrome string count
        int resultCount = 0;

        // in reverse in outer
        for (int i = chars.length-1; i >= 0 ; i--) {
            // transversal sequence inner
            for (int j = i; j < chars.length; j++) {
                if (chars[i] == chars[j]){
                    if (j - i <= 1){
                        dp[i][j] = true;
                        resultCount++;
                    }else if (dp[i+1][j-1]){
                        // dp[i+1][j-1] 为当前的左下角
                        dp[i][j] =true;
                        resultCount++;
                    }
                }
            }
        }

        return resultCount;
    }
}
