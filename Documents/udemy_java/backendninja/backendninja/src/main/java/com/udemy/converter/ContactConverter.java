package com.udemy.converter;

import org.springframework.stereotype.Component;

import com.udemy.entity.Contact;
import com.udemy.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {
	
	public Contact convertContactModelToContact(ContactModel contactModel) {
		
		Contact contact = new Contact();
		contact.setCity(contactModel.getCity());
		contact.setFirstName(contactModel.getFirstname());
		contact.setLastName(contactModel.getLastname());
		contact.setTelephone(contactModel.getTelephone());
		
		return contact;
		
	}
	
	/**
	 * devuelvo un ContactModel
	 * @param contact
	 * @return 
	 */
	public ContactModel convertContactToContactModel(Contact contact) {
		
		ContactModel contactModel = new ContactModel();
		contactModel.setCity(contact.getCity());
		contactModel.setFirstname(contact.getFirstName());
		contactModel.setLastname(contact.getLastName());
		contactModel.setTelephone(contact.getTelephone());
		contactModel.setId(contact.getId());
		
		return contactModel;
	}
}
