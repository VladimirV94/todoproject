package todogroup.todoproject.dao;

import org.springframework.stereotype.Repository;
import todogroup.todoproject.entity.Task;
import todogroup.todoproject.entity.TaskStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryTaskDAO {
	private final List<Task> tasks = new ArrayList<>(Arrays.asList(
			Task.builder()
					.id(1)
					.name("name1")
					.description("description1")
					.status(TaskStatus.TODO)
					.deadline(LocalDate.now().plusDays(2))
					.build(),
			Task.builder()
					.id(2)
					.name("name2")
					.description("description2")
					.status(TaskStatus.DONE)
					.deadline(LocalDate.now().plusDays(4))
					.build(),
			Task.builder()
					.id(3)
					.name("name3")
					.description("description3")
					.status(TaskStatus.INPROGRESS)
					.deadline(LocalDate.now().plusDays(6))
					.build(),
			Task.builder()
					.id(4)
					.name("база")
					.description("базуз")
					.status(TaskStatus.TODO)
					.deadline(LocalDate.now().plusDays(1))
					.build()
		));

	public List<Task> findAllTasks() {
		return new ArrayList<>(tasks);
	}

	public Optional<Task> findTaskById(int id) {
		return tasks.stream()
			.filter(task -> task.getId() == id)
			.findFirst();
	}

	public Task updateTask(Task task) {
		if(tasks.stream()
			.anyMatch(streaming_task -> streaming_task.getId() == task.getId()))
		{
			tasks.set(task.getId(), task);
		}
		return task;
	}

	public Task saveTask(Task task) {
		tasks.add(task);
		return task;
	}

	public void deleteTaskById(int id) {
		var task_to_delete = tasks.stream()
			.filter(task -> task.getId() == id)
			.findFirst();
		if(task_to_delete.isPresent()) {
			tasks.remove(task_to_delete.get());
		}
	}
}