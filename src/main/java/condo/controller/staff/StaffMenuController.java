package condo.controller.staff;

import condo.controller.ChangePasswordController;
import condo.controller.LoginController;
import condo.model.Mail;
import condo.model.Room;
import condo.model.Staff;
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

public class StaffMenuController
{
    @FXML Button signOutBtn, addMailBtn, listBtn, changePassBtn, refreshBtn;
    @FXML Label idLabel, nameLabel;
    @FXML TableColumn typeCol, roomCol, senderCol, dateCol, timeCol, staffCol, statusCol;
    @FXML TableView mailTable;
    @FXML TextField searchField;

    private User currentUser;
    private Mail currentMail;
    private ObservableList<Mail> mailList;
    private ProgramDataSource source = new ProgramDataSourceFile();

    @FXML public void initialize() throws IOException
    {
        Platform.runLater(() -> {
            idLabel.setText(currentUser.getId());
            nameLabel.setText(currentUser.getName());
        });

        showMailTable();

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

        /*searchField.textProperty().addListener((observable, before, after) ->
        {
            if (after != null)
            {

            }
        });*/
    }

    public void showMailTable() throws IOException
    {
        mailList = FXCollections.observableArrayList(source.readMail("in stock"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Type"));
        senderCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Sender"));
        roomCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Room"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Time"));
        staffCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Staff"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Status"));
        mailTable.setItems(mailList);
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

    @FXML public void handleAddRoomBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addroompage.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        AddRoomController addr = loader.getController();
        addr.setCurrentUser(currentUser);

        stage.show();
    }

    @FXML public void handleAddMailBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addmailpage.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        AddMailController amct = loader.getController();
        amct.setCurrentUser(currentUser);

        stage.show();
    }

    @FXML public void handleHistoryBtnOnAction(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mailhistorywindow.fxml"));
        stage.setScene(new Scene(loader.load(), 600, 400));

        MailHistoryController mhct = loader.getController();

        stage.show();
    }

    @FXML public void handleListBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/roomlistpage.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        RoomListController rlct = loader.getController();
        rlct.setCurrentUser(currentUser);

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

    @FXML public void handleRefreshBtnOnAction(ActionEvent event) throws IOException
    {
        ObservableList refreshList = FXCollections.observableArrayList(source.readMail("in stock"));
        mailTable.setItems(refreshList);
        mailTable.refresh();
    }

    private void setCurrentMail(Mail currentMail)
    {
        this.currentMail = currentMail;
    }

    public void setCurrentUser(Staff currentUser)
    {
        this.currentUser = currentUser;
    }
}
