package com.lxtx.im.admin.feign.request;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class FeignQuartzReq implements Serializable {

	private static final long serialVersionUID = 6892171779562964127L;
	private List<String> jobIds;

}
