package com.example.twitter.controller;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.twitter.models.Rules;
import com.example.twitter.models.Tweets;
import com.example.twitter.rep.KeyRepository;
import com.example.twitter.rep.TwitterRepository;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.FilterQuery;
import twitter4j.Status;
import twitter4j.StatusAdapter;
// import twitter4j.StreamListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

@CrossOrigin(origins = "localhost:4200/" , methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST})
@RestController
@RequestMapping("/t")
public class MyController {
    static ArrayList<String> ans = new ArrayList<>();
    @Autowired
    KeyRepository r;
    @Autowired
    TwitterRepository t;

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping(value = "/")
    public ResponseEntity<?> addKeyword(@RequestBody Rules rules) {
        ArrayList<String> keywords = new ArrayList<>();
        String[]  temp = {""};
        if(r.findAll().size()>0)  temp = r.findAll().get(0).getKeywords();
        
        for (String s : temp)
            keywords.add(s);
        for (String str : rules.getKeywords())
            if (!keywords.contains(str))
                keywords.add(str);
        r.deleteAll();
        String keywordsSave[] = new String[keywords.size()];
        int i = 0;
        for (String k : keywords)
            keywordsSave[i++] = k;
        rules.setKeywords(keywordsSave);
        r.save(rules);
        return ResponseEntity.ok("key added");
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping(value = "/keywords/get")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(this.r.findAll());
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @DeleteMapping(value = "/tweets/deleteAll")
    public ResponseEntity<?> deleteAllTweets() {
        t.deleteAll();
        return ResponseEntity.ok("deleted all Tweets");
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @DeleteMapping(value = "/keywords/delete")
    public ResponseEntity<?> deleteKeywords(@RequestBody Rules rules) {
        System.out.println("deleteThis  triggered");
        ArrayList<String> keywords = new ArrayList<>();
        String[] temp = r.findAll().get(0).getKeywords();
        for (String s : temp)
            keywords.add(s);
        for (String str : rules.getKeywords())
            if (keywords.contains(str)) {
                keywords.remove(str);
            }

        String keywordsSave[] = new String[keywords.size()];
        int i = 0;
        for (String k : keywords)
            keywordsSave[i++] = k;
        rules.setKeywords(keywordsSave);
        r.deleteAll();
        r.save(rules);
        return ResponseEntity.ok("deleted This keywords");
    }

    @CrossOrigin( "http://localhost:4200/")
    @DeleteMapping
    @RequestMapping(value = "/keywords/deleteAll")
    public ResponseEntity<?> deleteAllKeywords() {
        System.out.println("deleteAll triggered");
        r.deleteAll();
        return ResponseEntity.ok("deleted All keywords");
    }

    // tweets
    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping(value = "tweet")
    public ResponseEntity<?> checktweet(@RequestBody Tweets tweets) {
        t.save(tweets);
        return ResponseEntity.ok("tweet saved");
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping(value = "/tweet/get")
    public ResponseEntity<?> gettweets() {
        return ResponseEntity.ok(t.findAll());
    }

    ConfigurationBuilder cb;
    boolean flag = false;
    static TwitterStream tStream;

    public void createConnection() {
        flag = true;
        cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("")
                .setOAuthConsumerSecret("")
                .setOAuthAccessToken("")
                .setOAuthAccessTokenSecret("");
        tStream = new TwitterStreamFactory(cb.build()).getInstance();
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping(value = "/tweet")
    public ResponseEntity<?> matchingTweets() throws TwitterException {
        System.out.println("gathering tweets started");
        ArrayList<String> keywords = new ArrayList<>();
        if(r.findAll().size()<1)  return ResponseEntity.ok("no keywords to match");
        String[] temp = r.findAll().get(0).getKeywords();
        for (String s : temp)
            keywords.add(s);
        String keywordsSave[] = new String[keywords.size()];
        int i = 0;
        for (String k : keywords)
            keywordsSave[i++] = k;
        if (!flag)
            createConnection();
        StatusAdapter listener = new StatusAdapter() {
            @Override
            public void onStatus(Status status) {
                final String statusText = status.getText();
                for (String keyword : keywordsSave) {
                    if (statusText.contains(keyword)) {
                        String date = status.getCreatedAt().toString();
                        String hash = status.getHashtagEntities().toString();
                        Tweets tweet = new Tweets(keyword, statusText, date, hash);
                        t.save(tweet);
                        if (t.findAll().size() > 10) {
                            tStream.cleanUp();
                            tStream.clearListeners();
                            return;
                        }
                        ;
                        System.out.println("END OF listener Tweet saved ");
                    }
                }
            }
        };
        
        System.out.println("gathering tweets ended");
        // tStream.sample();
        FilterQuery filter = new FilterQuery();
        filter.track(keywordsSave);
        tStream.addListener(listener);
        tStream.filter(filter);

        return ResponseEntity.ok("gathering matches please wait");

    }

}
