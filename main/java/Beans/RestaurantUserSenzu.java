/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Exceptions.DoesNotExistException;
import Exceptions.AlreadyExistsException;
import Models.Restaurants;
import Models.Users;
import java.util.ArrayList;
import java.util.Arrays;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 *
 * @author chris
 *
 */
@Named(value = "restaurantUserSenzu")
@SessionScoped
public class RestaurantUserSenzu implements Serializable {
    
    @PersistenceContext(unitName = "t_soar_PU")
    private EntityManager em;

    private String username = "";
    private String password = "";
    private String email = "";
    private String restaurantName = "";
    private String restaurantOwner = "";
    private String address = "";
    private String openingHours = "";
    private String price = "";
    private String cookingtype = "";
    private String contact = "";
    private String dish = "";
    private String menu;
    private String specialdiet;
    private ArrayList<String> specialdietlist;
    @Transactional
    public String createARestaurantUser() {
        try {
            if (!emailExists() && !usernameExists()) {
                Users newUser = new Users();
                Restaurants newRestaurant = new Restaurants();
                newUser.setUsername(username);
                newUser.setPassword(password.hashCode());
                newUser.setEmail(email);
                newUser.setRestaurantName(restaurantName);
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
                em.persist(newUser);
                em.persist(newRestaurant);
            } //add to mock databese if User created
            return "/MainPage/LoginPageRestaurant.xhtml?faces-redirect=true";
        } catch (AlreadyExistsException | DoesNotExistException ex) {
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

    private boolean emailExists() throws AlreadyExistsException {
        Query query = em.createNamedQuery("Users.findByEmail");
        List<Users> users = query.setParameter("email", email).getResultList();
        return users.size() > 0;
    }

    private boolean usernameExists() throws DoesNotExistException {
        Query query = em.createNamedQuery("Users.findByUsername");
        List<Users> users = query.setParameter("username", username).getResultList();
        return users.size() > 0;
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
        //setMenu(new ArrayList<String>(Arrays.asList(dish.split(", "))));
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public void setSpecialdiet(String specialdiet) {
        this.specialdiet = specialdiet;
    }
    
    public void setSpecialdietlist(ArrayList<String> specialdietlist) {
        this.specialdietlist = specialdietlist;
        this.specialdiet = specialdietlist.toString().substring(1,specialdietlist.toString().length()-1);
        
    }

}
