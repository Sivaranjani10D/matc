package com.staticc;

 class Stat {
	static int count=4;
	Stat()
		{
		count++;
		System.out.println(count);
		}
		public static void main(String args[])
		{
			Stat A=new Stat();
			Stat B=new Stat();
			Stat C=new Stat();

}}
