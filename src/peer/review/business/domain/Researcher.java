package peer.review.business.domain;

import java.util.List;

public class Researcher {
	private int id;
	private String name;
	private University affiliation;
	private List<Topic> researchInterests;
	
	public Researcher(int id, String name, University affiliation, List<Topic> researchInterests) {
		this.id = id;
		this.name = name;
		this.affiliation = affiliation;
		this.researchInterests = researchInterests;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public University getAffiliation() {
		return affiliation;
	}
	
	public boolean isSameAffiliation(Researcher researcher) {
		return this.affiliation.equals(researcher.getAffiliation());
	}
	
	public boolean isTopicOfInterest(Topic topic) {
		return this.researchInterests.contains(topic);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Researcher other = (Researcher) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
