package com.ecc.app;

public class Main {
	public static void main(String[] args) {
		DatabaseService database = new DatabaseServiceImpl();
	    database.start();
	    ScannerUtil scanner = new ScannerUtilImpl();
	    GeneratorService generator = new GeneratorService(scanner);
	    PersonService personService = new PersonServiceImpl(scanner, generator, database);
	    RoleService roleService = new RoleServiceImpl(scanner, generator, database);
        Menu menu = new Menu(personService, roleService, scanner);
        menu.showMainMenu();
        System.out.println("Trainee yes yes yow");
    }
}