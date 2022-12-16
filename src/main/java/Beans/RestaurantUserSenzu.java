/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Client.PersistenceClient;
import Exceptions.DoesNotExistException;
import Exceptions.AlreadyExistsException;
import Models.Restaurants;
import Models.Users;
import java.util.ArrayList;
import java.util.Arrays;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author chris
 *
 */
@Named(value = "restaurantUserSenzu")
@SessionScoped
public class RestaurantUserSenzu implements Serializable {

    private String username = "";
    private String password = "";
    private String email = "";
    private String restaurantName = "";
    private String restaurantOwner = "";
    private String address = "";
    private String openingHours = "";
    private String price = "";
    private String firstName = "";
    private String lastName = "";
    private String cookingtype = "";
    private String contact = "";
    private String dish = "";
    private String menu;
    private String specialdiet;
    private String rating;
    private ArrayList<String> specialdietlist;
    //-----------------------------------------------------added

    public String createARestaurantUser() {
        try {
            boolean a = !PersistenceClient.getInstance().emailExists(email);
            boolean b = PersistenceClient.getInstance().getUserByName(username) == null;
            if (a && b) {
                Users newUser = new Users();
                Restaurants newRestaurant = new Restaurants();
                newUser.setUsername(username);
                newUser.setPassword(password.hashCode());
                newUser.setEmail(email);
                newUser.setRestaurantName(restaurantName);
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newRestaurant.setUsername(username);
                newRestaurant.setPassword(password.hashCode());
                newRestaurant.setEmail(email);
                newRestaurant.setRestaurantName(restaurantName);
                newRestaurant.setRestaurantOwner(restaurantOwner);
                newRestaurant.setAddress(address);
                newRestaurant.setOpeningHours(openingHours);
                newRestaurant.setPrice(price);
                newRestaurant.setCookingtype(cookingtype);
                newRestaurant.setContact(contact);
                newRestaurant.setMenu(menu);  
                newRestaurant.setSpecialdiet(specialdiet);
                newRestaurant.setRating("");
                PersistenceClient.getInstance().createUser(newUser);
                PersistenceClient.getInstance().createRestaurant(newRestaurant);
                
                } //add to mock databese if Users created
            return "/MainPage/LoginPageRestaurant.xhtml?faces-redirect=true";
        } catch (AlreadyExistsException ex) {
            System.out.println(ex.getMessage());
        }
        // empty values
        this.username = "";
        this.password = "";
        this.email = "";
        this.restaurantName = "";
        this.restaurantOwner = "";
        this.address = "";
        this.openingHours = "";
        this.price = "";
        this.cookingtype = "";
        this.contact = "";
        this.dish = "";
        this.menu = "";
        this.specialdiet = "";
        this.rating = "";
        return "/MainPage/LoginPageRestaurant.xhtml?faces-redirect=true";
    }

    //GET
    public String getEmail() {
        return email;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getRestaurantOwner() {
        return restaurantOwner;
    }

    public String getAddress() {
        return address;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public String getPrice() {
        return price;
    }

    public String getCookingtype() {
        return cookingtype;
    }

    public String getContact() {
        return contact;
    }
    
    public String getDish() {
        return dish;
    }
  
    public String getMenu() {
        return menu;
    }

    public String getSpecialdiet() {
        return specialdiet;
    }

    public ArrayList<String> getSpecialdietlist() {
        return specialdietlist;
    }
    //---------------------------------------------------added
    //SET
    public void setEmail(String email) {
        this.email = email;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRestaurantOwner(String restaurantOwner) {
        this.restaurantOwner = restaurantOwner;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCookingtype(String cookingtype) {
        this.cookingtype = cookingtype;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public void setDish(String dish) {
        this.dish = dish;
//        setMenu(new ArrayList<String>(Arrays.asList(dish.split(", "))));
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public void setSpecialdietlist(ArrayList<String> specialdietlist) {
        this.specialdietlist = specialdietlist;
        this.specialdiet = specialdietlist.toString().substring(1,specialdietlist.toString().length()-1);
        
    }
    
     public void setSpecialdiet(String specialdiet) {
        this.specialdiet = specialdiet;
    }
    

    //-----------------------------------------------------------------------added  
}
