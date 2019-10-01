import java.util.*;
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PersonManager {
   private static SessionFactory factory; 

   public PersonManager() {

   }

   public void addPerson(Name name, Address address, Date dateOfBirth, Double gwa, 
   	Date dateHired, Boolean currentlyEmployed, ContactInfo contactInfo, Role role) {
      Session session = factory.openSession();
      Transaction transaction = null;
      Integer id = null;
      
      try {
         transaction = session.beginTransaction();
         Person person = new Employee(name, address, dateOfBirth, gwa, 
   	dateHired, currentlyEmployed, contactInfo, role);
         id = (Integer) session.save(person); 
         transaction.commit();
      } catch (HibernateException e) {
         if (transaction!=null) transaction.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
}