/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.management.customer.edit;

import com.helmidev.entities.Customer;
import com.helmidev.management.customer.overview.CustomerPresenter;
import com.helmidev.services.CustomerService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javax.inject.Inject;

/**
 *
 * @author hoben
 */
public class EditcustomerPresenter implements Initializable {

    @FXML
    private TextField customerFirstName;
    @FXML
    private TextField customerLastName;
    @FXML
    private TextField customerEmailAdress;
    @FXML
    private TextField customerAmount;
    @FXML
    Button saveCustomerButton;
    @FXML
    Button cancelEditCustomer;
    @FXML private DialogPane dilaDialogPane;

    @Inject
    CustomerService customerService;
    @Inject
    CustomerPresenter customerPresenter;

    Customer customerToedit;

    Boolean pressed;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveCustomerButton.setOnAction((ActionEvent event) -> {
            onSaveClick(event);

        });
        cancelEditCustomer.setOnAction((ActionEvent event) -> {
            onCancelClick(event);

        });
    }

    public void setCustomer(Customer customer) {        
        customerToedit = customer;
        editselectedCustomer();

    }

    public void intializeCustomer(Customer customer) {
        this.customerToedit = customer;
    }

    public Boolean isPressed() {
        return pressed;
    }

    private void onSaveClick(ActionEvent event) {

        customerToedit.setFirstname(this.customerFirstName.getText());
        customerToedit.setLastname(this.customerLastName.getText());
        customerToedit.setEmail(this.customerEmailAdress.getText());

        customerToedit.setAccount(Double.valueOf(this.customerAmount.getText()));

        try {
            this.customerService.updateCustomer(customerToedit);

            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Confirmation");
            info.setHeaderText("Speichern erfolgreich");
            String msg = String.format("Kunde %1s wurde erfolgreich hinzugefügt", customerFirstName.getText() + " " + customerLastName.getText());
            info.setContentText(msg);
            info.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    ((Node)event.getSource()).getScene().getWindow().hide();
                    System.out.println("Pressed OK.");
                }
            });
            
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Bitte Ihre Eingaben überprüfen");
            alert.setContentText(ex.getMessage());
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }
    }

    private void onCancelClick(ActionEvent event) {

        System.out.println(event.getSource().toString());
    }

    private void editselectedCustomer() {
        if (customerToedit != null) {
            customerFirstName.setText(customerToedit.getFirstname());
            customerLastName.setText(customerToedit.getLastname());
            customerEmailAdress.setText(customerToedit.getEmail());
            customerAmount.setText(String.valueOf(customerToedit.getAccount()));

        }

    }

}
