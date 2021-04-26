package com.company;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;

import java.time.LocalDate;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {     // a Stage is the main window for a JavaFX application
        // initialize some test data
        LocalDate d1 = LocalDate.of(2021,2,22);
        LocalDate d2 = LocalDate.of(2021,2,23);
        LocalDate d3 = LocalDate.of(2021,2,23);
        LocalDate d4 = LocalDate.of(2021,2,24);

        Holidays emp1Holidays = new Holidays();
        Holidays clinicHolidays = new Holidays();

        emp1Holidays.addHoliday(d1);
        emp1Holidays.addHoliday(d2);
        emp1Holidays.addHoliday(d3);
        emp1Holidays.addHoliday(d4);

        Employee emp1 = new Employee("Hans", "Nielsen","mbe001");
        emp1.setHolidays(emp1Holidays);
        System.out.println("Employee holidays:");
        emp1.printHolidays();

        clinicHolidays.addHoliday(d1);
        clinicHolidays.addHoliday(d2);
        clinicHolidays.removeHoliday(d3);
        clinicHolidays.addHoliday(d4);
        Clinic clinic1 = new Clinic(21,clinicHolidays);

        System.out.println("Clinic holidays:");
        clinic1.getHolidays().printHolidays();

        //Emulating Booking Attempt on the console
        LocalDate bookingDate= LocalDate.of(2021,2,23);
        System.out.println("Attempt: Booking employee on "+ bookingDate);
        System.out.println(emp1.checkAvailable(clinic1,bookingDate));

        bookingDate= LocalDate.of(2021,2,24);
        System.out.println("Attempt: Booking employee on "+ bookingDate);
        System.out.println(emp1.checkAvailable(clinic1,bookingDate));

        bookingDate= LocalDate.of(2021,2,19);
        System.out.println("Attempt: Booking employee on "+ bookingDate);
        System.out.println(emp1.checkAvailable(clinic1,bookingDate));

        Patient p1 = new Patient(" Samuel","Killa","010101-0101");
        Patient p2 = new Patient("Sara","Hekop","020202-0202");
        p1.setPhoneNumber("+45 10203040");
        p1.setEmail("Sam54@hotmail.com");

        System.out.println(p1);

        // Change p1's email
        p1.setEmail("sam00@hotmail.com");

        System.out.println("email changed\n"+p1);

        // Set som more attributes for p2;
        p2.setPhoneNumber("+44 92868296");
        p2.setEmail("sasa99@gmail.com");

        System.out.println(p2);

        PatientRegister patientRegister = new PatientRegister();
        try {
            patientRegister.addPatient(p1);
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println(e);
        }
        try {
            patientRegister.addPatient(p2);
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println(e);
        }


        patientRegister.printPatients();
        System.out.println(patientRegister);


        // Finished initialization of test values
        // Now let us create a GUI!!

        // First set the stage and add a grid

        primaryStage.setTitle("Clinic App");   // set the title shown int th title bar
        GridPane grid = new GridPane();         // create a GridPane for a nice even flexible screen layout
        grid.setAlignment(Pos.CENTER);          // Align the grid to the center of the application window
        grid.setHgap(10);                       // set the horizontal gap between the fields to 10 pixels
        grid.setVgap(10);                       // set the vertical gap between the fields to 10 pixels
        //grid.setGridLinesVisible(true);       // uncomment if you want to check the grid layout


        // Now create some labels and input fields

        Label cprLabel = new Label("CPR number");      // create a label displaying "CPR number"
        TextField cprText = new TextField();

        cprText.setPromptText("######-####");

        Label firstNameLabel = new Label("First name");
        TextField firstNameText = new TextField();
        firstNameText.setPrefWidth(200);
        firstNameText.setPromptText("First Name");


        Label lastNameLabel = new Label("Last name");
        TextField lastNameText = new TextField();


        lastNameText.setMaxWidth(400);                  // add som more space for the last name
        lastNameText.setPromptText("last name");

        Label phoneLabel = new Label("Phone Number");
        TextField phoneText = new TextField();
        phoneText.setPrefWidth(200);
        phoneText.setPromptText("+## ########");

        Label emailLabel = new Label("Email adress");
        TextField emailText = new TextField();
        emailText.setMaxWidth(400);                     // add som more space for the email
        emailText.setPromptText("Email");

        // Create a TextArea to hold the view of the patients list.
        // for now we use the listPatients method which uses string formatter to get a decent layout
        // In later exercises we will go for table view
        TextArea patientListTextArea = new TextArea();
        patientListTextArea.setPromptText("Do nit be alarmed!\rThis massage is made bu a machine");
        patientListTextArea.setPrefHeight(190);         // set the preferable height of the patient list
        patientListTextArea.setPrefWidth(830);          // set the preferable width of the patient list
        patientListTextArea.setEditable(false);         // make sure the user can't edit the patient list field
        patientListTextArea.setFont(Font.font ("Courier New", 11)); // set a monospaced font



        patientListTextArea.setText(patientRegister.listPatients());

        Button buttonAdd = new Button("Add patient");       // create a clickable button to add a patient
        Button buttonClearAll = new Button("Clear Input");    // create another clickable button to clear input fields

        // now create an event handler which will be called when the "Add" button is clicked
        buttonAdd.setOnMouseClicked(event -> {
            String cprInput= cprText.getText();
            String firstNameInput= capitalize(firstNameText.getText());
            String lastNameInput= capitalize(lastNameText.getText());
            String phoneInput = phoneText.getText();
            String emailInput = emailText.getText();
            boolean validInput= true;

            // validate the user input

            if (!DataValidator.isValidCPR(cprInput)){
                // we need a CPR number in order to register a user
                // highlight the CPR input field if content is invalid
                cprText.setStyle("-fx-background-color: red");
                validInput = false;
            }
            if (!emailInput.isEmpty() && ! DataValidator.isValidEmail(emailInput)){
                // email is optional - but if it is there it needs to be valid
                emailText.setStyle("-fx-background-color: red");
                validInput = false;
            }

            if (!phoneInput.isEmpty()  && ! DataValidator.isValidPhone(phoneInput)){
                // phone is optional - but if it is there it needs to be valid
                phoneText.setStyle("-fx-background-color: red");
            }

            if (firstNameInput.isEmpty() || ! DataValidator.isValidName(firstNameInput)){
                // We need a valid first name
                firstNameText.setStyle("-fx-background-color: red");
                validInput = false;
            }

            if (lastNameInput.isEmpty() || ! DataValidator.isValidName(lastNameInput)){
                // We need a valid last name
                lastNameText.setStyle("-fx-background-color: red");
                validInput = false;
            }

            // Now register the patient in the patient register
            // alert the user if the patient is already there

            if (validInput) {
                Patient p = new Patient(firstNameInput,lastNameInput,cprInput);
                p.setPhoneNumber(phoneInput);
                p.setEmail(emailInput);
                try {
                    patientRegister.addPatient(p);
                    clearTextFields(grid);
                    resetTextFields(grid);
                } catch (Exception e) {

                    // The patient is already there
                    e.printStackTrace();

                    cprText.setStyle("-fx-background-color: yellow");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    //alert.setTitle("Error Dialog");
                    alert.setHeaderText("Registration problem");
                    alert.setContentText("CPR-number " + cprInput +" already found in register");

                    alert.showAndWait();
                }

            } else {
                // Something is wrong in one or more input fields
                // Tell the user to correct input

                Alert alert = new Alert(Alert.AlertType.ERROR);
                //alert.setTitle("Error Dialog");
                alert.setHeaderText("Registration problem");
                alert.setContentText("Correct highlighted fields");

                alert.showAndWait();
                resetTextFields(grid);
            }
            // list the patients
            patientListTextArea.setText(patientRegister.listPatients());

        });

        buttonClearAll.setOnMouseClicked(mouseEvent -> clearTextFields(grid));

        // Now place all the fields in the grid
        // maybe VBox and HBox would be better suited.. next time :-)

        grid.add(cprLabel, 0, 0);
        grid.add(cprText, 1, 0);
        grid.add(firstNameLabel, 0, 1);
        grid.add(firstNameText, 1, 1);
        grid.add(lastNameLabel, 2, 1);
        grid.add(lastNameText, 3, 1);
        grid.add(phoneLabel,0,2);
        grid.add(phoneText, 1, 2);
        grid.add(emailLabel, 2, 2);
        grid.add(emailText, 3, 2);

        grid.add(buttonAdd, 0, 3);
        grid.add(patientListTextArea, 0, 5, 4,1);    // allow it to expand to 4 columns
        grid.add(buttonClearAll, 3, 3);


        // and while we are at it - set monospaced font for text fields too..

        for (Node node : grid.getChildren()) {
            if (node instanceof TextField) {
                ((TextField)node).setFont(Font.font ("Courier New", 11));
            }
        }

        for (Node node : grid.getChildren()) {
            if (node instanceof Label) {
                node.setStyle("-fx-border-color:red; -fx-background-color: white; -fx-padding:4px");
            }
        }


        Scene scene = new Scene(grid, 1200, 500); // create a scene window 1200 x 400 pixels
        grid.setStyle("-fx-background-image: " +
                "url(\"https://previews.123rf.com/images/graphicbee/graphicbee1707/graphicbee170700093/83775192-male-general-practitioner-vector-illustration-of-a-smiling-doctor-or-family-practitioner-.jpg\"); -fx-background-radius: 20;"
        );
        primaryStage.setScene(scene);                   // add the scene to the stage / application window
        primaryStage.show();                 // display the stage - important! otherwise nothing happens :-)
    }

    private void clearTextFields(GridPane grid) {
        // Simple method to clear the text fields in a grid
        for (Node node : grid.getChildren()) {
            if (node instanceof TextField) {
                // clear
                ((TextField)node).setText("");
                node.setStyle(null);
            }
        }
    }

    private void resetTextFields(GridPane grid) {
        // Simple method to reset the style on text fields in a grid which have a style set
        // and don't touch the other fields
        // We use it to clear the fields we turned yellow
        for (Node node : grid.getChildren()) {
            if (node instanceof TextField) {
                System.out.println(node.getStyle());
                // clear
                if (node.getStyle() != null && !node.getStyle().equals("")) {
                    ((TextField) node).setText("");
                    node.setStyle(null);
                }
            }
        }
    }
    public static String capitalize(String str) {
        // Method to make names appear with capital first letter
        // also turn uppercase letters not in first position into lowercase
        // sorry McDonald!
        if(str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
