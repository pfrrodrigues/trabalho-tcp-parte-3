package peer.review.ui.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import peer.review.business.PeerReviewService;
import peer.review.business.domain.Article;
import peer.review.business.domain.Conference;
import peer.review.ui.UIUtils;


public class ReportCommand extends Command {

	List<Article> acceptedArticles;
	List<Article> rejectedArticles;
	
	public ReportCommand(PeerReviewService service) {
		super(service);
	}
	
	private boolean thereAreConferences(List<Conference> conferences) {
		return !(conferences.isEmpty());
	}
	
	private int chooseConferenceByIndex(List<Conference> listOfConferences) {
		int selectedConference;
		
		System.out.println();
		do {
			selectedConference = UIUtils.INSTANCE.readInteger("conference.select");
		} while (selectedConference < 0 || selectedConference > listOfConferences.size() - 1);
		return selectedConference; 
	}
	
	private void printArticlesReport(List<Article> acceptedArticles, List<Article> rejectedArticles) {
		StringBuffer acceptedArticlesList = new StringBuffer();
		StringBuffer rejectedArticlesList = new StringBuffer();
		
		acceptedArticlesList.append("\n");
		acceptedArticlesList.append(getTextManager().getText("header.articles.accepted")).append("\n");
		acceptedArticlesList.append("------------\n");
		
		for (Article article : acceptedArticles) {
			acceptedArticlesList.append(article.getTitle() + "\n");
		}
		
		rejectedArticlesList.append("\n");
		rejectedArticlesList.append(getTextManager().getText("header.articles.rejected")).append("\n");
		rejectedArticlesList.append("------------\n");
		
		for (Article article : rejectedArticles) {
			rejectedArticlesList.append(article.getTitle() + "\n");
		}
	
		System.out.println(acceptedArticlesList);
		System.out.println(rejectedArticlesList + "\n");
	}
	
	@Override
	public void execute() throws Exception {
		
		Map<String, Conference> conferences = this.service.getAllConferences();
		List<Conference> listOfConferences = new ArrayList<>(conferences.values());
		StringBuffer sb = new StringBuffer();
		
		printConferences(listOfConferences);
		
		if (thereAreConferences(listOfConferences)) {
			Conference selectedConference = listOfConferences.get(chooseConferenceByIndex(listOfConferences));
			
			if (selectedConference.isAllocated()) {
				try {
					acceptedArticles = selectedConference.getAcceptedArticles();
					rejectedArticles = selectedConference.getRejectedArticles();
					printArticlesReport(acceptedArticles, rejectedArticles);
				} catch (Exception e) {
					sb.append(getTextManager().getText("message.thereArePendingReviews"));
					System.out.println(sb);
				}
				
			} else {
				sb.append(getTextManager().getText("message.conference.notAllocated"));
				System.out.println(sb);
			}
			
		}
		
	}

}
