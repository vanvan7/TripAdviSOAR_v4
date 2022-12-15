/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Client.PersistenceClient;
import Exceptions.DoesNotExistException;
import Database.MockDatabase;
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
                newRestaurant.setUsername(username);
                newRestaurant.setPassword(password.hashCode());
                newRestaurant.setEmail(email);
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newRestaurant.setRestaurantName(restaurantName);
                newRestaurant.setRestaurantOwner(restaurantOwner);
                newRestaurant.setAddress(address);
                newRestaurant.setOpeningHours(openingHours);
                newRestaurant.setPrice(price);
                newRestaurant.setCookingtype(cookingtype);
                newRestaurant.setContact(contact);
                newRestaurant.setMenu(menu);  
                newRestaurant.setSpecialdiet(specialdiet);
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
        return "/MainPage/LoginPageRestaurant.xhtml?faces-redirect=true";
    }

    protected static Users findByUsername(String username) throws DoesNotExistException {
        for (Users user : MockDatabase.getInstance().getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new DoesNotExistException("The user " + username + " does not exist.");
    }

    protected boolean emailExists() throws AlreadyExistsException {
        for (Users user : MockDatabase.getInstance().getUsers()) {
            if (user.getEmail().equals(email)) {
                throw new AlreadyExistsException("The email " + email + " already in use.");
            }
        }
        return false;
    }

    protected boolean usernameExists() throws DoesNotExistException {
        for (Users user : MockDatabase.getInstance().getUsers()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
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

    public String getopeningHours() {
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

    public void setOwner(String restaurantOwner) {
        this.restaurantOwner = restaurantOwner;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setopeningHours(String openingHours) {
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

    public void setSpecialdietlist(ArrayList<String> specialdiet) {
        this.specialdietlist = specialdietlist;
    }
    
     public void setSpecialdiet(String specialdiet) {
        this.specialdiet = specialdiet;
    }
    

    //-----------------------------------------------------------------------added  
}
