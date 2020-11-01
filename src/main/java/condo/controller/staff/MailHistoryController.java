package condo.controller.staff;

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

public class MailHistoryController
{
    @FXML Button backBtn;
    @FXML TableColumn typeCol, receiverCol, roomCol, dateCol, timeCol, staffCol;
    @FXML TableView mailTable;

    private ObservableList<Letter> letterList;
    private ArrayList<Letter> thisRoom = new  ArrayList<>();
    private ArrayList<Letter> temp = new ArrayList<>();
    private ProgramDataSource source = new ProgramDataSourceFile();
    private Letter currentLetter;
    private User currentUser;
    private int check = 0;

    @FXML public void initialize() throws IOException
    {
        Platform.runLater(() ->
        {
            try {
                setMailTable();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setMailTable() throws IOException
    {
        if (currentUser != null)
        {
            if (currentUser.getId().equals("RESIDENT"))
            {
                temp = source.readMail("picked up");

                for (Letter checkLetter : temp)
                {
                    if (checkLetter.getRoom().equals(((Resident) currentUser).getRoom()))
                    {
                        thisRoom.add(checkLetter);
                    }
                }
                check = 1;
            }
        }
        else
        {
            check = -1;

        }

        if (check == 1)
        {
            if (thisRoom != null)
            {
                letterList = FXCollections.observableArrayList(thisRoom);
                typeCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Type"));
                receiverCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Receiver"));
                roomCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Room"));
                dateCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Date"));
                timeCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Time"));
                staffCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Staff"));
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
                mailTable.setPlaceholder(new Label("There is no mail history"));
            }

        }
        else
        {
            letterList = FXCollections.observableArrayList(source.readMail("picked up"));
            typeCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Type"));
            receiverCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Receiver"));
            roomCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Room"));
            dateCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Date"));
            timeCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Time"));
            staffCol.setCellValueFactory(new PropertyValueFactory<Letter, String>("Staff"));
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

    @FXML public void handleBackBtnOnAction(ActionEvent event)
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
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
