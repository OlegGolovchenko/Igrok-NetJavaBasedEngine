#include "IGNWindow.h"
#include "../org_igrok_net_engine_ui_IGNWindow.h"

JNIEXPORT jlong JNICALL Java_org_igrok_1net_engine_ui_IGNWindow_createNativeWindow(JNIEnv *env, jobject jobj, jstring title, jint x, jint y, jint width, jint height)
{
    const char *jtitle = env->GetStringUTFChars(title, NULL);
    jlong result = (jlong) new IGNWindow(jtitle, x, y, width, height);
    env->ReleaseStringUTFChars(title, jtitle);
    return result;
    return 0;
}

JNIEXPORT void JNICALL Java_org_igrok_1net_engine_ui_IGNWindow_destroyWindow(JNIEnv *env, jobject jobj, jlong wndPtr)
{
    IGNWindow *window = (IGNWindow *)wndPtr;
    if (window != NULL)
    {
        delete window;
    }
}

JNIEXPORT void JNICALL Java_org_igrok_1net_engine_ui_IGNWindow_augmentFps(JNIEnv *env, jobject jobj, jlong wndPtr, jint fps)
{
    IGNWindow *window = (IGNWindow *)wndPtr;
    if (window != NULL)
    {
        window->currentFps = fps;
    }
}

JNIEXPORT void JNICALL Java_org_igrok_1net_engine_ui_IGNWindow_mainLoop(JNIEnv *env, jobject jobj, jlong wndPtr)
{
    jclass jcl = env->GetObjectClass(jobj);
    jmethodID onKeyPress = env->GetMethodID(jcl, "setKeyPress", "(JJLjava/lang/String;)V");
    jmethodID onKeyRelease = env->GetMethodID(jcl, "setKeyRelease","(JJLjava/lang/String;)V");
    jmethodID onMousePress = env->GetMethodID(jcl, "setMousePress","(J)V");
    jmethodID onMouseRelease = env->GetMethodID(jcl, "setMouseRelease","(J)V");
    jmethodID onMouseMoved = env->GetMethodID(jcl, "setMouseMoved","(II)V");
    jmethodID isUpdateNeeded = env->GetMethodID(jcl, "isUpdateNeeded","()I");
    jmethodID renderUiFunc = env->GetMethodID(jcl,"renderUIElements","()V");
    jmethodID render3DFunc = env->GetMethodID(jcl,"render3D","()V");

    IGNWindow *window = (IGNWindow *)wndPtr;
    if (window != NULL)
    {
        XWindowAttributes gwa;
        while (window->IsRunning())
        {
            jint result = env->CallIntMethod(jobj, isUpdateNeeded);
            XEvent xev;
            if (XCheckIfEvent(window->display, &xev, IGNWindow::IsSelectedEvent, NULL))
            {

                if (xev.type == Expose)
                {
                    XGetWindowAttributes(window->display, window->window, &gwa);
                    glClearColor(1, 1, 1, 1);
                }
                if (xev.type == KeyPress)
                {
                    XIM xim = XOpenIM(window->display, 0, 0, 0);
                    XIC xic = XCreateIC(xim, XNInputStyle, XIMPreeditNothing | XIMStatusNothing, NULL);
                    char buffer[2];
                    KeySym ignore;
                    Status return_status;
                    Xutf8LookupString(xic, &xev.xkey, buffer, sizeof(buffer), &ignore, &return_status);
                    buffer[2] = 0;
                    jstring charString = env->NewStringUTF(buffer);
                    env->CallVoidMethod(jobj, onKeyPress, xev.xkey.keycode, xev.xkey.keycode, charString);
                    XDestroyIC(xic);
                    XCloseIM(xim);
                }
                if (xev.type == KeyRelease)
                {
                    XIM xim = XOpenIM(window->display, 0, 0, 0);
                    XIC xic = XCreateIC(xim, XNInputStyle, XIMPreeditNothing | XIMStatusNothing, NULL);
                    char buffer[2];
                    KeySym ignore;
                    Status return_status;
                    Xutf8LookupString(xic, &xev.xkey, buffer, sizeof(buffer), &ignore, &return_status);
                    buffer[2] = 0;
                    jstring charString = env->NewStringUTF(buffer);
                    env->CallVoidMethod(jobj, onKeyRelease, xev.xkey.keycode, xev.xkey.keycode, charString);
                    XDestroyIC(xic);
                    XCloseIM(xim);
                }
                if (xev.type == ButtonPress)
                {
                    env->CallVoidMethod(jobj, onMousePress, xev.xbutton.button);
                }
                if (xev.type == ButtonRelease)
                {
                    env->CallVoidMethod(jobj, onMouseRelease, xev.xbutton.button);
                }
                if (xev.type == MotionNotify)
                {
                    env->CallVoidMethod(jobj, onMouseMoved, xev.xmotion.x, xev.xmotion.y);
                }
                if (xev.type == ClientMessage)
                {
                    long int wmDeleteMessage = XInternAtom(window->display, "WM_DELETE_WINDOW", False);
                    if (xev.xclient.data.l[0] == wmDeleteMessage)
                    {
                        window->running = false;
                    }
                }
            }
            glClearColor(1, 1, 1, 1);
            if (result)
            {
                window->SetFps();
                glViewport(0, 0, gwa.width, gwa.height);
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                glMatrixMode(GL_PROJECTION);
                glLoadIdentity();
                gluPerspective(70, (gwa.width / (gwa.height * 1.0)), -1, 1);
                glLoadIdentity();
                glClearColor(1, 1, 1, 1);
                env->CallVoidMethod(jobj, render3DFunc);
                glLoadIdentity();
                glLoadIdentity();
                glMatrixMode(GL_PROJECTION);
                glLoadIdentity();
                glOrtho(0, gwa.width, gwa.height, 0, -1, 1);
                glMatrixMode(GL_MODELVIEW);
                glLoadIdentity();
                env->CallVoidMethod(jobj, renderUiFunc);
                glLoadIdentity();
                glXSwapBuffers(window->display, window->window);
            }
        }
    }
}

int IGNWindow::IsSelectedEvent(Display *display, XEvent *event, XPointer args)
{
    return event->type == Expose ||
           event->type == KeyPress ||
           event->type == KeyRelease ||
           event->type == ButtonPress ||
           event->type == ButtonRelease ||
           event->type == MotionNotify ||
           event->type == ClientMessage;
}

IGNWindow::IGNWindow(const char *title, int x, int y, int width, int height)
{
    this->display = XOpenDisplay(NULL);
    if (this->display == NULL)
    {
        printf("\n\tcannot connect to X server\n\n");
    }
    else
    {
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
        this->swa->event_mask = ExposureMask | KeyPressMask | ButtonPressMask | PointerMotionMask | KeyReleaseMask | ButtonReleaseMask;
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
    this->currentFps = 0;
}

bool IGNWindow::IsRunning()
{
    return this->running;
}

void IGNWindow::SetFps(){
    std::string fpsString = std::to_string(this->currentFps);
    fpsString += " fps";
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