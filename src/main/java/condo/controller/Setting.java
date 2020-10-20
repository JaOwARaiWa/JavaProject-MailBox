package condo.controller;

import condo.model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Setting
{
    @FXML Button signOutBtn, letterOnlyBtn, docOnlyBtn, parcelOnlyBtn, historyBtn, settingBtn, backBtn, changePassBtn, creditBtn;
    @FXML Label userLabel, roomLabel;
    private User currentuser;

    @FXML public void initialize()
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run()
            {

            }
        });
    }

    @FXML public void handleHistoryBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mailhistory_resident.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        History hit = loader.getController();
        hit.setCurrentuser(currentuser);

        stage.show();
    }

    @FXML public void handleSignOutBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        Login in = loader.getController();

        stage.show();
    }

    @FXML public void handleCreditBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/developer.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        Developer dev = loader.getController();
        dev.setCurrentuser(currentuser);

        stage.show();
    }

    @FXML public void handleChangePassBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/changepassword.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        ChangePassword chg = loader.getController();
        chg.setCurrentuser(currentuser);

        stage.show();
    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/residentmenu.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        ResidentMenu menu = loader.getController();
        menu.setCurrentuser(currentuser);

        stage.show();
    }

    public void setCurrentuser(User currentuser)
    {
        this.currentuser = currentuser;
    }

}
