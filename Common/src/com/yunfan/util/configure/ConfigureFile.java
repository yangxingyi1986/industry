package com.yunfan.util.configure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConfigureFile {
	
	/*
	 * 取得Agent配置文件中的Agent配置信息
	 * @param pFilePath配置文件的目录
	 */
	public PluginConfigure getConfigure(String pFilePath, Class<? extends PluginConfigure> pClazz) throws IOException  {
		PluginConfigure configure = null;
		try{
			File file = new File(pFilePath);
			String jsonlet = loadAFileToString(file);
			GsonBuilder builder = new GsonBuilder();
	        Gson gson = builder.create();
	        System.out.println(jsonlet);
	        configure = gson.fromJson(jsonlet, pClazz);
		}catch(Exception e){
			e.printStackTrace();
		}
		return configure;
	}
	
	/*
	 * 加载配置文件到字符串中
	 * @param pFile：Agent配置文件的目录
	 */
	private String loadAFileToString(File pFile) throws IOException {
        BufferedReader br = null;
        String ret = null;
        
        try {
            br =  new BufferedReader(new FileReader(pFile));
            String line = null;
            StringBuffer sb = new StringBuffer((int)pFile.length());
            while( (line = br.readLine() ) != null ) {
                sb.append(line);
            }
            ret = sb.toString();
        } finally {
            if(br!=null) {try{br.close();} catch(Exception e){} }
        }
        
        return ret;        
    }
}
