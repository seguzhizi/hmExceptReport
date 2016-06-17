/*
 * 文件名：JasperUtil.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：处理Jasper报表的工具类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
/**
 * 处理Jasper报表的工具类
 * @author zjj 2016-1-19
 * @see
 * @since 1.0
 */
public class JasperUtil {
	
    /**jasper模板需要使用的参数集合*/
	private final static Map map = new HashMap();
	
	/**
	 * 初始化jasper模板需要使用的参数集合
	 * @author zjj 2016-1-19
	 * @see
	 * @since 1.0
	 */
	static {
		map.put(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		map.put(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.TRUE);
		
		map.put(JRXlsExporterParameter.PROPERTY_IGNORE_CELL_BORDER, Boolean.TRUE);
		//对于导出excel来说下面三个很重要
		map.put(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		map.put(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		map.put(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
		
		map.put(JExcelApiExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
		map.put(JRCsvExporterParameter.FIELD_DELIMITER, "");
		map.put(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
	}
	
	/**
     * 返回jasper模板需要使用的参数集合
     * @author zjj 2016-1-19
     * @see
     * @since 1.0
     */
	public static Map getMap(){
		return map;
	}

}
