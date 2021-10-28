package com.example.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Profile;
import com.example.service.ProfileService;

@RestController
@RequestMapping("profile")
public class ProfileRestApi {

	@Autowired
	private ProfileService profileService;

	// storing the profile
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveProfile(@RequestBody Profile profile) {
		return ResponseEntity.status(HttpStatus.CREATED).body(profileService.save(profile));
	}
	// getting all the profiles
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getProfiles() {
		return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfiles());
	}
	// getting the contacts of a particular profile
	@GetMapping(path = "contact/{profileId}")
	public ResponseEntity<Object> getContactFromProfile(@PathVariable("profileId") int profileId) {
		return ResponseEntity.status(HttpStatus.OK).body(profileService.getContacts(profileId));
	}
	// getting a particular profile
	@GetMapping(path = "{profileId}")
	public ResponseEntity<Object> getProfile(@PathVariable("profileId") int profileId) {
		return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfile(profileId));
	}
	// getting a particular profile
	@DeleteMapping(path = "{contactId}/{profileId}")
	public ResponseEntity<Object> deleteContactFromProfile(@PathVariable("contactId") int contactId, 
			@PathVariable("profileId") int profileId) {
		profileService.deleteContact(contactId, profileId);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
		
}
