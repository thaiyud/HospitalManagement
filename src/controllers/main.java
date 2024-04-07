/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author HP VICTUS
 */
import java.io.IOException;
import view.menu;

/**
 *
 * @author HP VICTUS
 */
public class main {

    menu me;

    public main() throws IOException {
        this.me = new menu();
    }

    public static void main(String[] args) throws IOException, Exception {
        main ma = new main();
        ma.me.Menu();
    }
}

