/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.util.Arrays;
import java.util.ArrayList;
import Models.User;
import Models.Restaurant;
//import Models.CommentsRatings;
/**
 *
 * @author chris
 */
public class MockDatabase {
    private static MockDatabase instance;
    
    private ArrayList<User> users;
    public static ArrayList<Restaurant> restaurants;


    private MockDatabase() {
        users = new ArrayList<User>();
        
        users.add(new User("lisa", "lisa", "simpson", "lisa@simpson.com", "1234"));
        users.add(new User("homer", "homer", "simpson", "homer@simpson.com", "1234"));
        users.add(new User("marge", "marge", "simpson", "marge@simpson.com", "1234"));
        users.add(new User("bart", "bart", "simpson", "bart@simpson.com", "1234"));
        users.add(new User("maggie", "maggie", "simpson", "maggie@simpson.com", "1234"));
        //with the second construction, we can also create an instance for a restaurant
        users.add(new User("waffle1", "The Real Waffle", "waffle@gmail.com", "1234")); //added recently
        users.add(new User("pizza1", "Pomodoro", "pizza@gmail.com", "1234"));
        users.add(new User("meat1", "Hunter Place", "meat@gmail.com", "1234"));
        users.add(new User("japanese1", "Oishii Udon", "japanese@gmail.com", "1234"));
        users.add(new User("korean1", "The spicy House", "korean@gmail.com", "1234"));
           
    
     //restaurants or restaurant        
        restaurants = new ArrayList<Restaurant>(); {
        restaurants.add(new Restaurant("waffle1", "1234", "waffle@gmail.com", "The Real Waffle", "Thoralf", "High Avenue 1",
            "11:00-24:00", "$", "Traditional", "0794731452", 
            new ArrayList<>(Arrays.asList("The Authenthic", "Cheesy Lover", "Salted Butter Caramel")),
            new ArrayList<>(Arrays.asList("Vegetarian", "Lactose free"))));
        
        restaurants.add(new Restaurant("meat1", "1234", "meat@gmail.com", "Hunter Place", "pascal", "Highway Road 10",
            "18:00-23:00", "$$$", "French", "0798931452",
            new ArrayList<>(Arrays.asList("Steak 300grs", "The raw Road", "Creme Brulee")),
            new ArrayList<>(Arrays.asList("Lactose free", "Gluten free"))));
        
        restaurants.add (new Restaurant("pizza1", "1234", "pizza@gmail.com", "Pomodoro", "toni", "Beautiful Road 35",
            "12:00-22:00", "$", "Italian", "0761457452",
            new ArrayList<>(Arrays.asList("House pizza", "Melanzane", "Tiramisu")),       
            new ArrayList<>(Arrays.asList("Vegetarian"))));
        
        restaurants.add (new Restaurant("japanese1", "1234", "japanese@gmail.com", "Oishii Udon", "yoshida", "Michel Road 50",
            "17:00-21:30", "$", "Japanese", "0761237845",
            new ArrayList<>(Arrays.asList("Kitsune Udon", "Shouyu Udon", "Kare Udon")),  
            new ArrayList<>(Arrays.asList("None"))));
        
        restaurants.add (new Restaurant("korean1", "1234", "korean@gmail.com", "The spicy House", "seon", "Victoria Road 14",
            "11:00-00:00", "$$", "Korean", "0784521452",
            new ArrayList<>(Arrays.asList("Bibimbap", "Kimchi", "Jjigae")),
            new ArrayList<>(Arrays.asList("Gluten-free","Lactose free"))));
        }
    };
    
//    private static ArrayList<CommentsRatings> commentsratings = new ArrayList<CommentsRatings>() {
//        {
//            add(new CommentsRatings("Very nice restaurant",4));
//        }
//    };
    
    public static MockDatabase getInstance() {
        if (instance == null) {
            instance = new MockDatabase();
        }
        return instance;
    }
    
    public void addAUser (User user){
        users.add(user);
    }
    
    public void addARestaurant (Restaurant restaurant){
        restaurants.add(restaurant);
    }
    //Accessors to use in the views
    public ArrayList<User> getUsers() {
        return users;
    }
//same here, restaurant or restaurents? 
    public  ArrayList<Restaurant> getRestaurant() {
        return restaurants;
    }
    
//    public static ArrayList<CommentsRatings> getCommentsRatings(){
//        return commentsratings;
//    }       
//    
//     //allow to make a comment and rate a restaurant
//    public static void addCommentsRatings(CommentsRatings commentRating){
//        commentsratings.add(commentRating);
//    }

  


}
