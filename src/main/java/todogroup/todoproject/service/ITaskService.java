package todogroup.todoproject.service;

import todogroup.todoproject.entity.Task;
import todogroup.todoproject.entity.TaskStatus;
import todogroup.todoproject.entity.dto.TaskContainerDTO;
import todogroup.todoproject.service.sorting.SortDirection;

import java.util.Collection;

public interface ITaskService {

	TaskContainerDTO findAllTasks();

	TaskContainerDTO findTaskById(int id);

	TaskContainerDTO updateTask(Task task);

	TaskContainerDTO saveTask(Task task);

	void deleteTaskById(int id);

	TaskContainerDTO findTasksByStatus(TaskStatus status);

	TaskContainerDTO sortTasksByStatus(Collection<Integer> tasksId, SortDirection SortDirection);

	TaskContainerDTO sortTasksByDeadline(Collection<Integer> tasksId, SortDirection SortDirection);

	TaskContainerDTO sortAllTasksByStatus(SortDirection sortDirection);

	TaskContainerDTO sortAllTasksByDeadline(SortDirection sortDirection);
}
