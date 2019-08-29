package com.zcqshine.data.struct.learn;

import com.sun.java.swing.action.NextAction;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import sun.security.jca.GetInstance;

/**
 * @author zcqshine
 * @date 2019-08-07
 */
public class SingleLinked<T> {
    /**
     * 头节点类似一个哨兵元素.不算真实的节点
     */
    private Node<T> head = new Node<T>(null);
    private int size = 0;
    
    public SingleLinked(){}
    
    public T get(int i){
        if (i < 0 || i >= size){
            throw new ArrayIndexOutOfBoundsException("访问的位置超出了连表的范围");
        } else {
            //把第一个节点给临时节点tmp,让tmp遍历
            Node<T> tmp = head;
            //counter用来计数,找到i在链表里的节点位置,头节点不算链表的真实节点,所以从 -1 开始计数
            int counter = -1;
            while (tmp != null){
                if (counter == i){
                    return  tmp.getT();
                }
                tmp = tmp.next;
                counter++;
            }
        }
        return null;
    }

    /**
     * 添加元素
     * @param t
     */
    public void add(T t){
        Node<T> tmp = head;
        while (tmp.next != null){
            tmp = tmp.next;
        }
        tmp.next = new Node<T>(t);
        size++;
    }
    
    public void add(int i, T t){
        if (i < 0 || i > size){
            throw new ArrayIndexOutOfBoundsException("插入的位置超出了连表的范围");
        } else {
            Node<T> tmp = head;
            int counter = -1;
            while (tmp != null){
                if ((i-1) == counter){
                    Node node = new Node(t);
                    Node back = tmp.next;
                    tmp.next = node;
                    node.next = back;
                    size++;
                }
                tmp = tmp.next;
                counter++;
            }
        }
    }
    
    public Node revers(Node<T> node){
        Node<T> preNode = null;
        Node<T> nextNode = null;

        int i = 0;
        while(node != null){
            nextNode = node.next;
            node.next = preNode;
            preNode = node;
            node = nextNode;
            i++;
            if (i == size){
                this.head.next = preNode;
            }
        }
        return preNode;
    }
    
    public void revers(){
        revers(head.next);
    }
    
    public static void main(String[] args) {
        SingleLinked<String> linked = new SingleLinked<String>();
        linked.add("a");
        linked.add("b");
        linked.add("c");
        linked.add("d");
        
//        linked.add(1, "A");
//        System.out.println(linked.get(0));

        linked.revers();
        
        for (int i = 0; i < linked.size; i++) {
            System.out.println(linked.get(i));
        }
        
    }
    
}

