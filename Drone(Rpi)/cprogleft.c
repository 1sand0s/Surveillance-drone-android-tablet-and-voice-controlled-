#include<jni.h>
#include "survserver.h"
#include<stdio.h>
JNIEXPORT void JNICALL Java_survserver_left(JNIEnv* env,jclass j)
{
	FILE* f;
	f=fopen("/dev/ttyACM0","w");
	fprintf(f,"%d",4);
	fclose(f);
	return;
}
