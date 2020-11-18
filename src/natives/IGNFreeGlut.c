#include <string.h>
#include <jni.h>
#include <GL/freeglut.h>
#include "org_igrok_net_ignfreeglut_IGNFreeGlut.h"

JNIEXPORT void JNICALL Java_org_igrok_1net_ignfreeglut_IGNFreeGlut_glutInit(JNIEnv *env, jclass jcl, jobjectArray args, jint argsc)
{    
    char * argsvp[argsc];
    for(int i=0; i < argsc; i++){
        jstring element = (jstring)(*env)->GetObjectArrayElement(env,args,i);
        const char *argsC = (*env)->GetStringUTFChars(env, element, 0);
        strcpy(argsvp[i],argsC);
    }
    int argscp = (int)argsc;
    glutInit(&argscp, argsvp);
}