import java.sql.Timestamp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hm.excp.dao.BaseDao;
import com.hm.excp.dao.pojo.SysLog;


public class InitRole
{

    public static void main(String[] args)
    {
        String location = "WebRoot/WEB-INF/application.xml"; 
        ApplicationContext context = new FileSystemXmlApplicationContext(location);

        BaseDao dao = (BaseDao) context.getBean("BaseDao");
        System.out.println(context);
        
        SysLog log = new SysLog();
        log.setUsername("admin");
        log.setIp("127.0.0.1");
        log.setLogType("1");
        log.setLogMsg("这个是测试test");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        log.setLogTime(ts);
        dao.save(log);
        
//        Role role1 = new Role();
//        role1.setRoleName("部门经理");
//        Role role2 = new Role();
//        role1.setRoleName("主管");
//        Role role3 = new Role();
//        role1.setRoleName("部门领导");
//        Role role4 = new Role();
//        role1.setRoleName("超级管理员");
//        Role role5 = new Role();
//        role1.setRoleName("维护员");
//        Role role6 = new Role();
//        role1.setRoleName("管机员");
//        Role role7 = new Role();
//        role1.setRoleName("银行主管");
        
//        dao.save(role1);
//        dao.save(role2);
//        dao.save(role3);
//        dao.save(role4);
//        dao.save(role5);
//        dao.save(role6);
//        dao.save(role7);
    }
}
