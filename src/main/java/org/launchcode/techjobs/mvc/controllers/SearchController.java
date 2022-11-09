package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;
/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        ArrayList<Job> jobs;
        String title = "Jobs With All: " + searchTerm;
        try {
            if (searchType.toLowerCase().trim().equals("all") && searchTerm.toLowerCase().trim().equals("all") || searchTerm.equals("")) {
                jobs = JobData.findAll();
            } else {
                jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            }

            model.addAttribute("title", title);
            model.addAttribute("jobs", jobs);
            model.addAttribute("columns", columnChoices);
        } catch (Exception e) {
            Error error = new Error ("Sorry! No jobs found!");
            throw error;
        }
        return "search";
    }

}
