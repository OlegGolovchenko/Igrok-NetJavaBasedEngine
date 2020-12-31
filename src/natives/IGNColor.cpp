#include "IGNColor.h"

IGNColor::IGNColor(
    float r, 
    float g, 
    float b, 
    float a){
        this->a = a;
        this->b = b;
        this->g = g;
        this->r = r;
}

void IGNColor::ApplyColor(){
    glColor4f(this->r, this->g, this->b, this->a);
}

IGNColor::~IGNColor(){
    this->a = 0;
    this->b = 0;
    this->g = 0;
    this->r = 0;
}