package com.chowx;


/**
 * Created by zcqshine on 2017/2/21.
 */

public class Person {
    private int uid;
    private String name;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  public static void main(String[] args) throws CloneNotSupportedException {
    Person p1 = new Person();
    p1.setUid(1);
    p1.setName("1");

    Person p2 = new Person();
    p2 = (Person) p1.clone();
    p2.setUid(2);

    System.out.println(p1.getUid());
  }
}
