package com.example.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("server2")
public class ServerProperties {
	
	private String uploadPath;
	private String removePath;
	private String serverPath;   

    public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getRemovePath() {
		return removePath;
	}

	public void setRemovePath(String removePath) {
		this.removePath = removePath;
	}

	public String getServerPath() {
		return serverPath;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	@Override
    public String toString() {
        return "ServerProperties{" +
        		"serverPath='" + serverPath + '\'' +
        		"removePath='" + removePath + '\'' +
        		"uploadPath='" + uploadPath + '\'' +
                '}';
    }
}
