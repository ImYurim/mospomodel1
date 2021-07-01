package mvc.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDTO implements Serializable {
	private String id;
	private String password;
	private String name;
}
