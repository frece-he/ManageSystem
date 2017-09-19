package web.frece.mana.util;

public class ResultModel {

	public String resultCode;

	public String resultMsg = "";

	public Object resultData;

	public int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Object getResultData() {
		return resultData;
	}

	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}

	@Override
	public String toString() {
		return "ResultModel [resultCode=" + resultCode + ", resultMsg=" + resultMsg + ", resultData=" + resultData
				+ "]";
	}



}
