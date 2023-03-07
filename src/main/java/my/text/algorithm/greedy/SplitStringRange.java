package my.text.algorithm.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * == 划分字母区间 ==
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。
 *
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8] 解释： 划分结果为 "ababcbaca", "defegde", "hijhklij"。 每个字母最多出现在一个片段中。
 *      像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *
 *https://programmercarl.com/0763.%E5%88%92%E5%88%86%E5%AD%97%E6%AF%8D%E5%8C%BA%E9%97%B4.html#%E6%80%9D%E8%B7%AF
 * @Title: Solution
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/1/5 10:37
 * @Version 1.0
 */
public class SplitStringRange {

    public static void main(String[] args) {
        String S = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabels(S));
    }

    /**
     * 思路：
     *  1.创建一个数组存放每个字母出现的最远下标
     *  2.迭代字符串
     *      如果当前下标 等于 当前字符的最远下标；
     *      并且 当前字符的最远下标对于之前字符的最远下标是最大的
     *      则此位置为分界位置
     * @param str
     * @return
     */
    private static List<Integer> partitionLabels(String str) {
        List<Integer> charSplitLenth = new ArrayList<>();
        char[] chars = str.toCharArray();
        // 总的只有26个字母
        int[] charMaxEdgeIndex = new int[26];
        // 存放每个字符的出现的最远下标
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            charMaxEdgeIndex[c-'a'] = i;
        }

        int maxIndex = 0;
        int lastIndex = -1;
        for (int i = 0; i <chars.length; i++) {
            char c = chars[i];
            int currCharMaxIndex = charMaxEdgeIndex[c - 'a'];
            maxIndex = Math.max(currCharMaxIndex, maxIndex);
            if (maxIndex == i){
                charSplitLenth.add(i - lastIndex);
                lastIndex = i;
            }
        }
        return charSplitLenth;
    }

}
