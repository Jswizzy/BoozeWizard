package com.UI.Crud;

import com.model.Question;
import com.model.QuestionRepository;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A simple example to introduce building forms. As your real application is
 * probably much more complicated than this example, you could re-use this form in
 * multiple places. This example component is only used in VaadinUI.
 * <p>
 * In a real world application you'll most likely using a common super class for all your
 * forms - less code, better UX. See e.g. AbstractForm in Virin
 * (https://vaadin.com/addon/viritin).
 */
@SpringComponent
@UIScope
    public class QuestionEditor extends VerticalLayout {

    @Autowired
    private final QuestionRepository repository;

    /**
     * The currently edited question
     */
    private Question question;

    /* Fields to edit properties in Customer entity */
    TextField question_text = new TextField("question");
    TextField answer = new TextField("answer");
    TextField a = new TextField("choice 1");
    TextField b = new TextField("choice 2");
    TextField c = new TextField("choice 3");
    TextField d = new TextField("choice 4");
    TextField lessonId = new TextField("lesson id");

    /* Action buttons */
    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    CssLayout actions = new CssLayout(save, cancel, delete);

    @Autowired
    public QuestionEditor(QuestionRepository repository) {
        this.repository = repository;

        HorizontalLayout inputs1 = new HorizontalLayout(answer, question_text);
        HorizontalLayout inputs2 = new HorizontalLayout(a, b, c, d);
        inputs1.setWidth(425, Unit.PIXELS);
        inputs2.setSpacing(true);
        inputs2.setWidth(850, Unit.PIXELS);
        inputs2.setSpacing(true);

        addComponents(inputs1, inputs2, lessonId, actions);

        // Configure and style components
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> repository.save(question));
        delete.addClickListener(e -> repository.delete(question));
        cancel.addClickListener(e -> editQuestion(question));
        setVisible(false);
    }

    public interface ChangeHandler {

        void onChange();
    }

    public final void editQuestion(Question c) {
        final boolean persisted = c.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            question = repository.findOne(c.getId());
        } else {
            question = c;
        }
        cancel.setVisible(persisted);

        // Bind question properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        BeanFieldGroup.bindFieldsUnbuffered(question, this);

        setVisible(true);

        // A hack to ensure the whole form is visible
        save.focus();
        // Select all text in firstName field automatically
        question_text.selectAll();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }

}