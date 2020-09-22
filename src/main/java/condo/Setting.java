package condo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Setting
{
    @FXML Button signOutBtn, letterOnlyBtn, docOnlyBtn, parcelOnlyBtn, historyBtn, settingBtn, backBtn, changePassBtn, creditBtn;
    @FXML Label userLabel, roomLabel;
    private String username;
    private String room;
    int check;

    @FXML public void initialize()
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run()
            {
                if (check == 0)
                {

                }
                else if (check == 1)
                {
                    if (username.equals("test"))
                    {
                        room = "000";
                    }
                    else
                    {
                        room = "-1";
                    }
                    userLabel.setText(username);
                    roomLabel.setText(room);
                }

            }
        });
    }

    @FXML public void handleHistoryBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/history.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        History hit = loader.getController();
        hit.setUser(username);
        hit.setCheck(1);

        stage.show();
    }

    @FXML public void handleSignOutBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        Login in = loader.getController();
        in.setCheck(0);

        stage.show();
    }

    @FXML public void handleCreditBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/developer.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        Developer dev = loader.getController();
        dev.setUser(username);
        dev.setCheck(1);

        stage.show();
    }

    @FXML public void handleChangePassBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/changepassword.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        ChangePassword chg = loader.getController();
        chg.setUser(username);
        chg.setCheck(1);

        stage.show();
    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/usermenu.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        UserMenu menu = loader.getController();
        menu.setUser(username);
        menu.setCheck(1);

        stage.show();
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
