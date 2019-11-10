package test.src.main.java.com.example.demo.algorithm;

import com.alibaba.fastjson.JSON;

/**
 * 二叉搜索树
 *
 * @author hfj
 * @date 2019\11\9 0009
 */
public class BinarySearchTree {
    public Node root;
    public Node current;

    /**
     * 节点
     */
    class Node {
        public Node left;
        public Node right;
        public int data;

        public Node(int data) {
            this.left = null;
            this.right = null;
            this.data = data;
        }
    }

    /**
     * 插入数据（兼容重复数据，相同值放右子树，按照正常规律走）
     */
    public void addNode(int data) {
        if (root == null) {
            root = new Node(data);
        } else {
            current = root;
            while (current != null) {
                // 寻找叶子结点
                // 左子页查找
                if (data < current.data) {
                    if (current.left == null) {
                        current.left = new Node(data);
                        break;
                    } else {
                        current = current.left;
                    }
                } else {
                    // 右子页查找
                    if (current.right == null) {
                        current.right = new Node(data);
                        break;
                    } else {
                        current = current.right;
                    }
                }
            }
        }
    }

    /**
     * 查找数据
     */
    public Node findNode(int data) {
        Node p = root;
        while (p != null) {
            if (data < p.data) {
                // 左遍历
                p = p.left;
            } else if (data > p.data) {
                // 右遍历
                p = p.right;
            } else {
                // 找到返回
                return p;
            }
        }
        // 没有找到
        return null;
    }

    /**
     * 删除数据
     */
    public void removeNode(int data) {
        // p指向要删除的节点，初始化指向根节点
        Node p = root;
        // pp记录的是p的父节点
        Node pp = null;
        while (p != null && p.data != data) {
            pp = p;
            p = data > p.data ? p.right : p.left;
        }
        // 没有找到
        if (p == null) {
            return;
        }

        // 要删除的节点有两个子节点
        // 查找右子树中最小节点
        if (p.left != null && p.right != null) {
            Node minP = p.right;
            // minPP表示minP的父节点
            Node minPP = p;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            // 找到minP为最小节点，将minP的数据替换到p中
            p.data = minP.data;
            // p指针指向minP
            p = minP;
            pp = minPP;
            // 下面就变成了删除minP了，兼容minP有右子节点的情况
        }

        // 删除节点是叶子节点或者仅有一个子节点
        // p的子节点
        Node child;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        // 删除的是根节点
        if (pp == null) {
            root = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }
    }


    /**
     * 中序遍历树
     */
    public void inOrderPrintTree(Node node) {
        if (node != null) {
            // 左中右，不同的遍历调整位置就可以
            inOrderPrintTree(node.left);
            System.out.print(node.data + ",");
            inOrderPrintTree(node.right);
        }
    }

    public static void main(String[] args) {

        BinarySearchTree my = new BinarySearchTree();
        //下列插入顺序可以打乱
        my.addNode(9);
//        my.addNode(5);
//        my.addNode(15);
//        my.addNode(3);
//        my.addNode(7);
//        my.addNode(11);
//        my.addNode(17);
//        my.addNode(6);
//        my.addNode(4);
//        my.addNode(1);
//        my.addNode(10);
//        my.addNode(8);
//        my.addNode(12);
//        my.addNode(16);
//        my.addNode(7);
        System.out.println("删除前，中序遍历");
        my.inOrderPrintTree(my.root);
        // 删除
        my.removeNode(9);
        System.out.println("\n删除后，中序遍历");
        my.inOrderPrintTree(my.root);


//        Node node = my.findNode(23);
//        System.out.println("\n23 的查找结果：" + JSON.toJSONString(node));
//
//        Node node2 = my.findNode(11);
//        System.out.println("\n11 的查找结果：" + JSON.toJSONString(node2));

    }
}
