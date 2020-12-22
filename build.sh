#!/bin/bash
echo "compiling java files and generating native headers"
rm -r ./bin/*
javac -d ./bin -cp ./bin -h ./src/natives ./src/org/igrok_net/engine/ui/events/*.java ./src/org/igrok_net/engine/ui/*.java ./src/org/igrok_net/launcher/*.java
chmod -R 775 bin
rm -r ./pkg/*
cd ./pkg
jar cvf org.igrok-net.engine.jar -C ../bin org/igrok_net/engine/
jar cvfe org.igrok-net.launcher.jar org.igrok_net.launcher.Main -C ../bin org/igrok_net/launcher
cd ..
echo "done."
echo "compiling native library"
rm ./obj/IGNUI.o
gcc -I /usr/lib/jvm/adoptopenjdk-15-hotspot-amd64/include/ -I /usr/lib/jvm/adoptopenjdk-15-hotspot-amd64/include/linux/ -c -Wall -Werror -fpic -o ./obj/IGNUI.o ./src/natives/IGNUI/*.cpp
echo "done."
echo "linking native library"
rm ./lib/libIGNUI.so;
gcc -shared -o ./lib/libIGNUI.so ./obj/IGNUI.o -lX11 -lGL -lGLU -lglut
echo "done."