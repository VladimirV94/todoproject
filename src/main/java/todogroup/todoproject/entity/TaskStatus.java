package todogroup.todoproject.entity;


import lombok.Getter;

@Getter
public enum TaskStatus {
	
	TODO(0), INPROGRESS(1), DONE(2);

	private final int sortingWeight;

	TaskStatus(int sortingWeight) {
		this.sortingWeight = sortingWeight;
	}

}
