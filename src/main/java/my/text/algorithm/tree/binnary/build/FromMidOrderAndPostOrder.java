package my.text.algorithm.tree.binnary.build;

import my.text.algorithm.tree.binnary.Node;
import my.text.algorithm.tree.binnary.order.IterateTraverse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据中序遍历和后续遍历构建二叉树
 *  中序 [4, 2, 5, 1, 6, 3, 7]
 *  后续 [4, 5, 2, 6, 7, 3, 1]
 * @Title: FromMidOrderAndPostOrder
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/1/3 14:44
 * @Version 1.0
 */
public class FromMidOrderAndPostOrder {

    // 用于存放中序list，元素-下标，方便取父节点index
    private static Map<Integer,Integer> VALUE_INDEX_MAP = new HashMap<>();

    public static void main(String[] args) {
        List<Integer> midOrderList = Arrays.asList(4, 2, 5, 1, 6, 3, 7);
        List<Integer> postOrderList = Arrays.asList(4, 5, 2, 6, 7, 3, 1);
        for (int i = 0; i < midOrderList.size(); i++) {
            VALUE_INDEX_MAP.put(midOrderList.get(i), i);
        }
        Node node = byRecursive(midOrderList, postOrderList, 0, midOrderList.size(), 0, postOrderList.size());
        // 打印构建的二叉树
        System.out.println(IterateTraverse.preOrderTraversal(node));
    }

    /**
     * 递归方式
     * @param midOrderList
     * @param postOrderList
     * @param midStart
     * @param midEnd
     * @param postStart
     * @param postEnd
     * @return
     */
    private static Node byRecursive(List<Integer> midOrderList, List<Integer> postOrderList,
                                    int midStart, int midEnd, int postStart, int postEnd){

        System.out.println("----------------------recursive----------------------");
        System.out.println("midOrderList: "+ midOrderList);
        System.out.println("postOrderList: "+ postOrderList);
        System.out.println("midStart: " + midStart + ",midEnd: " + midEnd + ",postStart: " + postStart + ",postEnd: " + postEnd );
        if (midStart >= midEnd || postStart >= postEnd){
            return null;
        }

        Integer parentVal = postOrderList.get(postEnd - 1);
        Integer parentIndex = VALUE_INDEX_MAP.get(parentVal);

        // 父节点左边长度
        int leftLength = parentIndex - midStart;

        int leftMidStart = midStart;
        int leftMideEnd = parentIndex;
        int leftPostStart = postStart;
        int leftPostEnd = postStart + leftLength;

        int rightMidStart = parentIndex + 1;
        int rightMideEnd = midEnd;
        int rightPostStart = postStart + leftLength;
        int rightPostEnd = postEnd - 1;

        Node root = new Node(parentVal);
        root.setLeft(byRecursive(midOrderList, postOrderList, leftMidStart, leftMideEnd, leftPostStart, leftPostEnd));
        System.out.println("==========================right==========================");
        root.setRight(byRecursive(midOrderList, postOrderList, rightMidStart, rightMideEnd, rightPostStart, rightPostEnd));
        return root;
    }
}
