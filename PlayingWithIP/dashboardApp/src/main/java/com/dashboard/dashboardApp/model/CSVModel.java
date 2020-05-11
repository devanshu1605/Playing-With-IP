package com.dashboard.dashboardApp.model;

public class CSVModel {
	
	public CSVModel(String[] rowObject){
		this.OBJECT_NAME = getValue(rowObject[1], "OBJECT_NAME");
		this.DATE = getValue(rowObject[2], "DATETIME");
		this.RequestStartTime=rowObject[3];
		this.RequestEndTime=rowObject[4];
		this.APPLICATIONNAME= getValue(rowObject[6],"APPLICATIONNAME");
		this.HOST_NAME=getValue(rowObject[8],"HOST_NAME");
		this.OBJECT_TYPE= getValue(rowObject[7],"OBJECT_TYPE");
		this.EVENT_DESCRIPTION= getValue(rowObject[10],"EVENT_DESCRIPTION");
		this.USER_NAME= getValue(rowObject[13],"USER_NAME");				
	}

    private String OBJECT_NAME;

    private String DATE;
    
    private String RequestStartTime;
    
    private String RequestEndTime;

    private String APPLICATIONNAME;

    private String OBJECT_TYPE;
    
    private String HOST_NAME;

   
    private String MESSAGE;

    private String EVENT_DESCRIPTION;

    private String USER_NAME;
    
    private String COMMANDNAME;
    
    private String SOURCEUSERNAME;
   

	public String getOBJECT_NAME() {
		return OBJECT_NAME;
	}

	public String getDATE() {
		return DATE;
	}


	public String getAPPLICATIONNAME() {
		return APPLICATIONNAME;
	}


	public String getOBJECT_TYPE() {
		return OBJECT_TYPE;
	}

	public String getHOST_NAME() {
		return HOST_NAME;
	}

	public String getMESSAGE() {
		return MESSAGE;
	}

	public String getEVENT_DESCRIPTION() {
		return EVENT_DESCRIPTION;
	}

	public String getUSER_NAME() {
		return USER_NAME;
	}


	public String getCOMMANDNAME() {
		return COMMANDNAME;
	}

	public String getSOURCEUSERNAME() {
		return SOURCEUSERNAME;
	}

	public String getRequestStartTime() {
		return RequestStartTime;
	}

	public String getRequestEndTime() {
		return RequestEndTime;
	}
	
	private String getValue(String str, String fieldName) {
		if(str.contains(fieldName)) {
			return str.substring(str.indexOf("=")+1, str.length());
		}else {
			return "unKnown";
		}
	}
}