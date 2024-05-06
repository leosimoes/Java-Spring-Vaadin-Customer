package com.project.app.views;

import com.project.app.entities.CustomerEntity;
import com.project.app.repositories.CustomerRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Route("customers")
public class MainView extends VerticalLayout {

    private final CustomerRepository customerRepository;

    private ListDataProvider<CustomerEntity> listDataProvider;

    @Autowired
    public MainView(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;

        //this.setAlignItems(FlexComponent.Alignment.CENTER);
        this.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        this.setJustifyContentMode(JustifyContentMode.CENTER);
        this.setSizeFull();

        this.add(new H1("CRUD - Customer"));
        this.add(this.formCreateCustomer());
        this.add(new Hr());
        this.add(this.listCustomer());

    }

    private Collection<Component> formCreateCustomer(){
        Collection<Component> components = new ArrayList<>();

        FormLayout formCreateCustomer = new FormLayout();
        TextField firstName = new TextField("First Name:");
        TextField lastName = new TextField("Last Name:");

        formCreateCustomer.add(firstName, lastName);
        formCreateCustomer.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1)
        );
        formCreateCustomer.setWidth("40%");
        formCreateCustomer.setMaxWidth("400px");
        formCreateCustomer.getStyle().set("margin", "auto");

        Button buttomCreateCustomer = new Button("Create Customer");
        buttomCreateCustomer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttomCreateCustomer.addClickListener(e -> {
            this.createCustomer(firstName.getValue(), lastName.getValue());
            firstName.clear();
            lastName.clear();
            this.refreshGrid();
        });

        components.add(new H2("Create Customer"));
        components.add(formCreateCustomer);
        components.add(buttomCreateCustomer);

        return components;
    }

    private void createCustomer(String firstName, String lastName){
        CustomerEntity customer = CustomerEntity
                .builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
        this.customerRepository.save(customer);
    }

    private Collection<Component> listCustomer(){
        Collection<Component> components = new ArrayList<>();

        Grid<CustomerEntity> grid = new Grid<>(CustomerEntity.class, false);
        grid.addColumn(CustomerEntity::getFirstName).setHeader("First name");
        grid.addColumn(CustomerEntity::getLastName).setHeader("Last name");
        grid.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_COLUMN_BORDERS);

        grid.setWidth("60%");
        grid.getStyle().set("margin", "auto");
        grid.setAllRowsVisible(true);

        this.listDataProvider = new ListDataProvider<>(this.customerRepository.findAll());
        grid.setDataProvider(this.listDataProvider);

        components.add(new H2("Read All Customers"));
        components.add(grid);

        return components;
    }

    private void refreshGrid(){
        this.listDataProvider.getItems().clear();
        this.listDataProvider.getItems().addAll(this.customerRepository.findAll());
        this.listDataProvider.refreshAll();
    }

}
