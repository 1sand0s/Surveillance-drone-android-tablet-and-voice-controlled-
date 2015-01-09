#include<jni.h>
#include "survserver.h"
#include<stdio.h>
#include<opencv/cv.h>
#include<opencv/cxcore.h>
#include<opencv/highgui.h>
JNIEXPORT void JNICALL Java_survserver_cam(JNIEnv* env,jclass j)
{
	CvCapture* cap;
	const int* a=95;
	IplImage* img;
	IplImage* img2;
	
	cvNamedWindow("hello",CV_WINDOW_AUTOSIZE);
	cap=cvCreateCameraCapture(CV_CAP_ANY);
	img=cvQueryFrame(cap);
	img2=cvCreateImage(cvSize(img->width,img->height),IPL_DEPTH_8U,1);
	if(cap==NULL)
	{
		printf("ERROR");
		return -1;
	}
	while(1)
	{
		img=cvQueryFrame(cap);
		cvSaveImage("Desktop/img.jpg",img,a);
		char ch=cvWaitKey(20);
		if(ch==27)break;
	}
	cvReleaseCapture(&cap);
	cvReleaseImage(&img);
	return;
}
