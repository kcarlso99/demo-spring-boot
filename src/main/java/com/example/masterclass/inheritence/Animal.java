package com.example.masterclass.inheritence;

public abstract class Animal {
	public String name;

	public Animal(String name) {
		this.name = name;
	}
	
	/** Extending Classes must implement these */
	public abstract void eat();
	public abstract void breathe();

	public String getName() {
		return name;
	}
	
}
