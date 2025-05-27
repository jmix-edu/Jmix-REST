package com.company.jmixpm.mvc;

import com.company.jmixpm.entity.Project;
import io.jmix.core.DataManager;
import io.jmix.core.EntitySerialization;
import io.jmix.core.EntitySerializationOption;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mvc/projects")
public class ProjectsController {

    private final DataManager dataManager;
    private final EntitySerialization entitySerialization;

    public ProjectsController(DataManager dataManager, EntitySerialization entitySerialization) {
        this.dataManager = dataManager;
        this.entitySerialization = entitySerialization;
    }

    @GetMapping("/newProjects")
    public String getProjectsInNewStatus() {
        List<Project> projects = dataManager.load(Project.class)
                .query("select p from Project p where p.status = 10")
                .fetchPlan("project-with-links-fetch-plan")
                .list();

        return entitySerialization.toJson(projects, null, EntitySerializationOption.DO_NOT_SERIALIZE_DENIED_PROPERTY, EntitySerializationOption.SERIALIZE_NULLS);

    }
}
