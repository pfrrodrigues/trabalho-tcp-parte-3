package peer.review.ui.command;

import java.util.List;

import peer.review.business.PeerReviewService;
import peer.review.business.domain.Article;
import peer.review.business.domain.Conference;
import peer.review.business.domain.Researcher;
import peer.review.business.exception.IllegalValueException;
import peer.review.ui.UIUtils;

public class AllocationCommand extends Command {
	
	public AllocationCommand(PeerReviewService service) {
		super(service);
	}
	
	public void execute() {
		List<Conference> unallocatedConferences = this.service.getUnallocatedConferences();
		this.printConferences(unallocatedConferences);
		
		Integer conferenceIndex = UIUtils.INSTANCE.readInteger("conference.select");
		Integer reviewerNumber = UIUtils.INSTANCE.readInteger("reviewer.number");
		if (reviewerNumber < 2 || reviewerNumber > 5) 
			throw new IllegalValueException(getTextManager().getText("message.reviewer.number.invalid"));
		
		try {
			Conference selectedConference = unallocatedConferences.get(conferenceIndex);
			
			System.out.println(getTextManager().getText("message.allocation.begin"));
			for (int i = 0; i < reviewerNumber; i++)  
				selectedConference.allocate();
			
			this.printAllocationList(selectedConference);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println(getTextManager().getText("message.IndexOutOfBounds"));
		}
	}
	
	private void printAllocationList(Conference conference) {
		StringBuffer sb = new StringBuffer();
		
		for (Article article : conference.getArticles()) {
			for (Researcher reviewer : article.getReviewers()) {
				sb.append(getTextManager().getText("article")).append(" id ");
				sb.append(article.getId()).append(" ");
				sb.append(getTextManager().getText("message.allocatedTo")).append(" ");
				sb.append(getTextManager().getText("reviewer")).append(" id ");
				sb.append(reviewer.getId()).append("\n");
			}
		}
		System.out.println(sb);
		System.out.println(getTextManager().getText("message.allocation.end"));
	}
}
