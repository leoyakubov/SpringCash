package io.leonid.springcash;

import io.leonid.springcash.dao.impl.UserDAO;
import io.leonid.springcash.model.Role;
import io.leonid.springcash.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by leonid on 13.08.14.
 */
public class HibernateTest {
    @Autowired
    private static UserDAO userDAO;

    private static final Logger logger = LoggerFactory.getLogger(HibernateTest.class);

    public static void main(String args[]){
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory factory = configuration.buildSessionFactory(builder.build());
        Session session = factory.openSession();

        //addRoles(session);
    }

    private static void addRoles(Session session) {
        Role role = new Role();
        role.setName(Role.ROLE_ADMIN);
        session.beginTransaction();
        session.save(role);
        session.getTransaction().commit();

//        /*role.setName(Role.ROLE_USER);
//        session.beginTransaction();
//        session.save(role);
//        session.getTransaction().commit();*/
    }

    private static void test3(Session session) {
        Query query = session.createQuery("from " + User.class.getName() + " u WHERE u.login = 'user1'");
        User user = (User) query.list().get(0);;
        System.out.println("===============================");
        System.out.println("Found user: " + user);
        System.out.println("===============================");

        user.setActive(!user.isActive());

        Role role = new Role();
        if (user.getRole().getName().equals("User")) {
            role.setId(3L);
            role.setName("Administreator");
        }
        else {
            role.setId(5L);
            role.setName("User");
        }

        user.setRole(role);

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();


        System.out.println("===============================");
        System.out.println("Saved user: " + user);
        System.out.println("===============================");
    }


    private static void test2(SessionFactory sessionFactory) {
        UserDAO dao = new UserDAO();
        dao.setSessionFactory(sessionFactory);
        User user = dao.findByLogin("user1");
        System.out.println("===============================");
        System.out.println("Found user: " + user);
        System.out.println("===============================");
        user.setActive(!user.isActive());

        User savedUser = dao.update(user);

        System.out.println("===============================");
        System.out.println("Saved user: " + savedUser);
        System.out.println("===============================");
    }

    private static void test1(Session session) {
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
        //Query query = session.createQuery("from " + User.class.getName() + " u WHERE u.role = (select id from  " + Role.class.getName() + " r where r.name='Admin')");
        Query query = session.createQuery("from " + User.class.getName());
        List<User> users = query.list();

        logger.info("Users:");
        for (User uzer : users) {
            logger.info("{}", uzer.toString());
            //session.delete(uzer);
            //logger.info("{} deleted", uzer.getId());
        }
        session.getTransaction().commit();


        session.beginTransaction();
        query = session.createQuery("from " + Role.class.getName());
        List<Role> roles = query.list();

        logger.info("Roles:");
        for (Role role_ : roles) {
            /*if (role_.getName().equals("Admin")) {
                session.delete(role_);
            }*/
            logger.info("{}", role_.toString());
        }

        session.getTransaction().commit();
        session.close();
    }
}
