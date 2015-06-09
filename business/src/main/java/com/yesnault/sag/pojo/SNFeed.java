package com.yesnault.sag.pojo;

import org.springframework.social.facebook.api.*;
import org.springframework.social.linkedin.api.LinkedInNetworkUpdate;
import org.springframework.social.twitter.api.Tweet;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class SNFeed implements Serializable, Comparable<SNFeed>{

    private  String id;

    private  Reference from;

    private  Date createdTime;

    private  Date updatedTime;

    private List<Reference> to;

    private String message;

    private String picture;

    private String link;

    private String name;

    private String caption;

    private String description;

    private String icon;

    private Reference application;

    private List<Reference> likes;

    private List<CommentFeed> commentsFeeds;

    private int sharesCount;

    private String story;

    private String feedType;

    private String socialNetworkType;

    private String photoFrom;

    private Integer likesCount;

    private Integer commentsCount;

    private String src;

    private Boolean facebookFlag;

    private Boolean twitterFlag;

    private Boolean googleFlag;

    private Integer retweetsCount;

    private Integer favoritesCount;

    private String photoFromRetweet;

    private String nameFromRetweet;

    private Date dateFromRetweet;

    private String messageFromRetweet;


    public SNFeed(){
    }

    public SNFeed(Post post){
        this.id=post.getId();
        this.from=post.getFrom();
        this.createdTime=post.getCreatedTime();
        this.name=post.getName();
        this.picture=post.getPicture();
        this.link=post.getLink();
        this.description=post.getDescription();
        this.story=post.getStory();
        this.feedType=post.getType().name();

        //todo de facut constructori pentru facebook,linkedin si twiiter
    }

    public SNFeed(Tweet tweet){
    }

    public SNFeed(LinkedInNetworkUpdate linkedInNetworkUpdate){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Reference getFrom() {
        return from;
    }

    public void setFrom(Reference from) {
        this.from = from;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public List<Reference> getTo() {
        return to;
    }

    public void setTo(List<Reference> to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }



    public Reference getApplication() {
        return application;
    }

    public void setApplication(Reference application) {
        this.application = application;
    }

    public List<Reference> getLikes() {
        return likes;
    }

    public void setLikes(List<Reference> likes) {
        this.likes = likes;
    }

    public List<CommentFeed> getCommentsFeeds() {
        return commentsFeeds;
    }

    public void setCommentsFeeds(List<CommentFeed> commentsFeeds) {
        this.commentsFeeds = commentsFeeds;
    }

    public int getSharesCount() {
        return sharesCount;
    }

    public void setSharesCount(int sharesCount) {
        this.sharesCount = sharesCount;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getSocialNetworkType() {
        return socialNetworkType;
    }

    public void setSocialNetworkType(String socialNetworkType) {
        this.socialNetworkType = socialNetworkType;
    }

    public String getPhotoFrom() {
        return photoFrom;
    }

    public void setPhotoFrom(String photoFrom) {
        this.photoFrom = photoFrom;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Boolean getFacebookFlag() {
        return facebookFlag;
    }

    public void setFacebookFlag(Boolean facebookFlag) {
        this.facebookFlag = facebookFlag;
    }

    public Boolean getTwitterFlag() {
        return twitterFlag;
    }

    public void setTwitterFlag(Boolean twitterFlag) {
        this.twitterFlag = twitterFlag;
    }

    public Boolean getGoogleFlag() {
        return googleFlag;
    }

    public void setGoogleFlag(Boolean googleFlag) {
        this.googleFlag = googleFlag;
    }

    @Override
    public int compareTo(SNFeed snFeed) {
        if (getUpdatedTime() == null || snFeed.getUpdatedTime() == null)
            return 0;
        return getUpdatedTime().compareTo(snFeed.getUpdatedTime());
    }

    public Integer getRetweetsCount() {
        return retweetsCount;
    }

    public void setRetweetsCount(Integer retweetsCount) {
        this.retweetsCount = retweetsCount;
    }

    public Integer getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(Integer favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    public String getPhotoFromRetweet() {
        return photoFromRetweet;
    }

    public void setPhotoFromRetweet(String photoFromRetweet) {
        this.photoFromRetweet = photoFromRetweet;
    }

    public String getNameFromRetweet() {
        return nameFromRetweet;
    }

    public void setNameFromRetweet(String nameFromRetweet) {
        this.nameFromRetweet = nameFromRetweet;
    }

    public Date getDateFromRetweet() {
        return dateFromRetweet;
    }

    public void setDateFromRetweet(Date dateFromRetweet) {
        this.dateFromRetweet = dateFromRetweet;
    }

    public String getMessageFromRetweet() {
        return messageFromRetweet;
    }

    public void setMessageFromRetweet(String messageFromRetweet) {
        this.messageFromRetweet = messageFromRetweet;
    }
}
