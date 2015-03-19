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

    public User(int id, String gender, int age, String github_username, String quote, String bio) {
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.github_username = github_username;
        this.quote = quote;
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGithub_username() {
        return github_username;
    }

    public void setGithub_username(String github_username) {
        this.github_username = github_username;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getCompat_score() {
        return compat_score;
    }

    public void setCompat_score(int compat_score) {
        this.compat_score = compat_score;
    }

    public String getFirst_language() {
        return first_language;
    }

    public void setFirst_language(String first_language) {
        this.first_language = first_language;
    }

    public String getSecond_language() {
        return second_language;
    }

    public void setSecond_language(String second_language) {
        this.second_language = second_language;
    }

    public String getThird_language() {
        return third_language;
    }

    public void setThird_language(String third_language) {
        this.third_language = third_language;
    }
    
}
