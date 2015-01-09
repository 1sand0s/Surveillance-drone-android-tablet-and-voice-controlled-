#include<jni.h>
#include "survserver.h"
#include<stdio.h>
JNIEXPORT void JNICALL Java_survserver_start1(JNIEnv *env, jclass cls)

{
	FILE* f;
	f=fopen("/dev/ttyACM0","w");
	fprintf(f,"%d",1);
	fclose(f);
	return ;
}
