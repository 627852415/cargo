package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.YebAssetsStatisticsFeign;
import com.lxtx.im.admin.feign.feign.YebFeign;
import com.lxtx.im.admin.feign.request.FeignYebAssetsStatisticsCreateReq;
import com.lxtx.im.admin.feign.request.FeignYebAssetsStatisticsDownReq;
import com.lxtx.im.admin.feign.request.FeignYebAssetsStatisticsPageReq;
import com.lxtx.im.admin.feign.request.FeignYebUserEarningSyncReq;
import com.lxtx.im.admin.service.YebAssetsStatisticsService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.YebAssetsStatisticsCreateReq;
import com.lxtx.im.admin.service.request.YebAssetsStatisticsDownReq;
import com.lxtx.im.admin.service.request.YebAssetsStatisticsPageReq;
import com.lxtx.im.admin.service.request.YebUserEarningSyncReq;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * <p>
 * 余额宝资产统计列表业务类
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-09-12
 **/
@Service
public class YebAssetsStatisticsServiceImpl implements YebAssetsStatisticsService {

    @Autowired
    private YebAssetsStatisticsFeign yebAssetsStatisticsFeign;

    @Autowired
    private YebFeign yebFeign;

    @Override
    public BaseResult doSyncYebUserEearning(YebUserEarningSyncReq req) {
        if(StringUtils.isEmpty(req.getUserId())){
            throw LxtxBizException.newException("userId不能为空");
        }
        FeignYebUserEarningSyncReq feignReq = new FeignYebUserEarningSyncReq();
        BeanUtils.copyProperties(req, feignReq);
        BaseResult baseResult = yebFeign.doSyncYebUserEearning(feignReq);
        return baseResult;
    }


    @Override
    public BaseResult page(YebAssetsStatisticsPageReq req) {
        FeignYebAssetsStatisticsPageReq feignReq = new FeignYebAssetsStatisticsPageReq();
        BeanUtils.copyProperties(req, feignReq);
        BaseResult baseResult = yebAssetsStatisticsFeign.page(feignReq);
        return baseResult;
    }

    @Override
    public BaseResult create(YebAssetsStatisticsCreateReq req, HttpServletResponse response) {
        FeignYebAssetsStatisticsCreateReq feignReq = new FeignYebAssetsStatisticsCreateReq();
        BeanUtils.copyProperties(req, feignReq);
        BaseResult baseResult = yebAssetsStatisticsFeign.create(feignReq);
        return baseResult;
    }

    @Override
    public void down(YebAssetsStatisticsDownReq req, HttpServletResponse response) {
        FeignYebAssetsStatisticsDownReq feignReq = new FeignYebAssetsStatisticsDownReq();
        BeanUtils.copyProperties(req, feignReq);
        BaseResult baseResult = yebAssetsStatisticsFeign.down(feignReq);
        if(baseResult.isSuccessAndDataNotNull()){
            Map<String, String> map = (Map<String, String>)baseResult.getData();
            String fileName = map.get("fileName");
            String path = map.get("path");
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                InputStream is = new BufferedInputStream(new FileInputStream(path));
                // 设置response参数，可以打开下载页面
                response.reset();
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName
                        .getBytes("utf-8"), "ISO8859-1"));
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                ServletOutputStream out = response.getOutputStream();
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
