package condo.controller.resident;

import condo.controller.ChangePasswordController;
import condo.controller.LoginController;
import condo.controller.staff.MailDetailController;
import condo.controller.staff.MailHistoryController;
import condo.model.Letter;
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

public class ResidentMenuController
{
    @FXML Button signOutBtn, changePassBtn, historyBtn;
    @FXML Label userLabel, roomLabel;
    @FXML
    TableView mailTable;
    @FXML
    TableColumn typeCol, roomCol, senderCol, dateCol, timeCol, staffCol, statusCol;

    private User currentUser;
    private Letter currentLetter;
    private ObservableList<Letter> letterList;
    protected ArrayList<Letter> thisRoom = new ArrayList<>();
    private ArrayList<Letter> temp = new ArrayList<>();
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

        for (Letter checkLetter : temp)
        {
            if (checkLetter.getRoom().equals(((Resident) currentUser).getRoom()))
            {
                thisRoom.add(checkLetter);
            }
        }

        if (thisRoom != null)
        {
            letterList = FXCollections.observableArrayList(thisRoom);
            typeCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Type"));
            senderCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Sender"));
            roomCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Room"));
            dateCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Date"));
            timeCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Time"));
            staffCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Staff"));
            statusCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Status"));
            mailTable.setItems(letterList);

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
        System.out.println(currentLetter.getRoom());
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/maildetailwindow.fxml"));
        stage.setTitle("6210450032");
        stage.setScene(new Scene(loader.load(), 400, 600));

        MailDetailController mdct = loader.getController();
        mdct.setCurrentLetter(currentLetter);
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

    private void setCurrentLetter(Letter currentLetter)
    {
        this.currentLetter = currentLetter;
    }

    public void setCurrentUser(User currentUser)
    {
        this.currentUser = currentUser;
    }

}
