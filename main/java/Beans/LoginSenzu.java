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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author chris
 **/
@Named(value = "loginSenzu")
@SessionScoped
public class LoginSenzu implements Serializable {
    
    @PersistenceContext(unitName = "t_soar_PU")
    private EntityManager em;

    private String username = "";
    private String password = "";
    private String restaurantName = "";
    private Users currentUser;
    private static Restaurants currentRestaurant;
    private String rating = "";

    public String userLogsIn() {
        try {
            Users user = findByUsername();
            if (user != null && user.isPasswordCorrect(password)) {
                currentUser = user;
                if (currentUser.getFirstName().length() < 1){
                    return "/MainPage/LoginPage.xhtml?faces-redirect=true";
                }
                else {
                    return "/UserPage/UserMainPage.xhtml?faces-redirect=true";
                }
            }             
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        return "/MainPage/LoginPage.xhtml?faces-redirect=true";
    }
    protected Users findByUsername() throws DoesNotExistException {
        Query query = em.createNamedQuery("Users.findByUsername", Users.class);
        List<Users> users = query.setParameter("username", username).getResultList();
        if (users.size() > 0) {
            return users.get(0);
        }
        throw new DoesNotExistException("The user " + username + " does not exist.");
    }
    
    public void restaurantLogsIn(){
        try {
            Restaurants restaurant = findByRestaurantName();
            if (restaurant != null){
                currentRestaurant = restaurant;
                //System.out.println(this.getCurrentRestaurant().toString());
        
            }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }

    }
        protected Restaurants findByRestaurantName() throws DoesNotExistException {
        Query query = em.createNamedQuery("Restaurants.findByRestaurantName", Restaurants.class);
        List<Restaurants> restaurants = query.setParameter("restaurantName", restaurantName).getResultList();
        if (restaurants.size() > 0) {
            return restaurants.get(0);
        }
        throw new DoesNotExistException("The user " + restaurantName + " does not exist.");
    }
    
    public String RestaurantLogIn() {
        try {
            Users user = findByUsername();
            if (user != null && user.isPasswordCorrect(password)) {
                currentUser = user;
                restaurantName = currentUser.getRestaurantName();
                currentRestaurant = findByRestaurantName();
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

    public Users getCurrentUser() {
        return currentUser;
    }
    
    public Restaurants getCurrentRestaurant(){
        return currentRestaurant;
    }
    
    public static Restaurants getRestaurantLogged(){
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
     
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
   
    
//    public void update(Restaurants restaurants) {
//        em.merge(restaurants);
//    }
}
    