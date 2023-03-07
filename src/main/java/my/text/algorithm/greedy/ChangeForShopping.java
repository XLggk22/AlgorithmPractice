package my.text.algorithm.greedy;

import java.util.Arrays;

/**
 * ==买东西找零==
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
 * 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 * * 注意，一开始你手头没有任何零钱。
 * 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 *
 * 示例 1：
 *      输入：[5,5,5,10,20]
 *      输出：true
 *  https://programmercarl.com/0860.%E6%9F%A0%E6%AA%AC%E6%B0%B4%E6%89%BE%E9%9B%B6.html#%E6%80%9D%E8%B7%AF
 * @Title: ChangeForShopping
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/3/7 15:41
 * @Version 1.0
 */
public class ChangeForShopping {

    public static void main(String[] args) {
        int[] bills = new int[]{5,5,10,10,20};
        System.out.println(isCanChangeSuccess(bills));
    }

    /**
     * 是否能找零成功
     * 方法一：
     * 思路：
     *  模拟真实场景，想成功找零，先收零钱，即收取顺序：5快、10块、20块。
     *  直到某个步骤找不了零则返回失败(相对于方法二做了剪枝优化)。
     *
     *  时间复杂度 O(N)
     *  空间复杂度 O(1)
     * @param bills
     * @return
     */
    private static boolean isCanChangeSuccess(int[] bills){
        Arrays.sort(bills);
        int five = 0;
        int ten = 0;
        for (int i = 0; i < bills.length; i++) {
            int bill = bills[i];
            if (bill == 5){
                five ++;
            }else if (bill == 10){
                ten ++;
                five --;
            }else if (bill == 20){
                if (ten > 0){
                    ten --;
                    five --;
                }else{
                    five -= 3;
                }
            }else{
                System.err.println("error bill data!");
            }
            System.out.println(String.format("five nums : %s, ten nums : %s", five, ten));
            // 判断截止目前能不能找开
            if (five < 0 || ten < 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 是否能找零成功
     * 方法二：
     * 思路：
     *  根据前提条件只需要考虑5块、10块、20块的，分别处理：
     *      1.遇到五块的，不用找
     *      2.遇到十块的，找5块
     *      3.遇到二十块的，先找10，再找5(因为5是万能的)
     *  最后判断5块、10块的时候有小于0的，有则说明没法找，返回失败
     *
     *  时间复杂度 O(N)
     *  空间复杂度 O(1)
     * @param bills
     * @return
     */
    private static boolean isCanChangeSuccess02(int[] bills){
        // 方法一，只是不用排序，最后做判断5块和10块的数量是否为负数即可
        return true;
    }
}
