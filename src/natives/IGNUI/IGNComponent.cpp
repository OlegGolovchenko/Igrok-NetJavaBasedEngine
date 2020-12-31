#include "IGNComponent.h"

IGNComponent::IGNComponent(
    int x,
    int y,
    int width,
    int height)
{
    this->x = x;
    this->y = y;
    this->width = width;
    this->height = height;
}

void IGNComponent::Render(){
    this->RenderAt(0,0);
}

void IGNComponent::RenderAt(int x, int y){
}

IGNComponent::~IGNComponent(){
    this->x = 0;
    this->y = 0;
    this->width = 0;
    this->height = 0;
}

IGNLabel::IGNLabel(
    int x, 
    int y, 
    int width, 
    int height, 
    std::string text,
    IGNColor * color,
    void * font):IGNComponent(
        x,
        y,
        width,
        height){
    this->text = text;
    this->color = color;
    this->font = font;
}

void IGNLabel::RenderAt(int x, int y){
    glRasterPos2i(this->x + x, this->y + y);
    this->color->ApplyColor();
    glutBitmapString(this->font, reinterpret_cast<const unsigned char *>(this->text.c_str()));
}

void IGNLabel::SetText(std::string text){
    this->text = text;
}

IGNLabel::~IGNLabel()
{
    this->font = NULL;
    delete this->color;
}