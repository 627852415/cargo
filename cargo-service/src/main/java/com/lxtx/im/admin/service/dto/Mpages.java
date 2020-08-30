package com.lxtx.im.admin.service.dto;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;

import java.util.List;

/**
 * @author Lin hj
 * @title: Mpages
 * @projectName git_work
 * @description: TODO
 * @date 2020/8/2717:31
 */
@Data
public class Mpages {

    private Page page;

    private List<String> pagesNums;

}
