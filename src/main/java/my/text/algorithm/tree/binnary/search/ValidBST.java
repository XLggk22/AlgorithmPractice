package my.text.algorithm.tree.binnary.search;

import my.text.algorithm.tree.binnary.Node;

import java.util.Stack;

/**
 * 验证二叉树是否是一棵有效的二叉搜索树
 * 有效二叉搜索树
 *  定义：每个二叉树节点的
 *      左子树所有节点 均小于 父节点
 *      右子树所有节点 均大于 父节点
 *     值特征：左 < 父 < 右
 * @Title: ValidBST
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/1/5 11:27
 * @Version 1.0
 */
public class ValidBST {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);

        node5.setLeft(node1);
//        node5.setRight(node4);
//        node4.setLeft(node3);
//        node4.setRight(node6);

        node1.setRight(node3);
        node5.setRight(node7);
        node7.setLeft(node6);
        node7.setRight(node8);

        System.out.println(byRecursive(node5));
        System.out.println(byMidResursive(node5));
        System.out.println(byMidIterate(node5));
    }

    /**
     * 递归方式
     * @param root
     * @return
     */
    private static boolean byRecursive(Node root){
        return byRecursive(Long.MIN_VALUE, Long.MAX_VALUE, root);
    }

    private static boolean byRecursive(long lower, long upper, Node root){
        if (null == root){
            return true;
        }

        // 左节点走的是验证upper逻辑，右节点走的是验证lower逻辑
        if (root.getVal() < lower || root.getVal() > upper){
            return false;
        }

        return byRecursive(lower, root.getVal(), root.getLeft()) && byRecursive(root.getVal(), upper, root.getRight());
    }


    private static Integer preVal = null;

    /**
     * 递归中序遍历方式
     * 思路: 针对值特征：左 < 父 < 右， 采用中序遍历方式
     * @param root
     * @return
     */
    private static boolean byMidResursive(Node root){
        if (null == root){
            return true;
        }

        if (!byMidResursive(root.getLeft())){
            return false;
        }

        // 中序遍历后续节点小于前一个节点值说明为有效的二叉搜索树
        if (null != preVal && root.getVal() < preVal){
            return false;
        }

        // 中序遍历后，preVal赋值为前一个节点值，顺序：左-中-右
        preVal = root.getVal();

        return byMidResursive(root.getRight());
    }

    /**
     * 迭代中序遍历方式
     * 思路: 针对值特征：左 < 父 < 右， 采用中序遍历方式
     * @param root
     * @return
     */
    private static boolean byMidIterate(Node root){
        if (null == root){
            return true;
        }

        Stack<Node> stack = new Stack<>();
        Integer preVal = null;
        while (null != root || !stack.isEmpty()){
            while (null != root){
                stack.push(root);
                root = root.getLeft();
            }

            Node pop = stack.pop();
            if (null != preVal && pop.getVal() <= preVal){
                return false;
            }

            preVal = pop.getVal();

            root = pop.getRight();
        }

        return true;
    }

}
