package com.yesnault.sag.controller;

import com.yesnault.sag.interfaces.GoogleService;
import com.yesnault.sag.pojo.SNFriend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.google.api.Google;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by CParaschivescu on 5/27/2015.
 */

@Controller
public class GoogleController {

    Logger LOGGER = LoggerFactory.getLogger(GoogleController.class);

    @Inject
    private GoogleService googleService;

    @RequestMapping(value = "/isConnectGoogle", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    boolean isConnectLinkedin(){
        return googleService.isConnectGoogle();
    }

    @RequestMapping(value = "/friendsGoogle", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<SNFriend> friendsGoogle() {
        return googleService.findFriends();
    }
}