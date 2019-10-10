package com.ecc.app;
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.SQLQuery;
import java.util.Scanner;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import org.hibernate.Criteria;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.query.Query;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;

public class RoleServiceImpl implements RoleService {

	private ScannerUtil scanner;
	private GeneratorService generator;
	private DatabaseService database;

	public RoleServiceImpl(ScannerUtil scanner, GeneratorService generator, DatabaseService database) {
		this.scanner = scanner;
		this.generator = generator;
		this.database = database;
	}

	public RoleServiceImpl() {

	}
    
	public boolean createRole() {
		boolean created = false;
		System.out.print("Input role name:");
		String string = scanner.getString();
		Role role = new Role(string);
		created = database.create(role);
		if(created) {
			System.out.print("Person successfully created.");
		} else {
			System.out.print("Person unsuccessfully created.");
		}
		return created;
	}

	public void readRole(Role role) {
		System.out.print(role);
	}

	public void readRoles() {
		List<Role> roles = new ArrayList<Role>();
		roles = database.getRolesAsList();
		for(Role role: roles) {
			System.out.println(role);
		}
	}

	public boolean updateRole() {
		boolean updated = false;
		System.out.print("Input the ID of the Role you want to update: ");
		Integer roleId = generator.generateId();
		Role role = (Role) database.getRoleWithId(roleId);
		if(role == null) {
	    	System.out.println("Role with ID: " + roleId + " does not exist.");
	    } else {
			System.out.print("Rename role: ");
			String newRole = scanner.getString();
			role.setRole(newRole);
			updated = database.update(role);
			if(updated) {
				System.out.println("Role updated successfully.");
			} else {
				System.out.println("Role updated unsuccessfully.");
			}
		}
		return updated;
	}
 
    public boolean deleteRole() {
	    boolean deleted = false;
	    System.out.print("Input the ID of the Role you want to delete: ");
		Integer roleId = generator.generateId();
		Role role = (Role) database.getRoleWithId(roleId);
		if(role == null) {
			System.out.println("Role does not exist.");
		} else {
			database.removeRoleFromPersons(role);
			deleted = database.delete(role);
			if(deleted) {
				System.out.println("Role successfully deleted.");
			} else {
				System.out.println("Role unsuccessfully deleted.");
			}
		}	
	    return deleted;
    }
}