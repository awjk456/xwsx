package org.xwsx.controller.api;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xwsx.dto.*;
import org.xwsx.service.AdService;
import org.xwsx.service.BusinessService;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private AdService adService;

	@Autowired
	private BusinessService businessService;

	@Value("${ad.number}")
	private int adNumber;

	@Value("${businessHome.number}")
	private int businessHomeNumber;

	/**
	 * 首页 —— 广告（超值特惠）
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "/homead", method = RequestMethod.GET)
	public List<AdDto> homead() throws JsonParseException, JsonMappingException, IOException {
		AdDto adDto = new AdDto();
		adDto.getPage().setPageNumber(adNumber);
		//System.out.println("=================>"+adDto.getPage().getPageNumber());
		return adService.searchByPage(adDto);
	}


	/**
	 * 测试短信发送功能
	 */
	@RequestMapping(value = "/sms",method = RequestMethod.POST)
	public void sendMsg(String username){
		System.out.println("========进入短信接口"+username);
	}


	/**
	 * 首页 —— 推荐列表（猜你喜欢）
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "/homelist/{city}/{page.currentPage}", method = RequestMethod.GET)
	public BusinessListDto homelist(BusinessDto businessDto) throws JsonParseException, JsonMappingException, IOException {
		businessDto.getPage().setPageNumber(businessHomeNumber);
		return businessService.selectLikeByPage(businessDto);
	}

	/**
	 * 搜索结果页 - 搜索结果 - 三个参数
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "/search/{page.currentPage}/{city}/{category}/{keyword}", method = RequestMethod.GET)
	public BusinessListDto searchByKeyword(BusinessDto businessDto) throws JsonParseException, JsonMappingException, IOException {
		businessDto.getPage().setPageNumber(businessHomeNumber);
		return businessService.selectLikeByPage(businessDto);
	}

	/**
	 * 搜索结果页 - 搜索结果 - 两个参数
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "/search/{page.currentPage}/{city}/{category}", method = RequestMethod.GET)
	public BusinessListDto search(BusinessDto businessDto) throws JsonParseException, JsonMappingException, IOException {
		businessDto.getPage().setPageNumber(businessHomeNumber);
		return businessService.selectLikeByPage(businessDto);
	}

	/**
	 * 详情页 - 商户信息
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "/detail/info/{id}", method = RequestMethod.GET)
	public BusinessDto detail(@PathVariable("id") Long id) throws JsonParseException, JsonMappingException, IOException {
		long aa = id;
		int  IntId = (int)((long)id);
		return businessService.findById(IntId);
	}

	/**
	 * 详情页 - 用户评论
	 */
	@RequestMapping(value = "/detail/comment/{page}/{id}", method = RequestMethod.GET)
	public CommentListDto detail() throws JsonParseException, JsonMappingException, IOException {
		String s = "{\"hasMore\":true,\"data\":[{\"username\":\"133****3355\",\"comment\":\"非常好吃，棒棒的，下次再来\",\"star\":5},{\"username\":\"135****3452\",\"comment\":\"羊肉够分量，不错\",\"star\":4},{\"username\":\"137****1242\",\"comment\":\"有自助的水果，非常喜欢\",\"star\":4},{\"username\":\"131****3980\",\"comment\":\"桌子环境有点糟糕，不喜欢\",\"star\":2},{\"username\":\"135****3565\",\"comment\":\"基本还可以，中规中矩吧，虽然没有啥惊喜\",\"star\":3},{\"username\":\"130****9879\",\"comment\":\"感觉很棒，服务员态度非常好\",\"star\":5},{\"username\":\"186****7570\",\"comment\":\"要是能多给开点发票就好了，哈哈啊\",\"star\":4}]}";
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(s, new TypeReference<CommentListDto>() {});
	}

	/**
	 * 订单列表
	 */
	@RequestMapping(value = "/orderlist/{username}", method = RequestMethod.GET)
	public List<OrdersDto> orderlist() throws JsonParseException, JsonMappingException, IOException {
		String s = "[{\"id\":1494060890936,\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201638030-473660627.png\",\"title\":\"汉堡大王\",\"count\":3,\"price\":\"167\",\"commentState\":0},{\"id\":1494060890936,\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201708124-1116595594.png\",\"title\":\"麻辣香锅\",\"count\":1,\"price\":\"188\",\"commentState\":0},{\"id\":1494060890936,\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201645858-1342445625.png\",\"title\":\"好吃自出餐\",\"count\":2,\"price\":\"110\",\"commentState\":2}]";
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(s, new TypeReference<List<OrdersDto>>() {});
	}

	/**
	 * 提交评论
	 */
	@RequestMapping(value = "/submitComment", method = RequestMethod.POST)
	public Map<String, Object> submitComment() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("errno", 0);
		result.put("msg", "ok");
		return result;
	}
}
