package Exceptions;

public class NonExistingDiskException extends RuntimeException {

	public NonExistingDiskException(String msg){
		System.out.println(msg);
	}
}
