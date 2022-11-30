package com.example.masterclass.inheritence;

public class Bird extends Animal implements Fly {

	public Bird(String name) {
		super(name);
	}

	@Override
	public void eat() {
		System.out.println(this.name + " is Pecking");	
	}

	@Override
	public void breathe() {
		System.out.println(this.name + " Breathe In, Breathe Out");	
	}

	@Override
	public void fly() {
		System.out.println(this.name + " is flapping");
	}

}
