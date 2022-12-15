/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import static Beans.RestaurantSenzu.findByRestaurantName;
import Exceptions.DoesNotExistException;
import Database.MockDatabase;
import Models.Restaurants;
import java.util.ArrayList;
import Exceptions.NoRestaurantCorrespondingException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author chris
 */
@Named(value = "restaurantSenzu")
@SessionScoped
public class RestaurantSenzu implements Serializable {

    private String restaurantName = "";
    private String restaurantOwner = "";
    private String address = "";
    private String openingHours = "";
    private String price = "";
    private String cookingtype = "";
    private String contact = "";
    private String menu = "";
    private String specialdiet = "";
    private Integer ratings;
    private ArrayList<Integer> ratinglist;
    

    public static Restaurants findByRestaurantName(String restaurantName) throws DoesNotExistException {
        for (Restaurants restaurant : MockDatabase.getInstance().getRestaurant()) {
            if (restaurant.getRestaurantName().equals(restaurantName)) {
                return restaurant;
            }    
        }
        throw new DoesNotExistException("The restaurant " + restaurantName + " does not exist.");
        
     }
    
    
    public ArrayList<Restaurants> getRestaurant() {
        return MockDatabase.getInstance().getRestaurant();
       
    }
    
    public String getRestaurantName() {
        return restaurantName;
        
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    
    public void setRatings(Integer ratings)  {
        this.ratings=ratings;
    }
    
    public void setRatinglist (ArrayList<Integer> ratinglist){
        this.ratinglist=ratinglist;
    }
    

    public String getRestaurantOwnerName() {
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
    
    public String getMenu() {
        return menu;
    }
    
    public String getSpecialdiet() {
        return specialdiet;
    }
    
    public Integer getRatings() {
        return ratings;
    }
    
    public ArrayList<Integer> getRatinglist() {
        return ratinglist;
    }
}
