package org.example;

import org.example.config.ApplicationConfiguration;
import org.example.dao.CompanyDao;
import org.example.dao.UserDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {

            var companyDao = context.getBean("companyDao", CompanyDao.class);
            System.out.println("Bean companyDao: " + companyDao);

            var userDao = context.getBean("userDao", UserDao.class);
            System.out.println("Bean userDao: " + userDao);

            System.out.println("Find company by id=2: " + companyDao.findById(2L));
            System.out.println("Find user by id=1: " + userDao.findById(1L));
        }
    }
}