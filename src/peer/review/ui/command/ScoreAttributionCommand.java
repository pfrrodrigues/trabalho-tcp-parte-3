package peer.review.ui.command;

import java.util.List;
import java.util.Map;

import peer.review.business.PeerReviewService;
import peer.review.business.domain.Article;
import peer.review.business.domain.Researcher;
import peer.review.ui.UIUtils;

public class ScoreAttributionCommand extends Command {
	
	public ScoreAttributionCommand(PeerReviewService service) {
		super(service);
	}
	
	private boolean thereAreArticles(Map<Integer, Article> articles) {
		return !(articles.isEmpty());
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
		int selectedArticleId;
		
		do {
			selectedArticleId = UIUtils.INSTANCE.readInteger("message.choose.article");
		} while (!(articles.containsKey(selectedArticleId)));
		
		return selectedArticleId; 
	}
	
	private boolean thereAreReviewersAllocated(Article article) {
		return article.getConference().isAllocated();
	}
	
	private void printReviewersOfArticle(Article article) {
		List<Researcher> reviewers = article.getReviewers();
		StringBuffer sb = new StringBuffer();
		
		sb.append("\n").append(getTextManager().getText("article")).append(": ")
		.append(article.getTitle()).append("\n");
		sb.append(getTextManager().getText("id")).append("\t");
		sb.append(getTextManager().getText("reviewer")).append("\n");
		sb.append("---------------------------------\n");
			
		for (Researcher reviewer : reviewers) {
			sb.append(reviewer.getId() + "\t");
			sb.append(reviewer.getName() + "\n");
		}
		System.out.println(sb);
	}
	
	private int chooseReviewerById(List<Researcher> reviewers) {
		int selectedReviewerId;
		
		System.out.println();
		do {
			selectedReviewerId = UIUtils.INSTANCE.readInteger("message.choose.reviewer");
			
		} while (!(reviewers.contains(this.service.getResearcherById(selectedReviewerId))));
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
		StringBuffer sb = new StringBuffer();
		Map<Integer, Article> articles = this.service.getAllArticles();
		int score;
		
		if (thereAreArticles(articles)) {
			printArticles(articles);
			
			Article selectedArticle = articles.get(chooseArticleById(articles));
			
			if (thereAreReviewersAllocated(selectedArticle)) {
				printReviewersOfArticle(selectedArticle);
				
				Researcher selectedReviewer = this.service.getResearcherById(chooseReviewerById(selectedArticle.getReviewers()));
				score = scoreAttribution();
				
				selectedArticle.updateReview(selectedReviewer, score);
				sb.append(getTextManager().getText("message.sucessInScoreAttribution"));
				System.out.println(sb);
			} else {
				sb.append("\n").append(getTextManager().getText("message.thereIsNotAllocatedReviewers"))
				  .append("\n");
				System.out.println(sb);
			}

		}
		
	}
	
}
