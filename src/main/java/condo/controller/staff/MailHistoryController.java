package condo.controller.staff;

import condo.model.Mail;
import condo.process.ProgramDataSource;
import condo.process.ProgramDataSourceFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MailHistoryController
{
    @FXML Button backBtn;
    @FXML TableColumn typeCol, receiverCol, roomCol, dateCol, timeCol, staffCol;
    @FXML TableView mailTable;

    private ObservableList<Mail> mailList;
    private ProgramDataSource source = new ProgramDataSourceFile();
    private Mail currentMail;

    @FXML public void initialize() throws IOException
    {
        mailList = FXCollections.observableArrayList(source.readHistory());
        typeCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Type"));
        receiverCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Receiver"));
        roomCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Room"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Time"));
        staffCol.setCellValueFactory(new PropertyValueFactory<Mail, String>("Staff"));
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

    public void doubleClicked() throws IOException
    {
        System.out.println(currentMail);
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/maildetailwindow.fxml"));
        stage.setTitle("6210450032");
        stage.setScene(new Scene(loader.load(), 400, 600));

        MailDetailController mdct = loader.getController();
        mdct.setCurrentMail(currentMail);

        stage.show();
    }

    @FXML public void handleBackBtnOnAction(ActionEvent event)
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }

    private void setCurrentMail(Mail currentMail)
    {
        this.currentMail = currentMail;
    }

}
