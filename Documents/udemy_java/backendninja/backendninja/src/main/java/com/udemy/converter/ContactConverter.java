package com.udemy.converter;

import org.springframework.stereotype.Component;

import com.udemy.entity.Contact;
import com.udemy.model.ContactModel;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactConverter.
 */
@Component("contactConverter")
public class ContactConverter {
	
	/**
	 * Convert contact model to contact.
	 *
	 * @param contactModel the contact model
	 * @return the contact
	 */
	public Contact convertContactModelToContact(ContactModel contactModel) {
		
		Contact contact = new Contact();
		contact.setCity(contactModel.getCity());
		contact.setFirstName(contactModel.getFirstname());
		contact.setLastName(contactModel.getLastname());
		contact.setTelephone(contactModel.getTelephone());
		contact.setId(contactModel.getId());
		
		return contact;
		
	}
	
	/**
	 * devuelvo un ContactModel.
	 *
	 * @param contact the contact
	 * @return the contact model
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
