package com.abstinterface;

 interface Bike{
	void run();
	}
	class cycle implements Bike
	{
		public void run()
		{
			System.out.println("running cycle");
		}}
	class Bicycle implements Bike
		{ 
			public void run()
			{
				System.out.println("running bicycle");
			}}
	class Interface
	{
		public static void main(String args[]) 
		{
			Bike s=new Bicycle();
			s.run();
			
		}

	}



