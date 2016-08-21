package com.rhwayfun.examples.clone;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class Employee implements Cloneable,Comparable<Employee>{
	
	private static int nextid;

	private int id;
	private String name;
	private double salary;
	private Date hireday;

	static{
		Random generator = new Random();
		nextid = generator.nextInt(10000);
	}

	{
		id = nextid;
		nextid++;
	}
	
	public Employee(String n , double s){
		this.name = n;
		this.salary = s;
		hireday = new Date();
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	/**
	 * @return the hireday
	 */
	public Date getHireday() {
		return hireday;
	}

	/**
	 * @param hireday the hireday to set
	 */
	public void setHireday(Date hireday) {
		this.hireday = hireday;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Employee clone() throws CloneNotSupportedException{

		Employee cloned = (Employee) super.clone();
		cloned.hireday = (Date) hireday.clone();
		return cloned;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee [hireday=" + hireday + ", id=" + id + ", name=" + name
				+ ", salary=" + salary + "]";
	}

	public void setHireday(int i, int j, int k) {
		Date date = new GregorianCalendar(i,j-1,k).getTime();
		hireday.setTime(date.getTime());
	}

	public int compareTo(Employee o) {
		return this.name.compareTo(o.name);
	}
	
}
