package com.udemy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Contact.
 */
@Entity
@Table(name="contact")
public class Contact {
	
	/** The id. */
	@Id
	@GeneratedValue
	@Column(name="id_contact")
	private int id;
	
	/** The first name. */
	@Column(name="firstname")
	private String firstName;
	
	/** The last name. */
	@Column(name="lastname")
	private String lastName;
	
	/** The telephone. */
	@Column(name="telephone")
	private String telephone;
	
	/** The city. */
	@Column(name="city")
	private String city;
	
	/**
	 * Instantiates a new contact.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param telephone the telephone
	 * @param city the city
	 * @param id the id
	 */
	public Contact(String firstName, String lastName, String telephone, String city, Integer id) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephone = telephone;
		this.city = city;
		this.id = id;
	}
	
	/**
	 * Instantiates a new contact.
	 */
	public Contact() {
		super();
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the telephone.
	 *
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * Sets the telephone.
	 *
	 * @param telephone the new telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", telephone=" + telephone
				+ ", city=" + city + "]";
	}

	
	
	
}
