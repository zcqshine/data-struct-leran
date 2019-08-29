package com.zcqshine.data.struct.learn;

/**
 * @author zcqshine
 * @date 2019-08-07
 */
public class Node<T> {
    public Node<T> next = null;
    private T t;
    
    public Node(){}
    
    public Node(T t){
        this.t = t;
    }
    
    public boolean hasNext(){
        return next != null;
    }

    public T getT() {
        return t;
    }
}
