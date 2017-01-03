package com.yunfan.util.common;

public enum ChartType {

	LINE(1),BAR(2),MAP(3);
	private int type;
	ChartType(int type){
		this.type = type;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
