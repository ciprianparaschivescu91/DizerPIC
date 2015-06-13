package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.UserService;
import com.yesnault.sag.model.User;
import com.yesnault.sag.model.UserProfile;
import com.yesnault.sag.repository.UserProfileRepository;
import com.yesnault.sag.repository.UserRepository;
import com.yesnault.sag.util.WizzardDTO;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.google.api.Google;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by CParaschivescu on 1/20/2015.
 */
@Service(value = "userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserProfileRepository userProfileRepository;

    @Inject
    private UsersConnectionRepository usersConnectionRepository;

    @Inject
    private Facebook facebook;

    @Inject
    private Twitter twitter;

    @Inject
    private LinkedIn linkedIn;

    @Inject
    private Google google;

    @Override
    public List<User> findByUsername(String lastname) {
        return userRepository.findByUsername(lastname);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        List<User> users = userRepository.findByUsernameAndPassword(username, password);
        if(users!=null&&users.size()>0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public Boolean saveUserWizzardProfile(WizzardDTO wizzardDTO, User user) {

        user.setFirstname(wizzardDTO.getFirstName());
        user.setLastname(wizzardDTO.getLastName());
        user.setProfileConf(true);
        user.setActive(true);
        user = userRepository.save(user);
        UserProfile userProfile = null;
        if (user.getUserProfile() == null) {
            userProfile = new UserProfile();
            userProfile.setUser(user);
        } else {
            userProfile = user.getUserProfile();
        }

        userProfile.setFacebookFlag(wizzardDTO.getIsFacebook());
        userProfile.setTwitterFlag(wizzardDTO.getIsTwitter());
        userProfile.setLinkedinFlag(wizzardDTO.getIsLinkedin());
        userProfile.setGoogleFlag(wizzardDTO.getIsGoogle());
        userProfile.setFromProfileImage(wizzardDTO.getProfileImage());
        userProfile.setFromProfileAbout(wizzardDTO.getProfileAbout());
        userProfile.setFromProfileCover(wizzardDTO.getProfileCover());
        userProfile.setFromProfileFriends(wizzardDTO.getProfileFriend());
        userProfile.setFromProfileName(wizzardDTO.getProfileName());
        userProfileRepository.save(userProfile);
        return true;
    }

    @Override
    public void logoutFromSocialNetworks(User user) {
        user = userRepository.findByUsername(user.getUsername()).get(0);
        UserProfile userProfile = user.getUserProfile();

        if (userProfile.getFacebookFlag()) {
            String userIdFacebook = facebook.userOperations().getUserProfile().getId();
            Set<String> providerUserIdsFacebook = new HashSet<String>();
            providerUserIdsFacebook.add(userIdFacebook);
            Set<String> localUserIdsFacebook = usersConnectionRepository.findUserIdsConnectedTo("facebook", providerUserIdsFacebook);
            for (String localUserId : localUserIdsFacebook) {
                ConnectionRepository connectionRepository = usersConnectionRepository.createConnectionRepository(localUserId);
                connectionRepository.removeConnection(new ConnectionKey("facebook", userIdFacebook));
            }
        }

        if (userProfile.getTwitterFlag()) {
            String userIdTwitter = Long.valueOf(twitter.userOperations().getProfileId()).toString();
            Set<String> providerUserIdsTwitter = new HashSet<String>();
            providerUserIdsTwitter.add(userIdTwitter);
            Set<String> localUserIdsTwitter = usersConnectionRepository.findUserIdsConnectedTo("twitter", providerUserIdsTwitter);
            for (String localUserId : localUserIdsTwitter) {
                ConnectionRepository connectionRepository = usersConnectionRepository.createConnectionRepository(localUserId);
                connectionRepository.removeConnection(new ConnectionKey("twitter", userIdTwitter));
            }
        }

        if (userProfile.getLinkedinFlag()) {
            String userIdLinkedin = linkedIn.profileOperations().getProfileId();
            Set<String> providerUserIdsLinkedin = new HashSet<String>();
            providerUserIdsLinkedin.add(userIdLinkedin);
            Set<String> localUserIdsLinkedin = usersConnectionRepository.findUserIdsConnectedTo("linkedin", providerUserIdsLinkedin);
            for (String localUserId : localUserIdsLinkedin) {
                ConnectionRepository connectionRepository = usersConnectionRepository.createConnectionRepository(localUserId);
                connectionRepository.removeConnection(new ConnectionKey("linkedin", userIdLinkedin));
            }
        }

        if (userProfile.getGoogleFlag()) {
            String userIdGoogle = google.plusOperations().getGoogleProfile().getId();
            Set<String> providerUserIdsGoogle = new HashSet<String>();
            providerUserIdsGoogle.add(userIdGoogle);
            Set<String> localUserIdsGoogle = usersConnectionRepository.findUserIdsConnectedTo("google", providerUserIdsGoogle);
            for (String localUserId : localUserIdsGoogle) {
                ConnectionRepository connectionRepository = usersConnectionRepository.createConnectionRepository(localUserId);
                connectionRepository.removeConnection(new ConnectionKey("google", userIdGoogle));
            }
        }

        user.setLoginActive(false);
        userRepository.save(user);
    }

    @Override
    public String login(String username, String password, HttpServletRequest request) {
        User user = findByUsernameAndPassword(username, password);
        if (user == null) {
            return "notOK";
        } else {
            if (user.getUserProfile() == null) {
                request.getSession().setAttribute("user", user);
                user.setLoginActive(true);
                userRepository.save(user);
                return "okSettings";
            } else if (!user.getLoginActive()) {
                request.getSession().setAttribute("user", user);
                user.setLoginActive(true);
                userRepository.save(user);
                return "okLoginSocialNetwork";
            } else {
                request.getSession().setAttribute("user", user);
                user.setLoginActive(true);
                userRepository.save(user);
                return "okProfile";
            }
        }
    }

    @Override
    public String signUp(String username, String password, String firstName, String lastName) {
        List<User> users=userRepository.findByUsername(username);
        if(users == null || users.size() == 0) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setActive(true);
            user.setLoginActive(false);
            user.setFirstname(firstName);
            user.setLastname(lastName);
            userRepository.save(user);
        }else{
            return "existsUsername";
        }

        return "okSignUp";
    }

    @Override
    public void setLoginActive(User user) {
        if (user != null) {
            user.setLoginActive(true);
            userRepository.save(user);
        }
    }
}
