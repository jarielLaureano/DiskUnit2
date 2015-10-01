package Program;
import java.io.File;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.InvalidParameterException;
import java.util.Random;

import Exceptions.ExistingDiskException;
import Exceptions.InvalidBlockException;
import Exceptions.InvalidBlockNumberException;
import Exceptions.NonExistingDiskException;
import Exceptions.*;
/**
 * 
 * @author Jariel
 * Class that contains the implementation of a physical disk
 */
public class DiskUnit {
	
//-----------------------Instance Fields--------------------------------
private final static int DEF_CAPACITY=1024;
private final static int DEF_BLOCK_SIZE=256;
private static int capacity, blockSize;
private RandomAccessFile disk;

//-----------------------Constructors------------------------------------



/**
 * name is the name of the disk
**/
private DiskUnit(String name) {
 try {
     disk = new RandomAccessFile(name, "rw");
 }
 catch (IOException e) {
     System.err.println ("Unable to start the disk");
     System.exit(1);
 }
}



//-------------------------Operations Methods-----------------------------------------




/**
 * Read the first nibble of the storage and gets the capacity
 * @return capacity(number of blocks on the storage area)
 */
public int getCapacity(){
	return Utils.readINT(disk, 0);
}



/**
 * Get the size of each block that belongs to the Disk
 * by reading the second nibble of the storage area
 * @return blocksize
 */
public int  getBlockSize(){
	return Utils.readINT(disk,4);
}



/**
 * write the data that belongs to the virtual block on the physical storage area
 * @param blockNum -block of the physical storage where the data of the virtual block is wanted to be written 
 * @param b -virtual block that contains the data which is wanted to be written
 * @throws InvalidBlockNumberException -thrown whenever the block number received is not valid
    for the current disk instance
 * @throws InvalidBlockException -thrown whenever b does not represent a valid disk
    block for the current disk instance
 */
public void write(int blockNum, VirtualDiskBlock b)throws InvalidBlockNumberException,InvalidBlockException{
	
	if(blockNum<0||blockNum>getCapacity()){
		throw new InvalidBlockNumberException(blockNum);
	}
	if(b.getCapacity()!=getBlockSize()){
		throw new InvalidBlockException(b);
	}
	Utils.seekPosition(disk, getBlockSize()*blockNum);
	for(int i=0;i<b.getCapacity();i++){
		Utils.writeByte(disk, b.getElement(i));
	}
}


/**
 * read the data on the physical storage, and copy it to the virtual block given
 * @param blockNum -block of the physical storage where the data is wanted to be read
 * @param b -virtual block where the data read is copied 
 * @throws InvalidBlockNumberException -thrown whenever the block number received is not valid
    for the current disk instance
 * @throws InvalidBlockException -thrown whenever b does not represent a valid disk
    block for the current disk instance
 */
public void read(int blockNum, VirtualDiskBlock b)throws InvalidBlockNumberException,InvalidBlockException{
	
	if(blockNum<0||blockNum>getCapacity()){
		throw new InvalidBlockNumberException(blockNum);
	}
	if(b.getCapacity()!=getBlockSize()){
		throw new InvalidBlockException(b);
	}
	Utils.seekPosition(disk, blockNum*getBlockSize());
	for(int i=0;i<b.getCapacity();i++){
		b.setElement(i, Utils.readByte(disk));
	}
	
	
}


/**
 * Low level format just empty the positions on the disk unit
 * going block by block initializing with zero the bytes on each block
 */
public void lowLevelFormat(){
	int capacity= getCapacity();
	int  blockSize=getBlockSize();
	
	Random random=new Random();
	
	for(int i=1;i<capacity;i++){
		Utils.seekPosition(disk, i*blockSize);
		if(random.nextInt(100000)==1){
		   for(int j=0;j<blockSize;j++){
			Utils.writeByte(disk, (byte)-1);
		    }
		}
		else{
			for(int j=0;j<blockSize;j++){
				Utils.writeByte(disk, (byte)0);
			}
		}
	}
}

//-----------------------New Methods Added----------------------
/**
 * Simulates the process of turning of a disk unit
 */
public void shutdown() {
	  try {
	      disk.close();
	  } catch (IOException e) {
	      e.printStackTrace();
	  }
	}



/**
* Turns on an existing disk unit.
* @param name the name of the disk unit to activate
* @return the corresponding DiskUnit object
* @throws NonExistingDiskException whenever no
*    ¨disk¨ with the specified name is found.
*/
public static DiskUnit mount(String name)
throws NonExistingDiskException
{
  File file=new File(name);
  if (!file.exists())
      throw new NonExistingDiskException("No disk has name : " + name);
 
  DiskUnit dUnit = new DiskUnit(name);
      
  // get the capacity and the block size of the disk from the file
  // representing the disk
  try {
      dUnit.disk.seek(0);
      dUnit.capacity = dUnit.disk.readInt();
      dUnit.blockSize = dUnit.disk.readInt();
  } catch (IOException e) {
      e.printStackTrace();
  }
      
  return dUnit;         
}


/**
 * Creates a new disk unit with the given name. The disk is formatted
 * as with the specified capacity (number of blocks), each of specified
 * size (number of bytes).  The created disk is left as in off mode.
 * @param name the name of the file that is to represent the disk.
 * @param capacity number of blocks in the new disk
 * @param blockSize size per block in the new disk
 * @throws ExistingDiskException whenever the name attempted is
 * already in use.
 * @throws InvalidParameterException whenever the values for capacity
 *  or blockSize are not valid according to the specifications
*/
public static void createDiskUnit(String name, int capacity, int blockSize)
throws ExistingDiskException, InvalidParameterException
{
    File file=new File(name);
    if (file.exists())
       throw new ExistingDiskException("Disk name is already used: " + name);
       
    RandomAccessFile disk = null;
    if (capacity < 0 || blockSize < 0 ||
         !Utils.isPower2(capacity) || !Utils.isPower2(blockSize))
       throw new InvalidParameterException("Invalid values: " +
              " capacity = " + capacity + " block size = " +
              blockSize);
    // disk parameters are valid... hence create the file to represent the

    // disk unit.
    try {
        disk = new RandomAccessFile(name, "rw");
    }
    catch (IOException e) {
        System.err.println ("Unable to start the disk");
        System.exit(1);
    }

    reserveDiskSpace(disk, capacity, blockSize);
      
    // after creation, just leave it in shutdown mode - just
    // close the corresponding file
    try {
        disk.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}



/***
 * Creates a new disk unit with the given name. The disk is formatted
 * as having default capacity (number of blocks), each of default
 * size (number of bytes). Those values are: DEFAULT_CAPACITY and
 * DEFAULT_BLOCK_SIZE. The created disk is left as in off mode.
 * @param name the name of the file that is to represent the disk.
 * @throws ExistingDiskException whenever the name attempted is
 * already in use.
*/
public static void createDiskUnit(String name)
throws ExistingDiskException
{
    createDiskUnit(name, DEF_CAPACITY, DEF_BLOCK_SIZE);
}




private static void reserveDiskSpace(RandomAccessFile disk, int capacity,
        int blockSize)
{
try {
disk.setLength(blockSize * capacity);
} catch (IOException e) {
e.printStackTrace();
}

// write disk parameters (number of blocks, bytes per block) in
// block 0 of disk space
try {
disk.seek(0);
disk.writeInt(capacity);  
disk.writeInt(blockSize);
} catch (IOException e) {
e.printStackTrace();
}

}
}
