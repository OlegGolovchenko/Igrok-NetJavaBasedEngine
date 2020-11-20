#include "IGNWindow.h"
#include "../org_igrok_net_engine_ui_IGNWindow.h"

JNIEXPORT jlong JNICALL Java_org_igrok_1net_engine_ui_IGNWindow_CreateNativeWindow(JNIEnv *env, jobject jobj, jstring title, jint x, jint y, jint width, jint height)
{
    const char *jtitle = env->GetStringUTFChars(title, NULL);
    jlong result = (jlong) new IGNWindow(jtitle, x, y, width, height);
    env->ReleaseStringUTFChars(title, jtitle);
    return result;
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

JNIEXPORT void JNICALL Java_org_igrok_1net_engine_ui_IGNWindow_MainLoop(JNIEnv *env, jobject jobj, jlong wndPtr)
{

    IGNWindow *window = (IGNWindow *)wndPtr;
    if (window != NULL)
    {
        window->MainLoop();
    }
}

IGNWindow::IGNWindow(const char *title, int x, int y, int width, int height)
{
    this->display = XOpenDisplay(NULL);
    if (this->display == NULL)
    {
        printf("\n\tcannot connect to X server\n\n");
    }
    this->root = DefaultRootWindow(this->display);
    this->vi = glXChooseVisual(this->display, 0, this->att);

    if (this->vi == NULL)
    {
        printf("\n\tno appropriate visual found\n\n");
    }
    else
    {
        printf("\n\tvisual %p selected\n", (void *)this->vi->visualid); /* %p creates hexadecimal output like in glxinfo */
    }
    this->cmap = XCreateColormap(this->display, this->root, this->vi->visual, AllocNone);
    this->swa = new XSetWindowAttributes();
    this->swa->colormap = this->cmap;
    this->swa->event_mask = ExposureMask | KeyPressMask | ButtonPressMask | PointerMotionMask;
    this->window = XCreateWindow(this->display, this->root, x, y, width, height, 0, this->vi->depth, InputOutput, this->vi->visual, CWColormap | CWEventMask, this->swa);
    XMapWindow(this->display, this->window);
    XStoreName(this->display, this->window, title);
    glc = glXCreateContext(this->display, this->vi, NULL, GL_TRUE);
    glXMakeCurrent(this->display, this->window, glc);
    glEnable(GL_DEPTH_TEST);
    Atom vmDeleteMessage = XInternAtom(this->display, "WM_DELETE_WINDOW", GL_FALSE);
    XSetWMProtocols(this->display, this->window, &vmDeleteMessage, GL_TRUE);
    this->running = true;
}

void IGNWindow::MainLoop()
{
    XEvent *xev = new XEvent();
    while (this->running)
    {
        XNextEvent(this->display, xev);

        if (xev->type == Expose)
        {
            XWindowAttributes *gwa = new XWindowAttributes();
            XGetWindowAttributes(this->display, this->window, gwa);
            glViewport(0, 0, gwa->width, gwa->height);
            glXSwapBuffers(this->display, this->window);
        }

        else if (xev->type == KeyPress)
        {
        }

        else if (xev->type == ClientMessage)
        {
            long int wmDeleteMessage = XInternAtom(this->display, "WM_DELETE_WINDOW", False);
            if (xev->xclient.data.l[0] == wmDeleteMessage)
            {
                this->running = false;
            }
        }
    }
}

IGNWindow::~IGNWindow()
{
    if (this->display != NULL)
    {
        glXMakeCurrent(this->display, None, NULL);
        if (this->glc != NULL)
        {
            glXDestroyContext(this->display, this->glc);
        }
        XDestroyWindow(this->display, this->window);
        XCloseDisplay(this->display);
        this->display = NULL;
    }
}