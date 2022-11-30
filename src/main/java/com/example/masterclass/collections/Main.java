package com.example.masterclass.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.findYoungestEmployeeByStream();	
	}
	
	private void sortCollection() {
		MovieTheater theater = new MovieTheater("York", 5, 12);	
		theater.printSeats();
		
		theater.reserveSeat("H01");
		theater.reserveSeat("A01");
		theater.reserveSeat("A01");
		
		Collections.max(theater.getSeats());
		Collections.min(theater.getSeats());
		
		Collections.sort(theater.getSeats(), MovieTheater.PRICE_SORT);
		theater.printSeats();
		
		// Shallow copy. Changes to 1 array = changes in both
		ArrayList<String> colors = new ArrayList<String>(Arrays.asList("gold", "yellow", "green", "blue", "orange"));
		List<String> newColors = new ArrayList<String>(colors);
		newColors.add("brown");	// should add/remove from both arrays but that is not how this is working....
		
		colors.forEach(x -> { System.out.print(x+" "); });
		System.out.println();
		
		newColors.forEach(x -> { System.out.print(x+ " "); });
		System.out.println();
	}
	
	public void lambdaTest_Sort() {
		// Sort list and change first char in name to upper case using Lambda
		List<String> topName2015 = Arrays.asList(
				"Amelia", "Oliver", "henry", "emily", "Ilsa", "Ava", "oliver", "Jack", "Charlie");
		
		List<String> upperCaseName = new ArrayList<>();
		topName2015.forEach(name -> upperCaseName.add(name.substring(0, 1).toUpperCase() + name.substring(1)));
		upperCaseName.sort((s1, s2) -> s1.compareTo(s2));
		upperCaseName.forEach(name -> System.out.println(name));
	}
	
	/**
	 * Lambdas can be used with functional interfaces (@FunctionalInterface). Functional Interfaces have only 1 method.
	 */
	private void LambdaAndPredicate() {
		
		// Lambdas for sort
		// Lambdas available for Interfaces with 1 method
		MovieTheater york = new MovieTheater("York", 5, 12);	
		MovieTheater marcus = new MovieTheater("Marcus", 4, 10);	
		MovieTheater chicago = new MovieTheater("Chicago", 10, 15);	
		MovieTheater cinemark = new MovieTheater("Cinemark", 5, 12);	
		
		List<MovieTheater> theaters = new ArrayList<MovieTheater>();
		theaters.add(york);
		theaters.add(marcus);
		theaters.add(chicago);
		theaters.add(cinemark);
		
		Collections.sort(theaters, (theater1, theater2) -> 
			theater1.getTheaterName().compareTo(theater2.getTheaterName()));
		
		// Lambda to change the consumer object's name
		//String name = "new Name";
	//	theaters.forEach( theat -> theat.setName("New Name") );	
	//	theaters.forEach( theat -> System.out.println(theat.getTheaterName()) );
		
		// Use Predicate to Test a condition, repeatedly
		System.out.println("Theaters with > 100 seats");
		System.out.println("===================");
		printTheatersBySeats(theaters, theat -> theat.getNbrSeats() > 100);
		System.out.println("Theaters with <= 100 seats");
		System.out.println("===================");
		printTheatersBySeats(theaters, theat -> theat.getNbrSeats() <= 100);
		
		IntPredicate greaterthan15 = i -> i > 15;
		System.out.println( greaterthan15.test(10) );
		
	}
	
	private void findYoungestEmployeeByStream() {
		this.getEmployees()
		.stream()
		.reduce( (e1, e2) -> e1.getAge() < e2.getAge() ? e1 : e2 )
		.ifPresent(System.out::println);
		
		// Flat Map example
		Department dept1 = new Department("hr");
		dept1.setEmployees(this.getEmployees());
		Department dept2 = new Department("tech");
		dept2.setEmployees(getEmployees());
		
		List<Department> depts = new ArrayList<>();
		depts.add(dept1);
		depts.add(dept2);
		
		// Do something with a collection from another object
		depts
		.stream()
		.flatMap(department -> department.getEmployees().stream())
		.sorted();
	}
	
	private void StreamsExample() {
		List<String> bigNumbers = Arrays.asList(
				"A1", "B1", "E52", 
				"G52", "G80", "G90",
				"I24", "I17", "I77");
		
		List<String> gNumbers = new ArrayList<>();
		bigNumbers.forEach(number -> {
			if (number.startsWith("G"))
				gNumbers.add(number);
		});
		
		// Stream will not change the source (bigNumbers) and must be stateless
		// Stream to save to new value via collect
		List<String> gToNumbers = bigNumbers
			.stream()
			.map(String::toUpperCase)	// map accepts a function and returns a string (String returned from upperCase method). Method references cannot take an argument.
			.filter(s-> s.startsWith("G"))
			.sorted()
			.collect(Collectors.toList());
		//	.forEach(System.out::println);
		
		gToNumbers.forEach(n -> System.out.println(n));
		
		// Grouping
		Map<Object, List<String>> groupByLetter = bigNumbers
				.stream()
				.collect(Collectors.groupingBy(s -> s.substring(0, 1)));
				
		Stream.of("ABC", "DDDD", "EE", "GGG", "F", "POOP")
		.filter(s -> { return s.length() == 3; })
		.count();
	
	}
	
	private void testLambdas(String source) {
		Function<String, String> lambda = s -> {
			StringBuilder sb = new StringBuilder();
			for (int i=0; i < source.length(); i++) {
				if (i % 2 == 0)
					sb.append(s.charAt(i));
			}
			return sb.toString();
		};
		
		lambda.apply("1234567890");
		
		// supplier expects a return value but must use Get to get the returned value
		Supplier<String> iLoveJava = () -> "I love Java";
		String result = iLoveJava.get();
	}
	
	private String applyFunctionTest(Function<String, String> func) {
		return func.apply("1234567890");
	}
	
	private List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<>();
		
		employees.add(new Employee("Red RidingHood", 25));
		employees.add(new Employee("Wonder Women", 17));
		employees.add(new Employee("Big Bird", 55));
		employees.add(new Employee("Bat Man", 80));
		employees.add(new Employee("Princ Charming", 32));
		
		return employees;
	}
	
	private void FunctionUtilExample() {
		List<Employee> employees = this.getEmployees();
		
		// Functions are handy when slightly different needs depending on scenario
		Function<Employee, String> getLastName = (Employee e1) -> {
			return e1.getName().substring(e1.getName().indexOf(' ') + 1);
		};
		
		Function<Employee, String> getFirstName = (Employee e1) -> {
			return e1.getName().substring(0, e1.getName().indexOf(' '));
		};
		
		Random random = new Random();
		for (Employee emp : employees) {
			if (random.nextBoolean())
				System.out.println(getAName(getFirstName, emp));
			else
				System.out.println(getAName(getLastName, emp));
		}
		
		// BiFunction accepts 2 arguments and returns 1 object
		BiFunction<String, Employee, String> getPrefixName = (String name, Employee e) -> {
			return name + " " + e.getName();
		};
		System.out.println(getPrefixName.apply("Mrs.", employees.get(0)));
		
		// Unary operator
		IntUnaryOperator incBy5 = i -> i + 5;
		System.out.println(incBy5.applyAsInt(10)); // prints 15
		
	}
	
	private static String getAName(Function<Employee, String> getName, Employee emp) {
		return getName.apply(emp);
	}
	
	public static void printTheatersBySeats(List<MovieTheater> theaters, Predicate<MovieTheater> seatCondition) {
		//System.out.println(seatCondition.toString());
		for (MovieTheater theater : theaters) {
			if (seatCondition.test(theater)) {
				System.out.println(theater.getTheaterName());
			}
		}
	}

}
