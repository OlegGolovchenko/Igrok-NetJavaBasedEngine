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

JNIEXPORT void JNICALL Java_org_igrok_1net_engine_IGNEngine_printString2D(JNIEnv *env, jclass jcl, jint x, jint y, jfloat r, jfloat g, jfloat b, jfloat a, jstring text, jlong font)
{
    const char *textChars = env->GetStringUTFChars(text, NULL);
    const unsigned char *printeableTextChars = reinterpret_cast<const unsigned char *>(textChars);
    glRasterPos2i(x, y);
    glColor4f(r, g, b, a);
    glutBitmapString((void*)font, printeableTextChars);
    glLoadIdentity();
    printeableTextChars = NULL;
    env->ReleaseStringUTFChars(text, textChars);
}

JNIEXPORT jint JNICALL Java_org_igrok_1net_engine_IGNEngine_measureString(JNIEnv *env, jclass jcl, jlong font, jstring text)
{
    int result = 0;
    const char *textChars = env->GetStringUTFChars(text, NULL);
    const unsigned char *printeableTextChars = reinterpret_cast<const unsigned char *>(textChars);
    result = glutBitmapLength((void *)font, printeableTextChars);
    printeableTextChars = NULL;
    env->ReleaseStringUTFChars(text, textChars);
    return result;
}

JNIEXPORT void JNICALL Java_org_igrok_1net_engine_IGNEngine_RenderQuad(JNIEnv *env, jclass jcl, jint x, jint y, jint w, jint h, jfloat r, jfloat g, jfloat b, jfloat a, jfloat br, jfloat bg, jfloat bb, jfloat ba)
{
    glBegin(GL_LINES);
    glColor4f(br, bg, bb, ba);
    glVertex2i(x, y);
    glColor4f(br, bg, bb, ba);
    glVertex2i(x + w, y);
    glEnd();
    glBegin(GL_LINES);
    glColor4f(br, bg, bb, ba);
    glVertex2i(x + w, y);
    glColor4f(br, bg, bb, ba);
    glVertex2i(x + w, y + h);
    glEnd();
    glBegin(GL_LINES);
    glColor4f(br, bg, bb, ba);
    glVertex2i(x + w, y + h);
    glColor4f(br, bg, bb, ba);
    glVertex2i(x, y + h);
    glEnd();
    glBegin(GL_LINES);
    glColor4f(br, bg, bb, ba);
    glVertex2i(x, y + h);
    glColor4f(br, bg, bb, ba);
    glVertex2i(x, y);
    glEnd();
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
}

JNIEXPORT jlong JNICALL Java_org_igrok_1net_engine_IGNEngine_GLUT_1STROKE_1ROMAN(JNIEnv *env, jclass jcl)
{
    return (jlong)GLUT_STROKE_ROMAN;
}

JNIEXPORT jlong JNICALL Java_org_igrok_1net_engine_IGNEngine_GLUT_1STROKE_1MONO_1ROMAN(JNIEnv *env, jclass jcl)
{
    return (jlong)GLUT_STROKE_MONO_ROMAN;
}

JNIEXPORT jlong JNICALL Java_org_igrok_1net_engine_IGNEngine_GLUT_1BITMAP_19_1BY_115(JNIEnv *env, jclass jcl)
{
    return (jlong)GLUT_BITMAP_9_BY_15;
}

JNIEXPORT jlong JNICALL Java_org_igrok_1net_engine_IGNEngine_GLUT_1BITMAP_18_1BY_113(JNIEnv *env, jclass jcl)
{
    return (jlong)GLUT_BITMAP_8_BY_13;
}

JNIEXPORT jlong JNICALL Java_org_igrok_1net_engine_IGNEngine_GLUT_1BITMAP_1TIMES_1ROMAN_110(JNIEnv *env, jclass jcl)
{
    return (jlong)GLUT_BITMAP_TIMES_ROMAN_10;
}

JNIEXPORT jlong JNICALL Java_org_igrok_1net_engine_IGNEngine_GLUT_1BITMAP_1TIMES_1ROMAN_124(JNIEnv *env, jclass jcl)
{
    return (jlong)GLUT_BITMAP_TIMES_ROMAN_24;
}

JNIEXPORT jlong JNICALL Java_org_igrok_1net_engine_IGNEngine_GLUT_1BITMAP_1HELVETICA_110(JNIEnv *env, jclass jcl)
{
    return (jlong)GLUT_BITMAP_HELVETICA_10;
}

JNIEXPORT jlong JNICALL Java_org_igrok_1net_engine_IGNEngine_GLUT_1BITMAP_1HELVETICA_112(JNIEnv *env, jclass jcl)
{
    return (jlong)GLUT_BITMAP_HELVETICA_12;
}

JNIEXPORT jlong JNICALL Java_org_igrok_1net_engine_IGNEngine_GLUT_1BITMAP_1HELVETICA_118(JNIEnv *env, jclass jcl)
{
    return (jlong)GLUT_BITMAP_HELVETICA_18;
}