package lcbo.controller;

import lcbo.search.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * Controller for performing a search and handling the results of a search request.
 */
@Controller
public class ResultsController {

    private static final String API_KEY = "MDoyOTliYzRmMi00ZTYwLTExZTctYmM1YS0wMzBmNmMyNjI1NDI6ZzJuTUl2cWNlMFYwa0tDcWFkdkNhdlZreFNYbm9aQkkyQkVW";
    private static final String URL = "https://lcboapi.com/stores?access_key=" + API_KEY + "&q=";

    private static final Logger log = LoggerFactory.getLogger(ResultsController.class);

    @RequestMapping("/results")
    public String results(@RequestParam(value="keyword") String keyword, Model model) {

        RestTemplate restTemplate = new RestTemplate();
        SearchResult searchResult = restTemplate.getForObject(URL + keyword, SearchResult.class);

        model.addAttribute("keyword", keyword);
        model.addAttribute("searchResult", searchResult);

        log.debug("results: lcboapi.com search for \"" + keyword + "\" returned " + searchResult.toString());

        return "/results";
    }

}
