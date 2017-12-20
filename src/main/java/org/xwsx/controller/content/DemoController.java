package org.xwsx.controller.content;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoController {
	
	@RequestMapping("/initList")
	public String initList() {
		return "/content/adlist";
	}
	
	@RequestMapping("/initModify")
	public String initModify() { 
		return "/content/adAdd";
	}
}