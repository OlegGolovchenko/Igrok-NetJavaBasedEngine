/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_igrok_net_engine_ui_IGNWindow */

#ifndef _Included_org_igrok_net_engine_ui_IGNWindow
#define _Included_org_igrok_net_engine_ui_IGNWindow
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_igrok_net_engine_ui_IGNWindow
 * Method:    createNativeWindow
 * Signature: (Ljava/lang/String;IIII)J
 */
JNIEXPORT jlong JNICALL Java_org_igrok_1net_engine_ui_IGNWindow_createNativeWindow
  (JNIEnv *, jobject, jstring, jint, jint, jint, jint);

/*
 * Class:     org_igrok_net_engine_ui_IGNWindow
 * Method:    destroyWindow
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_igrok_1net_engine_ui_IGNWindow_destroyWindow
  (JNIEnv *, jobject, jlong);

/*
 * Class:     org_igrok_net_engine_ui_IGNWindow
 * Method:    mainLoop
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_igrok_1net_engine_ui_IGNWindow_mainLoop
  (JNIEnv *, jobject, jlong);

#ifdef __cplusplus
}
#endif
#endif
