#!/bin/bash
echo "compiling java files and generating native headers"
rm -r ./bin/*
javac -d ./bin -cp ./bin -h ./src/natives ./src/org/igrok_net/engine/*.java ./src/org/igrok_net/engine/ui/events/*.java ./src/org/igrok_net/engine/ui/interfaces/*.java ./src/org/igrok_net/engine/ui/components/*.java ./src/org/igrok_net/engine/ui/*.java ./src/org/igrok_net/launcher/*.java
chmod -R 775 bin
rm -r ./doc/*
javadoc -public -d ./doc -classpath ./bin -sourcepath ./src -subpackages org.igrok_net
rm -r ./pkg/*
cd ./pkg
jar cf org.igrok-net.engine.jar -C ../bin org/igrok_net/engine/
jar cfe org.igrok-net.launcher.jar org.igrok_net.launcher.Main -C ../bin org/igrok_net/launcher
cd ..
echo "done."
echo "compiling native library"
rm -r ./obj/*
gcc -I /usr/lib/jvm/adoptopenjdk-15-hotspot-amd64/include/ -I /usr/lib/jvm/adoptopenjdk-15-hotspot-amd64/include/linux/ -c -Wall -Werror -fpic -o ./obj/IGNUI.o ./src/natives/IGNUI/*.cpp
gcc -I /usr/lib/jvm/adoptopenjdk-15-hotspot-amd64/include/ -I /usr/lib/jvm/adoptopenjdk-15-hotspot-amd64/include/linux/ -c -Wall -Werror -fpic -o ./obj/IGNEngine.o ./src/natives/*.cpp
echo "done."
echo "linking native library"
rm -r ./lib/*
gcc -shared -o ./lib/libIGNUI.so ./obj/IGNUI.o -lX11 -lGL -lGLU -lglut
gcc -shared -o ./lib/libIGNEngine.so ./obj/IGNEngine.o -lX11 -lGL -lGLU -lglut
echo "done."