package com.ecc.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.Phaser;
import java.util.Scanner;
import java.util.GregorianCalendar;
import java.util.Calendar;
 
public class GeneratorServiceTest {
 
    @Test
    public void testGenerateId() throws Exception {
        String input = "123\n";
        Scanner scanner = new Scanner(input);
        ScannerUtil scannerUtil = new ScannerUtil(scanner);
        GeneratorService generator = new GeneratorService(scannerUtil);
        Integer num = 123;
        assertEquals(num, generator.generateId());
    }

    @Test
    public void testGenerateName() throws Exception {
        String input = "Smith\nAdam\nSimon\n\n";
        Scanner scanner = new Scanner(input);
        ScannerUtil scannerUtil = new ScannerUtil(scanner);
        GeneratorService generator = new GeneratorService(scannerUtil);
        Name name = generator.generateName();
        assertEquals("Smith Adam Simon", name.toString());
    }

    @Test
    public void testGenerateAddress() throws Exception {
        String input = "123\nSome Barangay\nSome Municipality\n0000\n";
        Scanner scanner = new Scanner(input);
        ScannerUtil scannerUtil = new ScannerUtil(scanner);
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
        ScannerUtil scannerUtil = new ScannerUtil(scanner);
        GeneratorService generator = new GeneratorService(scannerUtil);
        GregorianCalendar date = generator.generateDateOfBirth();
        String string = date.get(Calendar.YEAR) + "-" + date.get(Calendar.MONTH) + "-" + date.get(Calendar.DATE);
        assertEquals("2019-11-12", string);
    }
}