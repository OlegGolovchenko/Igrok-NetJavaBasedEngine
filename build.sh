#!/bin/bash
echo "compiling java files and generating native headers"
javac -d ./bin -cp ./bin -h ./src/natives -verbose ./src/org/igrok_net/engine/ui/NativeWindow.java ./src/org/igrok_net/launcher/Main.java
echo "done."
echo "compiling native library"
gcc -I /usr/lib/jvm/adoptopenjdk-15-hotspot-amd64/include/ -I /usr/lib/jvm/adoptopenjdk-15-hotspot-amd64/include/linux/ -c -Wall -Werror -fpic -o ./obj/NativeWindow.o ./src/natives/NativeWindow.c
echo "done."
echo "linking native library"
gcc -shared -o ./lib/libIgnWindow.so ./obj/NativeWindow.o -lX11 -lGL -lGLU
echo "done."