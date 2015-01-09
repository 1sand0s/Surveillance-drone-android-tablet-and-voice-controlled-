
#include <jni.h>
/* Header for class survserver */

#ifndef _Included_survserver
#define _Included_survserver
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     survserver
 * Method:    start1
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_survserver_start1
  (JNIEnv *, jclass);

/*
 * Class:     survserver
 * Method:    search
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_survserver_search
  (JNIEnv *, jclass, jstring);

/*
 * Class:     survserver
 * Method:    front
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_survserver_front
  (JNIEnv *, jclass);

/*
 * Class:     survserver
 * Method:    back
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_survserver_back
  (JNIEnv *, jclass);

/*
 * Class:     survserver
 * Method:    left
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_survserver_left
  (JNIEnv *, jclass);

/*
 * Class:     survserver
 * Method:    right
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_survserver_right
  (JNIEnv *, jclass);

/*
 * Class:     survserver
 * Method:    cam
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_survserver_cam
  (JNIEnv *, jclass);

#ifdef __cplusplus
}
#endif
#endif
