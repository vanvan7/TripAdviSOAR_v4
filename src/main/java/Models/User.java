/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import Database.MockDatabase;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class User {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String restaurantname; 
    private int password;
//    private CommentsRatings commentsratings;
    
    //constructor
   
    public User (String username, String firstname, String lastname, String email, String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password.hashCode();
//        this.commentsratings = new CommentsRatings();
   
    }
    
    //2nd constructor
        
    public User (String username, String restaurantname, String email, String password){
        this.username = username;
        this.restaurantname = restaurantname;
        this.email = email;
        this.password = password.hashCode();

        
    }
    

    

    //Accessor
    public String getFirstname(){
        return firstname;
    }
    
    public String getLastname(){
        return lastname;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getRestaurantname(){
        return restaurantname;
    }
    
    
//    public String getContact(){
//        return Restaurant.getContact(); // don't know how to correct that
//    }
    
//    public  CommentsRatings getCommentsRatings (){
//        return commentsratings;
//    }
   
    // mutators
    
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    
    public void setLastname (String lastname){
        this.lastname = lastname;
    }
    
    public void setUsername (String username){
        this.username = username;
    }
    
    public void setEmail (String email){
        this.email = email;
    }
    
    public void setRestaurantname  (String restaurantname){
        this.restaurantname = restaurantname;
    }
    
       public void setPassword (String password){
        this.password = password.hashCode(); 
    }
    
     
//    public void RateARestaurant (CommentsRatings commentsratings){ //error incompatible type
//        commentsratings.addRating(commentsratings);
//    }
//    
//    public void CommentARestaurant (CommentsRatings commentsratings){//error incompatible type
//        commentsratings.addComment(commentsratings);
//    }
//
//    public void CommentOtherComment (ArrayList<String> comments){
//        this.comments = restaurants.comments; //link ?
//    }
////    
    
    public boolean isPasswordCorrect(String password) {
        return password.hashCode() == this.password;
    }
    
    //added on november 8th
   @Override
    public String toString() {
        String S="";
        if (restaurantname==null){
            S=  "Username: " + this.username
                    + "\nFirst name: " + this.firstname
                    + "\nLast name: " + this.lastname
                    + "\nEmail: " + this.email;  
            
          } else{
            S= "Username: " + this.username
                    + "\nRestaurant Name: " + this.restaurantname
                    + "\nEmail: " + this.email;   
            
            
            for (int r=0; r<MockDatabase.restaurants.size();r++){
                       if (restaurantname == MockDatabase.restaurants.get(r).getRestaurantname()){
                           S = S + MockDatabase.restaurants.get(r).toString();
                       }                      
            }              
        }
        return S; 
    }
}
