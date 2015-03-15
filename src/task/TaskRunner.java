package task;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Runs a submitted task <code>times</code> number of times
 * and supports a sleep interval of <code>sleepMillis</code> between
 * each run and returns a Future result.
 * 
 * This method returns immediately with a Future object 
 * which can be used to obtain the result of the task 
 * when the task completes. 
 * 
 * @param task
 * @param times
 * @param sleepMillis
 */

public class TaskRunner {
	public <V> Future<V> runTaskAsync(final ITask<V> task, final int times, final long sleepMillis) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future future = executorService.submit(new Callable<String>(){
			public String call() throws Exception {
				String result="";
				for(int i=0;i<times;i++)
				{
					//appending call result in every run.
					result += task.call();
					try {
						Thread.sleep(sleepMillis);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				return result;
			}
		});
		return future;
	}
}


