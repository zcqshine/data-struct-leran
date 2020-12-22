package xsd;


import cn.hutool.core.util.StrUtil;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zcqshine
 * @date 2020/5/28
 */
public class XSDReader {


    private List<XSDNode> list = new ArrayList<XSDNode>();
    
    private List<ClassNode> classNodes = new ArrayList<>();

    /**
     * 数据类型map
     */
    private Map<String, String> map = new HashMap<>();
    

    /**
     * 解析XSD，返回数据节点对象列表
     *
     * @param xsd
     * @return
     * @throws Exception
     */

//    public List<XSDNode> paserXSD(String xsd) throws Exception {
    public List<ClassNode> paserXSD(String xsd) throws Exception {

        SAXReader saxReader = new SAXReader();

        // ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xsd.getBytes(BaseConstants.XM LENCODING));

        Document doc = saxReader.read(xsd);

        Element element = doc.getRootElement();

        String basePath = null;
        Element dataElement = null;
        if ("".equals(XMLConstants.XSD_DEFAULT_NAMESPACE)) {
            if ("".equals(XMLConstants.MESSAGE)) {
                dataElement = element;
            } else {
                basePath = "//element[@name='" + XMLConstants.MESSAGE + "']";
                dataElement = (Element) element.selectSingleNode(basePath);
            }
        } else {
            basePath = "//" + XMLConstants.XSD_DEFAULT_NAMESPACE + ":element[@name='" + XMLConstants.MESSAGE + "']";
//            basePath = "//" + XMLConstants.XSD_DEFAULT_NAMESPACE + ":complexType[@name='" + XMLConstants.MESSAGE + "']";
            dataElement = (Element) element.selectSingleNode(basePath);
        }

        String elementPath = null;
        if ("".equals(XMLConstants.XSD_DEFAULT_NAMESPACE)) {
            elementPath = "//element";
        } else {
            elementPath = "//" + XMLConstants.XSD_DEFAULT_NAMESPACE + ":element";
        }

        String filePath = "/Users/zcqshine/Downloads/test/TaxMLPublic.xsd";
        map = ReadFile.read(filePath);

        String complexTypeXpath =  elementPath + "[@name='" + XMLConstants.MESSAGE + "']" + "/" + XMLConstants.XSD_DEFAULT_NAMESPACE + ":complexType";
        List<Node> complexNodes = element.selectNodes(complexTypeXpath);
        if (complexNodes != null){
            for (Node complexNode : complexNodes) {
                Element element1 = (Element) complexNode;
                parseComplex(element1);
            }
        }
        
//        paseData(dataElement, "//", elementPath, "//");

        return classNodes;

    }
    
    public void parseComplex(Element element){
        ClassNode classNode = new ClassNode();
        String className = element.attributeValue("name");
        classNode.setClassName(className);
        String xpath = "//" + XMLConstants.XSD_DEFAULT_NAMESPACE + ":element[@name='" + XMLConstants.MESSAGE + "']" 
                + "/" + XMLConstants.XSD_DEFAULT_NAMESPACE + ":complexType[@name='" + element.attributeValue("name") + "']";
        String classAnnotationXpath = xpath + "/" + XMLConstants.XSD_DEFAULT_NAMESPACE + ":annotation/" + XMLConstants.XSD_DEFAULT_NAMESPACE + ":documentation";
        Node classAnnotationNode = element.selectSingleNode(classAnnotationXpath);
        if (classAnnotationNode != null){
            String classAnnotation = classAnnotationNode.getText();
            classNode.setClassAnnotation(classAnnotation);
        }
        
        List<XSDNode> children = new ArrayList<>();
        classNode.setXsdNodeList(children);
        String childXpath = xpath + "/" + XMLConstants.XSD_DEFAULT_NAMESPACE + ":sequence/" + XMLConstants.XSD_DEFAULT_NAMESPACE + ":element";
        List<Node> nodeList = element.selectNodes(childXpath);
        if (nodeList != null){
            for (Node node : nodeList) {
                XSDNode xsdNode = new XSDNode();
                Element ele = (Element) node;
                String nodeName = ele.attributeValue("name");
                xsdNode.setName(nodeName);
                String annotationXpath = childXpath + "[@name='" + nodeName + "']/" + XMLConstants.XSD_DEFAULT_NAMESPACE + ":annotation/" + XMLConstants.XSD_DEFAULT_NAMESPACE + ":documentation"; 
                Node annotationNode = ele.selectSingleNode(annotationXpath);
                if (annotationNode != null){
                    String annotation = annotationNode.getText();
                    xsdNode.setAnnotation(annotation);
                }
                
                Attribute type = ele.attribute("type");
                if (type != null){
                    String nodeType = type.getText();
                    String typeStr = map.get(nodeType);
                    if (typeStr != null){
                        xsdNode.setType(map.get(nodeType));
                    } else {
                        xsdNode.setType(StrUtil.upperFirst(nodeType));
                    }
                }

                children.add(xsdNode);
            }
        }
        
        classNodes.add(classNode);
    }

