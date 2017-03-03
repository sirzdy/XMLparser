/**
 * Created by zdy on 2017/3/3.
 */
public class main {
    public static void main(String[] args) throws Exception {
        Parser p=new Parser("/Users/zdy/Workspace/test.xml");
        //p.getNodes(p.getRoot());
        p.getTagAndName(p.getRoot(),0);//0是定义   COptionUI* m_pPiliang;
        p.getTagAndName(p.getRoot(),1);//1是置NULL    m_pPiliang(NULL),
        p.getTagAndName(p.getRoot(),2);//2是绑定   m_pPiliang = static_cast<COptionUI*>(m_PaintManager.FindControl(_T("piliang")));
    }
}
