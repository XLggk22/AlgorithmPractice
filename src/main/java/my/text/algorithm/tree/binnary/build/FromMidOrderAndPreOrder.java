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
 *  前序 [1, 2, 4, 5, 3, 6, 7]
 * @Title: FromMidOrderAndPreOrder
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/1/3 14:44
 * @Version 1.0
 */
public class FromMidOrderAndPreOrder {

    // 用于存放中序list，元素-下标，方便取父节点index
    private static Map<Integer,Integer> VALUE_INDEX_MAP = new HashMap<>();

    public static void main(String[] args) {
        List<Integer> midOrderList = Arrays.asList(4, 2, 5, 1, 6, 3, 7);
        List<Integer> preOrderList = Arrays.asList(1, 2, 4, 5, 3, 6, 7);
        for (int i = 0; i < midOrderList.size(); i++) {
            VALUE_INDEX_MAP.put(midOrderList.get(i), i);
        }
        Node node = byRecursive(midOrderList, preOrderList, 0, midOrderList.size(), 0, preOrderList.size());
        // 打印构建的二叉树
        System.out.println("===========================构建结果，前序遍历:===========================");
        System.out.println(IterateTraverse.preOrderTraversal(node));
        System.out.println("=======================================================================");
    }

    /**
     * 递归方式
     * @param midOrderList
     * @param preOrderList
     * @param midStart
     * @param midEnd
     * @param preStart
     * @param preEnd
     * @return
     */
    private static Node byRecursive(List<Integer> midOrderList, List<Integer> preOrderList,
                                    int midStart, int midEnd, int preStart, int preEnd){

        System.out.println("----------------------recursive----------------------");
        System.out.println("midOrderList: "+ midOrderList);
        System.out.println("preOrderList: "+ preOrderList);
        System.out.println("midStart: " + midStart + ",midEnd: " + midEnd + ",preStart: " + preStart + ",preEnd: " + preEnd );

        if (midStart >= midEnd || preStart >= preEnd){
            return null;
        }

        Integer parentVal = preOrderList.get(preStart);
        Integer parentIndex = VALUE_INDEX_MAP.get(parentVal);

        // 父节点左边长度
        int leftLength = parentIndex - midStart;

        int leftMidStart = midStart;
        int leftMideEnd = parentIndex;
        int leftPreStart = preStart + 1;
        int leftPreEnd = leftPreStart + leftLength;

        int rightMidStart = parentIndex + 1;
        int rightMideEnd = midEnd;
        int rightPreStart = leftPreEnd;
        int rightPreEnd = preEnd;

        Node root = new Node(parentVal);
        root.setLeft(byRecursive(midOrderList, preOrderList, leftMidStart, leftMideEnd, leftPreStart, leftPreEnd));
        System.out.println("\n ==========================right========================== \n");
        root.setRight(byRecursive(midOrderList, preOrderList, rightMidStart, rightMideEnd, rightPreStart, rightPreEnd));

        return root;
    }
}
