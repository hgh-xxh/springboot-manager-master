package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.company.project.entity.SysShopEntity;
import com.company.project.service.SysShopService;
import com.company.project.vo.req.ShopSearchVo;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysShopServiceImplTest extends TestCase {
    @Autowired
    private SysShopService sysShopService;
    @Test
    public void t1(){
        ShopSearchVo vo=new ShopSearchVo();
        vo.setShopName("a商店");
        vo.setShopPeople("张三1");
        vo.setShopPhone("15188588633");
        vo.setShopStatus("1");
        IPage<SysShopEntity> iPage=sysShopService.search(vo);
        System.out.println("aa");
    }
}