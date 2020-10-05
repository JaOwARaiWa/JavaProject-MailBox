package condo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangePassword implements Account
{
    @FXML Button backBtn, changeBtn;
    @FXML Label progressLabel;
    @FXML TextField usernameField;
    @FXML PasswordField newPassField, confirmField;
    private String user;
    private String pass;
    private String iden;
    private String id;
    private int index;
    private String newPass;
    private String confirm;


    @FXML public void initialize()
    {

    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException
    {
        if (iden.equals("ROOMER"))
        {
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/setting_roomer.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            Setting sett = loader.getController();

            stage.show();
        }
        else if (iden.equals("ADMIN"))
        {
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/setting_admin.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            Setting sett = loader.getController();

            stage.show();
        }
        else if (iden.equals("STAFF"))
        {
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/setting_staff.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            Setting sett = loader.getController();

            stage.show();
        }

    }

    @FXML public void handleChangeBtnOnAction(ActionEvent event) throws IOException
    {
        id = usernameField.getText();
        newPass = newPassField.getText();
        confirm = confirmField.getText();

        System.out.println(id);
        System.out.println(newPass);
        System.out.println(confirm);

        if (id.equals(user) && newPass.equals(confirm))
        {
            Account.changePass(index, newPass);
            progressLabel.setText("Your password has changed");
        }
        else
        {
            progressLabel.setText("Wrong user or mismatch confirm password, please try again");
        }
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
