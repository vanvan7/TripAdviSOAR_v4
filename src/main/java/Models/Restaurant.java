/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author chris
 */
public class Restaurant {
    
    private String username;
    private int password;
    private String email;
    private String restaurantname;
    private String owner;
    private String address;
    private String datetime;
    private String price;
    private String cookingtype;
    private String contact;
    private String dish;
    private ArrayList<String> menu;
    private ArrayList<String> specialdiet; 
    public Integer ratings;
    private ArrayList<Integer> ratinglist;
//    static ArrayList<String> commentsratings;
    
    
    //constructor
    public Restaurant(String username, String password, String email, 
                      String restaurantname, String owner, 
                      String address, String datetime, String price, 
                      String cookingtype, String contact,ArrayList<String> menulist, 
                      ArrayList<String> specialdietlist) {
                    
        this.username = username;
        this.password = password.hashCode();
        this.email = email;
        this.restaurantname= restaurantname;
        this.owner = owner;
        this.address = address;
        this.datetime = datetime; // different?
        this.price = price;
        this.cookingtype = cookingtype;
        this.contact = contact;
        this.dish = dish;
        menu = new ArrayList<>(); 
        menu.addAll(menulist); 
        specialdiet = new ArrayList<>();  
        specialdiet.addAll(specialdietlist);
        this.ratings = ratings;
        this.ratinglist = new ArrayList<>();
        
    }

    public String getRestaurantname() {
        return restaurantname;
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
    
    public ArrayList<String> getMenu() {
        return menu;
    }
    
    public ArrayList<String> getSpecialdiet() {
        return specialdiet;
    }
    
    public Integer getRatings() {
        return ratings;
    }
    
    public ArrayList<Integer> getRatinglist() {
        return ratinglist;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
    
    public String getDish() {
        return dish;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRestaurantname(String restaurantname) {
        this.restaurantname = restaurantname;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password.hashCode();
    }

    public void setRatings(Integer ratings)  {
     this.ratings = ratings;
     this.ratinglist.add(ratings);
    }

    public void setRatinglist (ArrayList<Integer> ratinglist){
        this.ratinglist=ratinglist;
    }
    
    public void setDish(String dish) {
        this.dish = dish;
    }
    
    public boolean isPasswordCorrect(String password) {
        return password.hashCode() == this.password;
    }

    @Override
    public boolean equals(Object obj) {
        return ((User) obj).getUsername().equals(this.username);
    }

    @Override
    public String toString() {
        return "Name: " + this.restaurantname
                + "\nContact: " + this.contact
                + "\nEmail: " + this.email
                + "\nAddress: " + this.address
                + "\nCooking Type: " + this.cookingtype
                + "\nOwner: " + this.owner
                + "\nDate Time: " + this.datetime
                + "\nPrice: " + this.price
                + "\nMenu: " + menu + '}'
                + "\nSpecial Diet: " + Arrays.toString(specialdiet.toArray()) + '}'
                + "\nRatings: " + ratinglist + '}'
                + "\n";
    }
}

