package com.rhwayfun.examples.io;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * 
 * <p>Title:Employee</p>
 * <p>Description:雇员类</p>
 * @author rhwayfun
 * @date Sep 12, 2015 4:48:20 PM
 * @version 1.0
 */
public class Employee {

	private String name;
	private double salary;
	private Date hireDay;
	
	public Employee(String name, double salary,int year,int month,int day){
		this.name = name;
		this.salary = salary;
		GregorianCalendar gc = new GregorianCalendar(year,month - 1,day);
		hireDay = gc.getTime();
	}
	
	public Employee() {
		// TODO Auto-generated constructor stub
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
	 * @return the hireDay
	 */
	public Date getHireDay() {
		return hireDay;
	}
	/**
	 * @param hireDay the hireDay to set
	 */
	public void setHireDay(Date hireDay) {
		this.hireDay = hireDay;
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee [hireDay=" + hireDay + ", name=" + name + ", salary="
				+ salary + "]";
	}

	//向文件中写入数据的方法
	public void writeData(PrintWriter out){
		//写入的格式为name|salary|year|month|day
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(hireDay);
		out.println(name + "|" + salary + "|" + calendar.get(Calendar.YEAR) 
				+ "|" + calendar.get(Calendar.MONTH) + "|" + calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	//从文件中读取雇员信息
	public void readData(Scanner in){
		String line = in.nextLine();
		String[] tokens = line.split("\\|");
		name = tokens[0];
		salary = Double.parseDouble(tokens[1]);
		int y = Integer.parseInt(tokens[2]);
		int m = Integer.parseInt(tokens[3]);
		int d = Integer.parseInt(tokens[4]);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(y, m - 1, d);
		hireDay = calendar.getTime();
	}
}
