#include <stdlib.h>
#include <cstring>
#include <X11/X.h>
#include <X11/Xlib.h>

class IGNWindow
{
private:
    Display *display;
    Window *window;

public:
    IGNWindow(char *title, int x, int y, int width, int height);
    ~IGNWindow();
};
