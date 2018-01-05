package jwd.wafepa.web.dto;

import jwd.wafepa.model.User;

public class UserDTO {

	private Long id;
	private String email;
	private String firstname;
	private String lastname;
	private String password;
	private Integer logCount;
	
	public UserDTO() {
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.password = user.getPassword();
		if (user.getLogs() != null){
			this.logCount = user.getLogs().size();
		}
	}

	public UserDTO(Long id, String email, String firstname, String lastname,
			String password, String username) {
		super();
		this.id = id;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLogCount() {
		return logCount;
	}

	public void setLogCount(Integer logCount) {
		this.logCount = logCount;
	}

}
