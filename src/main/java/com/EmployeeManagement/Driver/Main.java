package com.EmployeeManagement.Driver;

import java.util.Scanner;

import com.EmployeeManagement.Entity.Employeee;
import com.EmployeeManagement.Service.EmpService;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		System.out.println("Enter your choice");
		System.out.println("Enter 1 to add a record");
		System.out.println("Enter 2 to update the record");
		System.out.println("Enter 3 to remove the record");
		System.out.println("Eneter 4 to fetch all records");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("lavanya");

		EmpService es = new EmpService(emf);

		switch (i) {
		case 1: {
			Employeee e1 = new Employeee();
			boolean isInserted = es.addEmployee(e1);
			if (isInserted)
				System.out.println("Data inserted");
			else
				System.out.println("Data not inserted");
			break;
		}
		case 2: {
			boolean isUpdated = es.updateEmployee();
			if (isUpdated)
				System.out.println("Data updated");
			break;
		}
		case 3: {
			boolean isDeleted = es.deleteEmployee();
		}
		case 4: {
			es.getAllEmployees();
		}

		}

	}

}
