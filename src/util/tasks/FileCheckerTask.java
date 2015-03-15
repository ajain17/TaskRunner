package util.tasks;
import java.io.File;
import task.ITask;

/**
 * 
 * Checks if the passed in file name exists
 *
 * @param <T>
 */
public class FileCheckerTask<T> implements ITask<T>{
	T fileName;
	Boolean completed = false;
	public FileCheckerTask(T fileName) {
		this.fileName = fileName;

	}

	@Override
	public boolean isComplete() {
		return completed;
	}
	//if the file exists, file name is returned else null is returned.
	@Override
	public T call() {
		File f = new File(fileName.toString());
		if(f.exists() && !f.isDirectory())
		{
			completed = true;
			return fileName;
		}
		completed = true;
		return null;
	}

}
