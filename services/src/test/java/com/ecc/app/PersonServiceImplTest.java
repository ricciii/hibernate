package com.ecc.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class PersonServiceImplTest {

 	DatabaseService database = new DatabaseServiceMock();
    ScannerUtil scannerUtil = new ScannerUtilImpl();
    GeneratorService generator = new GeneratorService(scannerUtil);
    PersonService personService;
    Scanner scanner;

    @Test
    public void testCreatePerson() {
    	String string = "Smith\nJames\nPeterson\n\n";
	    string += "Topaz Rd.\nPasig\nManila\n90001\n";
	    string += "2019\n11\n12\n";
	    string += "12.21\n";
	    string += "2019\n11\n12\n";
	    string += "true\n";
	    string += "\n\n\n\n\n\n";
	    scanner = new Scanner(string);
        scannerUtil = new ScannerUtilImpl(scanner);
        generator = new GeneratorService(scannerUtil);
        personService = new PersonServiceImpl(scannerUtil, generator, database);
        boolean created = personService.createPerson();
        assertTrue(created);
    }

    @Test
    public void testUpdatePerson() {
       String string = "12\n6\ntrue\n";
       scanner = new Scanner(string);
       scannerUtil = new ScannerUtilImpl(scanner);
       generator = new GeneratorService(scannerUtil);
       personService = new PersonServiceImpl(scannerUtil, generator, database);
       boolean updated = personService.updatePerson();
       assertTrue(updated);
       string = "12\n1\nJames\nPotter\nLily\n\n\n";
       scanner = new Scanner(string);
       scannerUtil = new ScannerUtilImpl(scanner);
       generator = new GeneratorService(scannerUtil);
       personService = new PersonServiceImpl(scannerUtil, generator, database);
       updated = personService.updatePerson();
       assertTrue(updated);
       string = "12\n2\nStreet\nBarangay\nMunicipality\n0000\n\n";
       scanner = new Scanner(string);
       scannerUtil = new ScannerUtilImpl(scanner);
       generator = new GeneratorService(scannerUtil);
       personService = new PersonServiceImpl(scannerUtil, generator, database);
       updated = personService.updatePerson();
       assertTrue(updated);
       string = "12\n3\n1\n2\n12\n0000\n\n";
       scanner = new Scanner(string);
       scannerUtil = new ScannerUtilImpl(scanner);
       generator = new GeneratorService(scannerUtil);
       personService = new PersonServiceImpl(scannerUtil, generator, database);
       updated = personService.updatePerson();
       assertTrue(updated);
       string = "12\n4\n1\n2\nMunicipality\n0000\n\n";
       scanner = new Scanner(string);
       scannerUtil = new ScannerUtilImpl(scanner);
       generator = new GeneratorService(scannerUtil);
       personService = new PersonServiceImpl(scannerUtil, generator, database);
       updated = personService.updatePerson();
       assertTrue(updated);
       string = "12\n5\n1\n2\n12\n0000\n\n";
       scanner = new Scanner(string);
       scannerUtil = new ScannerUtilImpl(scanner);
       generator = new GeneratorService(scannerUtil);
       personService = new PersonServiceImpl(scannerUtil, generator, database);
       updated = personService.updatePerson();
       assertTrue(updated);
    }

    @Test
    public void testUpdatePersonContacts() {
       String string = "1\n1\n2\n2\n";
       scanner = new Scanner(string);
       scannerUtil = new ScannerUtilImpl(scanner);
       generator = new GeneratorService(scannerUtil);
       personService = new PersonServiceImpl(scannerUtil, generator, database);
       Person person = new Person();
       person = personService.updatePersonContacts(person);
       assertTrue(person != null);
    }

    @Test
    public void testUpdatePersonRoles() {
       String string = "1\n1\n2\n2\n";
       scanner = new Scanner(string);
       scannerUtil = new ScannerUtilImpl(scanner);
       generator = new GeneratorService(scannerUtil);
       personService = new PersonServiceImpl(scannerUtil, generator, database);
       Person person = new Person();
       person = personService.updatePersonRoles(person);
       assertTrue(person != null);
    }

    @Test
    public void testDeletePerson() {
       String string = "12\n6\ntrue\n";
       scanner = new Scanner(string);
       scannerUtil = new ScannerUtilImpl(scanner);
       generator = new GeneratorService(scannerUtil);
       personService = new PersonServiceImpl(scannerUtil, generator, database);
       boolean deleted = personService.deletePerson();
       assertTrue(deleted);
    }
}