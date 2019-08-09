package com.example.payload;


public class FileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String data;
    private String imgPath;

    public FileResponse(String fileName, String fileDownloadUri, String fileType, long size, String data, String imgPath) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.data = data;
        this.imgPath = imgPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	    
}
