package my.text.algorithm.back.track;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * https://programmercarl.com/0017.%E7%94%B5%E8%AF%9D%E5%8F%B7%E7%A0%81%E7%9A%84%E5%AD%97%E6%AF%8D%E7%BB%84%E5%90%88.html#c-%E4%BB%A3%E7%A0%81
 * @Title: LetterCombinations
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/2/1 16:35
 * @Version 1.0
 */
public class LetterCombinations {

    // 全局结果集合
    private static List<String> result = new ArrayList<>();

    // 手机键盘中的号码-字符映射数组
    private static String[] numStrMapArr = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String[] args) {
//        letterCombination("22");
//        letterCombination("23");
        letterCombination("234");
        System.out.println(result);
    }

    private static List<String> letterCombination(String digits){
        if (null == digits || digits.length() == 0){
            return result;
        }
        //递归回溯处理, index从第1为字符开始
        backTrack(digits, 0);
        return result;
    }

    private static StringBuilder temp = new StringBuilder();

    /**
     * 回溯方法
     * @param digits    数字串
     * @param index     数字下标
     */
    private static void backTrack(String digits, int index){
        if (index == digits.length()){
            result.add(temp.toString());
            return;
        }

        // 获取映射的字母字符串
        char num = digits.charAt(index);
        String str = numStrMapArr[num - '0'];

        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            backTrack(digits, index + 1);
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
