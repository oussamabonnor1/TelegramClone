package Controllers;

import Models.CellViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label chatRoomNameLabel;

    @FXML
    private ListView<CellViewModel> usersListView;
    private ObservableList<CellViewModel> cellsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 10; i++) {
            cellsList.add(new CellViewModel("user " + i, "message " + i,
                    "0" + i + ":00", i + "", new Image("resources/img/smile.png")));
        }
        usersListView.setItems(cellsList);
        usersListView.setCellFactory(param -> new UserCustomCellController() {
            {
                prefWidthProperty().bind(usersListView.widthProperty().subtract(1)); // 1
            }
        });
    }

    @FXML
    void attachFile(MouseEvent event) {

    }

    @FXML
    void searchChatRoom(MouseEvent event) {

    }

    @FXML
    void settingsButtonClicked(MouseEvent event) {

    }

    @FXML
    void slideMenuClicked(MouseEvent event) {

    }

    @FXML
    void smileyButtonClicked(MouseEvent event) {

    }

    @FXML
    void vocalMessageClicked(MouseEvent event) {

    }

}
