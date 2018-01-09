package java8Basics;

@FunctionalInterface
public interface FunctionalInterfaceDemo {
	
	void display();
	
	static void staticDisplay() {
		System.out.println("Static Display");
	}
	
	default void defaultDisplay() {
		System.out.println("Static Display");
	}

}
