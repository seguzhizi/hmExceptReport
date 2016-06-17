import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hm.excp.action.LoginAction;
import com.hm.excp.service.LoginServiceImpl;
import com.hm.excp.util.WebUtil;

public class TestUUID
{
    public static void main(String[] args)
    {
        // for(int i = 0 ; i<10; i++){
        // UUID uuid = UUID.randomUUID();
        // System.out.println(uuid.toString().replaceAll("-", ""));
        // }

        String location = "WebRoot/WEB-INF/application.xml";
        ApplicationContext context = new FileSystemXmlApplicationContext(
                location);
        LoginServiceImpl hello = (LoginServiceImpl) context.getBean("LoginService");
        // 直接调用这个业务类就行了

    }

}
