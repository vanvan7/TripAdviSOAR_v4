/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

//import static Beans.RestaurantSenzu.findByRestaurantName;
import Client.PersistenceClient;
import Exceptions.DoesNotExistException;
import Models.Restaurants;
import java.util.ArrayList;
import Exceptions.NoRestaurantCorrespondingException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author chris
 */
@Named(value = "restaurantSenzu")
@SessionScoped
public class RestaurantSenzu implements Serializable {

    private String username = "";
    private String password = "";
    private String restaurantName = "";
    private String email = "";
    private String restaurantOwner = "";
    private String address = "";
    private String openingHours = "";
    private String price = "";
    private String cookingtype = "";
    private String contact = "";
    private String menu = "";
    private String specialdiet = "";
    private String rating;
    private String ratingSubmit;
    private Restaurants currentRestaurant;
    

    public void SearchRestaurant(){
        try {
            Restaurants r = PersistenceClient.getInstance().checkExistingRestaurant(restaurantName);
            if (r != null){
                currentRestaurant = r;
            }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void addRating(){
        if (currentRestaurant.getRating() == null || currentRestaurant.getRating() == ""){
            currentRestaurant.setRating(ratingSubmit);
        }
        else{
            currentRestaurant.setRating(currentRestaurant.getRating()+", "+ratingSubmit);
        }
        PersistenceClient.getInstance().updateRestaurant(currentRestaurant);
    }
    
    
    
//    public static Restaurants findByRestaurantName(String restaurantName) throws DoesNotExistException {
//        for (Restaurants restaurant : MockDatabase.getInstance().getRestaurant()) {
//            if (restaurant.getRestaurantName().equals(restaurantName)) {
//                return restaurant;
//            }    
//        }
//        throw new DoesNotExistException("The restaurant " + restaurantName + " does not exist.");
//        
//     }
//    
//    
//    public ArrayList<Restaurants> getRestaurant() {
//        return MockDatabase.getInstance().getRestaurant();
//       
//    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRestaurantName() {
        return restaurantName;
        
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    
    public String getRatingSubmit() {
        return ratingSubmit;
    }

    public void setRatingSubmit(String ratingSubmit) {
        this.ratingSubmit = ratingSubmit;
    }

    public String getRestaurantOwner() {
        return restaurantOwner;
    }
    
     public void setRestaurantOwner(String restaurantOwner) {
        this.restaurantOwner = restaurantOwner;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }
    
    
    public String getPrice() {
        return price;
    }
    
    public void setPrice (String price){
        this.price = price;
    }
    
    public String getCookingtype() {
        return cookingtype;
    }
    
    public void setCookingtype(String cookingtype) {
        this.cookingtype = cookingtype;
    }
    
    public String getContact() {
        return contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
        
    }
    
    public String getMenu() {
        return menu;
    }
    
    public void setMenu(String menu) {
        this.menu = menu;
    }
    
    public String getSpecialdiet() {
        return specialdiet;
    }
    
    public void setSpecialdiet(String specialdiet) {
        this.specialdiet = specialdiet;
    }
    
    public Restaurants getCurrentRestaurant(){
        return currentRestaurant;
    }
    
    public void setCurrentRestaurant(Restaurants currentRestaurant) {
        this.currentRestaurant = currentRestaurant;
    }
    
    public List<Restaurants> getRestaurants(){
        return PersistenceClient.getInstance().getAllRestaurants();
    }
}
