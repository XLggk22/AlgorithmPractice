package my.text.algorithm.tree.binnary.order;

import my.text.algorithm.tree.binnary.Node;

import java.util.*;

/**
 * 二叉树遍历——循环遍历方式
 * @Title: IterateTraverse
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2022/12/14 15:03
 * @Version 1.0
 */
public class IterateTraverse {

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

        System.out.println(preOrderTraversal(root));
        System.out.println(midOrderTraversal(root));
        System.out.println(postOrderTraversal(root));
    }

    /**
     * 前序遍历
     * 遍历顺序：中-左-右
     * 入栈顺序：中-右-左
     * 思路:
     *  1.初始化stack包含root节点
     *  2.遍历体（条件:stack不为空）：
     *    1）.右节点入栈
     *    2）.左节点入栈
     * @param root
     * @return
     */
    public static List<Integer> preOrderTraversal(Node root){
        if (null == root){
            return null;
        }
        List<Integer> resultList = new ArrayList<>();
        Stack<Node> stack = new Stack<Node>(){{add(root);}};
        while (!stack.isEmpty()){
            Node node = stack.pop();
            resultList.add(node.getVal());
            if (null != node.getRight()){
                stack.push(node.getRight());
            }
            if (null != node.getLeft()){
                stack.push(node.getLeft());
            }
        }
        return resultList;
    }

    /**
     * 中序遍历
     * 遍历顺序: 左-中-右
     * 入栈顺序： 左-右
     * 思路：
     * 1.初始话stack为空，定义curr节点指向root
     * 2.循环体（条件:stack不为空）:
     *  1).curr不为空则入栈；curr指向它的左节点(此步骤不做任何加result list操作)
     *  2).curr为空则取出栈顶，加入到result list，curr执行它的右节点
     * @param root
     * @return
     */
    public static List<Integer> midOrderTraversal(Node root) {
        if (null == root) {
            return null;
        }
        List<Integer> resultList = new ArrayList<>();
        Stack<Node> stack = new Stack<Node>();
        Node curr = root;
        while (curr != null || !stack.isEmpty()){
            if (null != curr){
                stack.push(curr);
                curr = curr.getLeft();
            }else{
                Node pop = stack.pop();
                resultList.add(pop.getVal());
                curr = pop.getRight();
            }
        }
        return  resultList;
    }

    /**
     * 后序遍历
     * 遍历顺序：左-右-中
     * 入栈顺序：中-左-右
     * 出栈顺序：中-右-左， 最后翻转结果
     * 思路：
     *  1.初始化stack包含root
     *  2.循环体（条件stack不为空）
     *    1）取出stack栈顶节点node，加入result list
     *    2) 如果node左边不为空，则入栈stack
     *    3) 如果node右边不为空，则入栈stack
     * @param root
     * @return
     */
    public static List<Integer> postOrderTraversal(Node root) {
        if(null == root){
            return null;
        }
        List<Integer> resultList = new ArrayList<>();
        Stack<Node> stack = new Stack<Node>(){{add(root);}};
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            //第一轮root节点就会出栈加入到result list
            resultList.add(pop.getVal());
            if (null != pop.getLeft()){
                stack.push(pop.getLeft());
            }
            if (null != pop.getRight()){
                stack.push(pop.getRight());
            }
        }

        Collections.reverse(resultList);
        return resultList;
    }
}
