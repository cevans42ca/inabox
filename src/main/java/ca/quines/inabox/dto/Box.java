package ca.quines.inabox.dto;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "boxes")
public class Box {

    @Id
    public String id;

    public String name;
    public String description;
    public String location;

    public Box() {}

    public Box(String name, String description, String location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }

    @Override
    public String toString() {
    	return String.format(
                "Box[id=%s, name='%s', description='%s', location='%s']",
                id, name, description, location);
    }

}
