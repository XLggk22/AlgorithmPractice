package my.text.algorithm.tree.binnary.search;

import my.text.algorithm.tree.binnary.LevelTraverse;
import my.text.algorithm.tree.binnary.Node;

/**
 * 有序数组转换为有效平衡二叉树
 * @Title: SortedArrayToValidBST
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/1/9 21:24
 * @Version 1.0
 */
public class SortedArrayToValidBST {

    public static void main(String[] args) {
        int[] sortedArray = new int[]{1, 2, 3, 4, 5, 6, 7};

//        // 左闭右开
//        System.out.println(LevelTraverse.byIterate(byRecursive(sortedArray, 0, sortedArray.length)));

        // 左闭右闭
        System.out.println(LevelTraverse.byIterate(byRecursive2(sortedArray, 0, sortedArray.length - 1)));
    }

    /**
     * 左闭右开
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static Node byRecursive(int[] arr, int left, int right){
        if(left >= right){
            return null;
        }
        int mid = left + (right - left) / 2;
        Node node = new Node(arr[mid]);
        node.setLeft(byRecursive(arr, left, mid));
        node.setRight(byRecursive(arr, mid + 1, right));
        return  node;
    }

    /**
     * 左闭右闭
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static Node byRecursive2(int[] arr, int left, int right){
        if (left > right){
            return  null;
        }

        int mid = left + (right - left) / 2;
        Node node = new Node(arr[mid]);
        node.setLeft(byRecursive2(arr, left, mid - 1));
        node.setRight(byRecursive2(arr, mid + 1, right));
        return node;
    }
}
