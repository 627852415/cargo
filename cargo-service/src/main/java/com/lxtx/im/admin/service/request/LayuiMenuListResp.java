package com.lxtx.im.admin.service.request;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

/**
 * <p>
 * 返回数据
 * </p>
 *
 * @author tangdy
 * @since 2018-08-03
 */
@Getter
@Setter
public class LayuiMenuListResp {

    private Object data;

    private String msg;

    private boolean is;

    private String code = "0";

    private int count = 0;

    public static LayuiMenuListResp is() {
        return is(0,null);
    }

    public static LayuiMenuListResp is(int count, Object object) {
        LayuiMenuListResp baseResult = new LayuiMenuListResp();
        baseResult.setIs(true);
        baseResult.setData(object);
        baseResult.setMsg("");
        baseResult.setCount(count);
        return baseResult;
    }

    public static LayuiMenuListResp is(Page page) {
        LayuiMenuListResp baseResult = new LayuiMenuListResp();
        baseResult.setIs(true);
        if(CollectionUtils.isEmpty(page.getRecords())){
            baseResult.setCode("1");
            baseResult.setMsg("未查询到相关数据");
            baseResult.setData(new ArrayList<>());
        }else{
            baseResult.setData(page.getRecords());
        }
        baseResult.setCount(page.getTotal());
        return baseResult;
    }

    public static LayuiMenuListResp error(String code, String msg) {
        LayuiMenuListResp baseResult = new LayuiMenuListResp();
        baseResult.setIs(false);
        baseResult.setCode(code);
        baseResult.setCode("1");
        baseResult.setMsg(StringUtils.isBlank(msg) ? "系统走了神，请联系管理员" : msg);
        return baseResult;
    }
}