package com.bhushan.kafka.model;


import java.io.Serializable;


public class SuperObject implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String email;
    private String messageBody;
    private String firstName;
    private String lastName;

    public SuperObject() { }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SuperObject [email=" + email + ", messageBody=" + messageBody + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

	
  
}