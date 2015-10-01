Disk Unit User Guide:

Setup:

  Open the prompt or terminal, and write the following commands to Compile Succesfully:
 1) cd "Name of Directory where file was unziped"
 2) cd P1_4035_802123465_151
 3) cd P1_802123465
 4) cd src
 5) move Program Testers
 6) move Exceptions Testers
 7) cd Testers
 8) javac DiskUnitTester0.java
 9) javac DiskUnitTester1.java
 10) move Program ..
 11) move Exceptions ..
 12) cd ..
 
 Now you are ready to run the program, the first one that we want to run is the DiskUnitTester0
 DiskUnitTester0 creates the disk emulation files needed to run DiskUnitTester1.So for acomplish 
 this write the following while we are right now located on the src directory:

 1) java Testers.DiskUnitTester0 

 The files are supposed to be created by this time. There are 5 files in total representing 5 diks units wich names are as follows:
 disk1, disk2, disk3, disk4 , disk5.
 
 Now we want to look what is inside of each disk, so for that it is necessary to run DiskUnitTester1.
 for acomplish this write the following while we are right now located on the src directory: 
 
   1) java Testers.DiskUnitTester1 nameOfDiskWantedToUse 

  We are passing by argument to the program the name of the Disk we want to use.          

  For example if we want to use the disk1, the instruction will be as follows:

     java Testers.DiskUnitTester1 disk1

  By now you can run any disk with the command above.

  