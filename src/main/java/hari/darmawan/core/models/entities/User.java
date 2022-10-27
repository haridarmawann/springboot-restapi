package hari.darmawan.core.models.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "profile")
public class User {

    @Id
    private String id;
    @Field("email")
    private String email;
    @Field("first_name")
    private String firstName;
    @Field("last_name")
    private String lastName;
    @Field("photos")
    private String photos;

    public User(String email, String firstName, String lastName,String photos) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photos = photos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }
}
