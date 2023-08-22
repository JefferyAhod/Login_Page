package com.application.practical_exams;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Controller implements Initializable  {

    @FXML
    private Button btnCancel;

    @FXML
    private TextArea txtArea;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private Label txtInvalid;

    @FXML
    public ComboBox<String> btHall;

    @FXML
    private Button btnOpen;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> btnfaculty;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtphone;



    public String data;



    public void login() {
        String Username = txtUsername.getText();
        String Password = txtPassword.getText();
        if (Username.equals("jeff") && Password.equals("2003")) {

            btnLogin.getScene().getWindow().hide();
            mainApp();


        } else {

            txtInvalid.setText(String.valueOf("Invalid login details" + "\n" + "Register"));

        }
        txtUsername.setText("");
        txtPassword.setText("");


    }

    public void cancel(){

        btnCancel.getScene().getWindow().hide();
    }
    public void mainApp() {

        try {
            //Load the student data from FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainApp.fxml"));
            Parent root = loader.load();

            //Set up a new stage and scene for the student form
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Main App");

            //show the student form
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
         if (btHall != null) {
            String[] items = {"Gold Hall", "Dubai","KT", "Camp City"};
            btHall.getItems().addAll(items);
            btHall.setOnAction(event -> {
                data = btHall.getSelectionModel().getSelectedItem().toString();
            });}

        if (btnfaculty != null) {
            String[] items = {"SPETS", "SRID","FMMT", "FOE"};
            btnfaculty.getItems().addAll(items);
            btnfaculty.setOnAction(event -> {
                data = btnfaculty.getSelectionModel().getSelectedItem().toString();
            });}

    }





    public void handleSaveButtonAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Contact Details");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showSaveDialog(btnSave.getScene().getWindow());
        if (selectedFile != null) {
            saveContactDetails(selectedFile);
        }
    }
    public void saveContactDetails(File file) {
        try (FileWriter writer = new FileWriter(file)) {
            String fullName = txtname.getText();
            int number = Integer.parseInt(txtphone.getText());
            String emails = txtemail.getText();
            // Write contact details to file
            writer.write("Contact details" + "\n");
            writer.write("Fullname: " + fullName + "\n" );
            writer.write("Telephone Number: " + number + "\n");
            writer.write("Email: " + emails + "\n");
            writer.write("Faculty: SRID");
            writer.write("Hall: Gold Hall");

            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reset(){

        txtname.setText("");
        txtphone.setText("");
        txtemail.setText("");




    }

    public void OpenText() throws IOException {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(btnOpen.getScene().getWindow());
        fileChooser.setTitle("Open File");

        //File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                txtArea.setText(content.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }





}
