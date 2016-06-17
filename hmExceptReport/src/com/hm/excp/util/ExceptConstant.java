/*
 * 文件名：ExceptConstant.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：系统相关的常量类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统常量定义类
 * @author zjj
 * @since 2016.01.30
 */
public class ExceptConstant {
    
    /**
     * 用户常量
     * @author zjj
     * @since 2016.01.30
     */
    public class XPEOPLE_INFO {
        public static final String XPEOPLE = "XPEOPLE";
    }
    
    /**
     * 用户状态
     * @author zjj
     * @since 2016.01.30
     */
    public class XPEOPLE_STATUS {
        public static final String NORMAL = "1";
        public static final String LOCK = "2";
    }
	
//	/**
//	 * 用户状态码和状态描述对应关系
//	 */
//	public static Map<String,String> status_map = new HashMap<String,String>();
//	static {
//	    status_map.put("1", "正常");
//	    status_map.put("2", "锁定");
//	}
//	
//	/**
//	 * 角色多和角色描述对应关系
//	 * return_desc = ConstantDef.role_map.get(roleId)
//	 */
//	public static Map<String,String> role_map = new HashMap<String,String>();
//	static {
//	    role_map.put("1", "维护员");
//	    role_map.put("2", "管机员");
//	    role_map.put("3", "部门领导");
//	    role_map.put("4", "主管");
//	    role_map.put("5", "部门经理");
//	    role_map.put("6", "银行主管");
//	    role_map.put("7", "超级管理员");
//	}
	/**
	 * 返回码和返回描述对应的键值对，即由返回码获取对应的返回描述
	 * return_desc = ConstantDef.re_code_desc_map.get(return_code)
	 */
	public static Map<String,String> re_code_desc_map = new HashMap<String,String>();
	static {
	    re_code_desc_map.put(RETURN_CODE.RETURN_CODE_1000,RETURN_DESC.RETURN_DESC_1000);
	    re_code_desc_map.put(RETURN_CODE.RETURN_CODE_1001,RETURN_DESC.RETURN_DESC_1001);
	    re_code_desc_map.put(RETURN_CODE.RETURN_CODE_1002,RETURN_DESC.RETURN_DESC_1002);
	    re_code_desc_map.put(RETURN_CODE.RETURN_CODE_1003,RETURN_DESC.RETURN_DESC_1003);
	    re_code_desc_map.put(RETURN_CODE.RETURN_CODE_1004,RETURN_DESC.RETURN_DESC_1004);
	    re_code_desc_map.put(RETURN_CODE.RETURN_CODE_1005,RETURN_DESC.RETURN_DESC_1005);
	}
	
	/**
	 * 在服务端获取请求报文后的返回结果,指获取请求报文是否成功
	 * @author zjj
	 */
	public class RETURN_RST{
		/***/
		public static final String RETURN_RST_SUCCESS = "S";
		/***/
		public static final String RETURN_RST_FAILURE = "F";
	}
	
	/**
	 * 在服务端获取请求报文后返回的处理结果代码，与之对应的是处理结果描述
	 * @author zjj
	 */
	public static class RETURN_CODE{
		/**1000*/
		public static final String RETURN_CODE_1000 = "1000";
		/**1001*/
		public static final String RETURN_CODE_1001 = "1001";
		/**1002*/
		public static final String RETURN_CODE_1002 = "1002";
		/**1003*/
		public static final String RETURN_CODE_1003 = "1003";
		/**1004*/
		public static final String RETURN_CODE_1004 = "1004";
		/**1005*/
		public static final String RETURN_CODE_1005 = "1005";
	}
	
	/**
	 * 在服务端获取请求报文后返回的处理结果描述,与之对应的是处理结果代码
	 * @author zjj
	 */
	public static class RETURN_DESC{
		/**接收成功*/
		public static final String RETURN_DESC_1000 = "接收成功";
		/**输入信息无效*/
		public static final String RETURN_DESC_1001 = "输入信息无效";
		/**内部故障*/
		public static final String RETURN_DESC_1002 = "内部故障";
		/**XML处理故障*/
		public static final String RETURN_DESC_1003 = "XML处理故障";
		/**设备不存在，未登记*/
		public static final String RETURN_DESC_1004 = "设备不存在，未登记";
		/**文件不存在*/
		public static final String RETURN_DESC_1005 = "文件不存在";
	}
	
//	public enum Return_code_desc_1 {
//		RETURN_1000(RETURN_CODE.RETURN_CODE_1000,RETURN_DESC.RETURN_DESC_1000),
//		RETURN_1001(RETURN_CODE.RETURN_CODE_1001,RETURN_DESC.RETURN_DESC_1001),
//		RETURN_1002(RETURN_CODE.RETURN_CODE_1002,RETURN_DESC.RETURN_DESC_1002),
//		RETURN_1003(RETURN_CODE.RETURN_CODE_1003,RETURN_DESC.RETURN_DESC_1003),
//		RETURN_1004(RETURN_CODE.RETURN_CODE_1004,RETURN_DESC.RETURN_DESC_1004),
//		RETURN_1005(RETURN_CODE.RETURN_CODE_1005,RETURN_DESC.RETURN_DESC_1005);
//		
//		public String modelKey;
//		public String modelName;
//		
//		Return_code_desc_1(String modelKey,String modelName){
//			this.modelKey = modelKey;
//			this.modelName=modelName;
//		}
//	}
	
