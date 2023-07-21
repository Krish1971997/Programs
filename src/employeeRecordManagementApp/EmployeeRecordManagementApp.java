package employeeRecordManagementApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee {
	private String empName;
	private int age;
	private String designation;
	private String department;
	private String reportingTo;

	public Employee(String empName, int age, String designation, String department, String reportingTo) {
		this.empName = empName;
		this.age = age;
		this.designation = designation;
		this.department = department;
		this.reportingTo = reportingTo;
	}

	public String getEmpName() {
		return empName;
	}

	public int getAge() {
		return age;
	}

	public String getDesignation() {
		return designation;
	}

	public String getDepartment() {
		return department;
	}

	public String getReportingTo() {
		return reportingTo;
	}
}

public class EmployeeRecordManagementApp {
	private List<Employee> employees;

	public EmployeeRecordManagementApp() {
		employees = new ArrayList<>();
		// Hardcoded employee records
		employees.add(new Employee("John Doe", 30, "Manager", "IT", "Jane Smith"));
		employees.add(new Employee("Jane Smith", 35, "Director", "IT", ""));
		employees.add(new Employee("Mike Johnson", 28, "Developer", "IT", "John Doe"));
		employees.add(new Employee("Lisa Brown", 32, "Manager", "HR", "John Doe"));
		employees.add(new Employee("Chris Wilson", 25, "Intern", "HR", "Lisa Brown"));
	}

	public void displayAllEmployees() {
		System.out.println("Employee Records:");
		System.out.println("--------------------------------------------------");
		System.out.printf("| %-15s | %-4s | %-12s | %-10s | %-15s |\n", "EmpName", "Age", "Designation", "Department",
				"ReportingTo");
		System.out.println("--------------------------------------------------");
		for (Employee employee : employees) {
			System.out.printf("| %-15s | %-4d | %-12s | %-10s | %-15s |\n", employee.getEmpName(), employee.getAge(),
					employee.getDesignation(), employee.getDepartment(), employee.getReportingTo());
		}
		System.out.println("--------------------------------------------------");
	}

	public void searchRecords(String column, String condition, String value) {
		List<Employee> searchResults = new ArrayList<>();
		for (Employee employee : employees) {
			String fieldValue = "";
			switch (column) {
			case "EmpName":
				fieldValue = employee.getEmpName();
				break;
			case "Age":
				fieldValue = String.valueOf(employee.getAge());
				break;
			case "Designation":
				fieldValue = employee.getDesignation();
				break;
			case "Department":
				fieldValue = employee.getDepartment();
				break;
			case "ReportingTo":
				fieldValue = employee.getReportingTo();
				break;
			}

			boolean match = false;
			switch (condition) {
			case "Equals":
				match = fieldValue.equals(value);
				break;
			case "NotEquals":
				match = !fieldValue.equals(value);
				break;
			case "Contains":
				match = fieldValue.contains(value);
				break;
			case "NotContains":
				match = !fieldValue.contains(value);
				break;
			case "StartsWith":
				match = fieldValue.startsWith(value);
				break;
			case "EndsWith":
				match = fieldValue.endsWith(value);
				break;
			}

			if (match) {
				searchResults.add(employee);
			}
		}

		System.out.println("Search Results:");
		if (searchResults.isEmpty()) {
			System.out.println("No matching records found.");
		} else {
			displayEmployees(searchResults);
		}
	}

	public void searchRecordsWithMultipleConditions(List<String> columns, List<String> conditions,
			List<String> values) {
		List<Employee> searchResults = new ArrayList<>();
		for (Employee employee : employees) {
			boolean match = true;
			for (int i = 0; i < columns.size(); i++) {
				String column = columns.get(i);
				String condition = conditions.get(i);
				String value = values.get(i);

				String fieldValue = "";
				switch (column) {
				case "EmpName":
					fieldValue = employee.getEmpName();
					break;
				case "Age":
					fieldValue = String.valueOf(employee.getAge());
					break;
				case "Designation":
					fieldValue = employee.getDesignation();
					break;
				case "Department":
					fieldValue = employee.getDepartment();
					break;
				case "ReportingTo":
					fieldValue = employee.getReportingTo();
					break;
				}

				boolean columnMatch = false;
				switch (condition) {
				case "Equals":
					columnMatch = fieldValue.equals(value);
					break;
				case "NotEquals":
					columnMatch = !fieldValue.equals(value);
					break;
				case "Contains":
					columnMatch = fieldValue.contains(value);
					break;
				case "NotContains":
					columnMatch = !fieldValue.contains(value);
					break;
				case "StartsWith":
					columnMatch = fieldValue.startsWith(value);
					break;
				case "EndsWith":
					columnMatch = fieldValue.endsWith(value);
					break;
				}

				if (!columnMatch) {
					match = false;
					break;
				}
			}

			if (match) {
				searchResults.add(employee);
			}
		}

		System.out.println("Search Results:");
		if (searchResults.isEmpty()) {
			System.out.println("No matching records found.");
		} else {
			displayEmployees(searchResults);
		}
	}

	public void printReportingTree(String empName) {
		Employee employee = findEmployeeByName(empName);
		if (employee == null) {
			System.out.println("Employee not found.");
			return;
		}

		System.out.println("Reporting Tree for " + empName + ":");
		printReportingTreeRecursive(employee, 0);
	}

