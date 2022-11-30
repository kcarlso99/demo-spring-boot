package com.example.masterclass.inheritence;

public class Dog extends Animal {

	public Dog(String name) {
		super(name);
	}

	@Override
	public void eat() {
		System.out.println(this.name +  " Chomp Chomp");	
	}

	@Override
	public void breathe() {
		System.out.println(this.name +  " Breathe In, Breathe Out");	
	}

}
