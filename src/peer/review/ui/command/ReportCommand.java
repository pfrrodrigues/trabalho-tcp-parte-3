package peer.review.ui.command;

import java.util.List;

import peer.review.business.domain.Article;
import peer.review.business.domain.Conference;
import peer.review.business.impl.PeerReviewServiceImpl;
import peer.review.data.Database;
import peer.review.ui.UIUtils;


public class ReportCommand extends Command {

	List<Article> acceptedArticles;
	List<Article> rejectedArticles;
	private final PeerReviewServiceImpl service; // Must be PeerReviewService
	
	protected ReportCommand(Database database, PeerReviewServiceImpl service) {
		super(database);
		this.service = service;
	}
	
	private void printArticles(List<Article> articles) {
		// TODO
	}
	
	private Integer chooseConferenceIndex() {
		//List<Conference> conferences = this.service.getAllConferences();
		System.out.println();
		Integer indexConference = null;
		/*
		do {
			indexConference = UIUtils.INSTANCE.readInteger("index.conference");
		} while (indexConference < 0 || indexConference > conferences.size() - 1);
		*/
		return indexConference; 
	}
	
	private void printConferences() {
		StringBuffer sb = new StringBuffer();
	/*	List<Conference> conferences = this.service.getAllConferences();
		
		sb.append(getTextManager().getText("index")).append("\t\t\t");
		sb.append(getTextManager().getText("tag")).append("\t\t\t\n");
		sb.append("--------------------------------------------------\n");
		
		for (Conference conference : conferences) {
			sb.append(conferences.indexOf(conference) + "\t\t");
			sb.append(conference.getAcronym() + "\n"); 
		}
		System.out.println(sb); */
	}
	
	private void separateArticles() {
		// Here, use the conference's reject and accept methods.
		// To use printArticles()
	}
	
	@Override
	public void execute() throws Exception {
		Integer indexOfConference;
		
		printConferences();
		
		if (!(this.service.getAllConferences().isEmpty())) {
			indexOfConference = chooseConferenceIndex();
			
			// if ((this.service.getAllConferences().get(indexOfConference)))
			// Get conference
			// If conference is not allocated or needed reviews then
			// -- System emits an alert else
			// -- SeparateArticles();
		}
		
	}

}
