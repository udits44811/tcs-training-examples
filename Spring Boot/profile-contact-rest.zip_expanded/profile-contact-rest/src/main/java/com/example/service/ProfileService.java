package com.example.service;

import java.util.List;

import com.example.entities.Contact;
import com.example.entities.Profile;
import com.example.exceptions.ProfileIdNotFoundException;

public interface ProfileService {
	// you can perform all the crud operations
	public Profile save(Profile profile);
	public Profile getProfile(int profileId);
	public List<Contact> getContacts(int profileId);
	public List<Profile> getProfiles();
	public void deleteContact(int contactId, int profileId);
	// you can create addContact(int profileId, Contact contact);
	public void addContact(int profileId, Contact contact) throws ProfileIdNotFoundException;
}
