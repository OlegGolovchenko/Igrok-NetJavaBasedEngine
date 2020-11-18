#!/bin/bash
echo "compiling java files and generating native headers"
javac -d ./bin -cp ./bin -h ./src/natives -verbose ./src/org/igrok_net/ignfreeglut/*.java ./src/org/igrok_net/launcher/*.java
echo "done."
echo "compiling native library"
gcc -I /usr/lib/jvm/adoptopenjdk-15-hotspot-amd64/include/ -I /usr/lib/jvm/adoptopenjdk-15-hotspot-amd64/include/linux/ -c -Wall -Werror -fpic -o ./obj/IGNFreeGlut.o ./src/natives/IGNFreeGlut.c
echo "done."
echo "linking native library"
gcc -shared -o ./lib/libIgnFreeGlut.so ./obj/IGNFreeGlut.o -lX11 -lGL -lGLU -lglut
echo "done."