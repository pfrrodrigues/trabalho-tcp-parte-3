package peer.review.ui.command;

import java.util.List;

import peer.review.business.domain.Conference;
import peer.review.data.Database;
import peer.review.ui.TextManager;
import peer.review.ui.UIUtils;

public abstract class Command {
	
	protected Command(Database database) {
		// TODO
	}
	
	public void printConferences(List<Conference> conferences) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("\n");
		sb.append(getTextManager().getText("conference")).append("\n");
		sb.append("----------\n");
		
		for (Conference  conference : conferences) {
			sb.append(conference.getAcronym() + "\n");
		}
		System.out.println(sb);
	}
	
	public abstract void execute() throws Exception;
	
	protected TextManager getTextManager() {
		return UIUtils.INSTANCE.getTextManager();
	}

}
