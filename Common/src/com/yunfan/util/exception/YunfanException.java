package com.yunfan.util.exception;

/**
 * 业务异常类，用于云帆业务异常
 * @author yangxingyi
 *
 */
public class YunfanException extends RuntimeException {

	private String exceptionCode;
	
	private String exceptionMsg;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4084533407547213815L;

	public YunfanException(){
		super();
	}
	
	public YunfanException(String eCode, String eMsg){
		super(eCode, null);
		this.exceptionCode = eCode;
		this.exceptionMsg = eMsg;
	}
	
	public YunfanException(String eCode, Throwable t){
		super(eCode, t);
		this.exceptionCode = eCode;
		this.exceptionMsg = t.getMessage();
	}
	
	public String getExceptionCode() {
		return exceptionCode;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}
}
