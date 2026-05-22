package Projects.GameOfLife;

// Jean Raphael Barbosa Santos, CS 142, Winter 2026
// Programming Project # 5, 03/02/2026

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LifeMain {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the CS 142 Game of Life,\n" +
                "a simulation of the lifecycle of a bacteria colony.\n" +
                "Cells (X) live and die by the following rules:\n" +
                "- A cell with 1 or fewer neighbors dies.\n" +
                "- Locations with 2 neighbors remain stable.\n" +
                "- Locations with 3 neighbors will create life.\n" +
                "- A cell with 4 or more neighbors dies.");

        LifeModel life = null;

        while(life == null) {
            System.out.print("Grid input file name? ");
            String fileName = scanner.nextLine();

            File file = new File(fileName);
            if(file.exists()) {
                life = new LifeModel(fileName);
            } else {
                System.out.println("Unable to open that file.  Try again.");
            }
        }

        //Print initial grid
        System.out.println(life);

        while (true) {
            System.out.print("a)nimate, t)ick, q)uit? ");
            String input = scanner.nextLine().toLowerCase(); //Accepts letters capitalized or not

            if (input.equals("q")) {
                System.out.print("Load another file? (y/n) ");
                String answer = scanner.nextLine();

                //If "y" type new file name
                if (answer.equals("y")) {
                    System.out.print("Grid input file name? ");
                    String fileName = scanner.nextLine();

                    File file = new File(fileName);
                    if(file.exists()) {
                        life = new LifeModel(fileName);
                        System.out.print(life);
                    } else {
                        System.out.println("Unable to open that file.  Try again.");
                    }
                } else if (answer.equals("n")){
                    System.out.println("Have a nice Life!");
                    System.exit(0);
                }
            }
            //else if "t" call life update and print once
            else if (input.equals("t")) {
                life.update();
                System.out.println(life);
            }
            else if (input.equals("a")) {
                LifeModel finalLife = life;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new LifeGui(finalLife);
                    }
                });
            }
            else {
                System.out.println("Invalid option. Choose a, t, or q.");
            }
        }
    }
}
