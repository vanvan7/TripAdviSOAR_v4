/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Exceptions.DoesNotExistException;
import Database.MockDatabase;
import Exceptions.AlreadyExistsException;
import Models.Restaurant;
import Models.User;
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
    private String restaurantname = "";
    private String owner = "";
    private String address = "";
    private String datetime = "";
    private String price = "";
    private String cookingtype = "";
    private String contact = "";
    private String dish = "";
    //----------------------------------------------------added
    private ArrayList<String> menu;
    private ArrayList<String> specialdiet;
    //-----------------------------------------------------added

    public String createARestaurantUser() {
        try {
            if (!emailExists() && !usernameExists()) {
                MockDatabase.getInstance().addAUser(new User(username, restaurantname, email, password));
                MockDatabase.getInstance().addARestaurant(new Restaurant(username, password, email, restaurantname, owner, address, datetime, price, cookingtype, contact, menu, specialdiet));
            } //add to mock databese if User created
            return "/MainPage/LoginPageRestaurant.xhtml?faces-redirect=true";
        } catch (AlreadyExistsException | DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        // empty values
        this.username = "";
        this.password = "";
        this.email = "";
        this.restaurantname = "";
        this.owner = "";
        this.address = "";
        this.datetime = "";
        this.price = "";
        this.cookingtype = "";
        this.contact = "";
        this.dish = "";
        this.menu = new ArrayList<>();
        this.specialdiet = new ArrayList<>();
        
        return "/MainPage/LoginPageRestaurant.xhtml?faces-redirect=true";
    }

    protected static User findByUsername(String username) throws DoesNotExistException {
        for (User user : MockDatabase.getInstance().getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new DoesNotExistException("The user " + username + " does not exist.");
    }

    protected boolean emailExists() throws AlreadyExistsException {
        for (User user : MockDatabase.getInstance().getUsers()) {
            if (user.getEmail().equals(email)) {
                throw new AlreadyExistsException("The email " + email + " already in use.");
            }
        }
        return false;
    }

    protected boolean usernameExists() throws DoesNotExistException {
        for (User user : MockDatabase.getInstance().getUsers()) {
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

    public String getRestaurantname() {
        return restaurantname;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getOwner() {
        return owner;
    }

    public String getAddress() {
        return address;
    }

    public String getDatetime() {
        return datetime;
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
  

    //public static ArrayList<Menu> getMenu() {
    //    return MockDatabase.getInstance().getMenu();
    //}
    //public static ArrayList<SpecialDiet> getSpecialdiet() {
    //    return MockDatabase.getInstance().getSpecialdiet();
    //}
    //----------------------------------------------added => don't return error on NEtbeans
    public ArrayList<String> getMenu() {
        return menu;
    }

    public ArrayList<String> getSpecialdiet() {
        return specialdiet;
    }

    //---------------------------------------------------added
    //SET
    public void setEmail(String email) {
        this.email = email;
    }

    public void setRestaurantname(String restaurantname) {
        this.restaurantname = restaurantname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
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
        setMenu(new ArrayList<String>(Arrays.asList(dish.split(", "))));
    }

    public void setMenu(ArrayList<String> menu) {
        this.menu = menu;
    }

    public void setSpecialdiet(ArrayList<String> specialdiet) {
        this.specialdiet = specialdiet;
    }

    //-----------------------------------------------------------------------added  
}
