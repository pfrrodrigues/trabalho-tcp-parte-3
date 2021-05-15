package peer.review.ui.command;

import java.util.List;

import peer.review.business.PeerReviewService;
import peer.review.business.domain.Conference;
import peer.review.ui.TextManager;
import peer.review.ui.UIUtils;

public abstract class Command {
	protected final PeerReviewService service;

	protected Command(PeerReviewService service) {
		this.service = service;
	}
	
	public void printConferences(List<Conference> conferences) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("\n");
		sb.append(getTextManager().getText("conference")).append("\n");
		sb.append("----------\n");
		
		for (Conference  conference : conferences) {
			sb.append(conferences.indexOf(conference) +  " - "
					+  conference.getAcronym() + "\n");
		}
		System.out.println(sb);
	}
	
	public abstract void execute() throws Exception;
	
	protected TextManager getTextManager() {
		return UIUtils.INSTANCE.getTextManager();
	}

}
