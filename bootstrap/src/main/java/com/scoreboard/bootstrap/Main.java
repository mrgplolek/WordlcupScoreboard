package com.scoreboard.bootstrap;


import com.scoreboard.adapter.inweb.ScoreboardController;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        ScoreboardController controller = ScoreboardController.getInstance();

        //Setup data
        controller.startMatch("Mexico", "Canada");
        controller.updateMatch("Mexico", "Canada", 0, 1);
        controller.updateMatch("Mexico", "Canada", 0, 2);
        controller.updateMatch("Mexico", "Canada", 0, 3);
        controller.updateMatch("Mexico", "Canada", 0, 4);
        controller.updateMatch("Mexico", "Canada", 0, 5);
        Thread.sleep(1000);

        controller.startMatch("Spain", "Brazil");
        controller.updateMatch("Spain", "Brazil", 0, 1);
        controller.updateMatch("Spain", "Brazil", 0, 2);
        controller.updateMatch("Spain", "Brazil", 1, 2);
        controller.updateMatch("Spain", "Brazil", 2, 2);
        controller.updateMatch("Spain", "Brazil", 3, 2);
        controller.updateMatch("Spain", "Brazil", 4, 2);
        controller.updateMatch("Spain", "Brazil", 5, 2);
        controller.updateMatch("Spain", "Brazil", 6, 2);
        controller.updateMatch("Spain", "Brazil", 7, 2);
        controller.updateMatch("Spain", "Brazil", 8, 2);
        controller.updateMatch("Spain", "Brazil", 9, 2);
        controller.updateMatch("Spain", "Brazil", 10, 2);
        Thread.sleep(1000);

        controller.startMatch("Germany", "France");
        controller.updateMatch("Germany", "France", 0, 1);
        controller.updateMatch("Germany", "France", 1, 1);
        controller.updateMatch("Germany", "France", 2, 1);
        controller.updateMatch("Germany", "France", 2, 2);
        Thread.sleep(1000);

        controller.startMatch("Uruguay", "Italy");
        controller.updateMatch("Uruguay", "Italy", 0, 1);
        controller.updateMatch("Uruguay", "Italy", 0, 2);
        controller.updateMatch("Uruguay", "Italy", 0, 3);
        controller.updateMatch("Uruguay", "Italy", 1, 3);
        controller.updateMatch("Uruguay", "Italy", 2, 3);
        controller.updateMatch("Uruguay", "Italy", 3, 3);
        controller.updateMatch("Uruguay", "Italy", 4, 3);
        controller.updateMatch("Uruguay", "Italy", 5, 3);
        controller.updateMatch("Uruguay", "Italy", 6, 3);
        controller.updateMatch("Uruguay", "Italy", 6, 4);
        controller.updateMatch("Uruguay", "Italy", 6, 5);
        controller.updateMatch("Uruguay", "Italy", 6, 6);
        Thread.sleep(1000);

        controller.startMatch("Argentina", "Australia");
        controller.updateMatch("Argentina", "Australia", 0, 1);
        controller.updateMatch("Argentina", "Australia", 1, 1);
        controller.updateMatch("Argentina", "Australia", 2, 1);
        controller.updateMatch("Argentina", "Australia", 3, 1);
        Thread.sleep(1000);

        controller.getSummary();
        }
}
