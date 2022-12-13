/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import static Beans.RestaurantSenzu.findByRestaurantName;
import Exceptions.DoesNotExistException;
import Database.MockDatabase;
import Models.Restaurant;
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

    private String restaurantname = "";
    private String owner = "";
    private String address = "";
    private String datetime = "";
    private String price = "";
    private String cookingtype = "";
    private String contact = "";
    private String menu = "";
    private String specialdiet = "";
    private Integer ratings;
    private ArrayList<Integer> ratinglist;
    

    public static Restaurant findByRestaurantName(String restaurantname) throws DoesNotExistException {
        for (Restaurant restaurant : MockDatabase.getInstance().getRestaurant()) {
            if (restaurant.getRestaurantname().equals(restaurantname)) {
                return restaurant;
            }    
        }
        throw new DoesNotExistException("The restaurant " + restaurantname + " does not exist.");
        
     }
    
    
    public ArrayList<Restaurant> getRestaurant() {
        return MockDatabase.getInstance().getRestaurant();
       
    }
    
    public String getRestaurantname() {
        return restaurantname;
        
    }

    public void setRestaurantname(String restaurantname) {
        this.restaurantname = restaurantname;
    }
    
    public void setRatings(Integer ratings)  {
        this.ratings=ratings;
    }
    
    public void setRatinglist (ArrayList<Integer> ratinglist){
        this.ratinglist=ratinglist;
    }
    

    public String getOwnerName() {
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
