/*
	Created in: December 15, 2016
	Author: Cauim de Souza Lima (cauimsouza@gmail.com)
	Description: Generates a list of random numbers in the interval [0,1)
*/

import java.io.*;

public class NumbersGenerator{
	public static void main(String[] args) throws IOException{
		FileWriter file = new FileWriter("numbers.txt", false);
		PrintWriter printer = new PrintWriter(file);
		int num_numbers = Integer.parseInt(args[0]);
		printer.println(num_numbers);
		for(int i = 0; i < num_numbers; i++){
			printer.println(Math.random());
		}
		file.close();
	}
} 