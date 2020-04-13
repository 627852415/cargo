package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.file.enums.EnumProjectType;
import com.lxtx.framework.common.file.enums.EnumRelateType;
import com.lxtx.framework.common.file.utils.QiNiuUtil;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.feign.feign.GameFeign;
import com.lxtx.im.admin.feign.request.FeignGameDeleteReq;
import com.lxtx.im.admin.feign.request.FeignGameListPageReq;
import com.lxtx.im.admin.feign.request.FeignGameSaveReq;
import com.lxtx.im.admin.feign.request.FeignGameSelectReq;
import com.lxtx.im.admin.service.GameService;
import com.lxtx.im.admin.service.enums.EnumGameType;
import com.lxtx.im.admin.service.request.GameDeleteReq;
import com.lxtx.im.admin.service.request.GameListPageReq;
import com.lxtx.im.admin.service.request.GameSaveReq;
import com.lxtx.im.admin.service.request.GameSelectReq;
import com.lxtx.im.admin.service.response.GameResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * 游戏
 *
 * @author CaiRH
 */
@Slf4j
@Service
public class GameServiceImpl implements GameService {

    @Resource
    private GameFeign gameFeign;
    @Autowired
    private QiNiuUtil qiNiuUtil;

    @Override
    public BaseResult listPage(GameListPageReq req) {
        FeignGameListPageReq listPageReq = new FeignGameListPageReq();
        BeanUtils.copyProperties(req, listPageReq);
        BaseResult result = gameFeign.listPage(listPageReq);
        if (result.isSuccess() && result.getData() != null) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
            if (jsonObject == null) {
                return result;
            }

            List<GameResp> gameRespList = JSONObject.parseArray(jsonObject.getString("records"), GameResp.class);
            if (!CollectionUtils.isEmpty(gameRespList)) {
                gameRespList.forEach(gameResp -> gameResp.setTypeName(EnumGameType.findDescription(gameResp.getType())));
                jsonObject.put("records", gameRespList);
                result.setData(jsonObject);
            }
        }
        return result;
    }

    @Override
    public BaseResult save(GameSaveReq req) {
        FeignGameSaveReq saveReq = new FeignGameSaveReq();
        BeanUtils.copyProperties(req, saveReq);
        return gameFeign.save(saveReq);
    }

    @Override
    public void info(GameSelectReq req, Model model) {
        if (StringUtils.isNotBlank(req.getId())) {
            FeignGameSelectReq selectReq = new FeignGameSelectReq();
            BeanUtils.copyProperties(req, selectReq);
            BaseResult result = gameFeign.info(selectReq);
            if (result.isSuccess() && result.getData() != null) {
                model.addAttribute("game", JSONObject.parseObject(JSON.toJSONString(result.getData()), GameResp.class));
            }
        }
        model.addAttribute("gameTypes", EnumGameType.values());
        model.addAttribute("viewUrl", PropertiesUtil.getString(PropertiesUtil.FILE_VIEW_URL));
    }

    @Override
    public BaseResult delete(GameDeleteReq req) {
        FeignGameDeleteReq delReq = new FeignGameDeleteReq();
        BeanUtils.copyProperties(req, delReq);
        return gameFeign.delete(delReq);
    }

    @Override
    public BaseResult upload(MultipartFile multipartFile) throws IOException {
       // String path = FileSlicesUploadUtils.slicesUpload(file, EnumRelateType.ADMIN_GAME.getCode());
        String fileName = multipartFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        String filePath = null;
        try {
            filePath = qiNiuUtil.upload(multipartFile.getBytes(),prefix, EnumProjectType.FINCY, EnumRelateType.ADMIN_STICKER);
        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }
        return BaseResult.success(filePath);
    }
}
