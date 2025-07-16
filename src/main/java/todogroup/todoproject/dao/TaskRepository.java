package todogroup.todoproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todogroup.todoproject.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
