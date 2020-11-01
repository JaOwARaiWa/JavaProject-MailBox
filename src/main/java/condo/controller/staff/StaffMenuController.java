package condo.controller.staff;

import condo.controller.ChangePasswordController;
import condo.controller.LoginController;
import condo.model.Letter;
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
    @FXML Button signOutBtn, addMailBtn, listBtn, changePassBtn, refreshBtn, searchBtn;
    @FXML Label idLabel, nameLabel;
    @FXML TableColumn typeCol, roomCol, senderCol, dateCol, timeCol, staffCol, statusCol;
    @FXML TableView mailTable;
    @FXML TextField searchField;

    private User currentUser;
    private Letter currentLetter;
    private ObservableList<Letter> letterList;
    private ObservableList<Letter> showList;
    private ProgramDataSource source = new ProgramDataSourceFile();
    private int stage = 0;

    @FXML public void initialize() throws IOException
    {
        Platform.runLater(() ->
        {
            idLabel.setText(currentUser.getId());
            nameLabel.setText(currentUser.getName());

        });

        if (stage == 0)
        {
            showList = FXCollections.observableArrayList(source.readMail("in stock"));
        }
        showMailTable();


        mailTable.setRowFactory( table ->
        {
            TableRow row = new TableRow();
            row.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 2 && (!row.isEmpty()))
                {
                    Letter thisLetter = (Letter) row.getItem();
                    setCurrentLetter(thisLetter);
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

        //try to real time search but its not
        /*searchField.textProperty().addListener((observable, before, after) ->
        {
            if (after != null)
            {
                try {
                    showList = FXCollections.observableArrayList(source.searchRealtime(after));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mailTable.setItems(showList);
                mailTable.refresh();
            }
        });*/

    }

    @FXML public void handleSearchBtnOnAction(ActionEvent event) throws IOException
    {
        showList = FXCollections.observableArrayList(source.searchMail(searchField.getText()));
        stage = 1;
        mailTable.setItems(showList);
        mailTable.refresh();
    }

    public void showMailTable() throws IOException
    {
        letterList = FXCollections.observableArrayList(showList);
        typeCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Type"));
        senderCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Sender"));
        roomCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Room"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Time"));
        staffCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Staff"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Status"));
        mailTable.setItems(letterList);
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
        stage.setTitle("6210450032");

        MailHistoryController mhct = loader.getController();

        stage.show();
    }

    public void refresher() throws IOException
    {
        ObservableList refreshList = FXCollections.observableArrayList(source.readMail("in stock"));
        mailTable.setItems(refreshList);
        mailTable.refresh();
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
        System.out.println(currentLetter.getRoom());
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/maildetailwindow.fxml"));
        stage.setTitle("6210450032");
        stage.setScene(new Scene(loader.load(), 400, 600));

        MailDetailController mdct = loader.getController();
        mdct.setCurrentLetter(currentLetter);
        mdct.setCurrentUser(currentUser);

        stage.showAndWait();
        refresher();
    }

    @FXML public void handleRefreshBtnOnAction(ActionEvent event) throws IOException
    {
        ObservableList refreshList = FXCollections.observableArrayList(source.readMail("in stock"));
        mailTable.setItems(refreshList);
        mailTable.refresh();
    }

    private void setCurrentLetter(Letter currentLetter)
    {
        this.currentLetter = currentLetter;
    }

    public void setCurrentUser(Staff currentUser)
    {
        this.currentUser = currentUser;
    }
}
