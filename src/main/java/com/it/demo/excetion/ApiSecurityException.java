package com.it.demo.excetion;

/**
 * 自定义异常类
 */
public class ApiSecurityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private int code;

	private Object data;
	
	public ApiSecurityException(String message){
		super(message);
	}
	
	public ApiSecurityException(String message, int code){
		super(message);
		this.code = code;
	}
	public ApiSecurityException(String message, int code, Object data){
		super(message);
		this.code = code;
		this.data=data;
	}

	public ApiSecurityException(Throwable cause)
	{
		super(cause);
	}
	
	public ApiSecurityException(String message, Throwable cause)
	{
		super(message,cause);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
