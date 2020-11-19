#include <stdio.h>
#include <string.h>
#include <jni.h>
#include <GL/freeglut.h>
#include "org_igrok_net_ignfreeglut_IGNFreeGlut.h"

JNIEXPORT void JNICALL Java_org_igrok_1net_ignfreeglut_IGNFreeGlut_glutInitNative(JNIEnv *env, jclass jcl, jobjectArray args)
{
    jsize arraySize = (*env)->GetArrayLength(env, args);
    char *argsvp[arraySize];
    for (int i = 0; i < arraySize; i++)
    {
        jstring element = (jstring)(*env)->GetObjectArrayElement(env, args, i);
        const char *argsChars = (*env)->GetStringUTFChars(env, element, 0);
        jsize argSize = (*env)->GetStringUTFLength(env, element);
        char result[argSize];
        strcpy(result, argsChars);
        argsvp[i] = result;
        (*env)->ReleaseStringUTFChars(env, args, argsChars);
        element = NULL;
    }
    int argscp = (int)arraySize;
    glutInit(&argscp, argsvp);
}

JNIEXPORT void JNICALL Java_org_igrok_1net_ignfreeglut_IGNFreeGlut_glutInitWindowPositionNative(JNIEnv *env, jclass jcl, jint x, jint y)
{
    glutInitWindowPosition(x, y);
}

JNIEXPORT void JNICALL Java_org_igrok_1net_ignfreeglut_IGNFreeGlut_glutInitWindowSizeNative(JNIEnv *env, jclass jcl, jint width, jint height)
{
    glutInitWindowSize(width, height);
}