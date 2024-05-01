package com.project.app.views;

import com.project.app.entities.CustomerEntity;
import com.project.app.repositories.CustomerRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Route("/customers")
public class MainView extends VerticalLayout {

    private CustomerRepository customerRepository;

    private List<CustomerEntity> customerEntityList;

    private Grid<CustomerEntity> grid;

    @Autowired
    public MainView(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;

        this.setAlignItems(FlexComponent.Alignment.CENTER);

        this.add(new H1("CRUD - Customer"));
        this.add(new H2("Create Customer"));
        this.add(this.formCreateCustomer());

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
            //this.readAllCustomer();
            System.out.println("Botão CLICADO");
            firstName.clear();
            lastName.clear();
        });

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

}