    /**
     * 转换XSD的数据节点，生成XSDNode对象
     *
     * @param element
     * @param xPath
     * @param xsdPath
     * @param unboundedXpath
     */
    public void paseData(Element element, String xPath, String xsdPath, String unboundedXpath) {
        if (element == null) {
            return;
        }

        // 获取节点name属性
        String nodeName = element.attributeValue("name");

        // 组装xml文档中节点的XPath
        xPath += nodeName;
        unboundedXpath += nodeName;

        // 并列多节点限制属性
        String maxOccurs = element.attributeValue("maxOccurs");
        if (maxOccurs != null && !"1".equals(maxOccurs) && !("//" + XMLConstants.MESSAGE + "").equals(xPath)) {// 节点可以有多个
            unboundedXpath += XMLConstants.XSD_UNBOUNDED;
        }
        
        // 组装下一个element元素的XPath
        String currentXsdPath = xsdPath + "[@name='" + nodeName + "']" + "/" + XMLConstants.XSD_DEFAULT_NAMESPACE
                + ":complexType/" + XMLConstants.XSD_DEFAULT_NAMESPACE + ":sequence/" + XMLConstants.XSD_DEFAULT_NAMESPACE
                + ":element";

        System.out.println(currentXsdPath);

        // 查找该节点下所有的element元素
        List<Node> elementNodes = element.selectNodes(currentXsdPath);
        if (elementNodes != null && elementNodes.size() > 0) {// 如果下面还有element,说明不是叶子
            System.out.println("pName=" + nodeName);
            for (Node elementNode : elementNodes) {
                if (!xPath.endsWith("/")) {
                    xPath += "/";
                    unboundedXpath += "/";
                }

                Element ele = (Element) elementNode;
                paseData(ele, xPath, currentXsdPath, unboundedXpath);
            }

        } else { // 该element为叶子
            XSDNode xsdNode = new XSDNode();
            // 获取注释节点
            String annotation = "";
            Node annotationNode = element
                    .selectSingleNode(xsdPath + "[@name='" + nodeName + "']/" + XMLConstants.XSD_DEFAULT_NAMESPACE
                            + ":annotation/" + XMLConstants.XSD_DEFAULT_NAMESPACE + ":documentation");
            if (annotationNode != null) {
                annotation = annotationNode.getText();
            }

            // 获取节点类型属性
            String nodeType = "";
            String baseName = "";
            Attribute type = element.attribute("type");
            if (type != null) {
                nodeType = type.getText();
                baseName = map.get(nodeType);
            } 
            
            xsdNode.setName(nodeName);
            xsdNode.setXPath(xPath);
            xsdNode.setAnnotation(annotation);
            xsdNode.setType(baseName);
            xsdNode.setUnboundedXpath(unboundedXpath);

            list.add(xsdNode);
        }
    }

    public static void main(String[] args) {

        try {
            String realPath = "/Users/zcqshine/Documents/共治物联/GT3-ZJ-金税三期标准服务清册_V2.48/附录报文/核心征管业务/申报征收/申报/HXZG_SB_10071/TaxMLBd_061015038_V1.0.xsd";

            XSDReader xsdReader = new XSDReader();

            List<ClassNode> nodes = xsdReader.paserXSD(realPath);

            String fileName = realPath.substring(realPath.lastIndexOf("/")+1, realPath.lastIndexOf("."));
//            System.out.println(fileName);
            
            String outPutPath = "/Users/zcqshine/Downloads/test/" + fileName + ".txt";
            
            File file = new File(outPutPath);
            FileOutputStream fos = null;
            if (!file.exists()){
                file.createNewFile();
                fos = new FileOutputStream(file);
            }else {
                fos = new FileOutputStream(file);
            }
            try (OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");){
                for (ClassNode node : nodes) {
                    osw.write(node.toString() + "\n" + "\n");
                }
            }


        } catch (Exception ex){

            ex.printStackTrace();

        }

    }


//    public static void main(String[] args) throws DocumentException {
//        SAXReader reader = new SAXReader();
//        Document document = reader.read("/Users/zcqshine/Downloads/test.xsd");
//
//        Element root = document.getRootElement();
//        Node rootNode = root.selectSingleNode("//xs:complexType");
//
//        Iterator iterator = root.elementIterator();
//
//
//        for (Iterator i = root.elementIterator(); i.hasNext();){
//            Element element = (Element) i.next();
//            Node node = element.selectSingleNode("//xs:annotation");
//            Node documentation = node.selectSingleNode("//xs:documentation");
//            System.out.println(documentation.getText());
//
//            List<Element> elementList = element.elements();
//
//            List<Node> nodes = element.selectNodes("//xs:sequence");
//
//            List<Node> contents = element.content();
//
//            if (contents != null){
//                for (Node content : contents) {
//                    if (content.matches("/xs:schema/xs:complexType/xs:sequence")){
//                        Node node1 = content.selectSingleNode("//xs:element");
//
//                        System.out.println(node1.selectObject("//xs:element[@name='name']"));
//
//                    }
//                }
//            }
//
////            System.out.println(element.getQName());
////            Attribute attribute = element.attribute(0);
////            System.out.println(attribute.getData());
//        }
//
//    }
}
