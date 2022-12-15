/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Database.MockDatabase;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 *
 * @author chris
 *
 */
@XmlRootElement
public class Users {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String restaurantName;
    private Integer password;
//    private CommentsRatings commentsratings;

    //constructor
    public Users(){
    }

    //Accessor
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
    
    public Integer getPassword() {
        return password;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setPassword(Integer password) {
        this.password = password.hashCode();
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isPasswordCorrect(String password) {
        return password.hashCode() == this.password;
    }

    //added on november 8th
    @Override
    public String toString() {
        String S = "";
        if (restaurantName == null) {
            S = "Username: " + this.username
                    + "\nFirst name: " + this.firstName
                    + "\nLast name: " + this.lastName
                    + "\nEmail: " + this.email;

        } else {
            S = "Username: " + this.username
                    + "\nRestaurant Name: " + this.restaurantName
                    + "\nEmail: " + this.email;

            for (int r = 0; r < MockDatabase.restaurants.size(); r++) {
                if (restaurantName == MockDatabase.restaurants.get(r).getRestaurantName()) {
                    S = S + MockDatabase.restaurants.get(r).toString();
                }
            }
        }
        return S;
    }
}
