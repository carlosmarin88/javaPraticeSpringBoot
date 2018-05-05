package com.udemy.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.udemy.constant.ViewConstant;
import com.udemy.model.ContactModel;
import com.udemy.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	
	private static final Log LOG = LogFactory.getLog(ContactController.class);
	
	@Autowired
	@Qualifier("contactService")
	private ContactService contactService;
	
	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:/contacts/showcontacts";
	}
	
	//se puede escribir diferentes tipo de expresiones por ejemplo 
	//("hasRole('ROLE_USER') or/and hasRole('ROLE_ADMIN')")
	//tambien del siguiente tipo "permitAll()" etc
	//se puede añadir a nivel de  clase o servicio tambien
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/contactform")
	public String redirectContactForm(@RequestParam(name="id", required=false) int id,
			Model model) {
		ContactModel contactModel = null;
		if(id== 0) {
			contactModel = new ContactModel();
		}else {
			contactModel = this.contactService.findContactModelById(id);
		}
		model.addAttribute("contactModel",contactModel);
		return ViewConstant.CONTACT_FORM;
	}
	
	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel,
			Model model) {
		LOG.info("METHOD: addContact() -- PARAMS: " + contactModel.toString());
		
		 if(contactService.addContact(contactModel)!=null) {
			 model.addAttribute("result", 1);
		 }else {
			 model.addAttribute("result", 0);
		 }
		
		return "redirect:/contacts/showcontacts";
	}
	
	@GetMapping("/showcontacts")
	public ModelAndView showContacts() {
		ModelAndView ma = new ModelAndView(ViewConstant.CONTACTS);
		ma.addObject("contacts", contactService.listAllContacts()); 
		ma.addObject("username", obtenerUsuarioLogeado().getUsername());
		
		return ma;
	}
	/**
	 * Recupero el usuario logueado de la aplicacion
	 * @return usuario logueado
	 */
	private User obtenerUsuarioLogeado() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	@GetMapping("/removecontact")
	public ModelAndView removeContact(@RequestParam(name="id", required=true)int id) {
		contactService.removeContact(id);
		return this.showContacts();
	}
	
}
