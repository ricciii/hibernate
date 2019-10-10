// package com.ecc.app;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Tag;
// import org.junit.jupiter.api.Assertions;
// import static org.junit.jupiter.api.Assertions.*;
// import java.util.Scanner;
// import java.util.HashSet;
// import java.util.List;
// import org.hibernate.SessionFactory;
// import org.hibernate.cfg.Configuration;
 
// public class PersonServiceTest {
 
//     static PersonService service;

//     @BeforeAll
//     public static void setup() {
//         service = new PersonService();
//     }

//     @Test
//     public void testReadPerson() {
//         Assertions.assertThrows(Exception.class, () -> {
//         service.readPerson(1);
//         });
//     }

//     @Test
//     public void testGetContactWithId() {
//         Assertions.assertThrows(Exception.class, () -> {
//         service.getContactWithId(1);
//         });
//     }

//     @Test
//     public void testGetPersonWithId() {
//         Assertions.assertThrows(Exception.class, () -> {
//         service.getPersonWithId(1);
//         });
//     }

//      @Test
//     public void testGetPersonsAsList() { 
//         Assertions.assertThrows(Exception.class, () -> {
//         service.getPersonsAsList(PersonService.ListingOrder.DEFAULT);
//         });
//     }

    
// }