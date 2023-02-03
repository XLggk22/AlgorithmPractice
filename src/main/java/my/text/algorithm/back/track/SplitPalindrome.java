package my.text.algorithm.back.track;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案。
 * 示例: 输入: "aab" 输出: [ ["aa","b"], ["a","a","b"] ]
 * https://programmercarl.com/0131.%E5%88%86%E5%89%B2%E5%9B%9E%E6%96%87%E4%B8%B2.html#%E5%9B%9E%E6%BA%AF%E4%B8%89%E9%83%A8%E6%9B%B2
 * @Title: SplitPalindrome
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/2/2 15:18
 * @Version 1.0
 */
public class SplitPalindrome {

    private static List<List<String>> result = new ArrayList<>();

    private static LinkedList<String> queue = new LinkedList<>();

    public static void main(String[] args) {
        backTracking("aab", 0);
        System.out.println(result);
    }

    private static void backTracking(String s, int startIndex){
        if (startIndex >= s.length()){
            result.add(new ArrayList<>(queue));
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isPalindrome(s, startIndex, i)){
                // 截取的当前字符串是回文则
                String str = s.substring(startIndex, i + 1);
                queue.push(str);
            }else{
                // 剪枝，不是 回文串则跳过
                continue;
            }
            backTracking(s, i + 1);
            queue.pollLast();
        }
    }

    private static boolean isPalindrome(String str, int startIndex, int endIndex){
        for (int i = startIndex , j = endIndex; i < j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)){
                return false;
            }
        }
        return true;
    }
}
