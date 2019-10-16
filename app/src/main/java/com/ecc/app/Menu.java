package com.ecc.app;

public class Menu {

	private PersonService personService;
	private RoleService roleService;
	private ScannerUtil scanner;

	public Menu() {

	}

	public Menu(PersonService personService, RoleService roleService, ScannerUtil scanner) {
		this.personService = personService;
		this.roleService = roleService;
		this.scanner = scanner;
	}

	public void showMainMenu() {
    	int choice;
    	boolean done = false;
    	do {
    		System.out.println("\n<-- MAIN MENU -->\n");
	    	System.out.println("1=Person 2=Roles 3=Exit");
	    	System.out.print("Which table do you want to access: ");
    		choice = scanner.getInt();
    		switch(choice) {
    			case 1:
    				showPersonMenu();
    				break;
    			case 2:
    				showRoleMenu();
    				break;
    			case 3:
    				//done = true; 
    				System.exit(0);
                    break;
    			default: 
    				System.out.println("Not in the choices, try again.");
    		}
    	} while(done==false);
    }
	
	public void showPersonMenu() {
		int choice;
		boolean done = false;
    	do {
    		System.out.println("\n<--- PERSON TABLE --->\n");
	    	System.out.println("1=CREATE 2=READ 3=UPDATE 4=DELETE 5=BACK");
	    	System.out.print("What do you want to do: ");
    		choice = scanner.getInt();
    		switch(choice) {
    			case 1:
    				personService.createPerson();
    				break;
    			case 2:
    				personService.readPersons();
    				break;
    			case 3:
    				personService.updatePerson();
    				break;
    			case 4:
    				personService.deletePerson();
    				break;
    			case 5:
    				done = true; 
    				break;
    			default: 
    				System.out.println("Not in the choices, try again.");
	    	}
    	} while(done==false);
	}

	public void showRoleMenu() {
		int choice;
		boolean done = false;
    	do {
    		System.out.println("\n<--- ROLE TABLE --->\n");
	    	System.out.println("1=CREATE 2=READ 3=UPDATE 4=DELETE 5=BACK");
	    	System.out.print("What do you want to do: ");
	    	choice = scanner.getInt();
	    	switch(choice) {
    			case 1:
    				roleService.createRole();
    				break;
    			case 2:
    				roleService.readRoles();
    				break;
    			case 3:
    				roleService.updateRole();
    				break;
    			case 4:
    				roleService.deleteRole();
    				break;
    			case 5:
    				done = true; 
    				break;
    			default: 
    				System.out.println("Not in the choices, try again.");
	    	}
    	} while(done==false);
	}
}