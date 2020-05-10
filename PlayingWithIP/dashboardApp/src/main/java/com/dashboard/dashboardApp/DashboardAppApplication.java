package com.dashboard.dashboardApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.dashboard.dashboardApp.configuration.FileUploadProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileUploadProperties.class
})
public class DashboardAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardAppApplication.class, args);
	}

}
