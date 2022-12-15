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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chris
 **/
@XmlRootElement
public class Restaurants {
    
    private String username;
    private int password;
    private String email;
    private String restaurantName;
    private String restaurantOwner;
    private String address;
    private String openingHours;
    private String price;
    private String firstName;
    private String lastName;
    private String cookingtype;
    private String contact;
    private String dish;
    private String menu;
    private String specialdiet;
    private String ratings;
    private ArrayList<String> ratinglist;
    private ArrayList<String> specialdietlist;

    //constructor 
    public Restaurants() {
    }

    public String getRestaurantName() {
        return restaurantName;
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
    
    public String getMenu() {
        return menu;
    }
    
    public String getSpecialdiet() {
        return specialdiet;
    }

    public ArrayList<String> getSpecialdietlist() {
       return specialdietlist;
    }
    
//    public Integer getRatings() {
//        return ratings;
//    }
//    
//    public ArrayList<Integer> getRatinglist() {
//        return ratinglist;
//    }

    public String getUsername() {
        return username;
    }

    public Integer getPassword() {
        return password;
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

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setRestaurantOwner(String restaurantOwner) {
        this.restaurantOwner = restaurantOwner;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(Integer password) {
        this.password = password.hashCode();
    }

//    public void setRatings(Integer ratings)  {
//     this.ratings = ratings;
//     this.ratinglist.add(ratings);
//    }
//    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }
    
//    public void setRatinglist (ArrayList<Integer> ratinglist){
//        this.ratinglist=ratinglist;
//    }
    
    public void setDish(String dish) {
        this.dish = dish;
    }
    
    public void setMenu(String menu) {
        this.menu = menu;
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

    public void setSpecialdiet(String specialdiet) {
        this.specialdiet = specialdiet;
    }
    
    public void setSpecialdietlist(ArrayList<String> specialdietlist) {
        this.specialdietlist = specialdietlist;
        this.specialdiet = specialdietlist.toString().substring(1,specialdietlist.toString().length()-1);
    
    }
}

//    @Override
//    public boolean equals(Object obj) {
//        return ((Users) obj).getUsername().equals(this.username);
//    }

//    @Override
//    public String toString() {
//        return "Name: " + this.restaurantName
//                + "\nContact: " + this.contact
//                + "\nEmail: " + this.email
//                + "\nAddress: " + this.address
//                + "\nCooking Type: " + this.cookingtype
//                + "\nOwner: " + this.restaurantOwner
//                + "\nDate Time: " + this.openingHours
//                + "\nPrice: " + this.price
//                + "\nMenu: " + menu + '}'
//                + "\nSpecial Diet: " + Arrays.toString(specialdiet.toArray()) + '}'
//                + "\nRatings: " + ratinglist + '}'
//                + "\n";
//    }


