package com.example.demo.user;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.exception.UserNotFoundException;

@RestController
public class UserJPAResource {
	
	@Autowired
	private UserDaoService service;
	
	@Autowired
	private UserJPARepository userJPARepository;
	
	@Autowired
	private PostJPARepository postJPARepository;
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return userJPARepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id){
		Optional<User> user =  userJPARepository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id-"+id);
		
		Resource<User> resource = new Resource<User>(user.get());
		
		//dynamically getting url info of a router from this controller class
		//following hateoas way
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser = userJPARepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void dekereUser(@PathVariable int id){
		userJPARepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id){
		
		Optional<User> user = userJPARepository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id-"+id);
		return user.get().getPosts();
		
	}
	
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post){
		
		Optional<User> optionaluser = userJPARepository.findById(id);
		if(optionaluser.isEmpty())
			throw new UserNotFoundException("id-"+id);
		
		User user = optionaluser.get();
		post.setUser(user);
		
		postJPARepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	
	}
	
}
