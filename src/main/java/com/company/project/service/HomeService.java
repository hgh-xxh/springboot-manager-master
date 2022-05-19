package com.company.project.service;

import com.company.project.vo.resp.HomeRespVO;

/**
 * 首页
 */
public interface HomeService {

    /**
     * 获取首页信息
     *
     * @param userId userId
     * @return HomeRespVO
     */
    HomeRespVO getHomeInfo(String userId);
}
