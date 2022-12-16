/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Exceptions.DoesNotExistException;
import Models.Restaurants;
import Models.Users;
import Beans.RestaurantSenzu;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import Client.PersistenceClient;

/**
 *
 * @author chris
 **/
@Named(value = "loginSenzu")
@SessionScoped
public class LoginSenzu implements Serializable {

    private String username = "";
    private String password = "";
    private String restaurantName = "";
    private Users currentUser;
    private Restaurants currentRestaurant;

    public String userLogsIn() {
        try {
            if (username.length() == 0){
                return "/MainPage/LoginPage.xhtml?faces-redirect=true";
            }
            else{
                Users u = PersistenceClient.getInstance().checkPassword(username, password.hashCode());
                if (u != null) {
                    currentUser = u;
                    if (currentUser.getFirstName().length() < 1){
                        return "/MainPage/LoginPage.xhtml?faces-redirect=true"; 
                    }
                    else{
                        return "/UserPage/UserMainPage.xhtml?faces-redirect=true";
                    }   
                }  
            }
                       
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        return "/MainPage/LoginPage.xhtml?faces-redirect=true";
    }
    public void RestaurantLogged(){
        try {
            Restaurants r = PersistenceClient.getInstance().checkExistingRestaurant(currentUser.getRestaurantName());
            if (r != null){
                currentRestaurant = r;
            }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public String RestaurantLogIn() {
        try {
            if (username.length() == 0){
                return "/MainPage/LoginPageRestaurant.xhtml?faces-redirect=true";
            }
            else{
                Users u = PersistenceClient.getInstance().checkPassword(username, password.hashCode());
                if (u != null) {
                    currentUser = u;
                    if (currentUser.getRestaurantName() == ""){
                        return "/LoginPageRestaurant.xhtml?faces-redirect=true";
                    }
                    else{
                        RestaurantLogged();
                        return "/RestaurantPage/RestaurantMainPage.xhtml?faces-redirect=true";
                    }

                }
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

    public Users getCurrentUser() {
        return currentUser;
    }
    
    public Restaurants getCurrentRestaurant(){
        return currentRestaurant;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    
     public String getRestaurantName() {
        return restaurantName;
    }
    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }
    
    public void setCurrentRestaurant(Restaurants currentRestaurant) {
        this.currentRestaurant = currentRestaurant;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setRestaurantName(String restaurantName){
        this.restaurantName = restaurantName;
    }
    
}