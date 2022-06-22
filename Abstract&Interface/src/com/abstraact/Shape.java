package com.abstraact;

abstract class TestShapes {
abstract void draw();
}
class circle extends TestShapes
{
	void draw()
	{
		System.out.println("drawing circle");
	}}
class Rectangle extends TestShapes
	{ 
		void draw()
		{
			System.out.println("drawing rectangle");
		}}
class Shape
{
	public static void main(String args[]) 
	{
TestShapes s;
s=new circle();
s.draw();
s=new Rectangle();
		s.draw();
		
		
	}

}
