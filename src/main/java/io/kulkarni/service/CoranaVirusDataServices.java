package io.kulkarni.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.kulkarni.model.LocationStats;

@Service
public class CoranaVirusDataServices {

	public static String DATA_SERVICE_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	public List<LocationStats> allStat = new ArrayList<LocationStats>();
	@PostConstruct
	@Scheduled(cron = "* * * 1 * *")
	public void fetchVirusData() {
		List<LocationStats> newStat = new ArrayList<LocationStats>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DATA_SERVICE_URL)).build();
		HttpResponse<String> response = null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringReader reader = new StringReader(response.body());
		Iterable<CSVRecord> records = null;
		try {
			records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (CSVRecord record : records) {
			LocationStats locationStats = new LocationStats();
			locationStats.setState(record.get("Province/State"));
			locationStats.setCountry(record.get("Country/Region"));
			int latestRecord = Integer.parseInt(record.get(record.size()-1));
			locationStats.setLatestTotalCases(latestRecord);
			int PrevDay = latestRecord - Integer.parseInt(record.get(record.size()-2));
			locationStats.setChangeSincePreviousDay(PrevDay);
		    System.out.println(locationStats.toString());
		    newStat.add(locationStats);
		}
		this.allStat = newStat;
	}
	public List<LocationStats> getAllStat() {
		return allStat;
	}
}
