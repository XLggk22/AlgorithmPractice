package my.text.algorithm.tree.binnary.search;

import my.text.algorithm.tree.binnary.LevelTraverse;
import my.text.algorithm.tree.binnary.Node;

/**
 * 二叉搜索树
 * 子节点左小右大
 * @Title: Search
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/1/4 20:11
 * @Version 1.0
 */
public class Search {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node7 = new Node(7);

        node4.setLeft(node2);
        node4.setRight(node7);

        node2.setLeft(node1);
        node2.setRight(node3);

        Node root = node4;

        System.out.println(LevelTraverse.byIterate(searchNode(root, 2)));
    }

    /**
     * @param root  根节点
     * @param targetVal 目标节点值
     * @return
     */
    private static Node searchNode(Node root, Integer targetVal){
        if (null == root || null == targetVal){
            return null;
        }
        if (root.getVal().equals(targetVal)){
            return root;
        }
        if (targetVal < root.getVal()){
            return  searchNode(root.getLeft(), targetVal);
        }
        return searchNode(root.getRight(), targetVal);
    }
}
