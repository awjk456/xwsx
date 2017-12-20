package org.xwsx.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xwsx.constant.PageCodeEnum;
import org.xwsx.dto.AdDto;
import org.xwsx.service.AdService;

import java.util.List;

@Controller
@RequestMapping("/ad")
public class AdController {

    /**
     *
     */
    @Autowired
    private AdService adService;

    @RequestMapping
    public String inti(Model model){
        AdDto adDto = new AdDto();
        System.out.println("1231231"+adDto.getPage().getTotalPage());
        model.addAttribute("list",adService.searchByPage(adDto));
        model.addAttribute("searchParam",adDto);
        System.out.println("<========adDto"+adDto.getPage().getTotalPage());
        System.out.println("<========adDto========>"+adDto.getPage().getCurrentPage());
        return "/content/adlist";
    }

    @RequestMapping("/addInit")
    public String addInit(){

        return "/content/adAdd";
    }

    @RequestMapping("/findById")
    public String findById(@RequestParam("id")int id,Model model){

        AdDto adDto = adService.findById(id);
        model.addAttribute("Ad",adDto);
        return "/content/update";
    }

    @RequestMapping("/search")
    public String search(Model model,AdDto adDto){
        model.addAttribute("list",adService.searchByPage(adDto));
        model.addAttribute("searchParam",adDto);
        return "/content/adlist";
    }

    @RequestMapping("/add")
    public String add(AdDto adDto,Model model){
        if(adService.add(adDto)){
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_SUCCESS);
        }else {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_FAIL);
        }
        return "/content/adAdd";
    }

    @RequestMapping("/update")
    public String update(AdDto adDto,Model model){
        boolean updateFalg = adService.update(adDto);
        if(updateFalg){
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.UPDATE_SUCCESS);
        }else{
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.UPDATEP_FAIL);
        }

        return "forward:/ad";
    }


    /**
     * 参数列表中，参数如果是bean对象的话，spring是可以通过ioc的反射来拿到这个属性，与前端的参数做对比注入，但是属性是不行的
     * 然而spring做的是调试器类似的工作，他会在编译的过程中生成类的调试信息到调试器里面，这样我们就能获取到属性了
     * 这里注解是必须要写注解的，这样编译的问题可以避免
     * @param id
     * @return
     */
    @RequestMapping("/remove")
    public String remove(@RequestParam("id")int id,Model model){
        boolean flag = adService.deleteOne(id);
        if(flag){
            AdDto adDto = new AdDto();
            List<AdDto> list = adService.searchByPage(adDto);
            if(list.size()==0){
                if(adDto.getPage().getCurrentPage()>1){
                    adDto.getPage().setCurrentPage(adDto.getPage().getCurrentPage()-1);
                }
            }
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.DELETE_SUCCESS);
            model.addAttribute("list",adService.searchByPage(adDto));
            model.addAttribute("searchParam",adDto);

        }else{
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.DELETE_FAIL);
        }
        return "forward:/ad";
    }
}
