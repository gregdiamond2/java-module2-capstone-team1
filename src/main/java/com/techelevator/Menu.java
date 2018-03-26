package com.techelevator;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption <= options.length && selectedOption > 0) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will
			// be null
		}
		if (choice == null) {
			out.println("\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}

	public String getNameFromUserInput() {
		System.out.println("What name should the reservation be made under? \n");
		String userInput = in.nextLine();
		return userInput;
	}

	public LocalDate getUserDateInput(String message) {
		LocalDate choice = null;
		while (true) {
			System.out.println(message);
			String userInput = in.nextLine();
			if (userInput.equals("0")) {
				choice = LocalDate.parse("1000-01-01");
				break;
			} else {
				try {
					
					choice = LocalDate.parse(userInput);
					if(userInput.compareTo(LocalDate.now().toString()) < 0) {
						out.println("\n*** Unless you own a Delorean, that date won't work. " + userInput + " is not a valid option ***\n");
						out.flush();
					} else
					break;
				} catch (DateTimeParseException e) {
					// eat the exception, an error message will be displayed below since choice will
					// be null
				} 
				
				if (choice == null) {
					out.println("\n*** " + userInput + " is not a valid option ***\n");
					out.flush();

				}
			}
		}
		return choice;
	}

}
