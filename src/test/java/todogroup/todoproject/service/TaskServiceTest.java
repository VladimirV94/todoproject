package todogroup.todoproject.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import todogroup.todoproject.controller.TaskController;
import todogroup.todoproject.dao.TaskRepository;
import todogroup.todoproject.dto.TaskMapper;
import todogroup.todoproject.dto.TaskResponseDTO;
import todogroup.todoproject.entity.Task;
import todogroup.todoproject.entity.TaskStatus;
import todogroup.todoproject.service.sorting.SortDirection;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

	private final static int ID = 1;

	@Mock
	private TaskRepository taskRepository;

	@InjectMocks
	private TaskService taskService;

	@Test
	void findAllTasks() {
		final Task task = Mockito.mock(Task.class);
		final TaskResponseDTO responseTaskDTO = Mockito.mock(TaskResponseDTO.class);
		Mockito.when(taskRepository.findAll()).thenReturn(List.of(task));

		List<TaskResponseDTO> result = taskService.findAllTasks();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(responseTaskDTO.getName(), result.get(0).getName());
		Mockito.verify(taskRepository).findAll();
	}

	@Test
	void findTaskById() {
		final Task task = Mockito.mock(Task.class);
		final TaskResponseDTO responseTaskDTO = Mockito.mock(TaskResponseDTO.class);
		Mockito.when(taskRepository.findById(ID)).thenReturn(Optional.of(task));

		Optional<TaskResponseDTO> result = taskService.findTaskById(ID);

		assertNotNull(result);
		assertEquals(true, result.isPresent());
		assertEquals(responseTaskDTO.getName(), result.get().getName());
		Mockito.verify(taskRepository).findById(ID);
	}

	@Test
	void updateTask() {
		final Task task = Mockito.mock(Task.class);
		final TaskResponseDTO responseTaskDTO = Mockito.mock(TaskResponseDTO.class);
		Mockito.when(taskRepository.save(task)).thenReturn(task);

		Optional<TaskResponseDTO> result = taskService.updateTask(task);

		assertNotNull(result);
		assertEquals(true, result.isPresent());
		assertEquals(responseTaskDTO.getName(), result.get().getName());
		Mockito.verify(taskRepository).save(task);
	}

	@Test
	void saveTask() {
		final Task task = Mockito.mock(Task.class);
		final TaskResponseDTO responseTaskDTO = Mockito.mock(TaskResponseDTO.class);
		Mockito.when(taskRepository.save(task)).thenReturn(task);

		Optional<TaskResponseDTO> result = taskService.updateTask(task);

		assertNotNull(result);
		assertEquals(true, result.isPresent());
		assertEquals(responseTaskDTO.getName(), result.get().getName());
		Mockito.verify(taskRepository).save(task);
	}

	@Test
	void deleteTaskById() {
		taskService.deleteTaskById(ID);

		Mockito.verify(taskRepository).deleteById(ID);
	}

	@Test
	void findTasksByStatus() {
		final Task task = Mockito.mock(Task.class);
		final TaskResponseDTO responseTaskDTO = Mockito.mock(TaskResponseDTO.class);
		Mockito.when(task.getStatus()).thenReturn(TaskStatus.TODO);
		Mockito.when(taskRepository.findAll()).thenReturn(List.of(task));

		List<TaskResponseDTO> result = taskService.findTasksByStatus(TaskStatus.TODO);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(responseTaskDTO.getName(), result.get(0).getName());
		Mockito.verify(taskRepository).findAll();
	}

	@Test
	void sortAllTasksByStatus_shouldSortDirect() {
		final Task task1 = Mockito.mock(Task.class);
		final Task task2 = Mockito.mock(Task.class);
		final Task task3 = Mockito.mock(Task.class);
		Mockito.when(task1.getStatus()).thenReturn(TaskStatus.TODO);
		Mockito.when(task2.getStatus()).thenReturn(TaskStatus.INPROGRESS);
		Mockito.when(task3.getStatus()).thenReturn(TaskStatus.DONE);
		Mockito.when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2, task3));
		final TaskResponseDTO responseTaskDTO1 = Mockito.mock(TaskResponseDTO.class);
		final TaskResponseDTO responseTaskDTO2 = Mockito.mock(TaskResponseDTO.class);
		final TaskResponseDTO responseTaskDTO3 = Mockito.mock(TaskResponseDTO.class);

		List<TaskResponseDTO> directResult = taskService.sortAllTasksByStatus(SortDirection.DIRECT);

		assertNotNull(directResult);
		assertEquals(3, directResult.size());
		assertEquals(responseTaskDTO1.getName(), directResult.get(0).getName());
		assertEquals(responseTaskDTO2.getName(), directResult.get(1).getName());
		assertEquals(responseTaskDTO3.getName(), directResult.get(2).getName());
		Mockito.verify(taskRepository).findAll();
	}

	@Test
	void sortAllTasksByStatus_shouldSortReverse() {
		final Task task1 = Mockito.mock(Task.class);
		final Task task2 = Mockito.mock(Task.class);
		final Task task3 = Mockito.mock(Task.class);
		Mockito.when(task1.getStatus()).thenReturn(TaskStatus.TODO);
		Mockito.when(task2.getStatus()).thenReturn(TaskStatus.INPROGRESS);
		Mockito.when(task3.getStatus()).thenReturn(TaskStatus.DONE);
		Mockito.when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2, task3));
		final TaskResponseDTO responseTaskDTO1 = Mockito.mock(TaskResponseDTO.class);
		final TaskResponseDTO responseTaskDTO2 = Mockito.mock(TaskResponseDTO.class);
		final TaskResponseDTO responseTaskDTO3 = Mockito.mock(TaskResponseDTO.class);

		List<TaskResponseDTO> reverseResult = taskService.sortAllTasksByStatus(SortDirection.REVERSE);

		assertNotNull(reverseResult);
		assertEquals(3, reverseResult.size());
		assertEquals(responseTaskDTO1.getName(), reverseResult.get(2).getName());
		assertEquals(responseTaskDTO2.getName(), reverseResult.get(1).getName());
		assertEquals(responseTaskDTO3.getName(), reverseResult.get(0).getName());
		Mockito.verify(taskRepository).findAll();
	}

	@Test
	void sortAllTasksByDeadline_shouldSortDirect() {
		final Task task1 = Mockito.mock(Task.class);
		final Task task2 = Mockito.mock(Task.class);
		Mockito.when(task1.getDeadline()).thenReturn(LocalDate.now().plusDays(1));
		Mockito.when(task2.getDeadline()).thenReturn(LocalDate.now().plusDays(2));
		Mockito.when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));
		final TaskResponseDTO responseTaskDTO1 = Mockito.mock(TaskResponseDTO.class);
		final TaskResponseDTO responseTaskDTO2 = Mockito.mock(TaskResponseDTO.class);

		List<TaskResponseDTO> directResult = taskService.sortAllTasksByDeadline(SortDirection.DIRECT);

		assertNotNull(directResult);
		assertEquals(2, directResult.size());
		assertEquals(responseTaskDTO1.getName(), directResult.get(0).getName());
		assertEquals(responseTaskDTO2.getName(), directResult.get(1).getName());
		Mockito.verify(taskRepository).findAll();
	}

	@Test
	void sortAllTasksByDeadline_shouldSortReverse() {
		final Task task1 = Mockito.mock(Task.class);
		final Task task2 = Mockito.mock(Task.class);
		Mockito.when(task1.getDeadline()).thenReturn(LocalDate.now().plusDays(1));
		Mockito.when(task2.getDeadline()).thenReturn(LocalDate.now().plusDays(2));
		Mockito.when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));
		final TaskResponseDTO responseTaskDTO1 = Mockito.mock(TaskResponseDTO.class);
		final TaskResponseDTO responseTaskDTO2 = Mockito.mock(TaskResponseDTO.class);

		List<TaskResponseDTO> reverseResult = taskService.sortAllTasksByDeadline(SortDirection.REVERSE);

		assertNotNull(reverseResult);
		assertEquals(2, reverseResult.size());
		assertEquals(responseTaskDTO1.getName(), reverseResult.get(1).getName());
		assertEquals(responseTaskDTO2.getName(), reverseResult.get(0).getName());
		Mockito.verify(taskRepository).findAll();
	}
}