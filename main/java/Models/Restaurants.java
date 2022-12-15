/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author srivathshanparamalingam
 */
@Entity
@Table(name = "restaurants")
@NamedQueries({
    @NamedQuery(name = "Restaurants.findAll", query = "SELECT r FROM Restaurants r"),
    @NamedQuery(name = "Restaurants.findByRestaurantId", query = "SELECT r FROM Restaurants r WHERE r.restaurantId = :restaurantId"),
    @NamedQuery(name = "Restaurants.findByUsername", query = "SELECT r FROM Restaurants r WHERE r.username = :username"),
    @NamedQuery(name = "Restaurants.findByRestaurantName", query = "SELECT r FROM Restaurants r WHERE r.restaurantName = :restaurantName"),
    @NamedQuery(name = "Restaurants.findByEmail", query = "SELECT r FROM Restaurants r WHERE r.email = :email"),
    @NamedQuery(name = "Restaurants.findByRestaurantOwner", query = "SELECT r FROM Restaurants r WHERE r.restaurantOwner = :restaurantOwner"),
    @NamedQuery(name = "Restaurants.findByAddress", query = "SELECT r FROM Restaurants r WHERE r.address = :address"),
    @NamedQuery(name = "Restaurants.findByOpeningHours", query = "SELECT r FROM Restaurants r WHERE r.openingHours = :openingHours"),
    @NamedQuery(name = "Restaurants.findByPrice", query = "SELECT r FROM Restaurants r WHERE r.price = :price"),
    @NamedQuery(name = "Restaurants.findByCookingtype", query = "SELECT r FROM Restaurants r WHERE r.cookingtype = :cookingtype"),
    @NamedQuery(name = "Restaurants.findByContact", query = "SELECT r FROM Restaurants r WHERE r.contact = :contact"),
    @NamedQuery(name = "Restaurants.findByMenu", query = "SELECT r FROM Restaurants r WHERE r.menu = :menu"),
    @NamedQuery(name = "Restaurants.findBySpecialdiet", query = "SELECT r FROM Restaurants r WHERE r.specialdiet = :specialdiet"),
    @NamedQuery(name = "Restaurants.findByRating", query = "SELECT r FROM Restaurants r WHERE r.rating = :rating"),
    @NamedQuery(name = "Restaurants.findByPassword", query = "SELECT r FROM Restaurants r WHERE r.password = :password")})
@XmlRootElement
public class Restaurants implements Serializable {

    @Size(max = 50)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 50)
    @Column(name = "RESTAURANT_NAME")
    private String restaurantName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 50)
    @Column(name = "RESTAURANT_OWNER")
    private String restaurantOwner;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 50)
    @Column(name = "OPENING_HOURS")
    private String openingHours;
    @Size(max = 255)
    @Column(name = "PRICE")
    private String price;
    @Size(max = 50)
    @Column(name = "COOKINGTYPE")
    private String cookingtype;
    @Size(max = 50)
    @Column(name = "CONTACT")
    private String contact;
    @Size(max = 255)
    @Column(name = "MENU")
    private String menu;
    @Size(max = 255)
    @Column(name = "SPECIALDIET")
    private String specialdiet;
    @Size(max = 255)
    @Column(name = "RATING")
    private String rating;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RESTAURANT_ID")
    private Integer restaurantId;
    @Column(name = "PASSWORD")
    private Integer password;
    @ManyToMany(mappedBy = "restaurantsList")
    private List<Users> usersList;

    public Restaurants() {
    }

    public Restaurants(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }


    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }


    public String getRestaurantOwner() {
        return restaurantOwner;
    }

    public void setRestaurantOwner(String restaurantOwner) {
        this.restaurantOwner = restaurantOwner;
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

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    @XmlTransient
    public List<Users> getUsersList() {
        return usersList;
    }

     public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (restaurantId != null ? restaurantId.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param object
     * @return
     */
    
    @Override
    public boolean equals(Object obj) {
        return ((Restaurants) obj).getRestaurantName().equals(this.restaurantName);
    }

    @Override
    public String toString() {
        return "Beans.Restaurants[ restaurantId=" + restaurantId + " ]";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    
}