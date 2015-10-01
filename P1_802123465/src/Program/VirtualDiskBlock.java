package Program;
import Exceptions.*;
/**
 * 
 * @author Jariel
 *  Class that contains the implementation of a virtual disk block
 */
public class VirtualDiskBlock {
	
//-----------------------------Instances---------------------------------
private byte data[];
private static final int DEF_CAPACITY=256;
private int capacity;


//-----------------------------Constructors------------------------------

/**
 * Default constructor
 * construct a virtual block of 256 bytes
 * */
public VirtualDiskBlock(){
	data= new byte[DEF_CAPACITY];
	for(int i=0;i<data.length;i++){
		data[i]=0;
	}
	capacity=DEF_CAPACITY;
}

/**
 * Construct a virtual block with the given capacity
 * @param blockCapacity -capacity with which the virtual block is constructed 
 */
public VirtualDiskBlock(int blockCapacity){
	data= new byte[blockCapacity];
	for(int i=0;i<data.length;i++){
		data[i]=0;
	}
	capacity=blockCapacity;
}



//-----------------------------Operations--------------------------------------
/**
 * Replace the byte that was on the given index for the new one
 * @param index -position were the wanted data is located on the virtual block
 * @param nuevo -its the new byte that substitute the old byte located in the given position
 * @throws IndexOutOfBoundsException -throws when the index is not a valid position on the virtual block
 */
public void setElement(int index,byte nuevo) throws IndexOutOfBoundsException{
	data[index]=nuevo;
}


/**
 * Get the byte that is located on the given index
 * @param index -position where the data is located on the virtual block
 * @return return the byte located on the given index
 * @throws IndexOutOfBoundsException -throws when the index is not a valid position on the virtual block
 */
public byte getElement(int index)throws IndexOutOfBoundsException{
	
	return data[index];
}

/**
 * Get the capacity of the virtual block
 * @return the capacity of the virtual block
 */
public int getCapacity(){
	return capacity;
}

}
