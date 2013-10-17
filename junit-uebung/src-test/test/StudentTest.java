package test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import daten.Student;

public class StudentTest {

	@Before
	public void setUp() throws Exception {
	}

	@Ignore
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void compareStudents() {
		Student s1;
		Student s2;
		try {
			s1 = new Student("Max", "Mustermann", true, DateFormat.getDateInstance().parse("17.10.1995"));
			s2 = new Student("Franz", "Huber", false, DateFormat.getDateInstance().parse("22.5.1992"));

			int x = s1.compareTo(s2);
			assertTrue(x!=0);
		
		} catch (ParseException e) {
			assert(false);
		}
	}
}
