package my.text.algorithm.greedy;

import java.util.Arrays;

/**
 * 分发饼干，尽可能喂饱多的孩子，并返回喂饱孩子的个数。
 * https://programmercarl.com/0455.%E5%88%86%E5%8F%91%E9%A5%BC%E5%B9%B2.html#%E6%80%9D%E8%B7%AF
 * 示例 2:
 *      输入: g = [1,2], s = [1,2,3]
 *      输出: 2
 *      解释:你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。你拥有的饼干数量和尺寸都足以让所有孩子满足。所以你应该输出2.
 * @Title: DispatchCookie
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/3/1 16:51
 * @Version 1.0
 */
public class DispatchCookie {

    public static void main(String[] args) {
        int[] cookies = new int[]{1,2};
        int[] childrens = new int[]{1,2,3};
        int count = dispatch(cookies, childrens);
        System.out.println(count);
    }

    /**
     * 饼干分发方法
     * 思路：
     *      小饼干优先喂饱小胃口，最后喂饱的数量肯定是最大的
     * @param cookies
     * @param childrens
     */
    private static int dispatch(int[] cookies, int[] childrens){
        Arrays.sort(cookies);
        Arrays.sort(childrens);

        int count = 0;
        int childIndex = 0;
        for (int i = 0; i < cookies.length && childIndex < childrens.length; i++) {
            if (cookies[i] >= childrens[childIndex]){
                childIndex ++;
                count ++;
            }
        }
        return count;
    }
}
