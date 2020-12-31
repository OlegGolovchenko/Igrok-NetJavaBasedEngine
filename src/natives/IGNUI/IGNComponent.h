#include "../IGNEngine.h"
#include "../IGNColor.h"

class IIGNComponent
{
    public:
        virtual void Render() = 0;
        virtual void RenderAt(int x, int y) = 0;
};

class IGNComponent: public IIGNComponent
{
    protected:
        int x, y, width, height;
    public:
        IGNComponent(
            int x, 
            int y, 
            int width, 
            int height
        );
        virtual void Render();
        virtual void RenderAt(int x, int y);
        virtual ~IGNComponent();
};

class IGNLabel : public IGNComponent
{
    private:
        std::string text;
        void * font;
        IGNColor * color;
    public:
        IGNLabel(
            int x,
            int y,
            int width,
            int height,
            std::string text,
            IGNColor * color,
            void * font
        );
        void SetText(std::string text);
        virtual void RenderAt(int x, int y);
        virtual ~IGNLabel();
};