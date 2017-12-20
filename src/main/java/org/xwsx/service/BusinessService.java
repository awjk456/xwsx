package org.xwsx.service;

import org.xwsx.dto.BusinessDto;
import org.xwsx.dto.BusinessListDto;

import java.util.List;

public interface BusinessService {
    boolean add(BusinessDto businessDto);
    List<BusinessDto> findByPage(BusinessDto businessDto);
    BusinessDto findById(int id);
    boolean deleteById(int id);
    BusinessListDto selectLikeByPage(BusinessDto businessDto);
}
