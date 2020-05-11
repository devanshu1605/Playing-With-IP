package com.dashboard.dashboardApp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import com.dashboard.dashboardApp.model.CSVModel;
import com.dashboard.dashboardApp.model.ResponseModel;
import com.dashboard.dashboardApp.utility.FileTask;
import com.dashboard.dashboardApp.utility.LogUtility;


@Service
public class FileUploadService {
			 
	public ResponseModel uploadFile(MultipartFile file) throws IOException {

		BufferedReader br;
		InputStreamReader isr;
		Map<String, Integer> map = new HashMap<>();
		Map<String, Integer> fileMap = new HashMap<>();
		ZipInputStream zis = new ZipInputStream(file.getInputStream());
		Map<String, Integer>  sortedUsersMap = new LinkedHashMap<>();
		Map<String, Integer>  sortedFileMap = new LinkedHashMap<>();
		ZipEntry entry = zis.getNextEntry();
		List<List<CSVModel>> list = new ArrayList<>();
		Map<String, Integer> hourlyUserData = new HashMap<>();
		Pattern pattern = Pattern.compile("	");
		while(entry!=null) { 
       		 isr= new InputStreamReader(zis); 
       		 br = new BufferedReader(isr);
       		 List<CSVModel> csvModelList = new ArrayList<>();
       		 csvModelList = br
       			    .lines().map(line -> {
       			            String[] x = pattern.split(line);
       			            return new CSVModel(x);
       			        })
       			    .collect(Collectors.toList());

       		entry = zis.getNextEntry();
       		list.add(csvModelList);
       		map = findUserMap(csvModelList, map);
       		fileMap = findFileMap(csvModelList, fileMap);
       		hourlyUserData = getHourlyUserData(csvModelList, hourlyUserData);
		}
		sortedUsersMap = getTop5(map);
		sortedFileMap = getTop5(fileMap);
		zis.close();
		ResponseModel model = new ResponseModel();
		model.setSortedUsersMap(sortedUsersMap);
		model.setHourlyUserData(hourlyUserData);
		model.setTop5File(sortedFileMap);
	return model;
	}

	private Map<String, Integer> findFileMap(List<CSVModel> csvModelList, Map<String, Integer> fileMap) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<HashMap<String,Integer>> future = executorService.submit(new FileTask(csvModelList, fileMap));
		try {
			fileMap= future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return fileMap;
	}

	private Map<String, Integer> getHourlyUserData(List<CSVModel> csvModelList, Map<String, Integer> map) {
		Iterator<CSVModel> iter = csvModelList.iterator();
		while(iter.hasNext()) {
			CSVModel model = iter.next();
			String key = model.getRequestStartTime().substring(0,model.getRequestStartTime().indexOf(":"));
			if(map.get(key)!=null) {
				map.put(key, map.get(key)+1);
			}else {
				map.put(key,1);
			}
		}
		return map;
	}

	private Map<String, Integer> getTop5(Map<String, Integer> map) {
		Map<String, Integer> temp =map.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		int counter = 0;
		Map<String, Integer> sortedMap = new LinkedHashMap<>();
		for(Map.Entry<String, Integer> entry:temp.entrySet()) {
			sortedMap.put(entry.getKey(), entry.getValue());
			counter++;
			if(counter>4) {
				break;
			}
		}
		return sortedMap;
	}

	private Map<String, Integer> findUserMap(List<CSVModel> csvModelList, Map<String, Integer> map) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<HashMap<String,Integer>> future = executorService.submit(new LogUtility(csvModelList, map));
		try {
			map= future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return map;
	}
	
}
