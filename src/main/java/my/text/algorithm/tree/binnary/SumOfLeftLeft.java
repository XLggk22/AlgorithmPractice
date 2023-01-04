package my.text.algorithm.tree.binnary;

import java.util.List;
import java.util.Stack;

/**
 * 左子节点之和
 * 左子节点定义:
 *  1.节点是left节点
 *  2.此节点的左右子节点为空
 * @Title: SumOfLeftLeft
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2022/12/31 17:57
 * @Version 1.0
 */
public class SumOfLeftLeft {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node1.setLeft(node2);
        node1.setRight(node3);

        node2.setLeft(node4);
        node2.setRight(node5);

        node3.setLeft(node6);
        node3.setRight(node7);

        Node root = node1;

        System.out.println(byIterate(root));
    }

    /**
     * 递归法
     * @param root
     * @return
     */
    private static Integer byRecursive(Node root){
        // 如果当前节点为空, 返回0
        if (null == root){
            return 0;
        }

        // 说明当前节点的左节点是叶子节点，即左子节点
        // 这段逻辑只有在左子节点才会进去
        if (root.getLeft() != null && root.getLeft().getLeft() == null && root.getLeft().getRight() == null){
            return root.getLeft().getVal();
        }

        // 求出根节点 的 左节点 的 左子节点
        Integer left = byRecursive(root.getLeft());

        // 求出根节点 的 右节点 的 左子节点
        Integer right = byRecursive(root.getRight());

        // 根左子节点 + 根右子节点
        return left + right;
    }


    private static Integer byIterate(Node root){
        if (null == root){
            return 0;
        }

        // 队列队列或栈都可以
        Stack<Node> stack = new Stack<Node>(){{push(root);}};
        int sum = 0;
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            // 这段逻辑只有在左子节点才会进去，所以下面左右子节点入栈统一处理即可
            if (null != pop.getLeft() && null == pop.getLeft().getLeft() && null == pop.getLeft().getRight()){
                sum += pop.getLeft().getVal();
            }
            if (null != pop.getLeft()){
                stack.push(pop.getLeft());
            }
            if (null != pop.getRight()){
                stack.push(pop.getRight());
            }
        }
        return sum;
    }
}
