package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ContactRepository;
import com.example.dao.ProfileRepository;
import com.example.entities.Contact;
import com.example.entities.Profile;
import com.example.exceptions.ProfileIdNotFoundException;
@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileRepository profileDao;
	@Autowired
	private ContactRepository contactDao;

	@Override
	@Transactional
	public Profile save(Profile profile) {
		return profileDao.save(profile);
	}

	@Override
	public List<Contact> getContacts(int profileId) {
		Profile profile = getProfile(profileId);
		if(profile != null) {
			return profile.getContactsList();
		}
		return null;
	}

	@Override
	public List<Profile> getProfiles() {
		// TODO Auto-generated method stub
		return profileDao.findAll();
	}

	@Override
	@Transactional
	public void deleteContact(int contactId, int profileId) {
		contactDao.deleteContact(contactId, profileId);
	}

	@Override
	public Profile getProfile(int profileId) {
		Profile profile = profileDao.findById(profileId).orElse(null);
		if(profile != null) 
			return profile;
		return null;
	}
	
	@Override
	@Transactional
	public void addContact(int profileId, Contact contact) throws ProfileIdNotFoundException{
		Profile profile = getProfile(profileId);
		List<Contact> contacts = profile.getContactsList();
		contacts.add(contact);
		profile.setContactsList(contacts);
	}
}
