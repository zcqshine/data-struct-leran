package com.zcqshine.data.struct.learn;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 按广度遍历二叉树
 * @author zcqshine
 * @date 2019-09-19
 */
public class TreeLevelOrder {
    
    public static void levelOrder(Node node){
        if (node == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            System.out.println(node.getData());
            if (node.left != null){
                queue.add(node.left);
            } 
            if (node.right != null){
                queue.add(node.right);
            }
        }
        
    }
    
    class Node{
        Object data;
        Node left;
        Node right;

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
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
}