	/**
	 * 请求报文和响应报文中所有的xml节点定义
	 * @author zjj
	 */
	public class REPORT_ELEMENT{
		/***/
		public static final String MNT_ROOT = "MNT_ROOT";
		/***/
		public static final String MNT_HEADER = "MNT_HEADER";
		/***/
		public static final String MSGID = "MSGID";
		/***/
		public static final String CLIENTID = "CLIENTID";
		/***/
		public static final String TYPE = "TYPE";
		/***/
		public static final String TERMINALID = "TERMINALID";
		/***/
		public static final String HANDLE_DATE = "HANDLE_DATE";
		/***/
		public static final String HANDLE_TIME = "HANDLE_TIME";
		/***/
		public static final String STATUS = "STATUS";
		/***/
		public static final String MKZT = "MKZT";
		/***/
		public static final String MNT_MSGIN = "MNT_MSGIN";
		/***/
		public static final String MNT_MSGOUT = "MNT_MSGOUT";
		/***/
		public static final String RETURN_RST = "RETURN_RST";
		/***/
		public static final String RETURN_CODE = "RETURN_CODE";
		/***/
		public static final String RETURN_DESC = "RETURN_DESC";
	}
	
	/**
	 * 设备类型
	 * @author zjj
	 *
	 */
	public class CLIENTID{
		/**自主发卡机*/
		public static final String ACI = "ACI";
		/**自助终端设备*/
		public static final String ATM = "ATM";
		/**虚拟柜员机*/
		public static final String VTM = "VTM";
		
	}
	
	/**
	 * 交易类型
	 * @author zjj
	 *
	 */
	public class TRADE_TYPE{
		/**请求报文1000*/
		public static final String REQUST_CODE = "1000";
		/**响应报文1001*/
		public static final String RESPONSE_CODE = "1001";
	}
	
	/**
	 * 设备模块
	 * @author zjj
	 *
	 */
	public class TERMINAL_MODULE{
		/**PTR凭条打印机*/
		public static final String PTR = "PTR";
		/**存折/激光打印机*/
		public static final String PRP = "PRP";
		/**身份证读取*/
		public static final String IDR = "IDR";
		/**读卡器*/
		public static final String IDC = "IDC";
		/**取款模块*/
		public static final String CDM = "CDM";
		/**密码键盘*/
		public static final String PIN  = "PIN";
		/**支票读取*/
		public static final String CHK = "CHK";
		/**传感器和指示器*/
		public static final String SIU = "SIU";
		/**摄像头*/
		public static final String CAM = "CAM";
		/**报警设备*/
		public static final String ALM = "ALM";
		/**存款模块*/
		public static final String CIM = "CIM";
		/**发卡器*/
		public static final String CRD = "CRD";
		/**条码*/
		public static final String BCR = "BCR";
		/**回收模块*/
		public static final String REC = "REC";
		/**指纹仪*/
		public static final String FPI = "FPI";
	}
	
	/**
	 * PTR凭条打印机状态信息定义
	 * @author zjj
	 *
	 */
	public class PTR_STATUS{
		/**正常*/
		public static final String S_0 = "0";
		/**纸少*/
		public static final String S_1 = "1";
		/**纸尽*/
		public static final String S_2 = "2";
		/**故障*/
		public static final String S_3 = "3";
		/**未安装*/
		public static final String S_4 = "4";
		/**保留*/
		public static final String S_5 = "5";
		/**保留*/
		public static final String S_6 = "6";
		/**状态异常*/
		public static final String S_7 = "7";
		/**保留*/
		public static final String S_8 = "8";
	}
	
	/**
	 * PRP存折/激光打印机状态信息定义
	 * @author zjj
	 *
	 */
	public class PRP_STATUS{
		/**正常*/
		public static final String S_0 = "0";
		/**纸少*/
		public static final String S_1 = "1";
		/**纸尽*/
		public static final String S_2 = "2";
		/**故障*/
		public static final String S_3 = "3";
		/**未安装*/
		public static final String S_4 = "4";
		/**保留*/
		public static final String S_5 = "5";
		/**保留*/
		public static final String S_6 = "6";
		/**状态异常*/
		public static final String S_7 = "7";
		/**保留*/
		public static final String S_8 = "8";
		
	}
	
