/*
	Created in: December 15, 2016
	Author: Cauim de Souza Lima (cauimsouza@gmail.com)
	Description: Generates a file with the times spent
		by a sorting algorithm to sort the numbers in
		a file. The time is evaluated 10 times.
*/

import java.io.*;

public class TimeToSort {
    public static void main(String [] args) {

        // The name of the file to open.
        String inputFileName = "numbers.txt";
        String outputFileName = "time.txt";

        // This will reference one line at a time
        String line = null;

        try {
        	FileWriter file = new FileWriter(outputFileName, true);
			PrintWriter printer = new PrintWriter(file);
            
            for(int k = 0; k < 10; k++){
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(inputFileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            int num_lines = new Integer(bufferedReader.readLine());
            Double[] arr = new Double[num_lines];
            for(int i = 0; i < num_lines; i++){
            	String num = bufferedReader.readLine();
            	arr[i] = Double.parseDouble(num);
            } 
            
            long startTime = System.nanoTime();

            Heap.sort(arr);

            long estimatedTime = System.nanoTime() - startTime;

            printer.println(estimatedTime); 

            // Always close files.
            bufferedReader.close();}
            file.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                inputFileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + inputFileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
}