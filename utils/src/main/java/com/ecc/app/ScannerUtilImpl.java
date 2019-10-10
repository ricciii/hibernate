package com.ecc.app;

import java.util.Scanner;
import java.util.InputMismatchException;

public class ScannerUtilImpl implements ScannerUtil {
	
	Scanner scanner;
	
	public ScannerUtilImpl() {
		scanner = new Scanner(System.in);
	}

	public ScannerUtilImpl(Scanner scanner) {
		this.scanner = scanner;
	}

	public String getString() {
		String string = scanner.nextLine();
		if(string.equals("")) {
            string = null;
        }
		return string;
	}

	public Integer getInt() {
		boolean done = false;
		Integer integer = 0;
		do {
			try {
				integer = scanner.nextInt();
				scanner.nextLine();
				done = true;
			} catch(InputMismatchException mismatch) {
				System.out.print("Input should be of Integer type: ");
				scanner.nextLine();
			}
		} while(done==false);
		return integer;
	}

	public boolean getBoolean() {
		boolean bool = false;
		boolean done = false;
		do {
			try {
				bool = scanner.nextBoolean();
				done = true;
			} catch(InputMismatchException mismatch) {
				System.out.print("Input should be of Boolean type [true/false]: ");
				scanner.nextLine();
			}
		} while(done==false);
		scanner.nextLine();
		return bool;
	}

	public float getFloat() {
		boolean done = false;
		float floating = 0;
		do {
			try {
				floating = scanner.nextFloat();
				scanner.nextLine();
				done = true;
			} catch(InputMismatchException mismatch) {
				System.out.print("Input must be a real number: ");
				scanner.nextLine();
			}
			
		} while(done==false);
		return floating;
	}
}