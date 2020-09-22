package condo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Developer
{
    @FXML Button backBtn;
    private String username;
    private String room;
    int check;

    @FXML public void initialize()
    {

    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException
    {
        if (check == 1)
        {
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/setting.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            Setting sett = loader.getController();
            sett.setUser(username);
            sett.setCheck(check);

            stage.show();
        }
        else
        {
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            Login in = loader.getController();

            stage.show();
        }
    }

    public void setUser(String username)
    {
        this.username = username;
    }

    public void setCheck(int check)
    {
        this.check = check;
    }
}
