package my.text.algorithm.greedy;

/**
 * == 最大递增数 ==
 * 提供一个数字N，求小于或等于N的最大递增数,即各个位数上的数字从前到后是递增或相等的
 * 0 < N <= 10^9
 *
 * https://programmercarl.com/0738.%E5%8D%95%E8%B0%83%E9%80%92%E5%A2%9E%E7%9A%84%E6%95%B0%E5%AD%97.html#%E6%9A%B4%E5%8A%9B%E8%A7%A3%E6%B3%95
 * @Title: MaxIncreaseNumber
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/3/8 13:44
 * @Version 1.0
 */
public class MaxIncreaseNumber {

    public static void main(String[] args) {
        System.out.println(maxIncreaseNumber(1234));
        System.out.println(maxIncreaseNumber(998));
        System.out.println(maxIncreaseNumber(999));
        System.out.println(maxIncreaseNumber(98998));
    }


    /**
     * 思路：
     *  遍历各个位上的数字,如果出现 i-1 位上的数字大于 i 位上的数字，则 i-1 位上的数字-1，并把i位后面的全置为9
     *  注意：需要从后向前遍历
     * @param num
     * @return
     */
    private static int maxIncreaseNumber(int num){
        String str = String.valueOf(num);
        char[] chars = str.toCharArray();
        int resetStartIndex = chars.length;
        for (int i = chars.length-1; i > 0; i--) {
            if (chars[i] < chars[i-1]){
                chars[i-1] --;
                resetStartIndex = i;
            }
        }
        for (int i = resetStartIndex; i < chars.length; i++) {
            chars[i] = '9';
        }
        return Integer.valueOf(String.valueOf(chars));
    }

}
