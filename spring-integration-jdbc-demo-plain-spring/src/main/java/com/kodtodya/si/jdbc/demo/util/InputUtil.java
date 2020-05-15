package com.kodtodya.si.jdbc.demo.util;

import com.kodtodya.si.jdbc.demo.domain.Training;
import com.kodtodya.si.jdbc.demo.service.TrainingService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Scanner;

public class InputUtil {

    private int id = 3;

    private static final Log LOGGER = LogFactory.getLog(InputUtil.class);

    private final Scanner scanner = new Scanner(System.in);

    public void startIntegrationToHandleTrainings(TrainingService trainingService) {

        LOGGER.info("\n========================================================="
                + "\n                                                         "
                + "\n    Please press 'q + Enter' to quit the application.    "
                + "\n                                                         "
                + "\n=========================================================");

        System.out.println("Please enter a choice and press <enter>: \n" +
                "\t1. Find Training details\n" +
                "\t2. Create a new training detail\n" +
                "\tq. Quit the application\n" +
                "Enter you choice: ");
        while (true) {
            final String input = scanner.nextLine();
            if ("1".equals(input.trim()))
                this.getTrainingDetails(trainingService);
            else if ("2".equals(input.trim()))
                this.createTrainingDetails(trainingService);
            else if ("q".equals(input.trim()))
                break;
            else
                System.out.println("Invalid choice\n\n");


            System.out.println("Please enter a choice and press <enter>: ");
            System.out.println("\t1. Find Training details");
            System.out.println("\t2. Create a new Training detail");
            System.out.println("\tq. Quit the application");
            System.out.print("Enter you choice: ");
        }

        LOGGER.info("Exiting application...bye.");

    }

    public void createTrainingDetails(TrainingService service) {
        while (true) {
            System.out.print("\nEnter the Training name:");
            String name = scanner.nextLine();
            System.out.print("\nEnter the Training duration:");
            String duration = scanner.nextLine();
            System.out.print("\nEnter the Training pre-requisite:");
            String preRequisite = scanner.nextLine();

            Training training = new Training();
            training.setId(++id);
            training.setName(name);
            training.setDurationInDays(Integer.parseInt(duration));
            training.setPreRequisite(preRequisite);
            training = service.createTraining(training);
            System.out.println("Created training record with id: " + training.getId());
            System.out.print("Do you want to create another training? (y/n)");
            String choice = scanner.nextLine();
            if (!"y".equalsIgnoreCase(choice)) {
                break;
            }
        }
    }

    public void getTrainingDetails(final TrainingService service) {
        while (true) {
            System.out.print("Please enter the name of the person and press<enter>: ");
            String input = scanner.nextLine();
            final List<Training> trainingList = service.findTrainingByName(input);
            if (trainingList != null && !trainingList.isEmpty()) {
                for (Training training : trainingList) {
                    System.out.print(
                            String.format("Training found - Id: '%d', Name is: '%s',  Duration in Days: '%d', Pre-requisite: '%s'",
                                    training.getId(), training.getName(), training.getDurationInDays(), training.getPreRequisite()));
                }
            } else {
                System.out.println(String.format("No Training record found for name: '%s'.", input));
            }
            System.out.print("Do you want to find another Training? (y/n)");
            String choice = scanner.nextLine();
            if (!"y".equalsIgnoreCase(choice)) {
                break;
            }
        }
    }
}
