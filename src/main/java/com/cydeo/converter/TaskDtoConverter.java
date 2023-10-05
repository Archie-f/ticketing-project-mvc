package com.cydeo.converter;

import com.cydeo.dto.TaskDTO;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class TaskDtoConverter implements Converter<String, TaskDTO> {

    TaskService taskService;

    public TaskDtoConverter(TaskService taskService) {

        this.taskService = taskService;
    }

    @Override
    public TaskDTO convert(String source) {

        return taskService.findById(Long.parseLong(source));
    }
}
