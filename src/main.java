import org.dom4j.DocumentException;

import java.util.Scanner;

/**
 * Created by zdy on 2017/3/3.
 */
public class main {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入文件绝对路径（建议粘贴）：");
        Parser p= null;
        int select=-1;
        while(true){
            try {
                p = new Parser(sc.next());
                p.getTagAndName(p.getRoot());
                p.sortTag();
                break;
            } catch (DocumentException e) {
                //e.printStackTrace();
                System.out.print("文件路径有误，请重新输入：");
            }
        }
        System.out.println("**************************************************************************************************");
        System.out.println("*** 1 定义   COptionUI* m_pPiliang;                                                             ***");
        System.out.println("*** 2 置空   m_pPiliang(NULL),                                                                  ***");
        System.out.println("*** 3 绑定   m_pPiliang = static_cast<COptionUI*>(m_PaintManager.FindControl(_T(\"piliang\")));   ***");
        System.out.println("**************************************************************************************************");

        System.out.print("请输入选项(1，2，3)，按0退出：");
        while(true){
            select=sc.nextInt();
            if (select>=1&&select<=3){
                System.out.println();
                System.out.println("**************************************************************************************************");
                p.buildResult(select);
                System.out.println("**************************************************************************************************");
                System.out.println();
                System.out.print("继续输入选项(1,2,3)，按0退出：");
            }else if(select==0){
                break;
            }else{
                System.out.print("输入选项有误，请重新输入(1,2,3)：");
            }
        }

        //p.buildResult(0);//默认  Option:piliang
        //p.buildResult(1);//1是定义   COptionUI* m_pPiliang;
        //p.buildResult(2);//2是置NULL    m_pPiliang(NULL),
        //p.buildResult(3);//3是绑定   m_pPiliang = static_cast<COptionUI*>(m_PaintManager.FindControl(_T("piliang")));*/
    }
}
