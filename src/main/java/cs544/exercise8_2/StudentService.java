package cs544.exercise8_2;

import org.hibernate.Hibernate;

public class StudentService {

	private StudentDAO studentdao;

	public void setStudentdao(StudentDAO studentdao) {
		this.studentdao = studentdao;
	}

	public StudentService() {
	}

	public Student getStudent(long studentid) {
		Student student = studentdao.load(studentid);
		Hibernate.initialize(student.getCourselist());
		return student;
	}
}
