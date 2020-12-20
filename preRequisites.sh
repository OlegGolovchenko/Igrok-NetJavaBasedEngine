#!/bin/bash
echo "Run this script with sudo parameters to install prerequisites to be able to build"
apt-get update
apt-get upgrade -y
apt-get update
apt-get dist-upgrade -y
apt-get update
apt-get install freeglut3 freeglut3-dev libglew-dev mesa-utils libx11-dev xorg -y