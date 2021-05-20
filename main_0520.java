package matrix_calculation;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		
	
	// read in the data
		
	Scanner s;
	s = new Scanner(new File("C:\\Users\\m-zim\\Desktop\\Masterarbeit\\Python\\matrix_time-independent_2017to2019_delete.txt"));
	int array_size = 117;
	double[][] matrix = new double[array_size][array_size];
	
	
	for (int i = 0; i < matrix.length; i++) {
		for (int j = 0; j < matrix.length; j++) {
			matrix[i][j] = Double.parseDouble(s.next());
		}
	}
	
	for (int i = 0; i < matrix.length; i++) {
		for (int j = 0; j < matrix.length; j++) {
			//System.out.println(array[i][j]);
			if (matrix[i][j] == 0.00) {
				matrix[i][j] = 999;
			}
		}
	}
	
	
	for (int i = 0; i < matrix.length ; i++) {
		System.out.println(Arrays.toString(matrix[i]));
	}
	
	
	// shortest path
	double compare = 0;
	int helper_start = 0;
	int helper_middle = 0;
	int depth = 0;
	int[] way = new int[array_size];
	double[] way_length = new double[array_size];
	
	
	for (int i = 0; i < matrix.length; i++) {
		for (int j = 0; j < matrix.length; j++) {
			helper_start = i;
			helper_middle = 0;
			way[0] = i;
			depth = 0;
			compare = matrix[i][j];
			
			
			// Get back if everything was searched
			while (depth != 0 || helper_middle != matrix.length) {
				
				// helper_middle stays in range of array
				while (helper_middle >= matrix.length && depth > 0) {
					way[depth] = 0;
					depth -= 1;
					helper_start = way[depth - 1];
					way[depth] += 1;
					helper_middle = way[depth];
				}
				
				//Does not check for equality in array so far
				
				if((matrix[helper_start][helper_middle] + way_length[depth] < compare) && (helper_start != helper_middle)) {
					
					// Check for destination
	                if (matrix[helper_start][helper_middle] + matrix[helper_middle][j] + way_length[depth] < compare) {
	                    compare = matrix[helper_start][helper_middle] + matrix[helper_middle][j] + way_length[depth];
	                    //System.out.println(i+ j + "Found quicker: depth" + depth + "helper_i"+helper_start+ "helper_k "+ helper_middle + compare);
	                }
	                
	                depth += 1;
	                way_length[depth] = Math.round((way_length[depth - 1] + matrix[helper_start][helper_middle])* 100.0) / 100.0;
	                way[depth] = helper_middle;
	                helper_start = helper_middle;
	                helper_middle = 0;           
	                System.out.println("Get deeper: depth " +  depth +  " helper_i "  + helper_start + " helper_k " +  helper_middle + Arrays.toString(way));
					
					
				}
				
			helper_middle += 1;
	        way[depth] = helper_middle;
			
					
			}
			
	    if (compare == 999) {
	    	matrix[i][j] = 0;
	    } else {
	    	matrix[i][j] = Math.round(compare* 100.0) / 100.0;
	    }
	            
	    	System.out.println(i + " " + j);
			
	    
	    	System.out.println(Arrays.deepToString(matrix));
	    
		}
	}
	
	int zeros = 0;
	for (int i = 0; i < matrix.length; i++) {
		for (int j = 0; j < matrix.length; j++) {
			if (matrix[i][j] == 0) {
				zeros += 1;
			}
		}
	}
	System.out.println(zeros);
	}
}
	