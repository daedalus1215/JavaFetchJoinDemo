package com.luv2code.hibernate.demo;



import org.hibernate.query.Query;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class FetchJoinDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		
			
		try {
			
			// option 2: Hibernate query with HQL
			
			// get the instructor from the db
			int theId = 8;
			Course tempCourse = session.get(Course.class, theId);
			
			System.out.println("tempCourse: " + tempCourse);
			
			System.out.println("reviews: " + tempCourse.getReviews());
			
			
			session.getTransaction().commit();

		} catch(Exception exception) {
			exception.printStackTrace();
		} finally {			
			session.close();		
			factory.close();
		}
	}
}