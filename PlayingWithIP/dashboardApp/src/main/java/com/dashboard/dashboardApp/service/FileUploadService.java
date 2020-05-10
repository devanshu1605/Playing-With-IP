package com.dashboard.dashboardApp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.dashboard.dashboardApp.model.CSVModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


@Service
public class FileUploadService {
			 
	public List<List<CSVModel>> uploadFile(MultipartFile file) throws IOException {

		ZipInputStream zis = new ZipInputStream(file.getInputStream());
		BufferedReader br;
		InputStreamReader isr;
		ZipEntry entry = zis.getNextEntry();
		List<List<CSVModel>> list = new ArrayList<>();
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
       		ObjectMapper mapper = new ObjectMapper();
       		mapper.enable(SerializationFeature.INDENT_OUTPUT);
       		mapper.writeValue(System.out, csvModelList);
       		System.out.println("list size "+list.size()+"  List value : "+list);
       		entry = zis.getNextEntry();
       		list.add(csvModelList);
		}
		System.out.println("list size "+list.size()+"  List value : "+list);
		Iterator iter2 = list.iterator();
		 while (iter2.hasNext()) {
			 List<CSVModel> csvModel = (List) iter2.next();
		     System.out.println("Object Name"+csvModel.get(0).getOBJECT_NAME()+"Event despcription : "+csvModel.get(0).getEVENT_DESCRIPTION()+" Date Time: "+ csvModel.get(0).getDATE()+" hostName: "+csvModel.get(0).getHOST_NAME()+"\n");
		 }
		zis.close();
	return list;
	}
}
