package condo.controller;

import condo.model.Staff;
import condo.model.User;
import condo.process.Account;
import condo.process.AccountProcessor;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AdminMenu
{
    @FXML Button signOutBtn, addStaffBtn, profileBtn, changePassBtn, backBtn;
    @FXML Label userLabel, roomLabel;
    @FXML TableView staffTable;
    @FXML TableColumn userCol, nameCol, surnameCol, dateCol, timeCol;
    private User currentuser;

    private ObservableList<Staff> staffList;
    private ArrayList<Staff> staffs;
    private Account account = new AccountProcessor();

    @FXML public void initialize() throws IOException
    {
        staffList = FXCollections.observableArrayList(account.staffHistory());
        userCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("Username"));
        staffTable.setItems(staffList);
        nameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("Name"));
        staffTable.setItems(staffList);
        surnameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("Surname"));
        staffTable.setItems(staffList);
        dateCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("Date"));
        staffTable.setItems(staffList);
        timeCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("Time"));
        staffTable.setItems(staffList);
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


    @FXML public void handleChangePassBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/changepassword.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        ChangePassword chng = loader.getController();
        chng.setCurrentuser(currentuser);

        stage.show();
    }

    @FXML public void handleAddStaffBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addstaff.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        AddStaff adst = loader.getController();
        adst.setCurrentuser(currentuser);

        stage.show();
    }

    public void setCurrentuser(User currentuser)
    {
        this.currentuser = currentuser;
    }
}
