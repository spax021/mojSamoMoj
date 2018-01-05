package jwd.wafepa.web.controller;

import java.util.ArrayList;
import java.util.List;

import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;
import jwd.wafepa.web.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class ApiUserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getUsers(
			@RequestParam(value="email", required=false) String email,
			@RequestParam(value="password", required=false) String password,
			@RequestParam(value="firstname", required=false) String firstname,
			@RequestParam(value="lastname", required=false) String lastname,
			@RequestParam(value="page", required=false) int page,
//			ovde se salje vrednost po stranici
			@RequestParam(value="perPage", required=false) int perPage ) {
		List<UserDTO> users = new ArrayList<>();
		int totalPages = 0;
		long totalElements = 0;
//		izvuceno zato sto mora da vrati stranicu u repozitorijumu		
		Page<User> usersPage;
		
		if(email == null){
		usersPage = userService.findAll(perPage, page);
		for (User u : usersPage) {
			users.add(new UserDTO(u));
		}
		totalPages = usersPage.getTotalPages();
		totalElements = usersPage.getTotalElements();
	}
		else{
			usersPage = userService.findByEmail(email, perPage, page);
			for(User u : usersPage){
				users.add(new UserDTO(u));
			}
		totalPages = usersPage.getTotalPages();
		totalElements = usersPage.getTotalElements();
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("total-pages", "" + totalPages);
		httpHeaders.add("total-elements", "" + totalElements);
		
		return new ResponseEntity<>(users, httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		User user = userService.findOne(id);
		
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
		User user = userService.findOne(id);
		
		if (user != null) {
			userService.remove(id);
			return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {

		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setFirstname(userDTO.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setPassword(userDTO.getPassword());

		User userPersisted = userService.save(user);
		return new ResponseEntity<>(new UserDTO(userPersisted),
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<UserDTO> editUser(@PathVariable Long id,
			@RequestBody UserDTO userDTO) {
		User user = userService.findOne(id);
		if (user != null) {
			if (id != userDTO.getId()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			user.setEmail(userDTO.getEmail());
			user.setFirstname(userDTO.getFirstname());
			user.setLastname(userDTO.getLastname());
			user.setPassword(userDTO.getPassword());
			User userPersisted = userService.save(user);

			return new ResponseEntity<>(new UserDTO(userPersisted),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
