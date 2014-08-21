package io.leonid.springcash;

import io.leonid.springcash.dao.RoleDAO;
import io.leonid.springcash.dao.UserDAO;
import io.leonid.springcash.model.Role;
import io.leonid.springcash.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

/**
 * Created by leonid on 13.08.14.
 */
public class HibernateTest {
    @Autowired
    private static UserDAO userDAO;

    public static void main(String args[]){
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory factory = configuration.buildSessionFactory(builder.build());
        Session session = factory.openSession();

        /*Role role = new Role();
        role.setName(Role.ROLE_USER);
        session.beginTransaction();
        session.save(role);
        session.getTransaction().commit();*/

//        User user = new User();
//        user.setLogin("");
//        user.setActive(true);
//        user.setEmail("affdfa");
//        user.setFirstName("wss");
//        user.setLastName("sds");
//        user.setPassword("dsss");
//        Query query2 = session.createQuery("from " + Role.class.getName() + " r Where r.name='" + Role.ROLE_ADMIN + "'");
//        Role role = (Role) query2.list().get(0);
//        role.setName(Role.ROLE_ADMIN);
//        user.setRole(role);

        //ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        //Validator validator = validatorFactory.getValidator();
        //Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

//        session.beginTransaction();
//        session.save(user);
//        session.getTransaction().commit();

        session.beginTransaction();
        Query query = session.createQuery("from " + User.class.getName() + " u WHERE u.role = (select id from  " + Role.class.getName() + " r where r.name='Admin')");
        List<User> users = query.list();

        System.out.println("Users:");
        for (User uzer : users) {
            System.out.println(uzer);
            session.delete(uzer);
            System.out.println(uzer.getId() + "deleted");
        }
        session.getTransaction().commit();

        session.beginTransaction();
        query = session.createQuery("from " + Role.class.getName());
        List<Role> roles = query.list();

        System.out.println("Roles:");
        for (Role role_ : roles) {
            if (role_.getName().equals("Admin")) {
                session.delete(role_);
            }
            System.out.println(role_);
        }
        session.getTransaction().commit();
        session.close();
    }
}
