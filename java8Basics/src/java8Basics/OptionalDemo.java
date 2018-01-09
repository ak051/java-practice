package java8Basics;

import java.util.Optional;

public class OptionalDemo {
	
	public CustomerData getCustomerData () {
		
		CustomerData customerData = new CustomerData();
		customerData.setCustomerId("1");
		return null;
	}
	
	public Optional<CustomerData> getCustomerDataOptional() {

		CustomerData customerData = new CustomerData();
		customerData.setCustomerId("1");
		customerData = null;

		//Optional<CustomerData> optionalCustomer = Optional.ofNullable(customerData);
		
		Optional<CustomerData> optionalCustomer = Optional.of(customerData);
		return optionalCustomer;

	}

	public static void main(String[] args) {
		OptionalDemo optionalDemo= new OptionalDemo();
		// Get the Customer Object.
		CustomerData customerData = optionalDemo.getCustomerData();
		
		// If Customer Data is present get the customer Id.
		
		// Apply null check till Java 7.
		
		if (!(customerData == null)) {
			
			System.out.println("Customer Id: " + customerData.getCustomerId());

		}
		
		else {
			System.out.println("Customer Data not available");
		}
		
		// Or handle null pointer exception which is a runtime exception.
		
		try {
			System.out.println("Customer Id: " + customerData.getCustomerId());
		} catch (NullPointerException e) {
			System.out.println("Customer Data not available");

		}
		
		// Java 8 Optional class to avoid null checks
		
		Optional<CustomerData> customerDataOptional = optionalDemo.getCustomerDataOptional();
		
		if (customerDataOptional.isPresent()) {
			System.out.println("Customer Id: " + customerDataOptional.get().getCustomerId());
		}
		else {
			System.out.println("Customer Data not available");
		}

	}

}

class CustomerData {
	private String customerId;
	
	

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
