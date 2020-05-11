package com.dashboard.dashboardApp.utility;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.dashboard.dashboardApp.model.CSVModel;

public class LogUtility implements Callable{
    Map map; 
    List<CSVModel> csvModelList;
	public LogUtility(List<CSVModel> csvModelList, Map<String, Integer> map) {
		this.map=map;
		this.csvModelList = csvModelList;
	}

	public Map<String, Integer> calculateTop5Users(Map<String, Integer> map, List<CSVModel> csvModelList){
		Iterator<CSVModel> iter = csvModelList.iterator();
		CSVModel model = null;
		while(iter.hasNext()) {
			model = iter.next();
			if(map.get(model.getUSER_NAME()) != null){
				int temp = map.get(model.getUSER_NAME());
				map.put(model.getUSER_NAME(), temp+1);
			}else {
				map.put(model.getUSER_NAME(), 1);
			}
		}
		return map;
	     
	}

	@Override
	public Object call() throws Exception {
		return calculateTop5Users(map, csvModelList);
	}
}
