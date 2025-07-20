package todogroup.todoproject.service.sorting;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import todogroup.todoproject.entity.Task;

import java.util.Comparator;
import java.util.List;

@Component
public class SortingService {

	private SortingService() {}

	public static List<Task> sortTasksByDeadline(List<Task> tasks, SortDirection sortDirection) {
		switch (sortDirection) {
			case DIRECT -> tasks.sort(Comparator.comparing(Task::getDeadline));
			case REVERSE -> tasks.sort((o1, o2) -> o1.getDeadline().compareTo(o2.getDeadline()) * -1);
			default -> throw new IllegalStateException("Unexpected value: " + sortDirection);
		}
		return tasks;
	}

	public static List<Task> sortTasksByStatus(List<Task> tasks, SortDirection sortDirection) {
		switch (sortDirection) {
			case DIRECT -> tasks.sort(Comparator.comparingInt(o -> o.getStatus().getSortingWeight()));
			case REVERSE -> tasks.sort((o1, o2) -> Integer.compare(
					o1.getStatus().getSortingWeight(),
					o2.getStatus().getSortingWeight()) * -1);
			default -> throw new IllegalStateException("Unexpected value: " + sortDirection);
		}
		return tasks;
	}
}
