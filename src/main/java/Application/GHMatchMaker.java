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
import java.util.TreeMap;
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
        matches = new TreeMap<Integer, Set<User>>().descendingMap();

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

        //10
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

        //9
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

        //8
        for (User match : dao.getUsers(toMatch.getFirst_language(), toMatch.getThird_language())) {
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
        
        //7
        for (User match : dao.getUsers(toMatch.getSecond_language(), toMatch.getThird_language())) {
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
                if (add && matches.containsKey(7)) {
                    matches.get(7).add(match);
                } else if (add) {
                    //create the new set with the user
                    Set<User> temp = new HashSet<>();
                    temp.add(match);

                    matches.put(7, temp);
                }

            }
        }
        
        //6
        for (User match : dao.usersThatHaveLanguages(toMatch.getFirst_language(), toMatch.getSecond_language(), toMatch.getThird_language())) {
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
                if (add && matches.containsKey(6)) {
                    matches.get(6).add(match);
                } else if (add) {
                    //create the new set with the user
                    Set<User> temp = new HashSet<>();
                    temp.add(match);

                    matches.put(6, temp);
                }
            }
        }
        
        //5
        for (User match : dao.usersThatHaveLanguages(toMatch.getFirst_language(), toMatch.getSecond_language())) {
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
                if (add && matches.containsKey(5)) {
                    matches.get(5).add(match);
                } else if (add) {
                    //create the new set with the user
                    Set<User> temp = new HashSet<>();
                    temp.add(match);

                    matches.put(5, temp);
                }

            }
        }
        
        //5
        for (User match : dao.usersThatHaveLanguages(toMatch.getFirst_language(), toMatch.getThird_language())) {
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
                if (add && matches.containsKey(5)) {
                    matches.get(5).add(match);
                } else if (add) {
                    //create the new set with the user
                    Set<User> temp = new HashSet<>();
                    temp.add(match);

                    matches.put(5, temp);
                }

            }
        }
        
        //5
        for (User match : dao.usersThatHaveLanguages(toMatch.getSecond_language(), toMatch.getThird_language())) {
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
                if (add && matches.containsKey(5)) {
                    matches.get(5).add(match);
                } else if (add) {
                    //create the new set with the user
                    Set<User> temp = new HashSet<>();
                    temp.add(match);

                    matches.put(5, temp);
                }

            }
        }
        
        //4
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
                if (add && matches.containsKey(5)) {
                    matches.get(5).add(match);
                } else if (add) {
                    //create the new set with the user
                    Set<User> temp = new HashSet<>();
                    temp.add(match);

                    matches.put(5, temp);
                }
            }
        }
        
        //3
        for (User match : dao.getUsers(toMatch.getSecond_language())) {
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
                if (add && matches.containsKey(5)) {
                    matches.get(5).add(match);
                } else if (add) {
                    //create the new set with the user
                    Set<User> temp = new HashSet<>();
                    temp.add(match);

                    matches.put(5, temp);
                }
            }
        }
        
        //2
        for (User match : dao.getUsers(toMatch.getThird_language())) {
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
                if (add && matches.containsKey(5)) {
                    matches.get(5).add(match);
                } else if (add) {
                    //create the new set with the user
                    Set<User> temp = new HashSet<>();
                    temp.add(match);

                    matches.put(5, temp);
                }
            }
        }
        
        //1
        for (User match : dao.usersThatHaveLanguage(toMatch.getFirst_language())) {
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
                if (add && matches.containsKey(5)) {
                    matches.get(5).add(match);
                } else if (add) {
                    //create the new set with the user
                    Set<User> temp = new HashSet<>();
                    temp.add(match);

                    matches.put(5, temp);
                }
            }
        }
        
        //1
        for (User match : dao.usersThatHaveLanguage(toMatch.getSecond_language())) {
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
                if (add && matches.containsKey(5)) {
                    matches.get(5).add(match);
                } else if (add) {
                    //create the new set with the user
                    Set<User> temp = new HashSet<>();
                    temp.add(match);

                    matches.put(5, temp);
                }
            }
        }
        
        //1
        for (User match : dao.usersThatHaveLanguage(toMatch.getThird_language())) {
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
                if (add && matches.containsKey(5)) {
                    matches.get(5).add(match);
                } else if (add) {
                    //create the new set with the user
                    Set<User> temp = new HashSet<>();
                    temp.add(match);

                    matches.put(5, temp);
                }
            }
        }
    }
}
