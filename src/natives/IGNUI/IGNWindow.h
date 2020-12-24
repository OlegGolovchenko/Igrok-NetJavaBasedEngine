#include "../IGNEngine.h"

class IGNWindow
{
private:
    Window root;
    XVisualInfo *vi;
    GLint att[5] = {GLX_RGBA, GLX_DEPTH_SIZE, 24, GLX_DOUBLEBUFFER, None};
    Colormap cmap;
    XSetWindowAttributes *swa;
    GLXContext glc;

public:
    Display *display;
    Window window;
    bool running;
    IGNWindow(const char *title, int x, int y, int width, int height);
    bool IsRunning();
    static int IsSelectedEvent(Display * dipslay,XEvent * event, XPointer args);
    ~IGNWindow();
};
