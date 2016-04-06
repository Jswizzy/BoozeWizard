package com.UI;

import com.model.Lesson;
import com.model.LessonRepository;
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
public class LessonEditor extends VerticalLayout {

    @Autowired
    private final LessonRepository repository;

    /**
     * The currently edited lesson
     */
    private Lesson lesson;

    /* Fields to edit properties in Customer entity */
    TextField name = new TextField("lesson name");
    TextField description = new TextField("description");
    TextField moduleId = new TextField("module id");
    TextField lessonvid = new TextField("lesson vid");

    /* Action buttons */
    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    CssLayout actions = new CssLayout(save, cancel, delete);

    @Autowired
    public LessonEditor(LessonRepository repository) {
        this.repository = repository;

        VerticalLayout inputs1 = new VerticalLayout(name, description);
        inputs1.setSpacing(true);
        //inputs1.setMargin(true);
        VerticalLayout inputs2 = new VerticalLayout(moduleId, lessonvid);
        inputs2.setSpacing(true);
        //inputs2.setMargin(true);
        HorizontalLayout inputs = new HorizontalLayout(inputs1, inputs2);
        inputs.setSpacing(true);

        addComponents(inputs, actions);

        // Configure and style components
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> repository.save(lesson));
        delete.addClickListener(e -> repository.delete(lesson));
        cancel.addClickListener(e -> editLesson(lesson));
        setVisible(false);
    }

    public interface ChangeHandler {

        void onChange();
    }

    public final void editLesson(Lesson c) {
        final boolean persisted = c.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            lesson = repository.findOne(c.getId());
        } else {
            lesson = c;
        }
        cancel.setVisible(persisted);

        // Bind lesson properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        BeanFieldGroup.bindFieldsUnbuffered(lesson, this);

        setVisible(true);

        // A hack to ensure the whole form is visible
        save.focus();
        // Select all text in firstName field automatically
        name.selectAll();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }

}