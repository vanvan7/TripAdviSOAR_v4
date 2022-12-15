/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    private PersistenceClient() {
        PersistenceClient.client = ClientBuilder.newClient();
        PersistenceClient.client = ClientBuilder.newRestaurant();
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

    public void completeShopping(int id) {
        client.target(USERS_URL + "/completeShopping/" + id).request().get();
    }

    public void removeFromShoppingCart(int uId, int fId) {
        client.target(USERS_URL + "/removeFromShoppingCart/" + uId + "/" + fId).request().get();
    }

    public void addToShoppingCart(int uId, int fId) {
        client.target(USERS_URL + "/addToShoppingCart/" + uId + "/" + fId).request().get();
    }

    public List<Foods> getAllFoodsInShoppingCart(int id) {
        return parseFoodList(client.target(USERS_URL + "/getShoppingCart/" + id).request().get(String.class));
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
    
    public void createRestaurantUser(Restaurant restaurant) {
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
        user.setBalance(Double.valueOf(e.getElementsByTagName("balance").item(0).getTextContent()));
        user.setEmail(e.getElementsByTagName("email").item(0).getTextContent());
        user.setFirstName(e.getElementsByTagName("firstName").item(0).getTextContent());
        user.setLastName(e.getElementsByTagName("lastName").item(0).getTextContent());
        user.setPassword(Integer.valueOf(e.getElementsByTagName("password").item(0).getTextContent()));
        user.setUserId(Integer.valueOf(e.getElementsByTagName("userId").item(0).getTextContent()));
        user.setUsername(e.getElementsByTagName("username").item(0).getTextContent());

        return user;
    }

    public void createFood(Foods food) {
        client.target(FOODS_URL + "/create").request().post(Entity.entity(food, "application/xml"));
    }

    public void updateFood(Foods food) {
        client.target(FOODS_URL + "/edit/" + food.getFoodId()).request().put(Entity.entity(food, "application/xml"));
    }

    public void removeFood(int id) {
        client.target(FOODS_URL + "/remove/" + id).request().get().readEntity(String.class);
    }

    public Foods getFoodById(int id) {
        return parseFood(client.target(FOODS_URL + "/find/" + id).request().get().readEntity(String.class));
    }

    public Foods getFoodByName(String foodName) throws DoesNotExistException {
        Foods f = parseFood(client.target(FOODS_URL + "/findByName/" + foodName).request().get(String.class));
        if (f != null) {
            return f;
        }
        throw new DoesNotExistException("Food " + foodName + " does not exist.");
    }

    public List<Foods> getAllFoods() {
        return parseFoodList(client.target(FOODS_URL).request().get(String.class));
    }

    private List<Foods> parseFoodList(String xml) {
        List<Foods> foodList = new ArrayList<>();
        NodeList list = parseDocument(xml).getElementsByTagName("foods");
        for (int i = 0; i < list.getLength(); i++) {
            Element e = (Element) list.item(i);

            Foods food = new Foods();
            food.setFoodId(Integer.valueOf(e.getElementsByTagName("foodId").item(0).getTextContent()));
            food.setFoodName(e.getElementsByTagName("foodName").item(0).getTextContent());
            food.setFoodPrice(Double.valueOf(e.getElementsByTagName("foodPrice").item(0).getTextContent()));
            food.setIngredients(e.getElementsByTagName("ingredients").item(0).getTextContent());

            foodList.add(food);
        }
        return foodList;
    }

    private Foods parseFood(String xml) {
        if (xml.length() == 0) {
            return null;
        }
        Element e = (Element) parseDocument(xml).getElementsByTagName("foods").item(0);

        Foods food = new Foods();
        food.setFoodId(Integer.valueOf(e.getElementsByTagName("foodId").item(0).getTextContent()));
        food.setFoodName(e.getElementsByTagName("foodName").item(0).getTextContent());
        food.setFoodPrice(Double.valueOf(e.getElementsByTagName("foodPrice").item(0).getTextContent()));
        food.setIngredients(e.getElementsByTagName("ingredients").item(0).getTextContent());

        return food;
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

