package com.yunfan.util.configure;

public interface ConfigureDetectorObserver {
	public void notifyChangeFileName(String pFileName, ConfigureFileDetector.FileChangeStatus pFileChangeStatus);
}
