package com.dashboard.dashboardApp.utility;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.dashboard.dashboardApp.model.CSVModel;

public class FileTask implements Callable{
    Map map; 
    List<CSVModel> csvModelList;
	public FileTask(List<CSVModel> csvModelList, Map<String, Integer> map) {
		this.map=map;
		this.csvModelList = csvModelList;
	}
	
	public Map<String, Integer> calculateTop5Files(Map<String, Integer> map, List<CSVModel> csvModelList){
		Iterator<CSVModel> iter = csvModelList.iterator();
		CSVModel model = null;
		while(iter.hasNext()) {
			model = iter.next();
			if(map.get(model.getOBJECT_NAME()) != null){
				int temp = map.get(model.getOBJECT_NAME());
				map.put(model.getOBJECT_NAME(), temp+1);
			}else {
				map.put(model.getOBJECT_NAME(), 1);
			}
		}
		return map;
	     
	}
	
	@Override
	public Object call() throws Exception {
		return calculateTop5Files(map, csvModelList);
	} 

}
