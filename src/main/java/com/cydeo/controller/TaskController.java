package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;
    private final ProjectService projectService;

    public TaskController(TaskService taskService, UserService userService, ProjectService projectService) {
        this.taskService = taskService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/create")
    public String createTask(Model model){

        model.addAttribute("task", new TaskDTO());
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());

        return "/task/create";
    }

    @PostMapping("/create")
    public String insertTask(TaskDTO task){

        taskService.save(task);

        return "redirect:/task/create";
    }

    @GetMapping("/delete/{id}")
    public String removeTask(TaskDTO task){

        taskService.deleteById(task.getId());

        return "redirect:/task/create";
    }

    @GetMapping("/update/{id}")
    public String editTask(@PathVariable Long id, Model model){

        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());

        return "task/update";
    }

//    @PostMapping("/update/{id}")
//    public String updateTask(@PathVariable("id") Long id, TaskDTO task){
//
//        task.setId(id);
//        taskService.update(task);
//
//        return "redirect:/task/create";
//    }

    @PostMapping("/update/{id}") //We need to put the exactly same name of the field to make it easier to code
    public String updateTask(TaskDTO task){
        taskService.update(task);
        return "redirect:/task/create";
    }
}
