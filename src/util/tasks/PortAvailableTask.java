package util.tasks;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

import task.ITask;


public class PortAvailableTask<T> implements ITask<T>{
	T port;
	Boolean completed = false;
	public PortAvailableTask(T portno) {
		port = portno;
	}
	@Override
	public boolean isComplete() {
		return completed;
	}
	//if the port is available, port number is returned else null is returned.
	@Override
	public T call() {
		int portnumber = (Integer)(port);
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		DatagramSocket datagramSocket = null;
		try {
			serverSocket = new ServerSocket(portnumber);
			datagramSocket = new DatagramSocket(portnumber);
			completed = true;
			return port;
		} catch (IOException e) {
		} finally {
			if (datagramSocket != null) {
				datagramSocket.close();
			}
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
				}
			}
		}
		completed = true;
		return null;
	}

}
