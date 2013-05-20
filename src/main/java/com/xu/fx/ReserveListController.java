package com.xu.fx;

import com.xu.db.RoomHistoryDao;
import com.xu.model.RoomHistory;
import com.xu.model.RoomReserve;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReserveListController implements Initializable {
    Logger logger = LoggerFactory.getLogger(ReserveListController.class);

    @FXML
    public TableView<RoomReserve> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<RoomReserve> data = FXCollections.observableArrayList(
                    RoomHistoryDao.getReserveList()
            );
            list.setItems(data);
        } catch (SQLException e) {
            logger.error("db drr", e);
        }
    }
}
