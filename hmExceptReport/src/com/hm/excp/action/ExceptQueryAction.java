/*
 * 文件名：ExceptQueryAction.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：故障处理结果查询的Action类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hm.excp.dao.pojo.People;
import com.hm.excp.dao.pojo.TerminalExcept;
import com.hm.excp.dto.PageParams;
import com.hm.excp.dto.QueryExceptBean;
import com.hm.excp.dto.TerminalExceptExportBean;
import com.hm.excp.service.IExceptService;
import com.hm.excp.util.HMLogger;
import com.hm.excp.util.JasperUtil;
import com.hm.excp.util.WebUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ExceptQueryAction extends ActionSupport
{
    /***/
    HMLogger logger = HMLogger.getHMLogger(this.getClass());
    
    /**故障信息导出时的bean*/
    private List<TerminalExceptExportBean> exportList;
    
    /**页面查询的故障信息列表*/
    private List<TerminalExcept> exceptList;
    
    /**导出故障信息时，对应的jasper文件需要在excel中显示的常量参数*/
    private Map<String,String> reportParameters;
    
    /**导出故障信息时，对应的jasper文件需要的excel格式常量参数*/
    private final Map<Object,Object> exportParameters=(Map<Object,Object>)JasperUtil.getMap();
    
    /**Action对应的Service类*/
    private IExceptService exceptService;
    
    /**由页面查询条件封装的DTO*/
    private QueryExceptBean queryExceptBean;
    
    /**页面查询结果分页控件bean*/
    private PageParams pageParams;
    
    /**页面左侧显示的菜单在整个菜单栏中对应的下标值,用以回显该页面底色*/
    private String menuId;
    
    /**系统登陆用户*/
    private People xpeople;
    
    /**导出的excel名称，因为需要保证导出的excel包含中文，由其代表转换后的名字*/
    private String excelName;
    
    /**
     * 初始化方法，点击左侧菜单时触发，进入该功能的入口
     * <p>执行必要属性的初始化
     * @return
     * @author zjj 2016-2-4
     * @since 1.0
     */
    public String init(){
        if(!isLogin()){
            return "login";
        }
        logger.info("用户[" + xpeople.getUsername() + "]登陆[故障处理结果查询]界面，页面初始化");
        exceptList = new ArrayList<TerminalExcept>();
        exportList = new ArrayList<TerminalExceptExportBean>();
        queryExceptBean = new QueryExceptBean();
        pageParams = new PageParams();
        menuId = "6";
        return "init";
    }
    
    /**
     * 检测用户是否已经登陆
     * @return boolean 
     * <p>true:已登陆;false:未登陆
     * @author zjj 2016-2-1
     * @since 1.0
     */
    public boolean isLogin(){
        boolean flag = false;
        xpeople = (People) WebUtil.getLoginPeople();
        if(xpeople != null){
            flag = true;
        }
        return flag;
    } 
    
    /**
     * 根据查询条件查询出所有符合要求的故障信息
     * @return
     * @author zjj 2016-2-8
     * @since 1.0
     */
    public String listAllExcept(){
        
        if(!isLogin()){
            return "login";
        }
        logger.info("开始按条件查询故障列表：");
        
        Map<List<TerminalExcept>,PageParams> map = new HashMap<List<TerminalExcept>,PageParams>();  
        map = (HashMap<List<TerminalExcept>, PageParams>) exceptService.getExceptListByPage(queryExceptBean,pageParams);
        
        for(Entry<List<TerminalExcept>, PageParams> entry : map.entrySet()){
            exceptList = (List<TerminalExcept>) entry.getKey();
            pageParams = (PageParams) entry.getValue();
        }
        
        return "init";
    }

    /**
     * 导出已查询出来的故障信息excel
     * @return
     * @throws UnsupportedEncodingException
     * @author zjj 2016-2-21
     * @since 1.0
     */
    public String exportExcel() throws UnsupportedEncodingException{

        if(!isLogin()){
            return "login";
        }
        
        logger.info("开始导出excel");
        List<TerminalExcept> list = exceptService.getExceptList(queryExceptBean);
        try{
            exportList = exceptService.getExceptListForExport(list);
        } catch(Exception e){
            e.printStackTrace();
        }
        
        reportParameters = new HashMap<String,String>();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        reportParameters.put("TIME", date);
        
        String date2 = new SimpleDateFormat("yyyyMMdd").format(new Date());
        excelName = "故障信息列表" + date2;
        excelName = new String(excelName.getBytes("gb2312"),"iso8859-1");
        
        logger.info("导出excel成功，共" + exportList.size() + "条");
        return "export";
    }
    
    public IExceptService getExceptService()
    {
        return exceptService;
    }

    public void setExceptService(IExceptService exceptService)
    {
        this.exceptService = exceptService;
    }

    public PageParams getPageParams()
    {
        return pageParams;
    }

    public void setPageParams(PageParams pageParams)
    {
        this.pageParams = pageParams;
    }

    public List<TerminalExcept> getExceptList()
    {
        return this.exceptList;
    }
    public void setExceptList(List<TerminalExcept> exceptList)
    {
        this.exceptList = exceptList;
    }

    public String getMenuId()
    {
        return menuId;
    }

    public void setMenuId(String menuId)
    {
        this.menuId = menuId;
    }

    public QueryExceptBean getQueryExceptBean()
    {
        return queryExceptBean;
    }

    public void setQueryExceptBean(QueryExceptBean queryExceptBean)
    {
        this.queryExceptBean = queryExceptBean;
    }

    public Map<String, String> getReportParameters()
    {
        return reportParameters;
    }

    public void setReportParameters(Map<String, String> reportParameters)
    {
        this.reportParameters = reportParameters;
    }

    public Map<Object, Object> getExportParameters()
    {
        return exportParameters;
    }

    public List<TerminalExceptExportBean> getExportList()
    {
        return exportList;
    }

    public void setExportList(List<TerminalExceptExportBean> exportList)
    {
        this.exportList = exportList;
    }

    public String getExcelName()
    {
        return excelName;
    }

    public void setExcelName(String excelName)
    {
        this.excelName = excelName;
    }


}
