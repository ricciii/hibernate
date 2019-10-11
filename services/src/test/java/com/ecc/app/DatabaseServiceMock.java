package com.ecc.app;

import java.util.List;
import java.util.ArrayList;

public class DatabaseServiceMock implements DatabaseService {
 		public void start(){}
		public boolean create(Object object){return true;}
		public boolean read(List objects){return true;}
	    public boolean update(Object object){return true;}
	    public boolean delete(Object object){return true;}
	    public Object getPersonWithId(Integer personId){ return new Person();}
	    public Object getRoleWithId(Integer roleId){return new Role(); }
	    public Object getContactWithId(Integer contactId){return new Contact();}
	    public List<Person> getPersonsAsList(PersonServiceImpl.ListingOrder order){return new ArrayList<Person>();}
	    public List<Role> getRolesAsList(){return new ArrayList<Role>();}
	    public boolean removeRoleFromPersons(Role role){return true;}
 	}