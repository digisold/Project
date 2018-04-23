package com.digisold.web.manage.system.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
import com.digisold.web.manage.system.service.BrandIfc;
import com.digisold.web.mybatis.entity.Brand;

@Controller("brandController")
@RequestMapping("/brand")
public class BrandController extends BaseController {

	private static Logger logger = Logger.getLogger(BrandController.class);

	@Autowired
	private BrandIfc brandService;

	@RequestMapping("/index")
	public String brandPage() {
		return "system/brand";
	}

	/**
	 * 新增或修改品牌
	 * 
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String saveController(@ModelAttribute Brand brand, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		try {
			String id = brand.getId();
			if (brandService.getBrandByName(brand) != null) {
				result.put(MESSAGE, "品牌名称已经存在！");
				return toJSONString(result);
			}
			/******************************************* 上传文件 **********************************/
			String uploadFolder = request.getServletContext()
					.getRealPath(Constant.RESOURCE_FOLDER + "/" + Constant.BRAND_FOLDER);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<?, ?> fileMap = multipartRequest.getFileMap();
			if (fileMap != null && fileMap.size() > 0) {
				MultipartFile file = (MultipartFile) fileMap.get("brandLogo");
				String filename = file.getOriginalFilename(); // 文件名
				String nfilename = ToolUtil.uploadFile(uploadFolder, filename, file.getInputStream());
				brand.setLogo(Constant.BRAND_FOLDER + "/" + nfilename);
				// 删除原logo
				if (id != null && !"".equals(id)) {
					Brand b = brandService.getBrandById(id);
					String logo = b.getLogo();
					if (logo != null && !"".equals(logo)) {
						File oldLogoFile = new File(request.getServletContext().getRealPath(Constant.RESOURCE_FOLDER)
								+ File.separator + logo);
						if (oldLogoFile.exists() && oldLogoFile.isFile()) {
							oldLogoFile.delete();
						}
					}
				}
			}
			/******************************************* END *********************************************/
			if (id != null && !"".equals(id)) {
				brand.setUpdater(getUpdater());
				brand.setUpdateDate(new Date());
				brandService.editBrand(brand);
			} else {
				brand.setId(ToolUtil.getUUID());
				brand.setCreator(getCreator());
				brandService.addBrand(brand);
			}
			result.put(MESSAGE, "保存成功！");
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("保存品牌出错[BrandController.saveController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 品牌列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public String listController() {
		JSONObject result = new JSONObject();
		try {
			List<Brand> list = brandService.brandList();
			result.put(DATA, list);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取品牌列表出错[BrandController.listController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 品牌明细
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	@ResponseBody
	public String detailController(String id) {
		JSONObject result = new JSONObject();
		try {
			Brand brand = brandService.getBrandById(id);
			result.put(DATA, brand);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取品牌明细出错[BrandController.detailController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 删除品牌
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteController(String id, String filename, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		try {
			brandService.deleteBrand(id);
			if (filename != null && !"".equals(filename)) {
				String fpath = request.getServletContext().getRealPath(Constant.RESOURCE_FOLDER) + File.separator
						+ filename;
				File oldLogoFile = new File(fpath);
				if (oldLogoFile.exists() && oldLogoFile.isFile()) {
					oldLogoFile.delete();
				}
			}
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("删除品牌出错[BrandController.deleteController()].", ex);
		}
		return toJSONString(result);
	}
}
