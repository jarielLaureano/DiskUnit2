package Exceptions;

import Program.VirtualDiskBlock;

public class InvalidBlockException extends RuntimeException {
	
public InvalidBlockException( VirtualDiskBlock b){
	System.out.println("Invalid block capacity= "+b.getCapacity());
}

}
