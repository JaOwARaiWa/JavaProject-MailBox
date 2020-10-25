package condo.controller.admin;

import condo.controller.ChangePasswordController;
import condo.controller.LoginController;
import condo.model.Staff;
import condo.model.User;
import condo.process.ProgramDataSource;
import condo.process.ProgramDataSourceFile;
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

public class AdminMenuController
{
    @FXML Button signOutBtn, addStaffBtn, profileBtn, changePassBtn, backBtn;
    @FXML Label idLabel, nameLabel;
    @FXML TableView staffTable;
    @FXML TableColumn userCol, nameCol, surnameCol, dateCol, timeCol;
    private User currentuser;

    private ObservableList<Staff> staffList;
    private ArrayList<Staff> staffs;
    private ProgramDataSource programDataSource = new ProgramDataSourceFile();

    @FXML public void initialize() throws IOException
    {
        staffList = FXCollections.observableArrayList(programDataSource.showStaffHistory());
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

        Platform.runLater(() -> {
            idLabel.setText(currentuser.getId());
            nameLabel.setText(currentuser.getName());
        });
    }



    @FXML public void handleSignOutBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/loginpage.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        LoginController in = loader.getController();

        stage.show();
    }


    @FXML public void handleChangePassBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/changepasswordpage.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        ChangePasswordController chng = loader.getController();
        chng.setCurrentuser(currentuser);

        stage.show();
    }

    @FXML public void handleAddStaffBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addstaffpage.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        AddStaffController adst = loader.getController();
        adst.setCurrentuser(currentuser);

        stage.show();
    }

    public void setCurrentuser(User currentuser)
    {
        this.currentuser = currentuser;
    }
}
