package Program;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 
 * @author Jariel
 * Class with helpful methods to manage better the RandomAccessFile
 */
public class Utils {
	
	
//-------------------------------Utilities---------------------------------	
	
//Verify that the given number is a power of 2
/**
 * 
 * @param coe the number to verify if it is power of 2
 * @return true if the number is power of 2, else return false
 */
public static boolean isPower2(int coe){
	return auxPower(coe);
}



//auxiliary method
/**
 * Auxiliary method of isPower2
 * @param coe the coefficient by dividing it by 2
 * @return true if its power of 2, false if its not.
 */
private static boolean auxPower(int coe){
	if(coe==1){
		return true;
	}
	
	if(coe%2==0){
		coe=coe/2;
		return auxPower(coe);
	}
	else{
		return false;
	}
	
}


//--------------------------------- Random Access File Manage-------------------------------------------------

//Writes initially the number of bytes on the the storage. initializing all with zeros
/**
 * Initialize, also can be use to format 
 * @param file represents the random access file that represents the physical disk storage
 * @param bytesNum number of bytes that are going to be reserved on the Disk 
 */
public static void initWrite(RandomAccessFile file, int bytesNum){
	int count=0;
	try{
		while(count<bytesNum){
			file.writeByte(0);
			count++;
		}
		
	}catch(IOException e){
		System.out.println("Unable to initialize blocks of bytes!");
	}
	
}




//Write the byte in the given position
/**
 * 
 * @param file represents the random access file that represents the physical disk storage
 * @param pos position on the file where the byte is going to be written 
 * @param element the element that is going to be written on the given position
 */
public static void writeByte(RandomAccessFile file, int pos, int element){
	try{
		seekPosition(file,pos);
		file.writeByte(element);
	}catch(IOException e){
		System.out.println("The element can't be writed successfully");
	}
}



//Look for the position in the file
/**
 * 
 * @param file represents the random access file that represents the physical disk storage
 * @param pos position where we want to set the pointer on the file
 */
public static void seekPosition(RandomAccessFile file,int pos){
	try{
		file.seek(pos);
	}catch(IOException e){
		System.out.println("Unable to seek position");
	}
	
	
}



//Read the byte on the given position
/**
 * 
 * @param file represents the random access file that represents the physical disk storage
 * @param pos location on the file where the byte is wanted to be read
 * @return
 */
public static byte readByte(RandomAccessFile file, int pos){
	byte n=0;
	try{ seekPosition(file,pos);
		return file.readByte();
	}catch(IOException e){
		System.out.println("Unable to read!");
	}
	return n;
}


//Write a integer on the given position
/**
 * 
 * @param file represents the random access file that represents the physical disk storage
 * @param pos location where the integer is wanted to be written
 * @param element to be written on the given position
 */
public static void writeIntto(RandomAccessFile file,int pos, int element){
	try{seekPosition(file,pos);
		file.writeInt(element);
	}catch(IOException e){
		System.out.println("Can't write");
	}
}



//Read the integer that is located on the given position of the storage
/**
 * 
 * @param file represents the random access file that represents the physical disk storage
 * @param pos position for the integer to be read of
 * @return the integer was read on the position
 */
public static int readINT(RandomAccessFile file, int pos){
	int n=0;
	try{ seekPosition(file,pos);
		return file.readInt();
	}catch(IOException e){
		System.out.println("Unable to read!");
	}
	return n;
}


/**
 * 
 * @param file
 * @param element
 */
public static void writeByte(RandomAccessFile file, byte element){
	try{
		file.writeByte(element);
	}catch(IOException e){
		System.out.println("The element can't be writed successfully");
	}
}


public static byte readByte(RandomAccessFile file){
	byte n=0;
	try{ 
		return file.readByte();
	}catch(IOException e){
		System.out.println("Unable to read!");
	}
	return n;
}
}
