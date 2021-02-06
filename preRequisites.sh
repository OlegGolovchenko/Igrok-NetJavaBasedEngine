#!/bin/bash
echo "Run this script with sudo parameters to install prerequisites to be able to build"
apt-get update
apt-get upgrade -y
apt-get update
apt-get dist-upgrade -y
apt-get update
apt-get install lsb-release git gcc wget apt-transport-https gnupg freeglut3 freeglut3-dev libglew-dev mesa-utils libx11-dev xorg -y
wget -qO - https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public | apt-key add -
echo "deb https://adoptopenjdk.jfrog.io/adoptopenjdk/deb "$1" main" | tee /etc/apt/sources.list.d/adoptopenjdk.list
apt-get update
apt-get install adoptopenjdk-15-hotspot adoptopenjdk-15-hotspot-jre -y

