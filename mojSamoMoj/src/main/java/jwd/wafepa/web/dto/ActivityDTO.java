package jwd.wafepa.web.dto;

import jwd.wafepa.model.Activity;

public class ActivityDTO {

	private Long id;
	private String name;
	private boolean hidden;
	private Integer logCount;

	public ActivityDTO() {
		super();
	}

	public ActivityDTO(Activity activity) {
		this.id = activity.getId();
		this.name = activity.getName();
		this.hidden = activity.isHidden();
		if (activity.getLogs() != null) {
			this.logCount = activity.getLogs().size();
		}
	}
	
	public ActivityDTO(Long id, String name, boolean hidden) {
		super();
		this.id = id;
		this.name = name;
		this.hidden = hidden;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public Integer getLogCount() {
		return logCount;
	}

	public void setLogCount(Integer logCount) {
		this.logCount = logCount;
	}

}
