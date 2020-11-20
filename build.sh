#!/bin/bash
echo "compiling java files and generating native headers"
javac -d ./bin -cp ./bin -h ./src/natives -verbose ./src/org/igrok_net/engine/ui/*.java ./src/org/igrok_net/launcher/*.java
echo "done."
echo "compiling native library"
gcc -I /usr/lib/jvm/adoptopenjdk-15-hotspot-amd64/include/ -I /usr/lib/jvm/adoptopenjdk-15-hotspot-amd64/include/linux/ -c -Wall -Werror -fpic -o ./obj/IGNUI.o ./src/natives/IGNWindow.cpp
echo "done."
echo "linking native library"
gcc -shared -o ./lib/libIGNUI.so ./obj/IGNUI.o -lX11 -lGL -lGLU
echo "done."