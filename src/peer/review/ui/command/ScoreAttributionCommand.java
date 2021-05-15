package peer.review.ui.command;

import peer.review.business.PeerReviewService;
import peer.review.data.Database;

public class ScoreAttributionCommand extends Command {
	private final PeerReviewService service;
	
	public ScoreAttributionCommand(Database database, PeerReviewService service) {
		super(database);
		this.service = service;
	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
