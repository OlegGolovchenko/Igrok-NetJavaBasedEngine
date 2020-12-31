#include "IGNEngine.h"

class IGNColor
{
    private:
        float r,g,b,a;
    public:
        IGNColor(float r, float g, float b, float a);
        void ApplyColor();
        ~IGNColor();
};