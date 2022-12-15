package Client;

import Exceptions.AlreadyExistsException;
import Exceptions.DoesNotExistException;
import Models.Restaurants;
import Models.Users;
import java.util.List;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Users
 */
public class PersistenceClient {
    private static final String RESTAURANT_URL = "http://localhost:8080/TripAdviSOARService_v4/resources/models.restaurants";
    private static final String USERS_URL = "http://localhost:8080/TripAdviSOARService_v4/resources/models.users";

    private static Client client;
    private static WebTarget target;
    private static PersistenceClient instance;
//    private static PersistenceRestaurant instance;
    
    private PersistenceClient() {
        PersistenceClient.client = ClientBuilder.newClient();
    }
   
    
//    private PersistenceRestaurantClient(){
//        PersistenceRestaurantClient.client = ClientBuilder.newRestaurantClient();
//    }

    public static PersistenceClient getInstance() {
        if (instance == null) {
            instance = new PersistenceClient();
        }
        return instance;
    }
    
    

    public Users checkPassword(String username, int password) throws DoesNotExistException {
        Users u = getUserByName(username);
        if (u.getUsername().equals(username) & u.getPassword() == password) {
            return u;
        }
        throw new DoesNotExistException("User " + username + " does not exist.");
    }

    public boolean emailExists(String email) throws AlreadyExistsException {
        return client.target(USERS_URL + "/emailExists/" + email).request().get().readEntity(Boolean.class);
    }

    public void createUser(Users user) {
        client.target(USERS_URL + "/create").request().post(Entity.entity(user, "application/xml"));
    }
    
    public void createRestaurantUser(Users restaurant) {
        client.target(USERS_URL + "/create").request().post(Entity.entity(restaurant, "application/xml"));
    }
    

    public void updateUser(Users user) {
        client.target(USERS_URL + "/edit/" + user.getUserId()).request().put(Entity.entity(user, "application/xml"));
    }

    public void removeUser(int id) {
        client.target(USERS_URL + "/remove/" + id).request().get().readEntity(String.class);
    }

    public Users getUserById(int id) {
        return parseUser(client.target(USERS_URL + "/find/" + id).request().get().readEntity(String.class));
    }
    
    public Users getUserByName(String username) {
        Users u = parseUser(client.target(USERS_URL + "/findByName/" + username).request().get(String.class));
        return u;
    }
    
    public List<Users> getAllUser() {
        return parseUserList(client.target(USERS_URL).request().get(String.class));
    }

    
    private List<Users> parseUserList(String xml) {
        List<Users> userList = new ArrayList<>();
        NodeList list = parseDocument(xml).getElementsByTagName("users");
        for (int i = 0; i < list.getLength(); i++) {
            Element e = (Element) list.item(i);

            Users user = new Users();
            user.setEmail(e.getElementsByTagName("email").item(0).getTextContent());
            user.setRestaurantName(e.getElementsByTagName("restaurantname").item(0).getTextContent());
            user.setFirstName(e.getElementsByTagName("firstName").item(0).getTextContent());
            user.setLastName(e.getElementsByTagName("lastName").item(0).getTextContent());
            user.setPassword(Integer.valueOf(e.getElementsByTagName("password").item(0).getTextContent()));
            user.setUserId(Integer.valueOf(e.getElementsByTagName("userId").item(0).getTextContent()));
            user.setUsername(e.getElementsByTagName("username").item(0).getTextContent());

            userList.add(user);
        }
        return userList;
    }

    private Users parseUser(String xml) {
        if (xml.length() == 0) {
            return null;
        }
        Element e = (Element) parseDocument(xml).getElementsByTagName("users").item(0);

        Users user = new Users();
        user.setEmail(e.getElementsByTagName("email").item(0).getTextContent());
        user.setRestaurantName(e.getElementsByTagName("restaurantname").item(0).getTextContent());
        user.setFirstName(e.getElementsByTagName("firstName").item(0).getTextContent());
        user.setLastName(e.getElementsByTagName("lastName").item(0).getTextContent());
        user.setPassword(Integer.valueOf(e.getElementsByTagName("password").item(0).getTextContent()));
        user.setUserId(Integer.valueOf(e.getElementsByTagName("userId").item(0).getTextContent()));
        user.setUsername(e.getElementsByTagName("username").item(0).getTextContent());

        return user;
    }

     
    public void createRestaurant(Restaurants restaurant) {
        client.target(RESTAURANT_URL + "/create").request().post(Entity.entity(restaurant, "application/xml"));
    }

