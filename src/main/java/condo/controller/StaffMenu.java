package condo.controller;

import condo.model.Staff;
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

public class StaffMenu
{
    @FXML Button signOutBtn, storeLetterBtn, storeDocBtn, storeParcelBtn, receivedHistoryBtn, settingBtn, backBtn;
    @FXML Label userLabel, roomLabel;
    private Staff currentuser;

    @FXML public void initialize()
    {

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

    @FXML public void handleReceivedHistoryBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mailhistory_resident.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        History hit = loader.getController();

        stage.show();
    }

    @FXML public void handleSettingBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/setting_resident.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        Setting sett = loader.getController();

        stage.show();
    }

    public void setCurrentuser(Staff currentuser)
    {
        this.currentuser = currentuser;
    }
}
