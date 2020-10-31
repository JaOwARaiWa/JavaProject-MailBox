package condo.controller.resident;

import condo.controller.ChangePasswordController;
import condo.controller.LoginController;
import condo.controller.staff.MailDetailController;
import condo.controller.staff.MailHistoryController;
import condo.model.Mail;
import condo.model.Resident;
import condo.model.User;
import condo.process.ProgramDataSource;
import condo.process.ProgramDataSourceFile;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ResidentMenuController
{
    @FXML Button signOutBtn, changePassBtn, historyBtn;
    @FXML Label userLabel, roomLabel;
    @FXML
    TableView mailTable;
    @FXML
    TableColumn typeCol, roomCol, senderCol, dateCol, timeCol, staffCol, statusCol;

    private User currentUser;
    private Mail currentMail;
    private ObservableList<Mail> mailList;
    protected ArrayList<Mail> thisRoom = new ArrayList<>();
    private ArrayList<Mail> temp = new ArrayList<>();
    private ProgramDataSource source = new ProgramDataSourceFile();

    @FXML public void initialize() throws IOException
    {
        Platform.runLater(() ->
        {
            userLabel.setText(currentUser.getName());
            roomLabel.setText(((Resident) currentUser).getRoom());
            try {
                setMailTable();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    public void setMailTable() throws IOException {
        temp = source.readMail("in stock");

        for (Mail checkMail : temp)
        {
            if (checkMail.getRoom().equals(((Resident) currentUser).getRoom()))
            {
                thisRoom.add(checkMail);
            }
        }

        if (thisRoom != null)
        {
            mailList = FXCollections.observableArrayList(thisRoom);
            typeCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Type"));
            senderCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Sender"));
            roomCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Room"));
            dateCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Date"));
            timeCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Time"));
            staffCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Staff"));
            statusCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Status"));
            mailTable.setItems(mailList);

            mailTable.setRowFactory( table ->
            {
                TableRow row = new TableRow();
                row.setOnMouseClicked(event ->
                {
                    if (event.getClickCount() == 2 && (!row.isEmpty()))
                    {
                        Mail thisMail = (Mail) row.getItem();
                        setCurrentMail(thisMail);
                        try
                        {
                            doubleClicked();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                });
                return row;
            });
        }
        else
        {
            mailTable.setPlaceholder(new Label("There is no mail"));
        }
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

    public void doubleClicked() throws IOException
    {
        System.out.println(currentMail.getRoom());
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/maildetailwindow.fxml"));
        stage.setTitle("6210450032");
        stage.setScene(new Scene(loader.load(), 400, 600));

        MailDetailController mdct = loader.getController();
        mdct.setCurrentMail(currentMail);
        mdct.setCurrentUser(currentUser);

        stage.show();
    }

    @FXML public void handleHistoryBtnOnAction(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mailhistorywindow.fxml"));
        stage.setScene(new Scene(loader.load(), 600, 400));

        MailHistoryController mhct = loader.getController();
        mhct.setCurrentUser(currentUser);

        stage.show();
    }

    private void setCurrentMail(Mail currentMail)
    {
        this.currentMail = currentMail;
    }

    public void setCurrentUser(User currentUser)
    {
        this.currentUser = currentUser;
    }

}
