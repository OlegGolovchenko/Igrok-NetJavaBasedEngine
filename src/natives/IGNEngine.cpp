#include "IGNEngine.h"
#include "org_igrok_net_engine_IGNEngine.h"

JNIEXPORT void JNICALL Java_org_igrok_1net_engine_IGNEngine_init(JNIEnv *env, jclass jcl, jobjectArray args)
{
    jsize arraySize = env->GetArrayLength(args);
    char *argsvp[arraySize];
    for (int i = 0; i < arraySize; i++)
    {
        jobject arrayElement = env->GetObjectArrayElement(args, i);
        jstring element = (jstring)arrayElement;
        const char *argsChars = env->GetStringUTFChars(element, 0);
        jsize argSize = env->GetStringUTFLength(element);
        char result[argSize];
        strcpy(result, argsChars);
        argsvp[i] = result;
        env->ReleaseStringUTFChars(element, argsChars);
        element = NULL;
    }
    int argscp = sizeof(argsvp);
    glutInit(&argscp, argsvp);
}

JNIEXPORT void JNICALL Java_org_igrok_1net_engine_IGNEngine_printString2D(JNIEnv *env, jclass jcl, jint x, jint y, jfloat r, jfloat g, jfloat b, jfloat a, jstring text)
{
    const char* textChars = env->GetStringUTFChars(text,NULL);
    const unsigned char * printeableTextChars = reinterpret_cast<const unsigned char *>(textChars);
    glRasterPos2i(x, y);
    glColor4f(r, g, b, a);
    glutBitmapString(GLUT_BITMAP_9_BY_15, printeableTextChars);
    glLoadIdentity();
    printeableTextChars = NULL;
    env->ReleaseStringUTFChars(text,textChars);
}

JNIEXPORT void JNICALL Java_org_igrok_1net_engine_IGNEngine_RenderQuad(JNIEnv *env, jclass jcl, jint x, jint y, jint w, jint h, jfloat r, jfloat g, jfloat b, jfloat a, jfloat br, jfloat bg, jfloat bb, jfloat ba)
{
    glBegin(GL_QUADS);
    glColor4f(r, g, b, a);
    glVertex2i(x, y);    
    glColor4f(r, g, b, a);
    glVertex2i(x + w, y);    
    glColor4f(r, g, b, a);
    glVertex2i(x + w, y + h);    
    glColor4f(r, g, b, a);
    glVertex2i(x, y + h);
    glEnd();
    glBegin(GL_LINES);
    glColor4f(br, bg, bb, ba);
    glVertex2i(x, y);
    glVertex2i(x + w - 1, y);
    glEnd();
    glBegin(GL_LINES);
    glColor4f(br, bg, bb, ba);
    glVertex2i(x + w - 1, y);
    glVertex2i(x + w - 1, y + h - 1);
    glEnd();
    glBegin(GL_LINES);
    glColor4f(br, bg, bb, ba);
    glVertex2i(x + w - 1, y + h - 1);
    glVertex2i(x, y + h - 1);
    glEnd();
    glBegin(GL_LINES);
    glColor4f(br, bg, bb, ba);
    glVertex2i(x, y + h - 1);
    glVertex2i(x, y);
    glEnd();
}