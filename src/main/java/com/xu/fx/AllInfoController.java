package com.xu.fx;

import com.xu.db.CustomerDao;
import com.xu.db.RoomHistoryDao;
import com.xu.model.Customer;
import com.xu.model.RoomReserve;
import eu.schudt.javafx.controls.calendar.DatePicker;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AllInfoController implements Initializable {

    public TextField idCard;
    public TextField name;
    public TableView<Customer> userList;
    public DatePicker startDate;
    public DatePicker endDate;

    private Customer curCustomer = new Customer();
    private RoomReserve curReserve = new RoomReserve();

    Logger log = LoggerFactory.getLogger(AllInfoController.class);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                curCustomer.setName(newVal);
            }
        });
        idCard.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                curCustomer.setIdcard(newVal);
            }
        });
    }

    public void sch() {
        try {
            ObservableList<Customer> data = FXCollections.observableArrayList(
                    CustomerDao.getCustomerListByName(name.getText())
            );
            userList.setItems(data);
        } catch (SQLException e) {
            log.error("db err", e);
        }
    }

    public void fillCustomer() {
        Customer c = userList.getSelectionModel().getSelectedItem();
        if (c != null) {
            curCustomer = c;
            curReserve.setCustomerId(c.getId());
            name.setText(c.getName());
            idCard.setText(c.getIdcard());
        }
    }

    public void saveCustomer() {
        try {
            CustomerDao.save(curCustomer);
            curReserve.setCustomerId(curReserve.getId());
        } catch (SQLException e) {
            log.error("saveCustomer fail", e);
        }
    }

    public void saveReserve() {
        try {
            curReserve.setStartTime(startDate.getSelectedDate());
            curReserve.setEndTime(endDate.getSelectedDate());
            curReserve.setState(0);
            RoomHistoryDao.saveReserve(curReserve);
        } catch (SQLException e) {
            log.error("saveReserve fail", e);
        }
    }
}
