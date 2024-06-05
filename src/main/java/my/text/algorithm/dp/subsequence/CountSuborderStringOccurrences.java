package my.text.algorithm.dp.subsequence;

/**
 * 不同的子序列
 * 统计字符串出现的个数
 *
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 * https://programmercarl.com/0115.%E4%B8%8D%E5%90%8C%E7%9A%84%E5%AD%90%E5%BA%8F%E5%88%97.html#%E6%80%9D%E8%B7%AF
 * @Title: CountSuborderStringOccurrences
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/6/3 16:47
 * @Version 1.0
 */
public class CountSuborderStringOccurrences {

    public static void main(String[] args) {
        String a = "ABCDE11ABCDE", b = "ACE";
        System.out.println("短字符串在长字符串子串中出现的次数：" + method01_pointer(a, b));
    }


    /**
     * 方法一：遍历 + 指针
     * 思路：
     *  定义一个指针count, 指向短字符串的下标；
     *  遍历比较长的字符串，判断当前字符是否和短字符串的count%lengh下标字符一样。
     *  	如果是，则count+1；
     *  出现的次数即 count / 短字符串.length
     * @param a
     * @param b
     * @return
     */
    private static int method01_pointer(String a, String b){
        if(null == a || a.length() == 0 || null == b || b.length() == 0) return 0;

        String longerStr = a.length() > b.length() ? a : b;
        String shorterStr = a.length() > b.length() ? b : a;

        int count = 0;
        for (int i = 0; i < longerStr.length(); i++) {
            int index = count % shorterStr.length();
            if(shorterStr.charAt(index) == longerStr.charAt(i)){
                count ++;
            }
        }

        System.out.println("count: " + count);

        return count / shorterStr.length();
    }

    /**
     * 方法二：动态规划
     * 难度等级：5颗星
     * 思路：比较难理解，详细见资料
     * @param a
     * @param b
     * @return
     */
    private static int method02_dp(String a, String b){
        if(null == a || a.length() == 0 || null == b || b.length() == 0) return 0;
        return 0;
    }
}
