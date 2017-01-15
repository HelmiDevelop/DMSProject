/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.dashboard;

import com.helmidev.management.customer.overview.CustomerPresenter;
import com.helmidev.management.customer.overview.CustomerView;
import com.helmidev.management.product.ProductPresenter;
import com.helmidev.management.product.ProductView;
import com.helmidev.welcomeboard.WelcomePresenter;
import com.helmidev.welcomeboard.WelcomeView;
import com.main.utils.GuiUtils;




import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author mayel-1
 */
public class DashboardPresenter implements Initializable{

    @FXML private Button btn_toolbar_createNewCustomer;
    @FXML private Button btnAddNewBilling;
    @FXML private Button btnViewAllBillings;

    @FXML private Button btnAddNewProduct;
    @FXML private Button btnViewAllProducts;

    @FXML private Button btnAddNewPackaging;
    @FXML private Button btnViewAllPackagings;

    @FXML private Button btnAddNewCustomer;
    @FXML private Button btnViewAllCustomers;
    @FXML private StackPane mainPanelAnchorPane;
    @FXML private ToolBar bottomToolbar;
    @FXML private ToolBar topToolbar;
    @FXML private MenuBar menuBar;
    @FXML private Menu menuFile;
    @FXML private Menu menuHelp;
    @FXML private ProgressBar loadingController;

    CustomerPresenter customerPresenter;
    ProductPresenter productPresenter;
    WelcomePresenter welcomePresenter;

    private Consumer<CustomerPresenter> customerconsumer;

    private ResourceBundle bundle;
    private String imageResourceUrl;
    ProgressBar progressBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        bundle = resources;
        imageResourceUrl = getClass().getResource("/images").toExternalForm();
        handleMainToolbar();
        loadWelcomeView();
        alignTooltipBarItems();
        // set programmatically
        //btn_toolbar_createNewCustomer.setText(bundle.getString("lbl_btn_toolbar_createNewCustomer"));

        /*btn_toolbar_createNewCustomer.setOnAction((ActionEvent event) -> {
            onAddNewCustomer(event);
        });*/
        btnAddNewBilling.setOnAction((ActionEvent event) -> {
            onAddNewBilling(event);
        });
        btnViewAllBillings.setOnAction((ActionEvent actionEvent) -> {
            onViewAllBillings(actionEvent);
        });
        btnAddNewProduct.setOnAction((ActionEvent event) -> {
            onAddNewProduct(event);
        });
        btnViewAllProducts.setOnAction((ActionEvent event) -> {
            onViewAllProducts(event);
        });
        /*btnAddNewCustomer.setOnAction((ActionEvent actionEvent)->{
            onAddNewCustomer(actionEvent);
        });*/
        btnViewAllCustomers.setOnAction((ActionEvent event) -> {
            onViewAllCustomers(event);
        });
        btnAddNewPackaging.setOnAction((ActionEvent event) -> {
            onAddNewPackaging(event);
        });
        btnViewAllPackagings.setOnAction((ActionEvent event) -> {
            onViewAllPackagings(event);
        });
    }
    
    private void handleMainToolbar(){
        Button addBillingBtn = new Button();
        Image billingImage = new Image(imageResourceUrl+"/ic_content_paste_black_24dp.png");
        addBillingBtn.setGraphic(new ImageView(billingImage));
        topToolbar.getItems().add(addBillingBtn);
    }
    private void alignTooltipBarItems(){
        progressBar = new ProgressBar();
        progressBar.setProgress(0);
        bottomToolbar.getItems().add(progressBar);
    }
    private void loadWelcomeView(){
        WelcomeView welcomeView = new WelcomeView();
        Parent view = welcomeView.getView();
        welcomePresenter = (WelcomePresenter)welcomeView.getPresenter();
        this.mainPanelAnchorPane.getChildren().clear();
        this.mainPanelAnchorPane.getChildren().add(view);
    }

    private void onAddNewBilling(ActionEvent event) {
        //TODO load add new billing view
        System.out.println(event.getSource().toString());
    }

    private void onViewAllBillings(ActionEvent event) {
        //TODO load Billings table View
        System.out.println(event.getSource().toString());
    }

    private void onAddNewProduct(ActionEvent event) {
        //TODO laod add new Product Table
        System.out.println(event.getSource().toString());
    }

    private void onViewAllProducts(ActionEvent event) {
        //TOD load product table.
        GuiUtils.handleLoading(loadingController, true);
        loadingController.autosize();
        ProductView productView = new ProductView();
        Parent view = productView.getView();
        productPresenter = (ProductPresenter) productView.getPresenter();
        this.mainPanelAnchorPane.getChildren().clear();
        this.mainPanelAnchorPane.getChildren().add(view);
        GuiUtils.handleLoading(loadingController, false);
        System.out.println(event.getSource().toString());
    }

    private void onViewAllCustomers(ActionEvent event) {        
        CustomerView customerView = new CustomerView();
        Consumer<Object> customerConsumer = this::consume;
        customerView.getPresenter(customerConsumer);
        Parent view = customerView.getView();
        this.mainPanelAnchorPane.getChildren().clear();
        this.mainPanelAnchorPane.getChildren().add(view);
        
        System.out.println(event.getSource().toString());
    }

    private void consume(Object presenter) {
        if (presenter instanceof CustomerPresenter) {
            this.customerPresenter = (CustomerPresenter) presenter;
            customerPresenter.initializeCustomerTableView();
        }

    }

    private void onAddNewPackaging(ActionEvent event) {
        //TODO load add new packaging
        System.out.println(event.getSource().toString());
    }

    private void onViewAllPackagings(ActionEvent event) {
        //TODO load packaging table
        System.out.println(event.getSource().toString());
    }

}
