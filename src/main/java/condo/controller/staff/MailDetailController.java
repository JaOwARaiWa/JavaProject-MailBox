package condo.controller.staff;

import condo.model.Document;
import condo.model.Mail;
import condo.model.Parcel;
import condo.model.User;
import condo.process.ProgramDataSource;
import condo.process.ProgramDataSourceFile;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;

public class MailDetailController
{
    @FXML Button pickUpBtn, backBtn;
    @FXML Label senderLabel, typeLabel, residentLabel, roomLabel, staffLabel, dateLabel, timeLabel, sizeLabel, dynamicLabel, detailLabel, dynamicLabel2, detailLabel2, receiverLabel, receiverNameLabel;
    @FXML ImageView mailImage;
    @FXML TextField receiverField;

    private Mail currentMail;
    private User currentUser;
    private Path image;
    private Alert popup = new Alert(Alert.AlertType.NONE);
    private ProgramDataSource source = new ProgramDataSourceFile();

    @FXML public void initialize()
    {
        Platform.runLater(() ->
        {
            senderLabel.setText(currentMail.getSender());
            typeLabel.setText(currentMail.getType());
            residentLabel.setText(currentMail.getTo());
            roomLabel.setText(currentMail.getRoom());
            staffLabel.setText(currentMail.getStaff());
            dateLabel.setText(currentMail.getDate());
            timeLabel.setText(currentMail.getTime());
            sizeLabel.setText(currentMail.getSize());

            if (currentMail.getType().equals("Document"))
            {
                dynamicLabel.setText("Priority : ");
                detailLabel.setText(((Document) currentMail).getPriority());
            }
            else if (currentMail.getType().equals("Parcel"))
            {
                dynamicLabel.setText("Service : ");
                detailLabel.setText(((Parcel) currentMail).getService());
                dynamicLabel2.setText("Tracking number : ");
                detailLabel2.setText(((Parcel) currentMail).getTracknum());
            }

            image = FileSystems.getDefault().getPath(currentMail.getImage());
            mailImage.setImage(new Image(image.toUri().toString()));

            if (!currentMail.getStatus().equals("in stock"))
            {
                pickUpBtn.setVisible(false);
                receiverField.setVisible(false);
                receiverNameLabel.setText(currentMail.getReceiver());
            }


        });


    }

    @FXML public void handlePickUpBtnOnAction(ActionEvent event) throws IOException
    {
        source.updateMail(currentMail);

        currentMail.setDate(LocalDate.now().toString());
        currentMail.setTime(LocalTime.now().toString());
        currentMail.setStaff(currentUser.getName());
        currentMail.setReceiver(receiverField.getText());

        source.pickUpMail(currentMail);

        popup.setAlertType(Alert.AlertType.INFORMATION);
        popup.setTitle("Done");
        popup.setHeaderText("Pick up successful");
        popup.setContentText("Please check mail history");
        popup.showAndWait();

        if (popup.getResult() == ButtonType.OK)
        {
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();
            stage.close();
        }

    }

    @FXML public void handleBackBtnOnAction(ActionEvent event)
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }

    public void setCurrentMail(Mail currentMail)
    {
        this.currentMail = currentMail;
    }

    public void setCurrentUser(User currentUser)
    {
        this.currentUser = currentUser;
    }
}
