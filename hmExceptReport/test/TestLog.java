 import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hm.excp.action.LoginAction;
  
public class TestLog {  
    public static void main(String[] args)
    {
        new TestLog().test1();
    }
    public void test1()  
    {  
//        ClassPathXmlApplicationContext ctx =   
//                new ClassPathXmlApplicationContext("applicationContext-aop.xml");
        String location = "WebRoot/WEB-INF/application.xml"; 
        ApplicationContext context = new FileSystemXmlApplicationContext(location);

        LoginAction userManager = (LoginAction) context.getBean("LoginAction");  
        //userManager.testLogin();  
        System.out.println("222222222222222222222222222222222222222");
    }  
} 