package com.xu.fx;

import com.xu.db.CustomerDao;
import com.xu.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AllInfoController implements Initializable {
    @FXML
    public TextField idcard;
    Logger log = LoggerFactory.getLogger(AllInfoController.class);
    @FXML
    public TextField name;
    @FXML
    public TableView<Customer> userList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void sch() {
        try {
            ObservableList<Customer> data = FXCollections.observableArrayList(
                    CustomerDao.getCustomerListByName(name.getText())
            );
            userList.setItems(data);
        } catch (SQLException e) {
            log.error("db drr", e);
        }
    }

    public void fillCustomer() {
        Customer c = userList.getSelectionModel().getSelectedItem();
        if(c!=null){
            name.setText(c.getName());
            idcard.setText(c.getIdcard());
        }
    }
}
