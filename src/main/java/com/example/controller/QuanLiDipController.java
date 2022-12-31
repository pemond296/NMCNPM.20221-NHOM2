package com.example.controller;

import com.example.model.EventGiftModel;
import com.example.service.IEventGiftService;
import com.example.service.impl.EventGiftService;
import com.example.utils.DipUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class QuanLiDipController implements Initializable {
    private EventGiftService eventGiftService = new EventGiftService();

    @FXML
    private TableView<EventGiftModel> DipTable;

    @FXML
    private Button CapNhatB;

    @FXML
    private TableColumn<?, ?> DoiTuongNhanC;

    @FXML
    private TableColumn<?, ?> PhanQuaC;

    @FXML
    private TableColumn<?, ?> SttC;

    @FXML
    private Button TaoMoiDipB;

    @FXML
    private TableColumn<?, ?> TenDipC;

    @FXML
    private TableColumn<?, ?> ThoiGianC;

    @FXML
    private Button XemB;

    List<EventGiftModel> models = eventGiftService.findAll();
    ObservableList<EventGiftModel> observableListEvenGift = FXCollections.observableArrayList(models);

    @FXML
    void CapNhatE(ActionEvent event) throws IOException {
        EventGiftModel eventGiftModel = DipTable.getSelectionModel().getSelectedItem();
        DipUtil.getInstance().setData(eventGiftModel);
        if (DipUtil.getInstance().getData() != null) {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(CapNhatDipController.class.getResource("CapNhatDip.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
            observableListEvenGift.clear();
            observableListEvenGift.addAll(eventGiftService.findAll());
            DipTable.setItems(observableListEvenGift);
        }
    }

    @FXML
    void TaoMoiE(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(TaoMoiDipController.class.getResource("TaoMoiDip.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        observableListEvenGift.clear();
        observableListEvenGift.addAll(eventGiftService.findAll());
        DipTable.setItems(observableListEvenGift);
    }

    @FXML
    void XemE(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SttC.setCellValueFactory(new PropertyValueFactory<>("id"));
        TenDipC.setCellValueFactory(new PropertyValueFactory<>("nameEvent"));
        ThoiGianC.setCellValueFactory(new PropertyValueFactory<>("date"));
        DoiTuongNhanC.setCellValueFactory(new PropertyValueFactory<>("ten_doi_tuong"));
        PhanQuaC.setCellValueFactory(new PropertyValueFactory<>("ten_phan_thuong"));
        DipTable.setItems(observableListEvenGift);
    }
}