package java8Basics;

public class OuterClass {

	public static void main(String[] args) {
		OuterClassDefault.InnerClassNonStatic obj = new OuterClassDefault.InnerClassNonStatic();

	}

}

class OuterClassDefault {

	private static String nameOuter = "Outer class";

	 static class InnerClassNonStatic {

		private String name = "Inner class non static";

		public InnerClassNonStatic() {
			// System.out.println(nameOuter);
			System.out.println(this.name);

		}
	}

}
