package com.UI;

import com.model.Question;
import com.model.QuestionRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@SpringUI(path = "question")
@Theme("valo")
public class QuestionUI extends UI {

    private final QuestionRepository repo;

    private final QuestionEditor editor;

    private final Grid grid;

    private final TextField filter;

    private final Button addNewBtn;

    @Autowired
    public QuestionUI(QuestionRepository repo, QuestionEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid();
        this.filter = new TextField();
        this.addNewBtn = new Button("New question", FontAwesome.PLUS);
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
        //grid.setWidth(850, Unit.PIXELS);
        grid.setSizeFull();
        grid.setColumns("id", "question_text", "answer", "a", "b", "c", "d", "lessonId");

        filter.setInputPrompt("Filter by answer");

        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        filter.addTextChangeListener(e -> listQuestions(e.getText()));

        // Connect selected Customer to editor or hide if none is selected
        grid.addSelectionListener(e -> {
            if (e.getSelected().isEmpty()) {
                editor.setVisible(false);
            }
            else {
                editor.editQuestion((Question) grid.getSelectedRow());
            }
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> editor.editQuestion(new Question("", 0L, "", "", "", "", 0L)));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listQuestions(filter.getValue());
        });

        // Initialize listing
        listQuestions(null);
    }

    // tag::listQuestions[]
    private void listQuestions(String text) {
        if (StringUtils.isEmpty(text)) {
            grid.setContainerDataSource(
                    new BeanItemContainer(Question.class, repo.findAll()));
        }
        else {
//            grid.setContainerDataSource(new BeanItemContainer(Question.class,
//                    repo.findByQuestion_text(text)));
        }
    }
    // end::listQuestions[]

}