#include "IGNWindow.h"
#include "org_igrok_net_engine_ui_IGNWindow.h"

JNIEXPORT jlong JNICALL Java_org_igrok_1net_engine_ui_IGNWindow_CreateNativeWindow(JNIEnv *env, jobject jobj, jstring title, jint x, jint y, jint width, jint height)
{
    jsize titleSize = env->GetStringUTFLength(title);
    char ctitle[titleSize];
    const char *jtitle = env->GetStringUTFChars(title, NULL);
    strcpy(ctitle, jtitle);
    return (jlong) new IGNWindow(ctitle, x, y, width, height);
    return 0;
}

JNIEXPORT void JNICALL Java_org_igrok_1net_engine_ui_IGNWindow_DestroyWindow(JNIEnv *env, jobject jobj, jlong wndPtr)
{
    IGNWindow *window = (IGNWindow *)wndPtr;
    if (window != NULL)
    {
        delete window;
    }
}

IGNWindow::IGNWindow(char *title, int x, int y, int width, int height)
{
    this->display = XOpenDisplay(NULL);
    if (this->display == NULL)
    {
        printf("\n\tcannot connect to X server\n\n");
    }
}

IGNWindow::~IGNWindow()
{
    if (this->display != NULL)
    {
        XCloseDisplay(this->display);
        this->display = NULL;
    }
}