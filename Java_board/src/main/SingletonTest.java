package main;

public class SingletonTest {
	public static void main(String[] args) {
		Singleton singleton = Singleton.getInstance();
		System.out.println(singleton);
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton2);
		
		Prototype prototype = new Prototype();
		System.out.println(prototype);
		Prototype prototype2 = new Prototype();
		System.out.println(prototype2);
	}
}
