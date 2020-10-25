package condo.controller.staff;

import condo.controller.ChangePasswordController;
import condo.controller.LoginController;
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

public class StaffMenuController
{
    @FXML Button signOutBtn, addMailBtn, listBtn, changePassBtn;
    @FXML Label idLabel, nameLabel;
    private User currentUser;

    @FXML public void initialize()
    {
        Platform.runLater(() -> {
            idLabel.setText(currentUser.getId());
            nameLabel.setText(currentUser.getName());
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

    @FXML public void handleAddRoomBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addroompage.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        AddRoomController addr = loader.getController();
        addr.setCurrentUser(currentUser);

        stage.show();
    }

   /* @FXML public void handleAddMailBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(""));
        stage.setScene(new Scene(loader.load(), 800, 600));

         = loader.getController();

        stage.show();
    }*/

    @FXML public void handleListBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/roomlistpage.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        RoomListController rlct = loader.getController();
        rlct.setCurrentUser(currentUser);

        stage.show();
    }

    @FXML public void handleChangePassBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/changepasswordpage.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        ChangePasswordController chng = loader.getController();
        chng.setCurrentuser(currentUser);

        stage.show();
    }

    public void setCurrentUser(Staff currentUser)
    {
        this.currentUser = currentUser;
    }
}
