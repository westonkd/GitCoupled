/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import Data.MySQLUser;
import Data.SoulDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.kohsuke.github.GitHub;

/**
 *
 * @author weston
 */
public class GHMatchMaker {

    //the person to find the matches for
    User toMatch;

    //mapping of matches
    Map<Integer, Set<User>> matches;

    //github object
    GitHub github;

    GHMatchMaker(User user, GitHub github) {
        //set the user we are matching
        toMatch = user;

        //create an empty map
        matches = new HashMap<>();

        //set the github
        this.github = github;

        //calculate the matches!
        calculateMatches();
    }

    public User getToMatch() {
        return toMatch;
    }

    public void setToMatch(User toMatch) {
        this.toMatch = toMatch;
    }

    public Map<Integer, Set<User>> getMatches() {
        return matches;
    }

    /**
     * Calculates and rates all possible matches for the user. It is important
     * to match from the best match down.
     */
    private void calculateMatches() {
        SoulDao dao = new MySQLUser();

        //get the top matches
        for (User match : dao.getUsers(toMatch.getFirst_language(), toMatch.getSecond_language(), toMatch.getThird_language())) {
            if (!match.getGithub_username().equals(toMatch.getGithub_username())) {
                //if the key exists
                if (matches.containsKey(10)) {
                    matches.get(10).add(match);
                } else {
                    //create the new set with the user
                    Set<User> temp = new HashSet<>();
                    temp.add(match);

                    matches.put(10, temp);
                }
            }
        }

        //get the second matches
        for (User match : dao.getUsers(toMatch.getFirst_language(), toMatch.getSecond_language())) {
            //only add the match if not already in the dictionary
            if (!match.equals(toMatch)) {
                boolean add = true;

                //check if the match is already in the list
                for (Integer key : matches.keySet()) {
                    if (matches.get(key).contains(match)) {
                        add = false;
                    }
                }

                //if the user is not in a list already
                if (add && matches.containsKey(9)) {
                    matches.get(9).add(match);
                } else if (add) {
                    //create the new set with the user
                    Set<User> temp = new HashSet<>();
                    temp.add(match);

                    matches.put(9, temp);
                }

            }
        }

        //get the third matches
        for (User match : dao.getUsers(toMatch.getFirst_language())) {
            //only add the match if not already in the dictionary
            if (!match.equals(toMatch)) {
                boolean add = true;

                //check if the match is already in the list
                for (Integer key : matches.keySet()) {
                    if (matches.get(key).contains(match)) {
                        add = false;
                    }
                }

                //if the user is not in a list already
                if (add && matches.containsKey(8)) {
                    matches.get(8).add(match);
                } else if (add) {
                    //create the new set with the user
                    Set<User> temp = new HashSet<>();
                    temp.add(match);

                    matches.put(8, temp);
                }

            }
        }
    }
}
