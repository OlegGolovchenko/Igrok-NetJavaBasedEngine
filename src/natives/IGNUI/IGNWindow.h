#include "../IGNEngine.h"
#include "IGNComponent.h"

class IGNWindow
{
private:
    Window root;
    XVisualInfo *vi;
    GLint att[5] = {GLX_RGBA, GLX_DEPTH_SIZE, 24, GLX_DOUBLEBUFFER, None};
    Colormap cmap;
    XSetWindowAttributes *swa;
    GLXContext glc;
    std::list<IGNComponent *>* components;
    IGNLabel * fpsLabel;
    void AddFpsLabel(int fps, void * font);
public:
    Display *display;
    Window window;
    bool running;
    IGNWindow(const char *title, int x, int y, int width, int height);
    bool IsRunning();
    void RenderUI();
    void SetFps(int fps);
    static int IsSelectedEvent(Display * dipslay,XEvent * event, XPointer args);
    ~IGNWindow();
};
