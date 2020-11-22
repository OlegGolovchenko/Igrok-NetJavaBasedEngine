#!/bin/bash
echo "compiling java files and generating native headers"
rm -r ./bin/*
javac -d ./bin -cp ./bin -h ./src/natives ./src/org/igrok_net/engine/ui/events/*.java ./src/org/igrok_net/engine/ui/*.java ./src/org/igrok_net/launcher/*.java
echo "done."
echo "compiling native library"
rm ./obj/IGNUI.o
gcc -I /usr/lib/jvm/adoptopenjdk-15-hotspot-amd64/include/ -I /usr/lib/jvm/adoptopenjdk-15-hotspot-amd64/include/linux/ -c -Wall -Werror -fpic -o ./obj/IGNUI.o ./src/natives/IGNUI/*.cpp
echo "done."
echo "linking native library"
rm ./lib/libIGNUI.so;
gcc -shared -o ./lib/libIGNUI.so ./obj/IGNUI.o -lX11 -lGL -lGLU
echo "done."