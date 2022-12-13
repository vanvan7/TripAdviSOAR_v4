/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Exceptions.DoesNotExistException;
import Models.Restaurant;
import Models.User;
import static Beans.UserSenzu.findByUsername;
import static Beans.RestaurantSenzu.findByRestaurantName;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author chris
 **/
@Named(value = "loginSenzu")
@SessionScoped
public class LoginSenzu implements Serializable {

    private String username = "";
    private String password = "";
    private String restaurantname = "";
    private User currentUser;
    private Restaurant currentRestaurant;

    public String userLogsIn() {
        try {
            User user = findByUsername(username);
            if (user != null && user.isPasswordCorrect(password)) {
                currentUser = user;
                return "/UserPage/UserMainPage.xhtml?faces-redirect=true";
            }             
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        return "/MainPage/LoginPage.xhtml?faces-redirect=true";
    }
    public void restaurantLogsIn(){
        try {
            Restaurant restaurant = findByRestaurantName (restaurantname);
            if (restaurant != null){
                currentRestaurant = restaurant;
                System.out.println(this.getCurrentRestaurant().toString());
        
            }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public String RestaurantLogIn() {
        try {
            User user = findByUsername(username);
            if (user != null && user.isPasswordCorrect(password)) {
                currentUser = user;
                currentRestaurant = RestaurantSenzu.findByRestaurantName(user.getRestaurantname());
                return "/RestaurantPage/RestaurantMainPage.xhtml?faces-redirect=true";
            }          
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        return "LoginPageRestaurant.xhtml?faces-redirect=true";
    }

    public String userLogsout() {
        currentUser = null;
        return "/MainPage/MainPage.xhtml?faces-redirect=true";
    }

    public User getCurrentUser() {
        return currentUser;
    }
    
    public Restaurant getCurrentRestaurant(){
        return currentRestaurant;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    
     public String getRestaurantname() {
        return restaurantname;
    }
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    public void setCurrentRestaurant(Restaurant currentRestaurant) {
        this.currentRestaurant = currentRestaurant;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setRestaurantname(String restaurantname){
        this.restaurantname = restaurantname;
    }
    
}