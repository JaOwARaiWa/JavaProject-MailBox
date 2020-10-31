package condo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class DeveloperController
{
    @FXML Button backBtn;
    @FXML ImageView imageView;

    @FXML public void initialize()
    {
        Image image = new Image("/pictures/profile.jpg");
        imageView.setImage(image);
    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/loginpage.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        LoginController in = loader.getController();

        stage.show();
    }

}
