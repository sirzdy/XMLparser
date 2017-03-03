import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * Created by zdy on 2017/3/3.
 */

public class Parser {
    private Element root=null;
    Parser(String file) throws DocumentException {
        SAXReader sax = new SAXReader();//创建一个SAXReader对象
        File xmlFile = new File(file);//根据指定的路径创建file对象
        Document document = sax.read(xmlFile);//获取document对象,如果文档无节点，则会抛出Exception提前结束
        root = document.getRootElement();//获取根节点
    }
    public Element getRoot(){
        return root;
    }
    public void getNodes(Element node){
        System.out.println("--------------------");
        //当前节点的名称、文本内容和属性
        System.out.println("当前节点名称："+node.getName());//当前节点名称
        System.out.println("当前节点的内容："+node.getTextTrim());//当前节点名称
        List<Attribute> listAttr=node.attributes();//当前节点的所有属性的list
        for(Attribute attr:listAttr){//遍历当前节点的所有属性
            String name=attr.getName();//属性名称
            String value=attr.getValue();//属性的值
            System.out.println("属性名称："+name+"属性值："+value);
        }
        //递归遍历当前节点所有的子节点
        List<Element> listElement=node.elements();//所有一级子节点的list
        for(Element e:listElement){//遍历所有一级子节点
            this.getNodes(e);//递归
        }
    }
    public void getTagAndName(Element node,int type){
        List<Attribute> listAttr=node.attributes();//当前节点的所有属性的list
        boolean sign=false;//sign为true时含有name属性
        String name=null;
        String value=null;
        for(Attribute attr:listAttr){//遍历当前节点的所有属性
            if (attr.getName().equals("name")){
                sign=true;
                value=attr.getValue();//属性的值
            }
        }
        if (sign==true){
            //System.out.println(node.getName());
            //System.out.println(value);

            //COptionUI* m_pPiliang;
            if (type==0)
                System.out.println("C"+node.getName()+"UI* "+"m_p"+captureName(value)+";");

            //m_pPiliang(NULL),
            if (type==1)
                System.out.println("m_p"+captureName(value)+"(NULL),");

            //m_pPiliang = static_cast<COptionUI*>(m_PaintManager.FindControl(_T("piliang")));
            if (type==2)
                System.out.println("m_p"+captureName(value)+" = static_cast<C"+node.getName()+"UI*>(m_PaintManager.FindControl(_T(\""+value+"\")));");
        }

        //递归遍历当前节点所有的子节点
        List<Element> listElement=node.elements();//所有一级子节点的list
        for(Element e:listElement){//遍历所有一级子节点
            this.getTagAndName(e,type);//递归
        }
    }
    public static String captureName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return  name;
    }
}