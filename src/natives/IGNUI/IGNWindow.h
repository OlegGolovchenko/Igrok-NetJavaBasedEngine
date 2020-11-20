#include <stdlib.h>
#include <cstring>
#include <X11/X.h>
#include <X11/Xlib.h>
#include <GL/glx.h>

class IGNWindow
{
private:
    Display *display;
    Window window;
    Window root;
    XVisualInfo *vi;
    GLint att[5] = {GLX_RGBA, GLX_DEPTH_SIZE, 24, GLX_DOUBLEBUFFER, None};
    Colormap cmap;
    XSetWindowAttributes *swa;
    GLXContext glc;
    bool running;

public:
    IGNWindow(const char *title, int x, int y, int width, int height);
    void MainLoop();
    ~IGNWindow();
};
