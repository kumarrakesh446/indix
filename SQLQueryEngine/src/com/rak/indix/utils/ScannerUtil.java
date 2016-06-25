package com.rak.indix.utils;

import java.util.Scanner;

public class ScannerUtil {
	public static Scanner scanner=new Scanner(System.in);
	
	public int getRangeInt(int min, int max) {
		int val=0;
		do{
		try {
			System.out.println("Please enter value between "+min +" And "+max+":");
		val=	scanner.nextInt();
			
		} catch (Exception e) {
			scanner.next();
			System.out.println("Please enter valid value:");
		}
		
		}while(min>val&&max<val);
		return val;
	}
	
	public int getNextInt() {
		int val=0;
		do{
		try {
			
		val=	scanner.nextInt();
		break;
			
		} catch (Exception e) {
			scanner.next();
			System.out.println("Please enter valid Number:");
		}
		
		}while(true);
		return val;
	}
	
	public String getNextString() {
		
			String out="";
		while((out=scanner.nextLine() )== null||out.isEmpty());
		return out;
	}
	
	public String getNext() {

		return scanner.next();
	}
	
	public boolean getNextBoolean() {
		boolean val=false;
		do{
		try {
			
		val=	scanner.nextBoolean();
		break;
			
		} catch (Exception e) {
			scanner.next();
			System.out.println("Please enter valid boolean(True/False):");
		}
		
		}while(true);
		return val;
	}
}
