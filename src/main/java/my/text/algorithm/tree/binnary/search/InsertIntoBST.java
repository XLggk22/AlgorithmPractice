package my.text.algorithm.tree.binnary.search;

import my.text.algorithm.tree.binnary.LevelTraverse;
import my.text.algorithm.tree.binnary.Node;

/**
 * 插入到二叉搜索树，条件：
 *  1.插入的值和树中的值不重复
 *  2.最后的二叉树不要求是有效的二叉搜索树,即保证节点值：左边 < 中 < 右
 * @Title: InsertIntoBST
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/1/5 22:57
 * @Version 1.0
 */
public class InsertIntoBST {

    public static void main(String[] args) {
        Node node3 = new Node(3);
        Node node5 = new Node(5);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node10 = new Node(10);

        node7.setLeft(node5);
        node7.setRight(node10);

        node5.setLeft(node3);
        node5.setRight(node8);

        Node root = node7;

        System.out.println(LevelTraverse.byIterate(byRecursive(root, new Node(6))));

    }

    /**
     * 递归方式
     * @param root 根节点
     * @param insertNode 插入值
     * @return
     */
    private static Node byRecursive(Node root, Node insertNode){
        if (null == root){
            return null;
        }

        /**
         * 1.如果插入值 < root.val
         * 2.判断root左边是否为空
         *      为空，则把insert值放到root左边
         *      不为空，则递归左边
         */
        if (insertNode.getVal() < root.getVal()){
            if (null == root.getLeft()){
                root.setLeft(insertNode);
                return root;
            }else{
                byRecursive(root.getLeft(), insertNode);
            }
        }
        if (insertNode.getVal() > root.getVal()){
            if (null == root.getRight()){
                root.setRight(insertNode);
                return root;
            }else{
                byRecursive(root.getRight(), insertNode);
            }
        }

        System.out.println("=========未找到合适位置============");
        return root;
    }
}
