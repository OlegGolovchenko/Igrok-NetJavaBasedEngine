#include <stdio.h>
#include <jni.h>
#include "org_igrok_net_engine_ui_NativeWindow.h"

JNIEXPORT jlong JNICALL Java_org_igrok_1net_engine_ui_NativeWindow_createWindow
  (JNIEnv *env, jobject jobj, jstring title, jint x, jint y){
      printf("Test\n");
      return 0L;
  }