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
    private String pass;
    private String iden;
    private int index;
    private int check;

    @FXML public void initialize()
    {

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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/setting_roomer.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            Setting sett = loader.getController();
            sett.setUser(user);
            sett.setIden(iden);
            sett.setIndex(index);

            stage.show();
        }
    }

    public void setCheck(int check)
    {
        this.check = check;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public void setIden(String iden)
    {
        this.iden = iden;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

}