	/**
	 * IDR二代身份证扫描仪状态信息定义
	 * @author zjj
	 *
	 */
	public class IDR_STATUS{
		/**正常*/
		public static final String S_0 = "0";
		/**厂商定义状态*/
		public static final String S_1 = "1";
		/**厂商定义状态*/
		public static final String S_2 = "2";
		/**故障*/
		public static final String S_3 = "3";
		/**未安装*/
		public static final String S_4 = "4";
		/**厂商定义状态或保留*/
		public static final String S_5 = "5";
		/**厂商定义状态或保留*/
		public static final String S_6 = "6";
		/**状态异常*/
		public static final String S_7 = "7";
		/**保留*/
		public static final String S_8 = "8";
		
	}
	
	/**
	 * FPI 指纹仪状态信息定义
	 * @author zjj
	 *
	 */
	public class FPI_STATUS{
		/**正常*/
		public static final String S_0 = "0";
		/**厂商定义状态*/
		public static final String S_1 = "1";
		/**厂商定义状态*/
		public static final String S_2 = "2";
		/**故障*/
		public static final String S_3 = "3";
		/**未安装*/
		public static final String S_4 = "4";
		/**厂商定义状态或保留*/
		public static final String S_5 = "5";
		/**厂商定义状态或保留*/
		public static final String S_6 = "6";
		/**状态异常*/
		public static final String S_7 = "7";
		/**保留*/
		public static final String S_8 = "8";
		
	}
	
	/**
	 * CAM摄像头状态信息定义
	 * @author zjj
	 *
	 */
	public class CAM_STATUS{
		/**正常*/
		public static final String S_0 = "0";
		/**厂商定义状态*/
		public static final String S_1 = "1";
		/**厂商定义状态*/
		public static final String S_2 = "2";
		/**故障*/
		public static final String S_3 = "3";
		/**未安装*/
		public static final String S_4 = "4";
		/**厂商定义状态或保留*/
		public static final String S_5 = "5";
		/**厂商定义状态或保留*/
		public static final String S_6 = "6";
		/**状态异常*/
		public static final String S_7 = "7";
		/**保留*/
		public static final String S_8 = "8";
		
	}
	
	/**
	 * CRD发卡器状态信息定义
	 * @author zjj
	 *
	 */
	public class CRD_STATUS{
		/**正常（无卡）*/
		public static final String S_0 = "0";
		/**厂商定义状态（有卡）*/
		public static final String S_1 = "1";
		/**厂商定义状态（卡量超限）*/
		public static final String S_2 = "2";
		/**故障*/
		public static final String S_3 = "3";
		/**未安装*/
		public static final String S_4 = "4";
		/**厂商定义状态或保留*/
		public static final String S_5 = "5";
		/**厂商定义状态或保留*/
		public static final String S_6 = "6";
		/**状态异常*/
		public static final String S_7 = "7";
		/**保留*/
		public static final String S_8 = "8";
		
	}
	
	/**默认密码:123456
	 */
	public class DEFAULT_PWD{
	    /**123456*/
	    public static final String DEFAULT_PWD = "123456";
	}
	
	/**
	 * 故障处理状态
	 * @author zjj 2016-2-14
	 * @see
	 * @since 1.0
	 */
	public class EXCEPT_STATE{
	    /**已分发*/
	    public static final String DISPATCH = "1";
	    /**完成处理*/
	    public static final String DONE = "2";
	}
	
	
	/**
	 * 故障升级时间间隔
	 * @author zjj 2016-2-14
	 * @see
	 * @since 1.0
	 */
	public class TIME_SECTION{
	    /**8小时为28800000毫秒*/
	    public static final long T_8 = 28800000;
	    /**24小时为86400000毫秒*/
	    public static final long T_24 = 86400000;
	    /**32小时为115200000毫秒*/
	    public static final long T_32 = 115200000;
	}
	
	/**
     * 故障等级
     * @author zjj
     *
     */
    public static class EXCEPT_LEVEL{
        /***/
        public static final String LEVEL_1 = "1";
        /***/
        public static final String LEVEL_2 = "2";
        /***/
        public static final String LEVEL_3 = "3";
    }
    
    /**
     * 角色种类
     * @author zjj
     *
     */
    public static class ROLE_ID{
        /**维护员*/
        public static final String Role_1 = "1";
        /**管机员*/
        public static final String Role_2 = "2";
        /**部门领导*/
        public static final String Role_3 = "3";
        /**主管*/
        public static final String Role_4 = "4";
        /**部门经理*/
        public static final String Role_5 = "5";
        /**银行主管*/
        public static final String Role_6 = "6";
        /**超级管理员*/
        public static final String Role_7 = "7";
    }

}
