package condo.controller;

import condo.model.*;
import condo.process.Account;
import condo.process.AccountProcessor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Login
{
    @FXML Button loginBtn, creditBtn;
    @FXML Label faultLabel;
    @FXML TextField usernameField;
    @FXML PasswordField passwordField;

    private User currentuser;

    private String user;
    private String pass;

    private ArrayList acc;
    private ArrayList<String> idList = new ArrayList<>();
    private ArrayList<String> usernameList = new ArrayList<>();
    private ArrayList<String> passwordList = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();

    private int check = 0;
    private int i = 0;
    private int index = 0;
    private int limit = 0;
    private int fault = 0;

    private Account account = new AccountProcessor();

    @FXML public void initialize() throws IOException
    {
        acc = account.readAcc();
        idList = (ArrayList<String>) acc.get(0);
        usernameList = (ArrayList<String>) acc.get(1);
        passwordList = (ArrayList<String>) acc.get(2);
        nameList = (ArrayList<String>) acc.get(3);

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
                        currentuser = new Admin(usernameList.get(i), passwordList.get(i), nameList.get(i), idList.get(i));
                        index = i;
                        fault = -1;
                        break;
                    case "STAFF":
                        currentuser = new Staff(usernameList.get(i), passwordList.get(i), nameList.get(i), idList.get(i));
                        index = i;
                        fault = -1;
                        break;
                    case "RESIDENT":
                        currentuser = new Resident(usernameList.get(i), passwordList.get(i), nameList.get(i), idList.get(i));
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
            currentuser = new User("fault", "fault", "fault", "fault");
        }

        System.out.println(currentuser.getId());

        switch (currentuser.getId()) {
            case "ADMIN": {
                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminmenu.fxml"));
                stage.setScene(new Scene(loader.load(), 800, 600));

                AdminMenu menu = loader.getController();
                menu.setCurrentuser(currentuser);

                stage.show();
                break;
            }
            case "STAFF": {
                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/staffmenu.fxml"));
                stage.setScene(new Scene(loader.load(), 800, 600));

                StaffMenu menu = loader.getController();
                ((Staff) currentuser).setDate(java.time.LocalDate.now().toString());
                ((Staff) currentuser).setTime(java.time.LocalTime.now().toString());
                account.updateLogIn((Staff) currentuser);
                menu.setCurrentuser((Staff) currentuser);

                stage.show();
                break;
            }
            case "RESIDENT": {
                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/residentmenu.fxml"));
                stage.setScene(new Scene(loader.load(), 800, 600));

                ResidentMenu menu = loader.getController();
                menu.setCurrentuser(currentuser);

                stage.show();
                break;
            }
            default:
                faultLabel.setText("Wrong user or password, please try again");
                currentuser.reset();
                usernameField.setText("");
                passwordField.setText("");
                break;
        }
    }

    @FXML public void handleCreditBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/developer.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        Developer dev = loader.getController();
        dev.setCheck(-1);

        stage.show();
    }

    /*public void setCurrentuser(User currentuser)
    {
        this.currentuser = currentuser;
    }*/
}
