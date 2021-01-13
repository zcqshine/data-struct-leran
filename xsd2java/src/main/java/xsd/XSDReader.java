package xsd;


import cn.hutool.core.util.StrUtil;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.*;
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

    public List<ClassNode> paserXSD(String xsd, String taxMLPublicPath) throws Exception {

        SAXReader saxReader = new SAXReader();

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
            dataElement = (Element) element.selectSingleNode(basePath);
        }

        String elementPath = null;
        if ("".equals(XMLConstants.XSD_DEFAULT_NAMESPACE)) {
            elementPath = "//element";
        } else {
            elementPath = "//" + XMLConstants.XSD_DEFAULT_NAMESPACE + ":element";
        }
        
        map = ReadFile.read(taxMLPublicPath);

        String complexTypeXpath =  elementPath + "[@name='" + XMLConstants.MESSAGE + "']" + "/" + XMLConstants.XSD_DEFAULT_NAMESPACE + ":complexType";
        List<Node> complexNodes = element.selectNodes(complexTypeXpath);
        if (complexNodes != null){
            for (Node complexNode : complexNodes) {
                Element element1 = (Element) complexNode;
                parseComplex(element1);
            }
        }
        
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
                        if (map.get(nodeType) != null){
                            nodeType = map.get(nodeType);
                        }
                    } 
                    xsdNode.setType(StrUtil.upperFirst(nodeType));
                } else {
                    xsdNode.setType(StrUtil.upperFirst(nodeName));
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
                System.out.println(baseName);
                if (StrUtil.isBlank(baseName)){
                    baseName = nodeName;
                    System.out.println(nodeName);
                }
            } else {
                System.out.println(nodeName);
                baseName = nodeName;
            }

            baseName = StrUtil.upperFirst(baseName);
            
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
            //转换后的文件输出目录
            String dicPath = "/Users/zcqshine/Downloads/test/";
            //要转换的xsd文件
            String realPath = "/Users/zcqshine/Documents/云盘文件/开发公共文档/GT3-ZJ-金税三期标准服务清册_V2.48_fix01/附录报文/核心征管业务/02申报征收/HXZG_SB_10071/TaxMLBd_061015044_V1.0.xsd";
            //TaxMLPublic文件路径
            String taxMLPublicPath = "/Users/zcqshine/Downloads/test/TaxMLPublic.xsd";
            XSDReader xsdReader = new XSDReader();

            String tmpPath = xsdReader.editXsd(realPath, dicPath);
            
            if (tmpPath != null){
                List<ClassNode> nodes = xsdReader.paserXSD(tmpPath,taxMLPublicPath);

                String fileName = realPath.substring(realPath.lastIndexOf(File.separator)+1, realPath.lastIndexOf("."));

                String outPutPath = dicPath + fileName + ".txt";

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
                new File(tmpPath).delete();
            } else {
                System.out.println("xsd编辑后的临时路径为null");
            }
            
            
        } catch (Exception ex){
            ex.printStackTrace();

        }
    }
    
    private String editXsd(String path, String dicPath){
        try {
            //临时文件
            File outFile = File.createTempFile("tmp", ".xsd", new File(dicPath));
            try (
                    //输入
                    FileInputStream fis = new FileInputStream(path);
                    BufferedReader in = new BufferedReader(new InputStreamReader(fis));
                    //输出
                    FileOutputStream fos = new FileOutputStream(outFile);
                    PrintWriter out = new PrintWriter(fos);
                    ){
                //保存一行数据
                String thisLine;
                //从行号1开始
                int i = 1;
                while ((thisLine = in.readLine()) != null){
                    //在第一个xs:complexType行上插入行
                    if (i == 1 && thisLine != null && thisLine.contains("xs:complexType")){
                        out.println("<xs:element name=\"taxML\" type=\"taxDoc\">");
                        i++;
                    } else if (thisLine != null && thisLine.contains("</xs:schema>")){
                        out.println("</xs:element>");
                    } else if (thisLine != null && (thisLine.contains("xs:complexContent") || thisLine.contains("xs:extension")) ){
                        continue;
                    }
                    out.println(thisLine);
                }
                out.flush();
                return outFile.getPath();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
