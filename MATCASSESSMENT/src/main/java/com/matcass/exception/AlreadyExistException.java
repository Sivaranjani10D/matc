package com.matcass.exception;

public class AlreadyExistException extends RuntimeException  {
	private String ErrCode;
    private String ErrMsg;
	public String getErrCode() {
		return ErrCode;
	}
	public void setErrCode(String errCode) {
		ErrCode = errCode;
	}
	public String getErrMsg() {
		return ErrMsg;
	}
	public void setErrMsg(String errMsg) {
		ErrMsg = errMsg;
	}
  public AlreadyExistException()
  {
	  
  }
}
