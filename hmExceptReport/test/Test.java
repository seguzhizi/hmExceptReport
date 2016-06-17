import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hm.excp.dao.BaseDao;
import com.hm.excp.dao.pojo.People;


public class Test
{

    public static void main(String[] args)
    {
        //        ApplicationContext context = new ClassPathXmlApplicationContext(location);
        
        String location = "WebRoot/WEB-INF/application.xml"; 
        ApplicationContext context = new FileSystemXmlApplicationContext(location);

        BaseDao dao = (BaseDao) context.getBean("BaseDao");
        System.out.println(context);
        
        System.out.println(dao.toString());
        People people = new People();
        people.setUsername("admin");
        people.setPassword("123456");
        people.setStatus("1");
        dao.save(people);
        
    }
}
