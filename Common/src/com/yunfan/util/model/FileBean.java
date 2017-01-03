package com.yunfan.util.model;

public class FileBean extends JsonBean {
	private String fileID;
	private String fileName;
	private String fileFullPath;
	private Integer fileLength;
	private String resourceID;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileFullPath() {
		return fileFullPath;
	}

	public void setFileFullPath(String fileFullPath) {
		this.fileFullPath = fileFullPath;
	}

	public Integer getFileLength() {
		return fileLength;
	}

	public void setFileLength(Integer fileLength) {
		this.fileLength = fileLength;
	}

	public String getResourceID() {
		return resourceID;
	}

	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}

	public String getFileID() {
		return fileID;
	}

	public void setFileID(String fileID) {
		this.fileID = fileID;
	}

	public FileBean() {
		super();
		// TODO Auto-generated constructor stub
	}

}
