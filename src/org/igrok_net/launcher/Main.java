package org.igrok_net.launcher;

import org.igrok_net.engine.IGNEngine;
import org.igrok_net.engine.ui.IGNWindow;
import org.igrok_net.engine.ui.components.Label;
import org.igrok_net.engine.ui.components.Panel;
import org.igrok_net.engine.ui.components.TextBox;

/**
 * Main class of launcher
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public class Main {
    private static String version = "0.0.1";

    /**
     * Entry point of program
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Igrok-Net Game Engine v" + version);
        IGNEngine.init(args);
        IGNWindow window = new IGNWindow();
        Panel test = new Panel();
        Label testLbl = new Label(0, 0, "Test", IGNEngine.GLUT_BITMAP_9_BY_15());
        TextBox tbTest = new TextBox("type here", IGNEngine.GLUT_BITMAP_9_BY_15());
        tbTest.reposition(0, 50);
        tbTest.resize(50, 50);
        test.addChild(testLbl);
        test.addChild(tbTest);
        test.reposition(50, 50);
        test.resize(100, 100);
        window.addChild(test);
        window.run();
        window.dispose();
    }
}