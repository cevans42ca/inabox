package ca.quines.inabox.dto;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.AccessType.Type;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventory")
@AccessType(Type.PROPERTY) // Class-level instruction to use accessor and mutator methods
public class Box {

	private String id;
	private String name;
	private String description;
	private String location;
	private String phonetic;

	public Box() {}

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhonetic() {
		return phonetic;
	}

	public void setPhonetic(String phonetic) {
		this.phonetic = phonetic;
	}

	@Override
	public String toString() {
		return String.format(
				"Box[id=%s, name='%s', description='%s', location='%s', phonetic='%s']",
				this.getId(), this.getName(), this.getDescription(), this.getLocation(), this.getPhonetic());
	}

}