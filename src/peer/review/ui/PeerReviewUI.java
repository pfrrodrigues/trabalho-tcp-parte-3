package peer.review.ui;

import java.util.LinkedHashMap;
import java.util.Map;

import peer.review.PeerReview;
import peer.review.business.PeerReviewService;
import peer.review.business.impl.PeerReviewServiceImpl;
import peer.review.data.Database;
import peer.review.ui.command.AllocationCommand;
import peer.review.ui.command.Command;
import peer.review.ui.command.ReportCommand;
import peer.review.ui.command.ScoreAttributionCommand;

public class PeerReviewUI extends PeerReview {
	
	public static final String EXIT_CODE = "E";
	private final Database database;
	private final PeerReviewService peerReview;
	
	public PeerReviewUI() {
		this.database = new Database();
		this.peerReview = new PeerReviewServiceImpl(database);
	}
	
	protected final Map<String, Command> actions = new LinkedHashMap<>();
	
	@Override
	public void showInterface() throws Exception {
		UIUtils uiUtils = UIUtils.INSTANCE;
		String commandKey = null;
		
		this.actions.put("A", new AllocationCommand(peerReview));
		this.actions.put("R", new ReportCommand(peerReview));
		this.actions.put("S", new ScoreAttributionCommand(peerReview));
		
		do {
			System.out.println();
			System.out.print(getMenu(uiUtils.getTextManager()));
			commandKey = uiUtils.readString(null);
			Command command = (Command) actions.get(commandKey);
			if (command != null) {
				try {
					command.execute();					
				} catch (IllegalArgumentException iae) {
					System.out.println(iae.getMessage());
				}
			}
		} while (!EXIT_CODE.equals(commandKey));
	}
	
	protected String getMenu(TextManager textManager) {
		StringBuffer sb = new StringBuffer();
		sb.append(textManager.getText("message.options", EXIT_CODE, false))
			.append(":\n");
		for (String key : actions.keySet()) {
			Command action = actions.get(key);
			
			sb.append(key).append(" - ").append(textManager.getText(action.getClass()
					.getSimpleName())).append("\n");
		}
		sb.append(textManager.getText("message.choose.option")).append(": ");

		return sb.toString();
	}
}
