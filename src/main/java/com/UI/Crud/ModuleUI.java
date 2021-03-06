package com.UI.Crud;

import com.model.Module;
import com.model.ModuleRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@SpringUI(path = "module")
@Theme("valo")
public class ModuleUI extends UI {

    @Autowired
    private final ModuleRepository repo;

    @Autowired
    private final ModuleEditor editor;

    private final Grid grid;

    private final TextField filter;

    private final Button addNewBtn;

    @Autowired
    public ModuleUI(ModuleRepository repo, ModuleEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid();
        this.filter = new TextField();
        this.addNewBtn = new Button("New module", FontAwesome.PLUS);
    }

    @Override
    protected void init(VaadinRequest request) {
        // build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor);
        setContent(mainLayout);

        // Configure layouts and components
        actions.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);

        //grid.setHeight(300, Unit.PIXELS);
        grid.setSizeFull();
        grid.setColumns("id", "name", "description");

        filter.setInputPrompt("Filter by name");

        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        filter.addTextChangeListener(e -> listModules(e.getText()));

        // Connect selected Customer to editor or hide if none is selected
        grid.addSelectionListener(e -> {
            if (e.getSelected().isEmpty()) {
                editor.setVisible(false);
            }
            else {
                editor.editModule((Module) grid.getSelectedRow());
            }
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> editor.editModule(new Module("", "")));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listModules(filter.getValue());
        });

        // Initialize listing
        listModules(null);
    }

    // tag::listModules[]
    private void listModules(String text) {
        if (StringUtils.isEmpty(text)) {
            grid.setContainerDataSource(
                    new BeanItemContainer(Module.class, repo.findAll()));
        }
        else {
            grid.setContainerDataSource(new BeanItemContainer(Module.class,
                    repo.findByNameStartsWithIgnoreCase(text)));
        }
    }
    // end::listModules[]

}