package my.text.algorithm.tree.binnary.order;

import my.text.algorithm.tree.binnary.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树遍历——统一循环遍历方式
 * 统一遍历风格，解决访问节点（遍历节点）和处理节点（将元素放进结果集）不一致的情况。
 * 思路：
 *  1.使用stack存放遍历的节点：统一入栈方式，只是顺序不同
 *  2.避免处理过（访问过其左右节点并放入栈中）的节点在出栈的过程中再次被处理，则：
 *      在此节点入栈有再push一个null节点；
 *      再循环遍历到此节点的时候直接出栈并且加入到result list
 *
 * @Title: UnifiedInterateTraverse
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2022/12/17 14:08
 * @Version 1.0
 */
public class InterateUnifiedTraverse {

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
     * 入栈顺序：右-左-中
     * @param root
     * @return
     */
    private static List<Integer> preOrderTraversal(Node root){
        if(null == root){
            return null;
        }
        List<Integer> resultList = new ArrayList<>();
        Stack<Node> stack = new Stack<Node>(){{add(root);}};
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            if (null != pop){
                // 表示这个节点没有处理过，需要遍历左右节点
                if (null != pop.getRight()){
                    stack.push(pop.getRight());
                }
                if (null != pop.getLeft()){
                    stack.push(pop.getLeft());
                }
                stack.push(pop);
                stack.push(null);
            }else{
                //表示这个节点已经处理过，直接放入返回队列
                resultList.add(stack.pop().getVal());
            }
        }

        return resultList;
    }

    /**
     * 中序遍历
     * 入栈顺序：
     * @param root
     * @return
     */
    private static List<Integer> midOrderTraversal(Node root){
        if(null == root){
            return null;
        }
        List<Integer> resultList = new ArrayList<>();
        Stack<Node> stack = new Stack<Node>(){{add(root);}};
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            if (null != pop){
                if (null != pop.getRight()){
                    stack.push(pop.getRight());
                }
                stack.push(pop);
                stack.push(null);
                if (null != pop.getLeft()){
                    stack.push(pop.getLeft());
                }
            }else{
                resultList.add(stack.pop().getVal());
            }
        }

        return resultList;
    }

    /**
     * 后序遍历
     * 入栈顺序：中-右-左
     * @param root
     * @return
     */
    private static List<Integer> postOrderTraversal(Node root){
        if(null == root){
            return null;
        }
        List<Integer> resultList = new ArrayList<>();
        Stack<Node> stack = new Stack<Node>(){{add(root);}};
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            if (null != pop){
                stack.push(pop);
                stack.push(null);
                if (null != pop.getRight()) {
                    stack.push(pop.getRight());
                }
                if (null != pop.getLeft()){
                    stack.push(pop.getLeft());
                }
            }else{
                resultList.add(stack.pop().getVal());
            }
        }

        return resultList;
    }
}
