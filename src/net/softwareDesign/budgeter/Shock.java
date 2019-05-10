package net.softwareDesign.budgeter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Shock {

    public Shock() throws URISyntaxException, IOException, InterruptedException {

        while (true){

            java.awt.Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=oHg5SJYRHA0"));
            Thread.sleep(213000);

        }

    }

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {

        new Shock();

    }

}
