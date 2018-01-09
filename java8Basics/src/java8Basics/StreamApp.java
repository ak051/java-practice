package java8Basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

public class StreamApp {

	public static void main(String[] args) {
		List<Integer> listOfInteger = Arrays.asList(-3,2,4,4,-5,1);
		Stream<Integer> stream = listOfInteger.stream();
		
		Comparator<Integer> comparator1 = (num1, num2) -> {
			return num1.compareTo(num2);

		};
		
		
		listOfInteger.sort(comparator1.thenComparing(Comparator.reverseOrder()));
		listOfInteger.stream().forEach(num -> System.out.println(num));
		//Comparator.comparing
		
		FunctionalInterfaceDemo obj = new FunctionalInterfaceDemoImpl();
		
		FunctionalInterfaceDemoImpl.staticDisplay();
		obj.defaultDisplay();
	
		

	}

}
