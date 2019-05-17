package com.bootdo.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="bootdo")
public class BootdoConfig {
	//上传路径
	private String uploadPath;
	//js路径
	private String jsPath;
	//华龙网基础路径
	private String baseCqnewsUrl;
	
	private String appId;
	
	private String appKey;

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getJsPath() {
		return jsPath;
	}

	public void setJsPath(String jsPath) {
		this.jsPath = jsPath;
	}

	public String getBaseCqnewsUrl() {
		return baseCqnewsUrl;
	}

	public void setBaseCqnewsUrl(String baseCqnewsUrl) {
		this.baseCqnewsUrl = baseCqnewsUrl;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	
	
}
