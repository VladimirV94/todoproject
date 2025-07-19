package todogroup.todoproject.service.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import todogroup.todoproject.entity.Task;
import todogroup.todoproject.entity.TaskStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortingServiceTest {

	SortingService sortingService;

	@Test
	void sortTasksByDeadline_shouldSortDirectAndReverse() {
		final Task task1 = Mockito.mock(Task.class);
		final Task task2 = Mockito.mock(Task.class);
		Mockito.when(task1.getDeadline()).thenReturn(LocalDate.now().plusDays(1));
		Mockito.when(task2.getDeadline()).thenReturn(LocalDate.now().plusDays(2));

		List<Task> directResult = sortingService.sortTasksByDeadline(Arrays.asList(task1, task2), SortDirection.DIRECT);
		List<Task> reverseResult = sortingService.sortTasksByDeadline(Arrays.asList(task1, task2), SortDirection.REVERSE);

		assertNotNull(directResult);
		assertNotNull(reverseResult);
		assertEquals(directResult.get(0), task1);
		assertEquals(directResult.get(1), task2);
		assertEquals(reverseResult.get(0), task2);
		assertEquals(reverseResult.get(1), task1);
	}

	@Test
	void sortTasksByStatus_shouldSortDirectAndReverse() {
		final Task task1 = Mockito.mock(Task.class);
		final Task task2 = Mockito.mock(Task.class);
		final Task task3 = Mockito.mock(Task.class);
		Mockito.when(task1.getStatus()).thenReturn(TaskStatus.TODO);
		Mockito.when(task2.getStatus()).thenReturn(TaskStatus.INPROGRESS);
		Mockito.when(task3.getStatus()).thenReturn(TaskStatus.DONE);

		List<Task> directResult = sortingService.sortTasksByStatus(Arrays.asList(task1, task2, task3), SortDirection.DIRECT);
		List<Task> reverseResult = sortingService.sortTasksByStatus(Arrays.asList(task1, task2, task3), SortDirection.REVERSE);

		assertNotNull(directResult);
		assertNotNull(reverseResult);
		assertEquals(directResult.get(0), task1);
		assertEquals(directResult.get(1), task2);
		assertEquals(directResult.get(2), task3);
		assertEquals(reverseResult.get(0), task3);
		assertEquals(reverseResult.get(1), task2);
		assertEquals(reverseResult.get(2), task1);
	}
}