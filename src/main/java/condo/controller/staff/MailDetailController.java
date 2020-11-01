package condo.controller.staff;

import condo.model.Document;
import condo.model.Letter;
import condo.model.Parcel;
import condo.model.User;
import condo.process.ProgramDataSource;
import condo.process.ProgramDataSourceFile;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class MailDetailController
{
    @FXML Button pickUpBtn, backBtn;
    @FXML Label senderLabel, typeLabel, residentLabel, roomLabel, staffLabel, dateLabel, timeLabel, sizeLabel, dynamicLabel, detailLabel, dynamicLabel2, detailLabel2, receiverLabel, receiverNameLabel;
    @FXML ImageView mailImage;
    @FXML TextField receiverField;

    private Letter currentLetter;
    private User currentUser;
    private Path image;
    private Alert popup = new Alert(Alert.AlertType.NONE);
    private ProgramDataSource source = new ProgramDataSourceFile();

    @FXML public void initialize()
    {
        Platform.runLater(() ->
        {
            senderLabel.setText(currentLetter.getSender());
            typeLabel.setText(currentLetter.getType());
            residentLabel.setText(currentLetter.getTo());
            roomLabel.setText(currentLetter.getRoom());
            staffLabel.setText(currentLetter.getStaff());
            dateLabel.setText(currentLetter.getDate());
            timeLabel.setText(currentLetter.getTime());
            sizeLabel.setText(currentLetter.getSize());

            if (currentUser != null)
            {
                if (!currentUser.getId().equals("STAFF"))
                {
                    pickUpBtn.setDisable(true);
                    receiverField.setDisable(true);
                }
                else
                {
                    pickUpBtn.setDisable(false);
                    receiverField.setDisable(false);
                }
            }

            if (currentLetter.getType().equals("Document"))
            {
                dynamicLabel.setText("Priority : ");
                detailLabel.setText(((Document) currentLetter).getPriority());
            }
            else if (currentLetter.getType().equals("Parcel"))
            {
                dynamicLabel.setText("Service : ");
                detailLabel.setText(((Parcel) currentLetter).getService());
                dynamicLabel2.setText("Tracking number : ");
                detailLabel2.setText(((Parcel) currentLetter).getTracknum());
            }

            image = FileSystems.getDefault().getPath(currentLetter.getImage());
            mailImage.setImage(new Image(image.toUri().toString()));

            if (!currentLetter.getStatus().equals("in stock"))
            {
                pickUpBtn.setVisible(false);
                receiverField.setVisible(false);
                receiverNameLabel.setText(currentLetter.getReceiver());
            }
        });
    }

    @FXML public void handlePickUpBtnOnAction(ActionEvent event) throws IOException
    {
        currentLetter.setStaff(currentUser.getName());
        currentLetter.setReceiver(receiverField.getText());
        currentLetter.setStatus("picked up");

        source.pickUpMail(currentLetter);

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

    public void setCurrentLetter(Letter currentLetter)
    {
        this.currentLetter = currentLetter;
    }

    public void setCurrentUser(User currentUser)
    {
        this.currentUser = currentUser;
    }
}
