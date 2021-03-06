package condo.controller.admin;

import condo.model.Staff;
import condo.model.User;
import condo.process.ProgramDataSource;
import condo.process.ProgramDataSourceFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AddStaffController
{
    @FXML TextField nameField, surnameField, usernameField;
    @FXML PasswordField passwordField, confirmField;
    @FXML Button createBtn, backBtn;
    private Alert popup = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
    private User currentuser;
    private ProgramDataSource programDataSource = new ProgramDataSourceFile();
    private Staff newStaff;
    private Staff writeInAcc;

    @FXML public void initialize()
    {

    }

    @FXML public void handleCreateBtnOnAction(ActionEvent event) throws IOException
    {
        if (nameField.getText().equals("") ||
            surnameField.getText().equals("") ||
            usernameField.getText().equals("") ||
            passwordField.getText().equals("") ||
            confirmField.getText().equals("") ||
            !confirmField.getText().equals(passwordField.getText()))
        {
            popup.setAlertType(Alert.AlertType.ERROR);
            popup.setTitle("Failed");
            popup.setHeaderText("Creating failed");
            popup.setContentText("Please fill all the field or check your password");
            popup.showAndWait();
        }
        else
        {
            popup.setHeaderText("Do you want to create staff?");
            popup.setContentText("Name : " + nameField.getText() +
                    "\nSurname : " + surnameField.getText() +
                    "\nUsername : " + usernameField.getText() +
                    "\nPassword : " + passwordField.getText());
            popup.showAndWait();

            if (popup.getResult() == ButtonType.YES)
            {
                newStaff = new Staff(usernameField.getText(), passwordField.getText(), nameField.getText(), surnameField.getText(), "None", "None");

                if (programDataSource.addStaff(newStaff))
                {
                    writeInAcc = new Staff(usernameField.getText(), passwordField.getText(), nameField.getText(), "STAFF");

                    programDataSource.writeNewStaff(writeInAcc);
                    popup.setAlertType(Alert.AlertType.NONE);
                    popup.setTitle("Successful");
                    popup.setHeaderText("Creating successful");
                    popup.setContentText("You have created " + newStaff.getName() + " a staff");
                }
                else
                {
                    popup.setAlertType(Alert.AlertType.ERROR);
                    popup.setTitle("Failed");
                    popup.setHeaderText("Creating failed");
                    popup.setContentText(newStaff.getId() + " is already used, please change the username");

                }
                popup.showAndWait();
                nameField.setText("");
                surnameField.setText("");
                usernameField.setText("");
                passwordField.setText("");
                confirmField.setText("");
            }
        }
        popup.setAlertType(Alert.AlertType.CONFIRMATION);
    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminmenupage.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        AdminMenuController menu = loader.getController();
        menu.setCurrentuser(currentuser);

        stage.show();
    }

    public void setCurrentuser(User currentuser)
    {
        this.currentuser = currentuser;
    }
}
