package org.xwsx.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xwsx.bean.Ad;
import org.xwsx.dao.AdDao;
import org.xwsx.dto.AdDto;
import org.xwsx.service.AdService;
import org.xwsx.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdSerivceImpl implements AdService {

    @Autowired
    private AdDao adDao;

    @Value("${adImage.savePath}")
    private String adImageSavePath;

    @Value("${adImage.url}")
    private String adImageUrl;

    @Override
    //TODO可以改成获取详细成功或失败的原因
    public boolean add(AdDto adDto) {
        Ad ad = new Ad();
        ad.setTitle(adDto.getTitle());
        ad.setLink(adDto.getLink());
        ad.setWeight(adDto.getWeight());
        String fileName=null;
        if (adDto.getImgFile() != null && adDto.getImgFile().getSize() > 0) {

            try {
                fileName= FileUtil.save(adDto.getImgFile(),adImageSavePath);
                if(fileName == null){
                    return false;
                }
                ad.setImgFileName(fileName);
                adDao.insert(ad);
                return true;
            } catch (IOException e) {
                //TODO加日志
                return false;
            }
        } else {
            return false;
        }

    }



    @Override
    public List<AdDto> searchByPage(AdDto adDto) {
        List<AdDto> result = new ArrayList<AdDto>();
        Ad condition = new Ad();
        BeanUtils.copyProperties(adDto, condition);
        List<Ad> adList = adDao.selectByPage(condition);
        for(Ad ad:adList){
            AdDto adDtpTemp = new AdDto();
            BeanUtils.copyProperties(ad,adDtpTemp);
            adDtpTemp.setImg(adImageUrl+ad.getImgFileName());
            result.add(adDtpTemp);
        }
        return result;

    }

    @Override
    public boolean deleteOne(int id) {
        Ad ad = adDao.findById(id);
        int i = adDao.deleteOne(id);

        if(i>0){
            return FileUtil.delete(adImageSavePath+ad.getImgFileName());

        }
        return false;
    }

    @Override
    public AdDto findById(int id) {
        Ad ad = adDao.findById(id);
        AdDto adDto = new AdDto();
        BeanUtils.copyProperties(ad,adDto);
        adDto.setImg(adImageUrl+ad.getImgFileName());

        return adDto;
    }

    @Override
    public boolean update(AdDto adDto) {
        Ad ad = new Ad();
        BeanUtils.copyProperties(adDto,ad);
        String fileName =null;
        try {
            fileName = FileUtil.save(adDto.getImgFile(),adImageSavePath);
            ad.setImgFileName(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("fileName"+fileName);
        int updateFlag = adDao.update(ad);
        if(updateFlag!=1){
            return false;
        }

        if(fileName != null){
            return FileUtil.delete(adImageSavePath+adDto.getImgFileName());
        }
        return true;
    }

}
