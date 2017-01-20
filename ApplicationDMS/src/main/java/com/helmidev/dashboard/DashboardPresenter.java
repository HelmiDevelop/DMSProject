/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.dashboard;

import com.helmidev.database.config.DatabaseConfigPresenter;
import com.helmidev.database.config.DatabaseConfigView;
import com.helmidev.management.category.overview.CategoryView;
import com.helmidev.management.customer.overview.CustomerPresenter;
import com.helmidev.management.customer.overview.CustomerView;
import com.helmidev.management.product.overview.ProductPresenter;
import com.helmidev.management.product.overview.ProductView;
import com.helmidev.welcomeboard.WelcomePresenter;
import com.helmidev.welcomeboard.WelcomeView;
import com.main.utils.GuiUtils;
import com.main.utils.ImageNames;




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
import javafx.scene.control.MenuItem;
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

    
    @FXML private Button btnViewAllProducts;

    @FXML private Button btnCategoryOverview;
    

    @FXML private Button btnAddNewCustomer;
    @FXML private Button btnViewAllCustomers;
    @FXML private StackPane mainPanelAnchorPane;
    @FXML private ToolBar bottomToolbar;
    @FXML private ToolBar topToolbar;
    @FXML private MenuBar menuBar;
    @FXML private Menu menuFile;
    @FXML private Menu menuEdit;
    @FXML private Menu menuHelp;
    @FXML private ProgressBar loadingController;

    CustomerPresenter customerPresenter;
    ProductPresenter productPresenter;
    WelcomePresenter welcomePresenter;
    DatabaseConfigPresenter databaseConfigPresenter;

    private Consumer<CustomerPresenter> customerconsumer;

    private ResourceBundle bundle;
    private String imageResourceUrl;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        bundle = resources;
        imageResourceUrl = getClass().getResource("/images").toExternalForm();
        handleEditMenuItem();
        handleMainToolbar();
        loadWelcomeView();       
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
        
        btnViewAllProducts.setOnAction((ActionEvent event) -> {
            onViewAllProducts(event);
        });
        /*btnAddNewCustomer.setOnAction((ActionEvent actionEvent)->{
            onAddNewCustomer(actionEvent);
        });*/
        btnViewAllCustomers.setOnAction((ActionEvent event) -> {
            onViewAllCustomers(event);
        });
        btnCategoryOverview.setOnAction((ActionEvent event) -> {
            onCategoryOverview(event);
        });
        
    }
    private void handleEditMenuItem(){
        MenuItem editdb = new MenuItem(bundle.getString("lbl_menuItem_edit_editDb"));
        Image editdbConfig = new Image(ImageNames.EDIT_PEN.Name());
        editdb.setGraphic(new ImageView(editdbConfig));
        editdb.setOnAction((event) -> {
            onEditDbConfig(event);
        });
        this.menuEdit.getItems().add(editdb);
    }
    
    private void handleMainToolbar(){
        Button addBillingBtn = new Button();
        Image billingImage = new Image(ImageNames.CONTENT.Name());
        addBillingBtn.setGraphic(new ImageView(billingImage));
        topToolbar.getItems().add(addBillingBtn);
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
        GuiUtils.handleLoading(loadingController, true);
        CustomerView customerView = new CustomerView();
        Consumer<Object> customerConsumer = this::consume;
        customerView.getPresenter(customerConsumer);
        Parent view = customerView.getView();
        this.mainPanelAnchorPane.getChildren().clear();
        this.mainPanelAnchorPane.getChildren().add(view);
        GuiUtils.handleLoading(loadingController, false);
        System.out.println(event.getSource().toString());
    }

    private void consume(Object presenter) {
        if (presenter instanceof CustomerPresenter) {
            this.customerPresenter = (CustomerPresenter) presenter;
            customerPresenter.initializeCustomerTableView();
        }

    }

    

    private void onCategoryOverview(ActionEvent event) {
        GuiUtils.handleLoading(loadingController, true);
        CategoryView categoryView = new CategoryView();
        categoryView.getPresenter();
        Parent view = categoryView.getView();
        this.mainPanelAnchorPane.getChildren().clear();
        this.mainPanelAnchorPane.getChildren().add(view);
        GuiUtils.handleLoading(loadingController, false);
        System.out.println(event.getSource().toString());
    }

    private void onEditDbConfig(ActionEvent event) {
        
        DatabaseConfigView databaseConfigView = new DatabaseConfigView();
        databaseConfigPresenter = (DatabaseConfigPresenter)databaseConfigView.getPresenter();
        databaseConfigPresenter.fillToEdit();
        this.mainPanelAnchorPane.getChildren().clear();
        this.mainPanelAnchorPane.getChildren().add(databaseConfigView.getView());
    }

}
