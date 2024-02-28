package bcgov.jh.etk.jhetkcommon.model.task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ScheduledFuture;

import bcgov.jh.etk.jhetkcommon.util.DateUtil;

/**
 * The Class TaskDetails.
 * @author HLiang
 */
public class TaskDetails implements Comparable<TaskDetails> {
	
	/** The service. */
	private String service;
	
	/** The exec state obj. */
	private EnumTaskExecState execStateObj;
	
	/** The next run. */
	private LocalDateTime nextRun;
	
	/** The task ID. */
	private String taskID;
	
	/** The inputs. */
	private TaskInputs inputs;

	/** The next run in sec. */
	private String nextRunInSec;
	
	/** The spring task. */
	private ScheduledFuture<?> springTask;
	
	
	/**
	 * Gets the spring task.
	 *
	 * @return the spring task
	 */
	public ScheduledFuture<?> getSpringTask() {
		return springTask;
	}

	/**
	 * Sets the spring task.
	 *
	 * @param springTask the new spring task
	 */
	public void setSpringTask(ScheduledFuture<?> springTask) {
		this.springTask = springTask;
	}

	/**
	 * Gets the service.
	 *
	 * @return the service
	 */
	public String getService() {
		return service;
	}
	
	/**
	 * Sets the service.
	 *
	 * @param service the new service
	 */
	public void setService(String service) {
		this.service = service;
	}
	
	
	/**
	 * Gets the next run.
	 *
	 * @return the next run
	 */
	public LocalDateTime getNextRun() {
		return DateUtil.toPacificLDT(nextRun);
	}
	
	/**
	 * Gets the new run orig val.
	 *
	 * @return the new run orig val
	 */
	public LocalDateTime getNewRunOrigVal() {
		return nextRun;
	}
	
	/**
	 * Sets the next run.
	 *
	 * @param nextRun the new next run
	 */
	public void setNextRun(LocalDateTime nextRun) {
		this.nextRun = nextRun;
	}
	
	/**
	 * Gets the task ID.
	 *
	 * @return the task ID
	 */
	public String getTaskID() {
		return taskID;
	}
	
	/**
	 * Sets the task ID.
	 *
	 * @param taskID the new task ID
	 */
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
	
	/**
	 * Gets the inputs.
	 *
	 * @return the inputs
	 */
	public TaskInputs getInputs() {
		return inputs;
	}
	
	/**
	 * Sets the inputs.
	 *
	 * @param inputs the new inputs
	 */
	public void setInputs(TaskInputs inputs) {
		this.inputs = inputs;
	}

	
	/**
	 * Gets the exec state obj.
	 *
	 * @return the exec state obj
	 */
	public EnumTaskExecState getExecStateObj() {
		boolean isAfter = this.nextRun.isAfter(LocalDateTime.now());
		if (isAfter) {
			execStateObj = EnumTaskExecState.ACT;
		} else {
			execStateObj = EnumTaskExecState.EXP;
		}
		return execStateObj;
	}


	/**
	 * Gets the next run in sec.
	 *
	 * @return the next run in sec
	 */
	public String getNextRunInSec() {
		nextRunInSec = String.valueOf(Duration.between(LocalDateTime.now(), nextRun).toMillis()/1000);
		return nextRunInSec;
	}

	/**
	 * Sets the next run in sec.
	 *
	 * @param nextRunInSec the new next run in sec
	 */
	public void setNextRunInSec(String nextRunInSec) {
		this.nextRunInSec = nextRunInSec;
	}

	@Override
	public int compareTo(TaskDetails td2) {
		Long nextRunInSec1 = Long.parseLong(this.getNextRunInSec());
		Long nextRunInSec2 = Long.parseLong(td2.getNextRunInSec());
		if(nextRunInSec1 < nextRunInSec2) {
			return -1;
		} else if(nextRunInSec1 > nextRunInSec2) {
			return 1;
		} else {
			return this.getInputs().getServiceType().compareTo(td2.getInputs().getServiceType());
		}
	}

	@Override
	public String toString() {
		return "TaskDetails [service=" + service + ", execStateObj=" + execStateObj + ", taskID=" + taskID
				+ ", nextRunInSec=" + nextRunInSec + "]";
	}	
	
	
}