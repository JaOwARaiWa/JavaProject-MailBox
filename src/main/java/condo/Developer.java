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
    private String user;
    private String room;
    int check;
    private Receiver r;

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
            sett.setUser(user);
            sett.setCheck(check);
            sett.setR(r);

            stage.show();
        }
        else
        {
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            Login in = loader.getController();
            in.setCheck(-1);
            in.setR(r);

            stage.show();
        }
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public void setCheck(int check)
    {
        this.check = check;
    }

    public void setR(Receiver r)
    {
        this.r = r;
    }
}
