package serveur;

import java.util.TimerTask;

public class FinService extends TimerTask{
private Service service;
	
	public FinService(Service service) {
		this.service = service;
	}

	public void run() {
		this.service.finDeService();
	}
}
