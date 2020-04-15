package io.kulkarni.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.kulkarni.model.LocationStats;
import io.kulkarni.service.CoranaVirusDataServices;

@Controller
public class HomeController {

	@Autowired
	CoranaVirusDataServices dataServices;

	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = dataServices.getAllStat();
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int ChangesFromPrevDay = totalReportedCases
				- allStats.stream().mapToInt(stat -> stat.getChangeSincePreviousDay()).sum();
		model.addAttribute("locationStats", dataServices.getAllStat());
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("ChangesFromPrevDay", ChangesFromPrevDay);
		return "home";
	}
}
