package org.xwsx.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xwsx.bean.Business;
import org.xwsx.bean.Page;
import org.xwsx.constant.CategoryConst;
import org.xwsx.dao.BusinessDao;
import org.xwsx.dto.BusinessDto;
import org.xwsx.dto.BusinessListDto;
import org.xwsx.service.BusinessService;
import org.xwsx.util.CommonsUtil;
import org.xwsx.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    BusinessDao businessDao;

    @Value("${bsImage.savePath}")
    private String savePath;

    @Value("${bsImage.url}")
    private  String bsUrl;


    @Override
    public boolean add(BusinessDto businessDto) {

        Business business = new Business();
        BeanUtils.copyProperties(businessDto,business);
        String fileName = null;
        if(businessDto.getImgFile() !=null && businessDto.getImgFile().getSize() >0){
            try {
                fileName = FileUtil.save(businessDto.getImgFile(),savePath);
                if(fileName ==null){
                    return false;
                }
                business.setNumber(0);
                business.setStarTotalNum(0);
                business.setCommentTotalNum(0);
                business.setImgFileName(fileName);
                businessDao.insert(business);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    @Override
    public List<BusinessDto> findByPage(BusinessDto businessDto) {
        List<BusinessDto> result = new ArrayList<BusinessDto>();
        Business condition = new Business();
        BeanUtils.copyProperties(businessDto,condition);
        List<Business> businessList = businessDao.findByPage(condition);
        for(Business business:businessList){
            BusinessDto businessDto1 = new BusinessDto();
            BeanUtils.copyProperties(business,businessDto1);
            businessDto1.setImg(bsUrl+business.getImgFileName());
            result.add(businessDto1);
        }
        return result;
    }

    @Override
    public BusinessDto findById(int id) {
        Business business = businessDao.findById(id);
        BusinessDto businessDto = new BusinessDto();
        businessDto.setImg(bsUrl+business.getImgFileName());
        BeanUtils.copyProperties(business,businessDto);
        return businessDto;
    }

    @Override
    public boolean deleteById(int id) {
        Business business = businessDao.findById(id);
        if(business !=null){
            int flag = businessDao.deleteById(id);
            if(flag>0){
                return FileUtil.delete(savePath+business.getImgFileName());
            }
        }
        return false;
    }

    @Override
    public BusinessListDto selectLikeByPage(BusinessDto businessDto) {
        BusinessListDto result = new BusinessListDto();
        Business business = new Business();
        BeanUtils.copyProperties(businessDto,business);
        System.out.println("==========林森12312========="+businessDto.getKeyword());
        if(!CommonsUtil.isEmpty(businessDto.getKeyword())){
            business.setSubtitle(businessDto.getKeyword());
            business.setTitle(businessDto.getKeyword());
            business.setDesc(businessDto.getDesc());
        }
        // 当类别为全部(all)时，需要将类别清空，不作为过滤条件
        if (businessDto.getCategory() != null && CategoryConst.ALL.equals(businessDto.getCategory())) {
            business.setCategory(null);
        }
        // 前端app页码从0开始计算，这里需要+1
        int currentPage = business.getPage().getCurrentPage();
        business.getPage().setCurrentPage(currentPage + 1);
        List<Business> list = businessDao.selectLikeByPage(business);
        Page page = business.getPage();
        result.setHasMore(page.getCurrentPage()<page.getTotalPage());
        for(Business bs :list){
            BusinessDto bsto = new BusinessDto();
            BeanUtils.copyProperties(bs,bsto);
            bsto.setImg(bsUrl+bs.getImgFileName());
            bsto.setMumber(bs.getNumber());
            bsto.setStar(1);
            result.getData().add(bsto);
        }
        System.out.println("================林森===========>"+result.getData().size());
        return result;
    }

}
