package my.text.algorithm.dp;

/**
 * 斐波那契数
 *  定义：斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。
 *  也就是： F(0) = 0，F(1) = 1 F(n) = F(n - 1) + F(n - 2)，其中 n > 1 给你n ，请计算 F(n) 。
 * https://programmercarl.com/0509.%E6%96%90%E6%B3%A2%E9%82%A3%E5%A5%91%E6%95%B0.html#%E6%80%9D%E8%B7%AF
 * @Title: Fi
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/3/19 16:55
 * @Version 1.0
 */
public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(fib03(2));
        System.out.println(fib03(3));
        System.out.println(fib03(4));
        System.out.println(fib03(5));
        System.out.println(fib03(6));
        System.out.println(fib03(7));
    }

    /**
     * 方法1：压缩
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     * @param n
     * @return
     */
    private static int fib01(int n){
        if (n < 2){
            return n;
        }
        int a=0,b=1,c=0;
        for (int i=2 ; i<=n ; i++){
            c = a + b;
            a = b ;
            b = c;
        }
        return c;
    }

    /**
     * 方法2：动态规划
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     * @param n
     * @return
     */
    private static int fib02(int n){
        if (n < 2){
            return 1;
        }
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        for (int i=2 ; i <= n ; i ++){
            nums[i] = nums[i-1] + nums[i-2];
        }
        return nums[n];
    }

    /**
     * 方法3：递归
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     * @return
     */
    private static int fib03(int n){
        if (n <= 2){
            return 1;
        }else{
            return fib03(n-1) + fib03(n-2);
        }
    }
}
