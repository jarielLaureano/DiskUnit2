package Exceptions;

public class InvalidBlockNumberException extends  RuntimeException {
public InvalidBlockNumberException(int blockNum){
	System.out.println("Invalid number of block index= "+blockNum);
}
}
