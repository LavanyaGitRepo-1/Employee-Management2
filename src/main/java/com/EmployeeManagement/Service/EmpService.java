package com.EmployeeManagement.Service;

import java.util.List;
import java.util.Scanner;
import com.EmployeeManagement.Entity.Employeee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class EmpService {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("lavanya");

	public EmpService(EntityManagerFactory emf) {
		this.emf = emf;
	}

	Scanner sc = new Scanner(System.in);

	public boolean addEmployee(Employeee e) {
		System.out.println("Enter employee id");
		int id = sc.nextInt();
		System.out.println("Enter Employee name");
		String name = sc.next();
		System.out.println("Enter Employee age");
		int age = sc.nextInt();
		System.out.println("Enter Employee gender");
		String gender = sc.next();
		System.out.println("Enter Employee salary");
		int salary = sc.nextInt();

		e.setEid(id);
		e.setName(name);
		e.setAge(age);
		e.setGender(gender);
		e.setSalary(salary);

		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		em.persist(e);
		transaction.commit();
		System.out.println("Id:" + id);
		System.out.println("Name: " + name);
		System.out.println("Age: " + age);
		System.out.println("Gender: " + gender);
		System.out.println("Salary: " + salary);
		return true;

	}

	public boolean updateEmployee() {
		System.out.println("Enter employee id to update");
		int eid = sc.nextInt();

		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		Employeee e = em.find(Employeee.class, eid);

		if (e == null) {
			System.out.println("Employee not found");
			return false;
		}

		System.out.println("Enter employee name");
		String name = sc.next();
		System.out.println("Enter age");
		int age = sc.nextInt();
		System.out.println("Enter gender");
		String gender = sc.next();
		System.out.println("Enter salary");
		int salary = sc.nextInt();

		e.setName(name);
		e.setAge(age);
		e.setGender(gender);
		e.setSalary(salary);

		transaction.begin();
		em.persist(e);
		transaction.commit();

		return true;

	}

	public boolean deleteEmployee() {
		System.out.println("Enter  employee id to delete");
		int eid = sc.nextInt();

		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		Employeee e = em.find(Employeee.class, eid);

		if (e == null) {
			System.out.println("Employee not found");
			return false;
		}

		transaction.begin();
		em.remove(e);
		transaction.commit();
		System.out.println(e.getName() + " record deleted");
		return true;
	}

	public void getAllEmployees() {
		EntityManager em = emf.createEntityManager();
		List<Employeee> list = em.createQuery("SELECT e from Employeee e", Employeee.class).getResultList();
		if (list.isEmpty()) {
			System.out.println("No data found");
			return;
		}
		System.out.println("********Employee list***********");
		list.forEach(this::employeeDetails);

	}

	private void employeeDetails(Employeee emp) {
		System.out.println("ID : " + emp.getEid() + ", Name : " + emp.getName() + ", Age : " + emp.getAge()
				+ ", Gender : " + emp.getGender() + ", Salary : " + emp.getSalary());
		;
	}

}
