package peer.review.ui.command;

import java.util.List;
import java.util.Map;

import peer.review.business.PeerReviewService;
import peer.review.business.domain.Article;
import peer.review.business.domain.Conference;
import peer.review.business.impl.PeerReviewServiceImpl;
import peer.review.data.Database;
import peer.review.ui.UIUtils;


public class ReportCommand extends Command {

	List<Article> acceptedArticles;
	List<Article> rejectedArticles;
	private final PeerReviewService service; // Must be PeerReviewService
	
	public ReportCommand(Database database, PeerReviewService service) {
		super(database);
		this.service = service;
	}
	
	private void printArticles(List<Article> articles, int order) {
		if (order == 0) {
			
		} else if (order == 1) {
			
		} else {
			// DO NOTHING
		}
	}
	
	private String chooseConferenceByAcronym() {
		Map<String, Conference> conferences = this.service.getAllConferences();
		System.out.println();
		String selectedConference = null;
		
		do {
			selectedConference = UIUtils.INSTANCE.readString("acronym.conference");
			selectedConference = selectedConference.toUpperCase();
		} while (!(conferences.containsKey(selectedConference)));
		
		System.out.println("Conferência escolhida: " + selectedConference);
		return selectedConference; 
	}
	
	private void printConferences() {
		StringBuffer sb = new StringBuffer();
		Map<String, Conference> conferences = this.service.getAllConferences();
		
		sb.append("\n");
		sb.append(getTextManager().getText("conference")).append("\n");
		sb.append("----------\n");
		
		for (String acronym : conferences.keySet()) {
			sb.append(acronym + "\n");
		}
		System.out.println(sb);
	}
	
	@Override
	public void execute() throws Exception {
		String selectedConference;
		
		printConferences();
		
		if (!(this.service.getAllConferences().isEmpty())) {
			selectedConference = chooseConferenceByAcronym();
			
			// Nesse passo abaixo falta verificar se há reviews pendentes
		/*	 if (this.service.getConference(selectedConference).isAllocated()) {
				
				try {
					acceptedArticles = this.service.getConference(selectedConference).getAcceptedArticles();
					rejectedArticles = this.service.getConference(selectedConference).getRejectedArticles();
				} catch (Exception e) {
					System.out.println("Há revisões pendentes");
				} 
				
				
				printArticles(acceptedArticles, 0);
				printArticles(rejectedArticles, 1);
			} */
			
		}
		
	}

}
