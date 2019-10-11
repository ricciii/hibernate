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
 
public class RoleServiceImplTest {

   DatabaseService database = new DatabaseServiceMock();
   ScannerUtil scannerUtil = new ScannerUtilImpl();
   GeneratorService generator = new GeneratorService(scannerUtil);
   RoleService roleService;
   Scanner scanner;

   @Test
   public void testCreateRole() {
      String string = "QA\n";
      scanner = new Scanner(string);
      scannerUtil = new ScannerUtilImpl(scanner);
      generator = new GeneratorService(scannerUtil);
      roleService = new RoleServiceImpl(scannerUtil, generator, database);
      boolean created = roleService.createRole();
      assertTrue(created);
      
   }

   @Test
   public void testUpdateRole() {
      String string = "1\nEngineer\n";
      scanner = new Scanner(string);
      scannerUtil = new ScannerUtilImpl(scanner);
      generator = new GeneratorService(scannerUtil);
      roleService = new RoleServiceImpl(scannerUtil, generator, database);
      boolean updated = roleService.updateRole();
      assertTrue(updated);
      
   }

   @Test
   public void testDeleteRole() {
      String string = "1\n";
      scanner = new Scanner(string);
      scannerUtil = new ScannerUtilImpl(scanner);
      generator = new GeneratorService(scannerUtil);
      roleService = new RoleServiceImpl(scannerUtil, generator, database);
      boolean deleted = roleService.deleteRole();
      assertTrue(deleted);
   }
}