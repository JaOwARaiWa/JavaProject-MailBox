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

public class History
{
    @FXML
    Button signOutBtn, letterOnlyBtn, docOnlyBtn, parcelOnlyBtn, historyBtn, settingBtn, backBtn, changePassBtn, creditBtn;
    @FXML
    Label userLabel, roomLabel;
    private String user;
    private String room;
    int check;
    private Receiver r;

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
                    room = r.getId();
                    userLabel.setText(user);
                    roomLabel.setText(room);
                }

            }
        });
    }

    @FXML public void handleSettingBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/setting.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        Setting sett = loader.getController();
        sett.setUser(user);
        sett.setCheck(1);
        sett.setR(r);

        stage.show();
    }

    @FXML public void handleSignOutBtnOnAction(ActionEvent event) throws IOException
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

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/usermenu.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        UserMenu menu = loader.getController();
        menu.setUser(user);
        menu.setCheck(1);
        menu.setR(r);

        stage.show();
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
