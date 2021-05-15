package peer.review.ui.command;

import java.util.Map;

import peer.review.business.PeerReviewService;
import peer.review.business.domain.Article;
import peer.review.business.domain.Researcher;
import peer.review.data.Database;
import peer.review.ui.UIUtils;

public class ScoreAttributionCommand extends Command {
	private final PeerReviewService service;
	
	public ScoreAttributionCommand(Database database, PeerReviewService service) {
		super(database);
		this.service = service;
	}
	
	private void printArticles(Map<Integer, Article> articles) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("\n");
		sb.append(getTextManager().getText("id")).append("\t");
		sb.append(getTextManager().getText("article")).append("\n");
		sb.append("--------------------------------------\n");
		
		for (Integer id : articles.keySet()) {
			sb.append(id + "\t");
			sb.append(articles.get(id).getTitle() + "\n");
		}
		System.out.println(sb);
	}
	
	private int chooseArticleById(Map<Integer, Article> articles) {
		int selectedArticle = 0;
		
		System.out.println();
		do {
			selectedArticle = UIUtils.INSTANCE.readInteger("message.choose.article");
		} while (!(articles.containsKey(selectedArticle)));
		
		return selectedArticle; 
	}
	
	private void printReviewers(Map<Integer, Article> articles, int selectedArticleId) {
		Map<Researcher, Integer> reviews = articles.get(selectedArticleId).getReviews();
		StringBuffer sb = new StringBuffer();
		
		sb.append("\n");
		sb.append("Artigo: " + articles.get(selectedArticleId).getTitle() +"\n");
		sb.append(getTextManager().getText("id")).append("\t");
		sb.append(getTextManager().getText("reviewers")).append("\n");
		sb.append("--------------------------------------\n");
		
		for (Researcher researcher : reviews.keySet()) {
			sb.append(researcher.getId() + "\t");
			sb.append(researcher.getName() + "\n");
		}
		System.out.println(sb);
	}
	
	private int chooseReviewerById(Map<Researcher, Integer> researchers) {
		int selectedReviewerId = 0;
		boolean PASS = false;
		
		System.out.println();
		do {
			selectedReviewerId = UIUtils.INSTANCE.readInteger("message.choose.reviewer");
			
			for (Researcher researcher: researchers.keySet()) {
				if (researcher.getId() == selectedReviewerId) {
					PASS = true;
				}
			}
		} while (!PASS);
		
		return selectedReviewerId; 
	}
	
	private Integer scoreAttribution() {
		int score;
		do {
			score = UIUtils.INSTANCE.readInteger("message.choose.score");
			
		} while (score < -3 || score > 3);	
		return score;
	}
	
	@Override
	public void execute() throws Exception {
		Map<Integer, Article> articles = this.service.getAllArticles();
		int selectedArticleId;
		int score;
		int selectedReviewerId;
		Researcher selectedReviewer = null;
		
		if (!(articles.isEmpty())) {
			printArticles(articles);
			
			selectedArticleId = chooseArticleById(articles);
			
			printReviewers(articles, selectedArticleId);
			
			selectedReviewerId = chooseReviewerById(articles.get(selectedArticleId).getReviews());
			
			score = scoreAttribution();
			
			for (Researcher researcher: articles.get(selectedArticleId).getReviews().keySet()) {
				if (researcher.getId() == selectedReviewerId) {
					selectedReviewer = researcher;
				}
			}
			
			articles.get(selectedArticleId).updateReview(selectedReviewer, score);
			System.out.println("Revisão realizada com sucesso.");
		}
		
	}
	
}
