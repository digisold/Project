package com.digisold.web.manage.base;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/base_data")
public class CommonDataController extends BaseController {

	private static Logger logger = Logger.getLogger(CommonDataController.class);
}
