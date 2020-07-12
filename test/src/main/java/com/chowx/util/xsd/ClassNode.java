package com.chowx.util.xsd;

import java.util.List;

/**
 * @author zcqshine
 * @date 2020/7/8
 */
public class ClassNode {
    String className;
    String classAnnotation;
    List<XSDNode> xsdNodeList;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassAnnotation() {
        return classAnnotation;
    }

    public void setClassAnnotation(String classAnnotation) {
        this.classAnnotation = classAnnotation;
    }

    public List<XSDNode> getXsdNodeList() {
        return xsdNodeList;
    }

    public void setXsdNodeList(List<XSDNode> xsdNodeList) {
        this.xsdNodeList = xsdNodeList;
    }
    
    @Override
    public String toString(){
        StringBuilder sb =  new StringBuilder();
        sb.append("/** \n")
                .append(" * ").append(getClassAnnotation())
                .append("\n")
                .append("*/")
                .append("\n")
                .append("public class ").append(getClassName())
                .append(" {\n");
        if (getXsdNodeList() != null){
            for (XSDNode node : getXsdNodeList()) {
                sb.append(node.toString());
            }
        }
        sb.append("\n")
                .append("}")
                .append("\n\n");
        
        return sb.toString();
    }
}
