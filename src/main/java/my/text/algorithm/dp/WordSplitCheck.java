package my.text.algorithm.dp;

/**
 * // todo 难度高
 * 单词分割判断
 *  给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *  说明：
 *      拆分时可以重复使用字典中的单词。
 *      你可以假设字典中没有重复的单词。
 *
 *  实例 1：
 *      输入: s = "leetcode", wordDict = ["leet", "code"]
 *      输出: true
 *      解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *
 *  https://programmercarl.com/0139.%E5%8D%95%E8%AF%8D%E6%8B%86%E5%88%86.html#%E6%80%9D%E8%B7%AF
 * @Title: WordSplitCheck
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/4/16 11:27
 * @Version 1.0
 */
public class WordSplitCheck {

    public static void main(String[] args) {
        String[] wordDict = new String[]{"leet", "code", "codes"};
        String wordString = "leetcodes";
        System.out.println("是否可以切分成单词组合: " + method01_dp(wordDict, wordString));
    }

    /**
     * 方法一：动态规划
     * 思路：其实是一个完全背包（因为每个单词可以使用任意次）
     *   那物品就是单词数组，背包就是目标单词。题目变为背包是否能被给定的单词数组的单词装满。
     *   1.确定数组下标及含义：dp[i] : 字符串长度为i的话，dp[i]为true，表示可以拆分为一个或多个在字典中出现的单词。
     *   2.递推公式：如果确定dp[j] 是true，且 [j, i] 这个区间的子串出现在字典里（j~i这段也出现了），那么dp[i]一定是true
     *          那公式为 -> if([j, i] 这个区间的子串出现在字典里 && dp[j]是true) 那么 dp[i] = true。
     *   3.初始化：dp[0] = true, 其他为false
     *   4.遍历顺序：完全背包遍历顺序为正序。先遍历背包（好理解），再遍历单词。
     *   5.遍历推导
     * @param wordDict
     * @param wordStr
     * @return
     */
    public static boolean method01_dp(String[] wordDict, String wordStr){

        // define dp array and init
        Boolean[] dp = new Boolean[wordStr.length()+1];
        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = false;
        }

        // outer layer iterate bag
        for (int i = 1; i <= wordStr.length(); i++) {
            // inner layer iterate goods
            for (int j = 0; j < wordDict.length; j++) {
                String substring = wordStr.substring(0, i);
                String word = wordDict[j];
                System.out.println("i: " + i + ", j: " + j + ", substring: " + substring + ", word: " + word);
                if (substring.contains(word) && dp[substring.length() - word.length()]){
                    dp[i] = true;
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            System.out.println("dp array i: " + dp[i]);
        }

        return dp[dp.length-1];
    }
}
