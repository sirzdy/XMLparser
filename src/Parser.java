import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zdy on 2017/3/3.
 */

public class Parser {
    private Element root=null;
    private List<TagAndName> tagAndNameList=new ArrayList<TagAndName>();

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
    public void getTagAndName(Element node){
        List<Attribute> listAttr=node.attributes();//当前节点的所有属性的list
        for(Attribute attr:listAttr){//遍历当前节点的所有属性
            if (attr.getName().equals("name")){
                TagAndName tagAndName=new TagAndName(node.getName(),attr.getValue());//第一个参数是tag，第二个参数是name
                tagAndNameList.add(tagAndName);
            }
        }
        //递归遍历当前节点所有的子节点
        List<Element> listElement=node.elements();//所有一级子节点的list
        for(Element e:listElement){//遍历所有一级子节点
            this.getTagAndName(e);//递归
        }
    }
    public void sortTag(){
        Collections.sort(tagAndNameList);
    }
    public void buildResult(int type){
        if (type==0){
            for(TagAndName tagAndName : tagAndNameList){
                System.out.println(tagAndName.getTag()+":"+tagAndName.getName());
            }
        }
        //COptionUI* m_pPiliang;
        if (type==1){
            for(TagAndName tagAndName : tagAndNameList){
                System.out.println("C"+tagAndName.getTag()+"UI* "+"m_p"+captureName(tagAndName.getName())+";");
            }
        }
        //m_pPiliang(NULL),
        if (type==2){
            for(TagAndName tagAndName : tagAndNameList){
                System.out.println("m_p"+captureName(tagAndName.getName())+"(NULL),");
            }
        }
        //m_pPiliang = static_cast<COptionUI*>(m_PaintManager.FindControl(_T("piliang")));
        if (type==3){
            for(TagAndName tagAndName : tagAndNameList){
                System.out.println("m_p"+captureName(tagAndName.getName())+" = static_cast<C"+tagAndName.getTag()+"UI*>(m_PaintManager.FindControl(_T(\""+tagAndName.getName()+"\")));");
            }
        }

    }
    public static String captureName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return  name;
    }
}