    public void updateRestaurant(Restaurants restaurant) {
        client.target(RESTAURANT_URL + "/edit/" + restaurant.getRestaurantId()).request().put(Entity.entity(restaurant, "application/xml"));
    }

    public void removeRestaurant(int id) {
        client.target(RESTAURANT_URL + "/remove/" + id).request().get().readEntity(String.class);
    }
    
    public Restaurants getRestaurantById(int id) {
        return parseRestaurant(client.target(RESTAURANT_URL + "/find/" + id).request().get().readEntity(String.class));
    }


    public Restaurants getFoodByName(String restaurantName) throws DoesNotExistException {
        Restaurants f = parseRestaurant(client.target(RESTAURANT_URL + "/findByName/" + restaurantName).request().get(String.class));
        if (f != null) {
            return f;
        }
        throw new DoesNotExistException("Restaurant " + restaurantName + " does not exist.");
    }

    public List<Restaurants> getAllRestaurants() {
        return parseRestaurantList(client.target(RESTAURANT_URL).request().get(String.class));
    }

    private List<Restaurants> parseRestaurantList(String xml) {
        List<Restaurants> restaurantList = new ArrayList<>();
        NodeList list = parseDocument(xml).getElementsByTagName("restaurants");
        for (int i = 0; i < list.getLength(); i++) {
            Element e = (Element) list.item(i);

            Restaurants restaurant = new Restaurants();
            restaurant.setEmail(e.getElementsByTagName("email").item(0).getTextContent());
            restaurant.setRestaurantName(e.getElementsByTagName("restaurantName").item(0).getTextContent());
            restaurant.setRestaurantOwner(e.getElementsByTagName("restaurantOwner").item(0).getTextContent());
            restaurant.setPassword(Integer.valueOf(e.getElementsByTagName("password").item(0).getTextContent()));
            restaurant.setRestaurantId(Integer.valueOf(e.getElementsByTagName("restaurantId").item(0).getTextContent()));
            restaurant.setUsername(e.getElementsByTagName("username").item(0).getTextContent());     
            restaurant.setAddress(e.getElementsByTagName("address").item(0).getTextContent());
            restaurant.setOpeningHours(e.getElementsByTagName("openingHours").item(0).getTextContent());
            restaurant.setPrice(e.getElementsByTagName("price").item(0).getTextContent());
            restaurant.setCookingtype(e.getElementsByTagName("cookingtype").item(0).getTextContent());
            restaurant.setContact(e.getElementsByTagName("contact").item(0).getTextContent());
            restaurant.setMenu(e.getElementsByTagName("menu").item(0).getTextContent());
            restaurant.setRatings(e.getElementsByTagName("rating").item(0).getTextContent());
            restaurant.setSpecialdiet(e.getElementsByTagName("specialdiet").item(0).getTextContent());

            restaurantList.add(restaurant);
        }
        return restaurantList;
    }

    private Restaurants parseRestaurant(String xml) {
        if (xml.length() == 0) {
            return null;
        }
        Element e = (Element) parseDocument(xml).getElementsByTagName("restaurants").item(0);

        Restaurants restaurant = new Restaurants();
        restaurant.setEmail(e.getElementsByTagName("email").item(0).getTextContent());
        restaurant.setRestaurantName(e.getElementsByTagName("restaurantName").item(0).getTextContent());
        restaurant.setRestaurantOwner(e.getElementsByTagName("restaurantOwner").item(0).getTextContent());
        restaurant.setPassword(Integer.valueOf(e.getElementsByTagName("password").item(0).getTextContent()));
        restaurant.setRestaurantId(Integer.valueOf(e.getElementsByTagName("restaurantId").item(0).getTextContent()));
        restaurant.setUsername(e.getElementsByTagName("username").item(0).getTextContent());     
        restaurant.setAddress(e.getElementsByTagName("address").item(0).getTextContent());
        restaurant.setOpeningHours(e.getElementsByTagName("openingHours").item(0).getTextContent());
        restaurant.setPrice(e.getElementsByTagName("price").item(0).getTextContent());
        restaurant.setCookingtype(e.getElementsByTagName("cookingtype").item(0).getTextContent());
        restaurant.setContact(e.getElementsByTagName("contact").item(0).getTextContent());
        restaurant.setMenu(e.getElementsByTagName("menu").item(0).getTextContent());
        restaurant.setRatings(e.getElementsByTagName("rating").item(0).getTextContent());
        restaurant.setSpecialdiet(e.getElementsByTagName("specialdiet").item(0).getTextContent());

        return restaurant;
    }
    

    private Document parseDocument(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(new StringReader(xml)));
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}

