package org.igrok_net.launcher;

import org.igrok_net.engine.ui.IGNWindow;

public class Main {
    private static String version = "1.0.0";

    public static void main(String[] args) {
        System.out.println("Igrok-Net Game Engine v" + version);
        IGNWindow window = new IGNWindow();
        window.Dispose();
    }
}