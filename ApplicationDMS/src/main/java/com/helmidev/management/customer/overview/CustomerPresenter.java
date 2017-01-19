/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.management.customer.overview;

import com.helmidev.entities.Customer;
import com.helmidev.jpacontrollers.exceptions.IllegalOrphanException;
import com.helmidev.jpacontrollers.exceptions.NonexistentEntityException;
import com.helmidev.management.customer.edit.EditCustomerPresenter;
import com.helmidev.management.customer.edit.EditCustomerView;
import com.helmidev.services.CustomerService;
import com.main.commons.ModalDialog;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javax.inject.Inject;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mayel-1
 */
public class CustomerPresenter implements Initializable {

    // =======Add customer 
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
    //=======End add customer

    // ======Edit customer
    @FXML
    Button editCustomerButton;
    // ======End Edit customer

    // ======Delete Customer
    @FXML
    Button deleteCustomerButton;
    // ======Delete Customer

    //========Customer table=========
    @FXML
    private TableView customersTable;
    @FXML
    AnchorPane customerViewPanel;
    @FXML
    TableColumn<Customer, Integer> idcolumn;
    @FXML
    TableColumn<Customer, String> firstname;
    @FXML
    TableColumn<Customer, String> lastName;
    @FXML
    TableColumn<Customer, String> emailadr;
    @FXML
    TableColumn<Customer, Double> amount;

    // =======Mouse Events
    // =======End Mouse Events
    private ObservableList<Customer> customerData;
    //========End customer table=============

    @Inject
    CustomerService customerService;

    EditCustomerPresenter editPresenter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        saveCustomerButton.setOnAction((ActionEvent event) -> {
            onCustomerSaveClick(event);
        });
        deleteCustomerButton.setOnAction((ActionEvent event) -> {
            onCustomerDeleteClick(event);
        });
        editCustomerButton.setOnAction((ActionEvent event) -> {
            onCustomerEditClick(event);
        });

    }

    /**
     * setCustomer the customer list table and set data
     */
    public void initializeCustomerTableView() {

        idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        emailadr.setCellValueFactory(new PropertyValueFactory<>("email"));
        amount.setCellValueFactory(new PropertyValueFactory<>("account"));
        customerData = FXCollections.observableArrayList();
        customerData.addAll(customerService.getAllCustomer());
        customersTable.setItems(customerData);

    }

    @FXML
    public void customerSelected(MouseEvent event) {

    }

    private void onCustomerSaveClick(ActionEvent event) {        
        Customer customer = new Customer();
        customer.setFirstname(this.customerFirstName.getText());
        customer.setLastname(this.customerLastName.getText());
        customer.setEmail(this.customerEmailAdress.getText());

        if (StringUtils.isEmpty(this.customerAmount.getText())) {
            customer.setAccount(0);
        } else {
            customer.setAccount(Double.valueOf(this.customerAmount.getText()));
        }
        try {
            this.customerService.addCustomer(customer);
            initializeCustomerTableView();
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Confirmation");
            info.setHeaderText("Speichern erfolgreich");
            String msg = String.format("Kunde %1s wurde erfolgreich hinzugefügt", customerFirstName.getText() + " " + customerLastName.getText());
            info.setContentText(msg);
            info.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
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

        System.out.println(event.getSource().toString());
        System.out.println(this.customerFirstName.getText());
        System.out.println(this.customerLastName.getText());
        System.out.println(this.customerEmailAdress.getText());
        System.out.println(this.customerAmount.getText());
    }

    private void onCustomerDeleteClick(ActionEvent event) {
        Customer customer = (Customer) customersTable.getSelectionModel().getSelectedItem();
        String f = customer.getFirstname();
        String l = customer.getLastname();
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Bestätigung");
        confirm.setHeaderText("Möchten Sie wircklich den Kunden" + " " + f + " " + l + " " + "löschen ?");
        confirm.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                try {
                    customerService.removeCustomer(customer);
                    initializeCustomerTableView();
                } catch (IllegalOrphanException ex) {
                    Logger.getLogger(CustomerPresenter.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(CustomerPresenter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );

    }

    public Customer selectedCustomer() {
        return (Customer) customersTable.getSelectionModel().getSelectedItem();
    }

    private void onCustomerEditClick(ActionEvent event) {

        Customer customer = (Customer) customersTable.getSelectionModel().getSelectedItem();
        if (customer != null) {
            EditCustomerView editView = new EditCustomerView();
            this.editPresenter = (EditCustomerPresenter) editView.getPresenter();
            editPresenter.setCustomer(customer);
            ModalDialog dialogimpl = new ModalDialog();
            Stage dialog = dialogimpl.createModal(editView.getView(), (Node) event.getSource());
            dialog.setMaxWidth(700);
            dialog.setMaxHeight(250);
            dialog.showAndWait();

        }
    }
}
