package com.ecc.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;
 
public class ScannerUtilImplTest {
 	ScannerUtil scannerUtil = new ScannerUtilImpl();

    @Test
    public void testGetString() throws Exception {
        String input = "hello world";
        Scanner scanner = new Scanner(input);
        scannerUtil = new ScannerUtilImpl(scanner);
        assertEquals(input, scannerUtil.getString());
    }

    @Test
    public void testGetBoolean() throws Exception {
        String input = "true\n";
        Scanner scanner = new Scanner(input);
        scannerUtil = new ScannerUtilImpl(scanner);
        assertEquals(true, scannerUtil.getBoolean());
    }

    @Test
    public void testGetInt() throws Exception {
        String input = "     1\n";
        Scanner scanner = new Scanner(input);
        scannerUtil = new ScannerUtilImpl(scanner);
        Integer intgr = 1;
        assertEquals(intgr, scannerUtil.getInt());
    }

    @Test
    public void testGetFloat() throws Exception {
        String input = "     1.23\n";
        Scanner scanner = new Scanner(input);
        float f = (float) 1.23;
        scannerUtil = new ScannerUtilImpl(scanner);
        assertEquals(f, scannerUtil.getFloat());
    }
}