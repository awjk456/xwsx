package org.xwsx.service;

import org.xwsx.dto.AdDto;

import java.util.List;


public interface AdService {
    boolean add(AdDto adDto);
    List<AdDto> searchByPage(AdDto adDto);
    boolean deleteOne(int id);
    AdDto findById(int id);
    boolean update(AdDto adDto);

}
