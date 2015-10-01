package Exceptions;

public class ExistingDiskException extends RuntimeException {

	public ExistingDiskException(String msg){
		System.out.println(msg);
	}
}
