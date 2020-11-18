package org.igrok_net.ignfreeglut;

import java.io.File;

public class IGNFreeGlut {

    static {
        File lib = new File("lib/libIgnFreeGlut.so");
        System.load(lib.getAbsolutePath());
    }

    private static native void glutInit(String[]argcp,int argv);

    public static void glutInit(String[]args){
        glutInit(args, args.length);
    }

}
