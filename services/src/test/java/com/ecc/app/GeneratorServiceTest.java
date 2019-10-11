package com.ecc.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.HashSet;
 
public class GeneratorServiceTest {
 	ScannerUtil scannerUtil = new ScannerUtilImpl();

    // @Test
    // public void testGeneratePerson() throws Exception {
    //     String input = "Smith\nJames\nPeterson\n\n";
    //     input += "Topaz Rd.\nPasig\nManila\n90001\n";
    //     input += "2019\n11\n12\n";
    //     input += "12.21\n";
    //     input += "2019\n11\n12\n";
    //     input += "true\n";
    //     input += "\n\n\n\n\n\n";
    //     RoleService roleService = new RoleService();
    //     Scanner scanner = new Scanner(input);
    //     ScannerUtil scannerUtil = new ScannerUtil(scanner);
    //     GeneratorService generator = new GeneratorService(scannerUtil);
    //     Person person = generator.generatePerson(roleService);
    //     assertEquals("Smith James Peterson", person.getName().toString());
    //     assertEquals("Topaz Rd., Pasig, Manila, 90001", person.getAddress().toString());
    //     String date = person.getDateOfBirth().get(Calendar.YEAR) + "-" + person.getDateOfBirth().get(Calendar.MONTH) + "-" + person.getDateOfBirth().get(Calendar.DATE);
    //     assertEquals("2019-11-12", date);
    //     date = person.getDateHired().get(Calendar.YEAR) + "-" + person.getDateHired().get(Calendar.MONTH) + "-" + person.getDateHired().get(Calendar.DATE);
    //     assertEquals("2019-11-12", date);
    //     float flt = (float) 12.21;
    //     assertEquals(flt, person.getGwa());
    //     assertEquals(true, person.getCurrentlyEmployed());
    //     assertTrue(person.getContacts()==null);
    //     assertTrue(person.getRoles()==null);

    // }

    @Test
    public void testGenerateId() throws Exception {
        String input = "123\n";
        Scanner scanner = new Scanner(input);
        scannerUtil = new ScannerUtilImpl(scanner);
        GeneratorService generator = new GeneratorService(scannerUtil);
        Integer num = 123;
        assertEquals(num, generator.generateId());
    }

    @Test
    public void testGenerateName() throws Exception {
        String input = "Smith\nAdam\nSimon\n\n";
        Scanner scanner = new Scanner(input);
        scannerUtil = new ScannerUtilImpl(scanner);
        GeneratorService generator = new GeneratorService(scannerUtil);
        Name name = generator.generateName();
        assertEquals("Smith Adam Simon", name.toString());
    }

    @Test
    public void testGenerateAddress() throws Exception {
        String input = "123\nSome Barangay\nSome Municipality\n0000\n";
        Scanner scanner = new Scanner(input);
        scannerUtil = new ScannerUtilImpl(scanner);
        GeneratorService generator = new GeneratorService(scannerUtil);
        Address address = generator.generateAddress();
        assertEquals("123", address.getStreet());
        assertEquals("Some Barangay", address.getBarangay());
        assertEquals("Some Municipality", address.getMunicipality());
        assertEquals("0000", address.getZipCode());
    }

    @Test
    public void testGenerateDateOfBirth() throws Exception {
        String input = "2019\n11\n12\n";
        Scanner scanner = new Scanner(input);
        scannerUtil = new ScannerUtilImpl(scanner);
        GeneratorService generator = new GeneratorService(scannerUtil);
        GregorianCalendar date = generator.generateDateOfBirth();
        String string = date.get(Calendar.YEAR) + "-" + date.get(Calendar.MONTH) + "-" + date.get(Calendar.DATE);
        assertEquals("2019-11-12", string);
    }

    @Test
    public void testGenerateDateHired() throws Exception {
        String input = "2019\n11\n12\n";
        Scanner scanner = new Scanner(input);
        scannerUtil = new ScannerUtilImpl(scanner);
        GeneratorService generator = new GeneratorService(scannerUtil);
        GregorianCalendar date = generator.generateDateHired();
        String string = date.get(Calendar.YEAR) + "-" + date.get(Calendar.MONTH) + "-" + date.get(Calendar.DATE);
        assertEquals("2019-11-12", string);
    }

    @Test
    public void testGenerateGwa() throws Exception {
        String input = "12.21\n";
        float expected = (float) 12.21;
        Scanner scanner = new Scanner(input);
        scannerUtil = new ScannerUtilImpl(scanner);
        GeneratorService generator = new GeneratorService(scannerUtil);
        assertEquals(expected, generator.generateGwa());
    }

    @Test
    public void testGenerateCurrentlyEmployed() throws Exception {
        String input = "false\n";
        Scanner scanner = new Scanner(input);
        scannerUtil = new ScannerUtilImpl(scanner);
        GeneratorService generator = new GeneratorService(scannerUtil);
        boolean expected = false;
        assertEquals(expected, generator.generateCurrentlyEmployed());
    }

    @Test
    public void testGenerateContact() throws Exception {
        String input = "1\n09274990787\n";
        Scanner scanner = new Scanner(input);
        scannerUtil = new ScannerUtilImpl(scanner);
        GeneratorService generator = new GeneratorService(scannerUtil);
        String expected = "09274990787";
        assertEquals(expected, generator.generateContact().getContact());
    }

    @Test
    @Tag("SkipTest")
    public void testGenerateContacts() throws Exception {
        String input = "1\n09274990787\n1\n2\n911\n1\n3\nemail@gmail.com\n2\n";
        Scanner scanner = new Scanner(input);
        scannerUtil = new ScannerUtilImpl(scanner);
        GeneratorService generator = new GeneratorService(scannerUtil);
        String expected = "09274990787";
        HashSet<Contact> contacts = generator.generateContacts();
        Contact contact1 = new Contact(Contact.ContactType.MOBILE, "09274990787");
        Contact contact2 = new Contact(Contact.ContactType.LANDLINE, "911");
        Contact contact3 = new Contact(Contact.ContactType.EMAIL, "email@gmail.com");
        assertTrue(contacts.contains(contact1));
        assertTrue(contacts.contains(contact2));
        assertTrue(contacts.contains(contact3));
    }

    @Test
    public void testGenerateRole() throws Exception {
        String input = "QA\n09274990787\n";
        Scanner scanner = new Scanner(input);
        scannerUtil = new ScannerUtilImpl(scanner);
        GeneratorService generator = new GeneratorService(scannerUtil);
        String expected = "QA";
        assertEquals(expected, generator.generateRole().getRole());
    }

    @Test
    public void testGenerateRoles() throws Exception {
        String input = "QA\n2\n2\n1\nAdmin\n3\n";
        Scanner scanner = new Scanner(input);
        scannerUtil = new ScannerUtilImpl(scanner);
        GeneratorService generator = new GeneratorService(scannerUtil);
        HashSet<Role> roles = generator.generateRoles();
        Role role1 = new Role("QA");
        assertTrue(roles.contains(role1));
    }
}