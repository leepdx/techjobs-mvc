package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

   @RequestMapping(value = "results")
   public String listJobsByColumnAndValue(Model model,
            @RequestParam String searchType, @RequestParam String searchTerm) {

       // if search type is all, search jobs arraylist with findByValue
       if (searchType.equals("all")) {
           ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
           // add each job hashmap to the model
           model.addAttribute("jobs", jobs);
       }

       // else search jobs arraylist with findByColumnAndValue
       else {
           ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
           // add each job hashmap to the model
           model.addAttribute("jobs", jobs);
       }

       // display columnChoices on recurring visits to search page
       model.addAttribute("columns", ListController.columnChoices);
       return "search";
   }

}
