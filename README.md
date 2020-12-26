# Igrok-NetJavaBasedEngine
Java based game engine that uses XLib as window managing api.

To build correctly you need to:
* run preRequisites.sh [distro codename] as sudo to install update and all needed prerequisites: xlib, mesa, freeglut and openjdk15
* run build.sh (you don't need administrator permissions because we don't copy any libraries or updating anything that requires sudo)

You are free to install only missing prerequisites if you need, just browse preRequisites.sh file and check what you need or just run it passing distname in parameter.
You can run this project with visual studio code, run definition is already added.