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
import java.util.ArrayList;

public class Login implements Account
{
    @FXML Button loginBtn, creditBtn;
    @FXML Label faultLabel;
    @FXML TextField usernameField;
    @FXML PasswordField passwordField;
    private String user;
    private String pass;
    private String iden;
    private int index;
    private int i;
    private ArrayList<String> userList = new ArrayList<>();
    private ArrayList<String> passList = new ArrayList<>();
    private ArrayList<String> idenList = new ArrayList<>();
    private ArrayList acc;
    private int check = 0;

    @FXML public void initialize() throws IOException
    {
        acc = Account.readAcc();
        userList = (ArrayList<String>) acc.get(0);
        passList = (ArrayList<String>) acc.get(1);
        idenList = (ArrayList<String>) acc.get(2);
    }

    @FXML public void handleLoginBtnOnAction(ActionEvent event) throws IOException
    {
        user = usernameField.getText();
        pass = passwordField.getText();
        int fault = 0;
        for (i = 0; i < userList.size(); i-=-1)
        {
            if (user.equals(userList.get(i)) && pass.equals(passList.get(i)))
            {
                iden = idenList.get(i);
                index = i;
                fault = -1;
                break;
            }
            else
            {
                fault-=-1;
            }
        }
        if (fault != -1)
        {
            iden = "Fault";
        }

        System.out.println(user);
        System.out.println(pass);
        System.out.println(iden);

        if (iden.equals("ROOMER"))
        {
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/roomermenu.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            RoomerMenu menu = loader.getController();
            menu.setUser(user);
            menu.setIden(iden);
            menu.setIndex(index);

            stage.show();
        }
        else if (iden.equals("ADMIN"))
        {
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminmenu.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            AdminMenu menu = loader.getController();
            /*menu.setUser(user);
            menu.setIden(iden);
            menu.setIndex(index);*/

            stage.show();
        }
        else if (iden.equals("STAFF"))
        {
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/staffmenu.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            StaffMenu menu = loader.getController();
            /*menu.setUser(user);
            menu.setIden(iden);
            menu.setIndex(index);*/

            stage.show();
        }
        else if (iden.equals("Fault"))
        {
            faultLabel.setText("Wrong user or password, please try again");
        }
    }

    @FXML public void handleCreditBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/developer.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        Developer dev = loader.getController();
        dev.setCheck(-1);

        stage.show();
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
