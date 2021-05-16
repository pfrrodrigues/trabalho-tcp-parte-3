package peer.review.ui.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import peer.review.business.PeerReviewService;
import peer.review.business.domain.Conference;

class ReportCommandTest {

	@Test
	void ShouldNotPrintArticlesReportWithEmptyConferencesList() throws Exception {
		Map<String, Conference> conferences = Collections.emptyMap();

		List<Conference> listOfConferences = new ArrayList<>(conferences.values());

		PeerReviewService mockedService = mock(PeerReviewService.class);

		ReportCommand command = spy(new ReportCommand(mockedService));

		when(mockedService.getAllConferences()).thenReturn(conferences);

		command.execute();

		verify(command, times(1)).printConferences(listOfConferences);
	}

}
