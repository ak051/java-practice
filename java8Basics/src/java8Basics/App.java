package java8Basics;

public class App {

	public static void main(String[] args) {
		int b = 5;
		Process process = (a) -> {
			System.out.println(a + b);
		};
		process.display(5);
		

	}
	
	private static void execute() {
		
		System.out.println("Execute Method.");
		
	}

	@FunctionalInterface
	interface Process {
		void display(int a);
	}

}
