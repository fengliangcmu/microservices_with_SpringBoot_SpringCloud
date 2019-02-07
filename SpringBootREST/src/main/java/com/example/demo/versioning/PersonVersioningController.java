package com.example.demo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("firstN","lastName"));
		
	}
	
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("firstN lastN");
		
	}
	
	//different companies are using different URI versionings
	//@GetMapping(value="person", headers="X-API-VERSION=2")
	//@GetMapping(value="person", produces="application/vnd.company.app-v1+json")


}
