package com.winterbe.java8.samples.misc;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
         //Use case for flatMap in caseof Stream
		
		List<List<String>> phoneList=Arrays.asList(
			 Arrays.asList("a"),
			 Arrays.asList("b")
			 
		 );
		System.out.println( "Test By FlatMap in case with Stream"); 
	    System.out.println(	 phoneList.stream()
		          .flatMap(Collection::stream)
		          .collect(Collectors.toList()));
		
	    
	    
	    
	    
	    
	   
	}

}
