package ru.vadimio.TestRestApi.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vadimio.TestRestApi.errors.Error;
import ru.vadimio.TestRestApi.service.MainService;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MainController {

    private MainService mainservice;
    private static final Logger log = Logger.getLogger(MainController.class);

    @Autowired
    public void setMainController(MainService mainservice){
        this.mainservice = mainservice;
    }

    @GetMapping("/v1/A")
    public String updateString(@RequestParam String str,
                               HttpServletResponse response){
        log.info(str);
        String result = mainservice.updateString(str);
        if(result.equals(Error.NOT_INTEGER_VALUE.getErr())
                || result.equals(Error.SMALL_NUMBER.getErr())
                || result.equals(Error.BIG_NUMBER.getErr()))
            response.setStatus(400);
        else
            response.setStatus(200);
        log.info("Status:" + response.getStatus() + "\n Result: " + result);
        return "Status:" + response.getStatus() + "\n Result: " + result;
    }

    @PostMapping("/v1/B")
    public String updateN(@RequestParam Integer n,
                          HttpServletResponse response){
        log.info(n);
        if(!mainservice.updateN(n))
            response.setStatus(400);
        else
            response.setStatus(200);
        log.info("Status:" + response.getStatus());
        return "Status:" + response.getStatus();
    }
}
