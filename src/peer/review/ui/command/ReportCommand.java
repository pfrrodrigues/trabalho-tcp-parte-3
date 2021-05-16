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
	
	private void printArticlesReport(List<Article> acceptedArticles, List<Article> rejectedArticles) {
		StringBuffer acceptedArticlesList = new StringBuffer();
		StringBuffer rejectedArticlesList = new StringBuffer();
		
		// falta ordernar as listas
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
	
		System.out.println(acceptedArticlesList + "\n");
		System.out.println(rejectedArticlesList + "\n");
	}
	
	private int chooseConferenceByIndex(List<Conference> listOfConferences) {
		
		int selectedConference;
		
		System.out.println();
		do {
			selectedConference = UIUtils.INSTANCE.readInteger("conference.select");
		} while (selectedConference < 0 || selectedConference > listOfConferences.size() - 1);
		
		return selectedConference; 
	}
	
	@Override
	public void execute() throws Exception {
		int selectedConference;
		Map<String, Conference> conferences = this.service.getAllConferences();
		List<Conference> listOfConferences = new ArrayList<>(conferences.values());
		
		printConferences(listOfConferences);
		
		if (!(conferences.isEmpty())) {
			selectedConference = chooseConferenceByIndex(listOfConferences);
			
			if (this.service.getConference(listOfConferences.get(selectedConference).getAcronym()).isAllocated()) {
				try {
					acceptedArticles = this.service.getConference(listOfConferences.get(selectedConference).getAcronym()).getAcceptedArticles();
					rejectedArticles = this.service.getConference(listOfConferences.get(selectedConference).getAcronym()).getRejectedArticles();
					printArticlesReport(acceptedArticles, rejectedArticles);
				} catch (Exception e) {
					System.out.println("Há revisões pendentes");
				}
				
			} else {
				System.out.println("Ainda não foi realizada a alocação de artigos para esta conferência.");
			}
			
		}
		
	}

}
