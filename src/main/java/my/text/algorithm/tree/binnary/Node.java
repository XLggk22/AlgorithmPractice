package my.text.algorithm.tree.binnary;

/**
 *
 * @Title: Node
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2022/12/14 14:50
 * @Version 1.0
 */
public class Node {
    Integer val;

    private Node left;

    private Node right;

    public Node(Integer val) {
        this.val = val;
    }

    public Node(Integer val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
