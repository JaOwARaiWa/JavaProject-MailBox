package condo.controller;

import condo.controller.admin.AdminMenuController;
import condo.controller.resident.RegisterController;
import condo.controller.resident.ResidentMenuController;
import condo.controller.staff.StaffMenuController;
import condo.model.*;
import condo.process.ProgramDataSource;
import condo.process.ProgramDataSourceFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController
{
    @FXML Button loginBtn, creditBtn;
    @FXML Label faultLabel;
    @FXML TextField usernameField;
    @FXML PasswordField passwordField;
    @FXML ImageView backGround;

    private User currentUser;

    private String user;
    private String pass;

    private ArrayList acc;
    private ArrayList<String> idList = new ArrayList<>();
    private ArrayList<String> usernameList = new ArrayList<>();
    private ArrayList<String> passwordList = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<String> roomList = new ArrayList<>();

    private int i = 0;
    private int index = 0;
    private int limit = 0;
    private int fault = 0;
    private Alert popup = new Alert(Alert.AlertType.NONE);

    private ProgramDataSource programDataSource = new ProgramDataSourceFile();

    @FXML public void initialize() throws IOException
    {
        acc = programDataSource.readAllAccount();
        idList = (ArrayList<String>) acc.get(0);
        usernameList = (ArrayList<String>) acc.get(1);
        passwordList = (ArrayList<String>) acc.get(2);
        nameList = (ArrayList<String>) acc.get(3);
        roomList = (ArrayList<String>) acc.get(4);

        limit = idList.size();
    }

    @FXML public void handleLoginBtnOnAction(ActionEvent event) throws IOException
    {
        user = usernameField.getText();
        pass = passwordField.getText();

        System.out.println(user);
        System.out.println(pass);

        for (i = 0; i < limit; i-=-1)
        {
            if (user.equals(usernameList.get(i)) && pass.equals(passwordList.get(i)))
            {
                switch (idList.get(i))
                {
                    case "ADMIN":
                        currentUser = new Admin(usernameList.get(i), passwordList.get(i), nameList.get(i), idList.get(i));
                        index = i;
                        fault = -1;
                        break;
                    case "STAFF":
                        currentUser = new Staff(usernameList.get(i), passwordList.get(i), nameList.get(i), idList.get(i));
                        index = i;
                        fault = -1;
                        break;
                    case "RESIDENT":
                        currentUser = new Resident(usernameList.get(i), passwordList.get(i), nameList.get(i), idList.get(i), roomList.get(i));
                        index = i;
                        fault = -1;
                        break;
                }
                break;
            }
            else
            {
                fault-=-1;
            }
        }
        if (fault != -1)
        {
            currentUser = new User("fault", "fault", "fault", "fault");
        }

        System.out.println(currentUser.getId());

        switch (currentUser.getId()) {
            case "ADMIN": {
                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminmenupage.fxml"));
                stage.setScene(new Scene(loader.load(), 800, 600));

                AdminMenuController menu = loader.getController();
                menu.setCurrentuser(currentUser);

                stage.show();
                break;
            }
            case "STAFF": {
                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/staffmenupage.fxml"));
                stage.setScene(new Scene(loader.load(), 800, 600));

                StaffMenuController menu = loader.getController();
                ((Staff) currentUser).setDate(java.time.LocalDate.now().toString());
                ((Staff) currentUser).setTime(java.time.LocalTime.now().toString());
                programDataSource.updateLogInStaff((Staff) currentUser);
                menu.setCurrentUser((Staff) currentUser);

                stage.show();
                break;
            }
            case "RESIDENT": {
                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/residentmenupage.fxml"));
                stage.setScene(new Scene(loader.load(), 800, 600));

                ResidentMenuController menu = loader.getController();
                menu.setCurrentUser((Resident) currentUser);

                stage.show();
                break;
            }
            default:
                popup.setAlertType(Alert.AlertType.ERROR);
                popup.setTitle("Login failed");
                popup.setHeaderText("Wrong user or password, please try again");
                popup.setContentText("Please check your username amd password");
                popup.showAndWait();
                faultLabel.setText("Wrong user or password, please try again");
                currentUser.reset();
                usernameField.setText("");
                passwordField.setText("");
                break;
        }
    }

    @FXML public void handleRegisterBtnOnAction(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/registerwindow.fxml"));
        stage.setScene(new Scene(loader.load(), 500, 500));

        RegisterController rgct = loader.getController();

        stage.show();
    }

    @FXML public void handleCreditBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/developerpage.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        DeveloperController dev = loader.getController();

        stage.show();
    }

    /*public void setCurrentuser(User currentuser)
    {
        this.currentuser = currentuser;
    }*/
}
