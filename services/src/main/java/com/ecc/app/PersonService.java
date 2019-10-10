package com.ecc.app;

import java.util.List;

public interface PersonService {
	
	public boolean createPerson();
	public void readPerson(Person person);
	public void readPersons();
	public boolean updatePerson();
	public Person updatePersonContacts(Person person);
	public Person updatePersonRoles(Person person);
	public boolean deletePerson(); 
		
}