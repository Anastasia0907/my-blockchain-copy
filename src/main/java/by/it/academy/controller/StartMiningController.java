package by.it.academy.controller;

import by.it.academy.pojo.User;
import by.it.academy.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
public class StartMiningController {

    private static final Logger logger = LoggerFactory.getLogger(StartMiningController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}/start-mining")
    public ModelAndView showStartMiningForm(
            @PathVariable String id,
            ModelAndView modelAndView
    ) {
        logger.info("Calling showStartMiningForm - GET");

        User user = userService.get(id);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("start-mining");

        return modelAndView;
    }

    @PostMapping(value = "/{id}/start-mining")
    public String startMining(@PathVariable String id) throws IOException, InterruptedException {

        logger.info("Calling startMining - POST");

        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(userService.get(id));
        post(userJson);

        return "redirect:userpage";
    }

    private static HttpResponse<String> post(String body) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8085/start-mining"))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Content-Type", "application/json")
                .build();

        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