	private void printReportingTreeRecursive(Employee employee, int level) {
		StringBuilder indent = new StringBuilder();
		for (int i = 0; i < level; i++) {
			indent.append("  ");
		}

		System.out.println(indent.toString() + employee.getEmpName());

		List<Employee> subordinates = findEmployeesByReportingTo(employee.getEmpName());
		for (Employee subordinate : subordinates) {
			printReportingTreeRecursive(subordinate, level + 1);
		}
	}

	public void printEmployeesReportingTo(String managerName) {
		List<Employee> subordinates = findEmployeesByReportingTo(managerName);

		System.out.println("Employees reporting to " + managerName + ":");
		if (subordinates.isEmpty()) {
			System.out.println("No employees found.");
		} else {
			displayEmployees(subordinates);
		}
	}

	public void printSummary() {
		int totalEmployees = employees.size();

		System.out.println("Summary:");
		System.out.println("Total Employees: " + totalEmployees);

		System.out.println("Departments:");
		List<String> departments = new ArrayList<>();
		for (Employee employee : employees) {
			if (!departments.contains(employee.getDepartment())) {
				departments.add(employee.getDepartment());
			}
		}
		for (String department : departments) {
			int count = countEmployeesByDepartment(department);
			System.out.println("- " + department + ": " + count);
		}

		System.out.println("Designations:");
		List<String> designations = new ArrayList<>();
		for (Employee employee : employees) {
			if (!designations.contains(employee.getDesignation())) {
				designations.add(employee.getDesignation());
			}
		}
		for (String designation : designations) {
			int count = countEmployeesByDesignation(designation);
			System.out.println("- " + designation + ": " + count);
		}

		System.out.println("ReportingTo:");
		List<String> reportingTo = new ArrayList<>();
		for (Employee employee : employees) {
			if (!reportingTo.contains(employee.getReportingTo())) {
				reportingTo.add(employee.getReportingTo());
			}
		}
		for (String manager : reportingTo) {
			int count = countEmployeesByReportingTo(manager);
			System.out.println("- " + manager + ": " + count);
		}
	}

	private Employee findEmployeeByName(String empName) {
		for (Employee employee : employees) {
			if (employee.getEmpName().equals(empName)) {
				return employee;
			}
		}
		return null;
	}

	private List<Employee> findEmployeesByReportingTo(String managerName) {
		List<Employee> subordinates = new ArrayList<>();
		for (Employee employee : employees) {
			if (employee.getReportingTo().equals(managerName)) {
				subordinates.add(employee);
			}
		}
		return subordinates;
	}

	private int countEmployeesByDepartment(String department) {
		int count = 0;
		for (Employee employee : employees) {
			if (employee.getDepartment().equals(department)) {
				count++;
			}
		}
		return count;
	}

	private int countEmployeesByDesignation(String designation) {
		int count = 0;
		for (Employee employee : employees) {
			if (employee.getDesignation().equals(designation)) {
				count++;
			}
		}
		return count;
	}

	private int countEmployeesByReportingTo(String managerName) {
		int count = 0;
		for (Employee employee : employees) {
			if (employee.getReportingTo().equals(managerName)) {
				count++;
			}
		}
		return count;
	}

	private void displayEmployees(List<Employee> employeesToDisplay) {
		System.out.println("Employee Records:");
		System.out.println("--------------------------------------------------");
		System.out.printf("| %-15s | %-4s | %-12s | %-10s | %-15s |\n", "EmpName", "Age", "Designation", "Department",
				"ReportingTo");
		System.out.println("--------------------------------------------------");
		for (Employee employee : employeesToDisplay) {
			System.out.printf("| %-15s | %-4d | %-12s | %-10s | %-15s |\n", employee.getEmpName(), employee.getAge(),
					employee.getDesignation(), employee.getDepartment(), employee.getReportingTo());
		}
		System.out.println("--------------------------------------------------");
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		EmployeeRecordManagementApp app = new EmployeeRecordManagementApp();

		while (true) {
			System.out.println("Employee Record Management App");
			System.out.println("1. Display all Employee records");
			System.out.println("2. Search records based on criteria");
			System.out.println("3. Print reporting tree of an employee");
			System.out.println("4. Print employees reporting to a manager");
			System.out.println("5. Print summary");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume the newline character

			switch (choice) {
			case 1:
				app.displayAllEmployees();
				break;
			case 2:
				System.out.println("Search records based on criteria");
				System.out.println("Available Columns: EmpName, Age, Designation, Department, ReportingTo");
				System.out.print("Enter the column: ");
				String column = scanner.nextLine();
				System.out.println("Available Conditions:");
				System.out.println("1. String - Equals, NotEquals, Contains, NotContains, StartsWith, EndsWith");
				System.out.println("2. Integer - <, >, =, !=, Between");
				System.out.print("Enter the condition: ");
				String condition = scanner.nextLine();
				System.out.print("Enter the value: ");
				String value = scanner.nextLine();
				app.searchRecords(column, condition, value);
				break;
			case 3:
				System.out.print("Enter the employee name: ");
				String empName = scanner.nextLine();
				app.printReportingTree(empName);
				break;
			case 4:
				System.out.print("Enter the manager name: ");
				String managerName = scanner.nextLine();
				app.printEmployeesReportingTo(managerName);
				break;
			case 5:
				app.printSummary();
				break;
			case 6:
				System.out.println("Exiting the program...");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}

			System.out.println();
		}
	}
}
