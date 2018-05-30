package com.luv2code.hibernate.demo;



import org.hibernate.query.Query;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;


public class FetchJoinDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		
			
		try {
			session.beginTransaction();
			// option 2: Hibernate query with HQL
			
			// get the instructor from the db
			int theId = 1;
			Course tempCourse = session.get(Course.class, theId);
			
			
			tempCourse = addNewReviews(session, tempCourse);
			
			
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
	
	private static Course addNewReviews(Session session, Course tempCourse) {
		
		Review tempReview = new Review("oh yeah, a comment");
		session.save(tempReview);
		tempCourse.add(tempReview);
		
		Review tempReview2 = new Review("oh yeah, a comment");
		session.save(tempReview2);
		tempCourse.add(tempReview2);
		
		session.save(tempCourse);
		return tempCourse;
		
	}
}