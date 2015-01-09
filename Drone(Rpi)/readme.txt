1.Run the program on the drone.
2.Connect to the drone server either from laptop or tablet.

the survserver.java program encapsulates all the functions of the drone into a single java file, i decided to use 'c'
for communicating with the arduino and implementing opencv ,since 'C' is way more efficeint than java and also due to
the fact that when it comes to embedded systems processor time and resources is off utmost importance.JNI(java native 
interface) allows translation of 'C' functions thereby allowing them to respond to java method calls.

.so files being compiled on raspbian may not work on other Rpi OS's.