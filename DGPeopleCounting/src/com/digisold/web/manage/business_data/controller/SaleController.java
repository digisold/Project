package com.digisold.web.manage.business_data.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.digisold.core.util.Constant;
import com.digisold.core.util.ToolUtil;
import com.digisold.web.manage.base.BaseController;
import com.digisold.web.manage.business_data.model.SaleListModel;
import com.digisold.web.manage.business_data.service.SaleDataIfc;
import com.digisold.web.manage.business_data.vo.SaleListVo;
import com.digisold.web.manage.business_data.vo.TotalSaleDataVo;
import com.digisold.web.mybatis.entity.Sales;

@Controller("saleController")
@RequestMapping("/sale")
public class SaleController extends BaseController {

	private static Logger logger = Logger.getLogger(SaleController.class);

	@Autowired
	private SaleDataIfc saleService;

	final static int pageSize = 15;

	@RequestMapping("/index")
	public String salePage() {
		setReqAttribute("curDate", ToolUtil.dateFormat("yyyy-MM-dd", new Date()));
		return "business_data/sale";
	}

	/**
	 * 新增或修改销售数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String saveController(@ModelAttribute Sales sale) {
		JSONObject result = new JSONObject();
		try {
			String id = sale.getId();
			final String date_fmt = "yyyy-MM-dd";
			String saleDateStr = sale.getSaleDateStr();
			if (saleDateStr != null && !"".equals(saleDateStr)) {
				sale.setSaleDate(ToolUtil.dateParse(date_fmt, sale.getSaleDateStr()));
			}
			if (id != null && !"".equals(id)) {
				sale.setUpdater(getUpdater());
				sale.setUpdateDate(new Date());
				saleService.editSale(sale);
			} else {
				sale.setId(ToolUtil.getUUID());
				sale.setCreator(getCreator());
				saleService.addSale(sale);
			}
			result.put(MESSAGE, "保存成功！");
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("保存销售数据出错[SaleController.saveController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 销售数据记录数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list_count", method = RequestMethod.POST)
	@ResponseBody
	public String listCountController(@ModelAttribute SaleListModel listModel) {
		JSONObject result = new JSONObject();
		try {
			long records = saleService.countSaleList(listModel);
			result.put(RECORDS, records);
			result.put(PAGESIZE, pageSize);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取销售数据记录数出错[SaleController.listCountController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 销售数据列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public String listController(@ModelAttribute SaleListModel listModel, int currentPage) {
		JSONObject result = new JSONObject();
		try {
			listModel.setPageSize(pageSize);
			listModel.setPageIndex((currentPage - 1) * pageSize);
			List<SaleListVo> list = saleService.saleList(listModel);
			TotalSaleDataVo totalSale = saleService.totalCountSale(listModel);
			result.put("total", totalSale);
			result.put(DATA, list);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取销售数据列表出错[SaleController.listController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 销售数据明细
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	@ResponseBody
	public String detailController(String id) {
		JSONObject result = new JSONObject();
		try {
			Sales sale = saleService.getSaleById(id);
			result.put(DATA, sale);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取销售数据明细出错[SaleController.detailController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 删除销售数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteController(String id) {
		JSONObject result = new JSONObject();
		try {
			saleService.deleteSale(id);
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("删除销售数据出错[SaleController.deleteController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 导入销售数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/upload_sale_data", method = RequestMethod.POST)
	@ResponseBody
	public String uploadSaleDataController(String storeId, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		InputStream ins = null;
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<?, ?> fileMap = multipartRequest.getFileMap();
			MultipartFile file = (MultipartFile) fileMap.get("file");
			ins = file.getInputStream();
			Workbook wb = null;
			String filename = file.getOriginalFilename().toLowerCase();
			if (filename.endsWith("xls")) {
				wb = new HSSFWorkbook(ins);
			} else if (filename.endsWith("xlsx")) {
				wb = new XSSFWorkbook(ins);
			}
			Sheet sheet = wb.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			int startRow = 2;
			for (int i = startRow; i < rows; i++) {
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(0);
				Number cell_0 = cell.getNumericCellValue();
				Date saleDate = DateUtil.getJavaDate(cell_0.doubleValue());
				cell = row.getCell(1);
				if (cell == null) {
					result.put(MESSAGE, "第" + (i + 1) + "行的交易笔数不能为空！");
					return toJSONString(result);
				}
				Number orders = cell.getNumericCellValue();
				cell = row.getCell(2);
				if (cell == null) {
					result.put(MESSAGE, "第" + (i + 1) + "行的销售数量不能为空！");
					return toJSONString(result);
				}
				Number amount = cell.getNumericCellValue();
				cell = row.getCell(3);
				if (cell == null) {
					result.put(MESSAGE, "第" + (i + 1) + "行的销售金额不能为空！");
					return toJSONString(result);
				}
				Number total = cell.getNumericCellValue();
				if (orders.intValue() < 0 || amount.intValue() < 0 || total.doubleValue() < 0) {
					result.put(MESSAGE, "第" + (i + 1) + "行的数据必须大于0！");
					return toJSONString(result);
				}
				if (orders.intValue() > amount.intValue()) {
					result.put(MESSAGE, "第" + (i + 1) + "行的交易笔数不能大于销售数量！");
					return toJSONString(result);
				}
				Sales sale = new Sales();
				{
					sale.setStoreId(storeId);
					sale.setSaleDate(saleDate);
					sale.setOrders(orders.intValue());
					sale.setAmount(amount.intValue());
					sale.setTotal(total.doubleValue());
				}
				if (saleDate.getTime() > new Date().getTime()) {
					result.put(MESSAGE, "第" + (i + 1) + "行的销售日期大于当前的时间！");
					return toJSONString(result);
				}
				Sales detailSale = saleService.selectSaleByDate(sale);
				if (detailSale != null) {
					sale.setUpdater(getUpdater());
					sale.setUpdateDate(new Date());
					sale.setId(detailSale.getId());
					saleService.updateSaleByDate(sale);
				} else {
					sale.setId(ToolUtil.getUUID());
					sale.setCreator(getCreator());
					saleService.addSale(sale);
				}
			}

			result.put(MESSAGE, "数据导入成功！");
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("销售数据导入出错[SaleController.uploadSaleDataController()].", ex);
		} finally {
			if (ins != null) {
				try {
					ins.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return toJSONString(result);
	}

}
