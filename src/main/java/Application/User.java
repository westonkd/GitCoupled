/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

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
    }

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
    
}
