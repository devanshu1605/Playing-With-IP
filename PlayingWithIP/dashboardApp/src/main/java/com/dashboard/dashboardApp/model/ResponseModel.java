package com.dashboard.dashboardApp.model;

import java.util.Map;

public class ResponseModel {

	Map<String,Integer> sortedUsersMap;
	Map<String, Integer> hourlyUserData;
	Map<String, Integer> sortedFileMap;

	public void setSortedUsersMap(Map<String,Integer> sortedUsersMap) {
		this.sortedUsersMap = sortedUsersMap;	
	}
	
	public Map<String,Integer> getSortedUsersMap() {
		return this.sortedUsersMap;	
	}

	public void setHourlyUserData(Map<String, Integer> hourlyUserData) {
		this.hourlyUserData = hourlyUserData;		
	}
	
	public Map<String, Integer> getHourlyUserData() {
		return this.hourlyUserData;		
	}

	public void setTop5File(Map<String, Integer> sortedFileMap) {
		this.sortedFileMap=sortedFileMap;
	}
	
	public Map<String, Integer> getTop5File() {
		return this.sortedFileMap;
	}
}
