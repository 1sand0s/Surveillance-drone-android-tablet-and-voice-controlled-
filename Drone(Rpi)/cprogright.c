#include<jni.h>
#include "survserver.h"
#include<stdio.h>
JNIEXPORT void JNICALL Java_survserver_right(JNIEnv* env,jclass j)
{
	FILE* f;
	f=fopen("/dev/ttyACM0","w");
	fprintf(f,"%d",5);
	fclose(f);
	return;
}
