package client;
import java.util.concurrent.Future;
import task.ITask;
import task.TaskRunner;
import util.tasks.FileCheckerTask;
import util.tasks.PortAvailableTask;

/**
 * Main program for creating and submitting tasks to the Task Runner.
 *author Ayushi Jain 
 *
 */
public class TaskSubmitter {

	/**
	 * Creates two tasks and submits them to the TaskRunner.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception { 
		final int times = 5;
		final long sleepMillis = 1000;
		//checking for a random port number
		int portNo = 135;
		// creating a test file here called test.text
		String fileName = "test.text";
		/* TODO: Instantiate and submit an instance of the FileCheckerTask to the TaskRunner */
		TaskRunner taskRunner = new TaskRunner();
		ITask<String> fileCheckerTask = new FileCheckerTask<String>(fileName);
		ITask<Integer> portAvailableTask = new PortAvailableTask<Integer>(portNo);
		Future<String> futureFileChecker = taskRunner.runTaskAsync(fileCheckerTask, times, sleepMillis);
		Future<Integer> futurePortAvailable= taskRunner.runTaskAsync(portAvailableTask, times, sleepMillis);
		//isComplete is not really required because future.get() is a blocking call until task completes.
		//if the future returned contains null anywhere in the string, it means file does not exist.
		if(!futureFileChecker.get().toString().contains("null"))
			System.out.println("File exists");
		else
			System.out.println("File does not exist");
		//if the future returned contains null anywhere in the result, it means port is not available.
		if(!String.valueOf(futurePortAvailable.get()).contains("null"))
			System.out.println("Port is available");
		else
			System.out.println("Port is not available");
	}
}
