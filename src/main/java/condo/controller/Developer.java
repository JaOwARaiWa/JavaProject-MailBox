package condo.controller;

import condo.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Developer
{
    @FXML Button backBtn;
    @FXML ImageView imageView;
    private User currentuser;
    private int check;

    @FXML public void initialize()
    {
        /*Image image = new Image("/image/profile.jpg");
        imageView.setImage(image);*/
    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException
    {
        if (check == -1)
        {
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            Login in = loader.getController();

            stage.show();
        }
        else
        {
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/setting_resident.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            Setting sett = loader.getController();
            sett.setCurrentuser(currentuser);

            stage.show();
        }
    }

    public void setCheck(int check)
    {
        this.check = check;
    }

    public void setCurrentuser(User currentuser)
    {
        this.currentuser = currentuser;
    }

}
