package com.company.project.controller.trace;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.code.QRCodeEncoder;
import com.company.project.common.config.FileUploadProperties;
import com.company.project.common.exception.BusinessException;
import com.company.project.common.utils.DateUtils;
import com.company.project.entity.*;
import com.company.project.mapper.SysOrderMapper;
import com.company.project.mapper.SysTraceMapper;
import com.company.project.service.impl.SysFilesServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.company.project.common.utils.DataResult;

import com.company.project.service.SysTraceService;

import javax.annotation.Resource;


/**
 * 
 *
 * @author huguanghong
 * @email 994138979@mail.com
 * @date 2022-04-13 18:59:29
 */
@Controller
@RequestMapping("/")
public class SysTraceController {
    @Autowired
    private SysTraceService sysTraceService;
    @Autowired
    private SysTraceMapper sysTraceMapper;

    @Resource
    private FileUploadProperties fileUploadProperties;
    @Autowired
    private SysFilesServiceImpl sysFilesService;
    @Autowired
    private SysOrderMapper sysOrderMapper;
    /**
    * 跳转到页面
    */
    @GetMapping("/index/sysTrace")
    public String sysTrace() {
        return "systrace/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("sysTrace/add")
    @RequiresPermissions("sysTrace:add")
    @ResponseBody
    public DataResult add(@RequestBody SysTraceEntity sysTrace){
        sysTrace.setTraceCreatetime(new Date());
        //存储文件夹
        String createTime = DateUtils.format(new Date(), DateUtils.DATEPATTERN);
        String newPath = fileUploadProperties.getPath() + createTime +"/";
        File uploadDirectory = new File(newPath);
        if (uploadDirectory.exists()) {
            if (!uploadDirectory.isDirectory()) {
                uploadDirectory.delete();
            }
        } else {
            uploadDirectory.mkdir();
        }
        try {
            String fileName = UUID.randomUUID().toString().replace("-", "")+".png";
            //id与filename保持一直，删除文件
            //String fileNameNew = UUID.randomUUID().toString().replace("-", "") + getFileType(fileName);
            String newFilePathName = newPath + fileName;
            System.out.println(newFilePathName+" "+newPath);
            String url = fileUploadProperties.getUrl() + "/" + createTime + "/" + fileName;
            sysTrace.setTraceQrcodeSrc(url);
            String content="http://127.0.0.1:8080/test/b?orderId="+sysTrace.getTraceOrderid();
            QRCodeEncoder encoder = new QRCodeEncoder();
            encoder.encoderQRCode(
                    content, //内容
                    newFilePathName, //文件路径
                    "png",   //文件类型
                    "UTF-8", //编码方式
                    4,      //大小
                    null,   //边框
                    null, //前景色
                    null, //背景色
                    6,    //图标比例
                    null, //图标路径
                    null  //动画图标路径
            );
            //保存文件记录
            SysFilesEntity sysFilesEntity = new SysFilesEntity();
            sysFilesEntity.setFileName(fileName);
            sysFilesEntity.setFilePath(newFilePathName);
            sysFilesEntity.setUrl(url);
            sysFilesService.save(sysFilesEntity);
        } catch (Exception e) {
            throw new BusinessException("上传文件失败");
        }
        sysTraceService.save(sysTrace);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("sysTrace/delete")
    @RequiresPermissions("sysTrace:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        sysTraceService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("sysTrace/update")
    @RequiresPermissions("sysTrace:update")
    @ResponseBody
    public DataResult update(@RequestBody SysTraceEntity sysTrace){
        sysTraceService.updateById(sysTrace);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("sysTrace/listByPage")
    @RequiresPermissions("sysTrace:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody SysTraceEntity sysTrace){
        Page page = new Page(sysTrace.getPage(), sysTrace.getLimit());
        LambdaQueryWrapper<SysTraceEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(SysTraceEntity::getId, sysTrace.getId());
        IPage<SysTraceEntity> iPage = sysTraceService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    @GetMapping("sysTrace/init2")
    @ResponseBody
    public DataResult init2(){

        return DataResult.success(sysOrderMapper.selectList(null));
    }
    @PostMapping("sysTrace/search")
    @ResponseBody
    public DataResult search(@RequestParam String traceOrderid){
        LambdaQueryWrapper<SysTraceEntity> queryWrapper = Wrappers.lambdaQuery();
        BaseEntity baseEntity=new BaseEntity();
        Page page = new Page(baseEntity.getPage(), baseEntity.getLimit());
        if(!traceOrderid.isEmpty()){
            queryWrapper.like(SysTraceEntity::getTraceOrderid, traceOrderid);
        }
        IPage<SysStoreIoEntity> iPage =sysTraceMapper.selectPage(page,queryWrapper);
        return DataResult.success(iPage);
    }
}
