package com.lxtx.framework.common.utils.bcbbank.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Lin hj
 * @title: BcbBaseResp
 * @projectName git_work
 * @description: TODO
 * @date 2019/4/2315:04
 */
@Data
public class BcbStateListResp {


    private List<StateInfo> states;

    private BcbBaseResp status;


    @Getter
    @Setter
    public class StateInfo{
        private  int stateId;
        private String stateName;
    }


}
