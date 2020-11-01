package condo.controller.resident;

import condo.controller.LoginController;
import condo.model.Resident;
import condo.model.Room;
import condo.model.User;
import condo.process.ProgramDataSource;
import condo.process.ProgramDataSourceFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class RegisterController
{
    @FXML
    Button signUpBtn, backBtn;
    @FXML
    TextField nameField, roomField, userField;
    @FXML
    PasswordField passwordField, confirmField;

    private String name;
    private String room;
    private String user;
    private String password;
    private int check = 0;

    private ArrayList<Room> rooms;
    private ArrayList<String> residents;
    private ProgramDataSource source = new ProgramDataSourceFile();
    private Alert popup = new Alert(Alert.AlertType.NONE);
    private User newUser;

    @FXML public void handleSignUpBtnOnAction(ActionEvent event) throws IOException
    {
        if (nameField.getText().equals("") || roomField.getText().equals("") || userField.getText().equals("") || passwordField.getText().equals("") || confirmField.getText().equals(""))
        {
            popup.setAlertType(Alert.AlertType.ERROR);
            popup.setTitle("Failed");
            popup.setHeaderText("Can't register");
            popup.setContentText("Please enter all information");
            popup.showAndWait();

            passwordField.setText("");
        }
        else if (!confirmField.getText().equals(passwordField.getText()))
        {
            popup.setAlertType(Alert.AlertType.ERROR);
            popup.setTitle("Failed");
            popup.setHeaderText("Password mismatched");
            popup.setContentText("Please try again");
            popup.showAndWait();

            passwordField.setText("");
            confirmField.setText("");
        }
        else
        {
            rooms = source.readCondoRoom();
            name = nameField.getText();
            room = roomField.getText();
            user = userField.getText();
            password = passwordField.getText();

            for (Room checkRoom : rooms)
            {
                if (checkRoom.getRoom().equals(room))
                {
                    residents = checkRoom.getAllResident();

                    for (String checkName : residents)
                    {
                        if (checkName.equals(name))
                        {
                            check = 1;
                            break;
                        }
                    }
                }
            }

            if (check == 1)
            {
                newUser = new Resident(user, password, name, "RESIDENT", room);
                System.out.println(room);
                source.register((Resident) newUser);

                popup.setAlertType(Alert.AlertType.INFORMATION);
                popup.setTitle("Done");
                popup.setHeaderText("Create resident account successful");
                popup.setContentText("You can use " + user + " to login as Resident");
                popup.showAndWait();

                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();
                stage.close();
            }
            else
            {
                popup.setAlertType(Alert.AlertType.ERROR);
                popup.setTitle("Failed");
                popup.setHeaderText("Room not available or not found this resident name");
                popup.setContentText("Please check your information or contract staff");
                popup.showAndWait();

                nameField.setText("");
                roomField.setText("");
                userField.setText("");
                passwordField.setText("");
                confirmField.setText("");
            }

        }
    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }

}
