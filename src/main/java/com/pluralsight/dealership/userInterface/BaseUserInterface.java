package com.pluralsight.dealership.userInterface;

import java.util.Scanner;

public abstract class BaseUserInterface {
    protected final Scanner scanner;

    public BaseUserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void nowDisplayingMgs(String what) {
        System.out.print("Searching");
        loadingDots();
        System.out.println();
        System.out.print("Now displaying " + what);
        loadingDots();
        System.out.println();

    }

    public void nowDoingMgs(String what) {
        System.out.print("Loading");
        loadingDots();
        System.out.println();
        System.out.print("Now " + what);
        loadingDots();
        System.out.println();

    }

    public void loadingDots() {
        try {
            for (int i = 0; i < 4; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
        } catch (InterruptedException e) {
            // ignore
        }
    }
}
