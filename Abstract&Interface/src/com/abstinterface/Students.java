package com.abstinterface;
interface Exam
{
	void name();
	void rollno();
}
interface Login
{
	void id();
	void pwd();
}
interface View{
	void age();
}
interface Register extends Exam,Login,View
{
	void dob();
}
class Students implements Register
{
	public void name() 
	{
		System.out.println("student name is:siva");
	}
	public void rollno() {
		System.out.println("student rollno is:1201069");
	}
		public void id() {
				System.out.println("student name id is:ABC123");
		}
		public void pwd()
		{
				System.out.println("student pwd is:****");			
}
		public void dob()
		{
				System.out.println("student dob is:4.11.94");
		}
		public void age() {
			System.out.println("student age is:29");
		}

	public static void main(String args[])
	{
	Students a=new Students();
		a.name();
		a.rollno();
		a.id();
		a.pwd();
		a.dob();
	}
}


