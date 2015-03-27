/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import Data.MySQLUser;
import Data.SoulDao;
import com.jcabi.github.Github;
import com.sun.org.apache.xpath.internal.operations.Variable;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

/**
 *
 * @author McKay
 */
public class User {

    private int id;
    private String gender;
    private int age;
    private String github_username;
    private String quote;
    private String bio;
    private int compat_score;
    private String first_language;
    private String second_language;
    private String third_language;
    private Set<User> matches;

    public User() {
        id = -1;
        gender = null;
        age = -1;
        github_username = null;
        quote = null;
        bio = null;
        compat_score = -1;
        first_language = null;
        second_language = null;
        third_language = null;
        matches = null;
    }

    /**
     *
     * @param gender
     * @param age
     * @param github_username
     * @param quote
     * @param bio
     */
    public User(String gender, int age, String github_username, String quote, String bio) {
        this.id = -1;
        this.gender = gender;
        this.age = age;
        this.github_username = github_username;
        this.quote = quote;
        this.bio = bio;
        
        //get the matches
        SoulDao db = new MySQLUser();
        db.getMatches(this);
        db.close();
    }

    /**
     * 
     * @param id
     * @param gender
     * @param age
     * @param github_username
     * @param quote
     * @param bio 
     */
    public User(int id, String gender, int age, String github_username, String quote, String bio) {
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.github_username = github_username;
        this.quote = quote;
        this.bio = bio;
        
    }

    /**
     *
     * @param id
     * @param gender
     * @param age
     * @param github_username
     * @param quote
     * @param bio
     * @param compat_score
     * @param first_language
     * @param second_language
     * @param third_language
     */
    public User(int id, String gender, int age, String github_username, String quote, String bio, int compat_score, String first_language, String second_language, String third_language) {
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.github_username = github_username;
        this.quote = quote;
        this.bio = bio;
        this.compat_score = compat_score;
        this.first_language = first_language;
        this.second_language = second_language;
        this.third_language = third_language;
        
    }

    public Set<User> getMatches() {
        return matches;
    }

    public void setMatches(Set<User> matches) {
        this.matches = matches;
    }

    /**
     *
     * @param github
     * @throws IOException
     */
    public void calcTopThreeLangs(GitHub github) throws IOException {
        //TODO!! the top three langs need to be stored in the DB after this method is run

        //store a mapping of languages to bytes
        Map<String, Integer> allLangs = new HashMap<>();

        //get all repositories for the user
        Map<String, GHRepository> repositories = github.getMyself().getRepositories();

        //loop through each repository
        for (String repo : repositories.keySet()) {

            //get languages specific to repository mapped to bytes written in languages
            Map<String, Integer> languages = repositories.get(repo).listLanguages();

            //loop through languages in repository
            for (String lang : languages.keySet()) {

                //if the global language map contains the key
                if (allLangs.containsKey(lang)) {
                    //add the value for this repository to the total bytes written
                    allLangs.replace(lang, languages.get(lang) + allLangs.get(lang));
                } else {
                    //just add the number of bytes written
                    allLangs.put(lang, languages.get(lang));
                }
            }
        }

        //get a reverse of the map
        Map<Integer, String> langsForSort = new HashMap<>();

        for (Map.Entry<String, Integer> entry : allLangs.entrySet()) {
            langsForSort.put(entry.getValue(), entry.getKey());
        }

        //get the top languages
        ArrayList<String> topLangs = new ArrayList<>();
        SortedSet<Integer> keys = new TreeSet<Integer>(langsForSort.keySet()).descendingSet();
        for (Integer key : keys) {
            if (langsForSort.get(key) != "HTML" && langsForSort.get(key) != "CSS") {
                topLangs.add(langsForSort.get(key));
            }
        }

        //set the top language
        if (topLangs.size() > 0) {
            this.setFirst_language(topLangs.get(0));
        }

        //set the second language
        if (topLangs.size() > 1) {
            this.setSecond_language(topLangs.get(1));
        }

        //set the third language
        if (topLangs.size() > 2) {
            this.setThird_language(topLangs.get(2));
        }

        //updates the user in the db.
        SoulDao dao = new MySQLUser();
        //This updates the entire user
        //dao.updateUser(this);
        //or, you could just set the languages...probably more efficient...
        dao.saveLanguages(this.getId(), topLangs.get(0), topLangs.get(1), topLangs.get(2));

    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     *
     * @return
     */
    public String getGithub_username() {
        return github_username;
    }

    /**
     *
     * @param github_username
     */
    public void setGithub_username(String github_username) {
        this.github_username = github_username;
    }

    /**
     *
     * @return
     */
    public String getQuote() {
        return quote;
    }

    /**
     *
     * @param quote
     */
    public void setQuote(String quote) {
        this.quote = quote;
    }

    /**
     *
     * @return
     */
    public String getBio() {
        return bio;
    }

    /**
     *
     * @param bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     *
     * @return
     */
    public int getCompat_score() {
        return compat_score;
    }

    /**
     *
     * @param compat_score
     */
    public void setCompat_score(int compat_score) {
        this.compat_score = compat_score;
    }

    /**
     *
     * @return
     */
    public String getFirst_language() {
        return first_language;
    }

    /**
     *
     * @param first_language
     */
    public void setFirst_language(String first_language) {
        this.first_language = first_language;
    }

    /**
     *
     * @return
     */
    public String getSecond_language() {
        return second_language;
    }

    /**
     *
     * @param second_language
     */
    public void setSecond_language(String second_language) {
        this.second_language = second_language;
    }

    /**
     *
     * @return
     */
    public String getThird_language() {
        return third_language;
    }

    /**
     *
     * @param third_language
     */
    public void setThird_language(String third_language) {
        this.third_language = third_language;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            User toCompare = (User) o;
            return this.github_username.equals(toCompare.getGithub_username());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.github_username);
        return hash;
    }
}
