package com.ecc.app;

import java.util.List;

public interface DatabaseService {

	public void start();
	public boolean create(Object object);
	public boolean read(List objects);
    public boolean update(Object object);
    public boolean delete(Object object);
    public Object getPersonWithId(Integer personId);
    public Object getRoleWithId(Integer roleId);
    public Object getContactWithId(Integer contactId);
    public List<Person> getPersonsAsList(PersonServiceImpl.ListingOrder order);
    public List<Role> getRolesAsList();
    public boolean removeRoleFromPersons(Role role);
}