package com.example.masterclass.inheritence;

public class Main {

	public static void main(String[] args) {
		Dog dog = new Dog("Yorkie");
		dog.eat();
		dog.breathe();
		
		Bird bird = new Bird("Pigeon");
		bird.eat();
		bird.breathe();
		bird.fly();

	}

}
