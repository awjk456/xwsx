package org.xwsx.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.xwsx.constant.DicTypeConst;
import org.xwsx.constant.PageCodeEnum;
import org.xwsx.dto.BusinessDto;
import org.xwsx.service.BusinessService;
import org.xwsx.service.DicService;

import java.util.List;

@Controller
@RequestMapping("/businesses")
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @Autowired
    DicService dicService;


    /**
     * 商户新增页初始化
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addInit(Model model){

        model.addAttribute("cityList",dicService.getListByType(DicTypeConst.CITY));
        model.addAttribute("categoryList",dicService.getListByType(DicTypeConst.CATEGORY));

        return "/content/businessAdd";
    }

    /**
     * 新增商户
     * 传入的参数RedirectAttributes是重定向传入的参数
     */
    @RequestMapping(method = RequestMethod.POST)
    public String add(BusinessDto businessDto, RedirectAttributes attr){
        System.out.println("==========>添加方法"+businessDto);

        if(businessService.add(businessDto)){
            attr.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_SUCCESS);
            return "redirect:/businesses" ;
        }else{
            attr.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_FAIL);
            return "redirect:/businesses/addPage" ;
        }

    }

    /**
     * 初始进入
     */
    @RequestMapping
    public String initList(){
        return "/comment/bussinessList";
    }

    /**
     * 修改商户初始化
     */
    @RequestMapping(value = "/{id}",method=RequestMethod.GET)
    public String updateInit(@PathVariable("id")int id,Model model){
        System.out.println("id=======>"+id);
        BusinessDto businessDto = businessService.findById(id);
        System.out.println("<=======businessDto============>"+businessDto);
        model.addAttribute("modifyObj",businessDto);
        model.addAttribute("cityList",dicService.getListByType(DicTypeConst.CITY));
        model.addAttribute("categoryList",dicService.getListByType(DicTypeConst.CATEGORY));
        model.addAttribute("video","http://127.0.0.1:8881/upload/business/oceans.mp4");
        return "/content/businessModify";
    }


    /**
     * 商户列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public String seach(BusinessDto businessDto,Model model){
        System.out.println("===========123123");
        List<BusinessDto> businessDtoList = businessService.findByPage(businessDto);
        System.out.println("=========进入查询方法=====+>"+businessDtoList);
        model.addAttribute("list",businessDtoList);
        model.addAttribute("searchParam",businessDto);
        return "/content/businessList";
    }

    /**
     * 删除商户
     */
    @RequestMapping(value = "/{id}",method=RequestMethod.DELETE)

    public String remove(@PathVariable("id")int id,Model model){

        if(businessService.deleteById(id)){
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.DELETE_SUCCESS);
        }else{
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.DELETE_FAIL);
        }
        System.out.println("id2312312312"+id);
        return "redirect:/businesses";
    }
}
