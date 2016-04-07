package com.UI.Crud;

import com.model.Lesson;
import com.model.LessonRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@SpringUI(path = "lesson")
@Theme("valo")
public class LessonUI extends UI {

    @Autowired
    private final LessonRepository repo;

    private final LessonEditor editor;

    private final Grid grid;

    private final TextField filter;

    private final Button addNewBtn;

    @Autowired
    public LessonUI(LessonRepository repo, LessonEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid();
        this.filter = new TextField();
        this.addNewBtn = new Button("New lesson", FontAwesome.PLUS);
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
        grid.setColumns("id", "name", "description", "lessonvid", "moduleId");

        filter.setInputPrompt("Filter by name");

        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        filter.addTextChangeListener(e -> listLessons(e.getText()));

        // Connect selected Customer to editor or hide if none is selected
        grid.addSelectionListener(e -> {
            if (e.getSelected().isEmpty()) {
                editor.setVisible(false);
            }
            else {
                editor.editLesson((Lesson) grid.getSelectedRow());
            }
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> editor.editLesson(new Lesson("", "", 0L, "", 0L)));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listLessons(filter.getValue());
        });

        // Initialize listing
        listLessons(null);
    }

    // tag::listLessons[]
    private void listLessons(String text) {
        if (StringUtils.isEmpty(text)) {
            grid.setContainerDataSource(
                    new BeanItemContainer(Lesson.class, repo.findAll()));
        }
        else {
            grid.setContainerDataSource(new BeanItemContainer(Lesson.class,
                    repo.findByNameStartsWithIgnoreCase(text)));
        }
    }
    // end::listLessons[]

}