package com.company.jmixpm.screen.project;

import com.company.jmixpm.entity.Task;
import io.jmix.core.DataManager;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.Table;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@UiController("Project.edit")
@UiDescriptor("project-edit.xml")
@EditedEntityContainer("projectDc")
@PrimaryEditorScreen(Project.class)
public class ProjectEdit extends StandardEditor<Project> {
    @Autowired
    private Table<Task> tasksTable;

    @Autowired
    private ScreenBuilders screenBuilders;

    @Autowired
    private DataManager dataManager;


//    @Install(to = "tasksTable.create", subject = "initializer")
//    private void tasksTableCreateInitializer(Task task) {
//        task.setProject(getEditedEntity());

    //    }
//    @Subscribe
//    public void onInit(final InitEvent event) {
//        tasksTable.setEnabled(false);
//    }


    //    @Subscribe(target = Target.DATA_CONTEXT)
//    public void onPostCommit(final DataContext.PostCommitEvent event) {
//        tasksTable.setEnabled(true);
//    }
    @Subscribe("tasksTable.create")
    public void onTasksTableCreate(final Action.ActionPerformedEvent event) {
        Task newTask = dataManager.create(Task.class);
        newTask.setProject(getEditedEntity());

        screenBuilders.editor(tasksTable)
                .newEntity(newTask)
                .withParentDataContext(getScreenData().getDataContext())
                .show();
    }

    @Subscribe("tasksTable.edit")
    public void onTasksTableEdit(final Action.ActionPerformedEvent event) {
        Task selected = tasksTable.getSingleSelected();
        if (selected == null) {
            return;
        }

        screenBuilders.editor(tasksTable)
                .editEntity(selected)
                .withParentDataContext(getScreenData().getDataContext())
                .show();
    }
}