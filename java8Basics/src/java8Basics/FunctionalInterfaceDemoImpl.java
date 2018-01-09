package java8Basics;

public class FunctionalInterfaceDemoImpl implements FunctionalInterfaceDemo {

	@Override
	public void display() {
		System.out.println("Class Demo");

	}
	
	static void staticDisplay() {
		System.out.println("Static Display");
	}
	
	public void defaultDisplay() {
		System.out.println("default of Class Display");
	}
}
