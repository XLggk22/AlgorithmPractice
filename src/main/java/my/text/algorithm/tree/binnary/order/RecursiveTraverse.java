package my.text.algorithm.tree.binnary.order;

import my.text.algorithm.tree.binnary.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树遍历——递归方式
 * @Title: BinaryTree
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2022/12/14 14:09
 * @Version 1.0
 */
public class RecursiveTraverse {

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
     * 前序遍历：父—左-右
     * @param node
     * @return
     */
    public static List<Integer> preOrderTraversal(Node node){
        List<Integer> list = new ArrayList<>();
        if (null == node){
            return list;
        }

        list.add(node.getVal());
        list.addAll(preOrderTraversal(node.getLeft()));
        list.addAll(preOrderTraversal(node.getRight()));
        return  list;
    }

    /**
     * 中序遍历：左-父-右
     * @param node
     * @return
     */
    public static List<Integer> midOrderTraversal(Node node){
        List<Integer> list = new ArrayList<>();
        if (null == node){
            return list;
        }

        list.addAll(midOrderTraversal(node.getLeft()));
        list.add(node.getVal());
        list.addAll(midOrderTraversal(node.getRight()));
        return  list;
    }

    /**
     * 后序遍历：左-父-右
     * @param node
     * @return
     */
    public static List<Integer> postOrderTraversal(Node node){
        List<Integer> list = new ArrayList<>();
        if (null == node){
            return list;
        }

        list.addAll(postOrderTraversal(node.getLeft()));
        list.addAll(postOrderTraversal(node.getRight()));
        list.add(node.getVal());
        return  list;
    }
}

