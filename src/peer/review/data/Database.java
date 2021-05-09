package peer.review.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import peer.review.business.domain.Article;
import peer.review.business.domain.Conference;
import peer.review.business.domain.Researcher;
import peer.review.business.domain.Topic;
import peer.review.business.domain.University;

public class Database {
	private Map<String, Conference> allConferences;
    private Map<Integer, Researcher> allResearchers;
    private Map<Integer, Article> allArticles;
    
    public Database() {
    	this(true);
    }
    
    public Database(boolean initData) {
    	this.allResearchers = new HashMap<>();
    	this.allConferences = new HashMap<>();
    	this.allArticles = new HashMap<>();
    	if (initData) {
    		initData();
    	}
    }
    
    private void initData() {
    	// Universities
    	University ufrgs = new University("UFRGS");
    	University usp = new University("USP");
    	University ufrj = new University("UFRJ");

        // Topics 
        Topic swProdLines = new Topic("Software Product Lines");
        Topic swReuse = new Topic("Software Reuse");
        Topic modul = new Topic("Modularity");
        Topic swArch = new Topic("Software Architecture");
        Topic swTest = new Topic("Software Testing");
        Topic aoProg = new Topic("Aspect-oriented Programming");
        Topic swQual = new Topic("Software Quality");

    	// Researchers
        int auxId = 0;
   	    Researcher joao = new Researcher(++auxId, "Joao", ufrgs, new ArrayList<Topic>(
                List.of(swProdLines, swReuse, modul)));
        save(joao);
        Researcher ana = new Researcher(++auxId, "Ana", usp, new ArrayList<Topic>(
            List.of(swArch, modul, swReuse)));
        save(ana);
        Researcher manoel = new Researcher(++auxId, "Manoel", ufrgs, new ArrayList<Topic>(
            List.of(swProdLines, swTest)));
        save(manoel);
        Researcher joana = new Researcher(++auxId, "Joana", ufrj, new ArrayList<Topic>(
            List.of(swProdLines, swReuse, swArch, aoProg)));
        save(joana);
        Researcher miguel = new Researcher(++auxId, "Miguel", ufrgs, new ArrayList<Topic>(
            List.of(swArch, modul, swTest)));
        save(miguel);
        Researcher beatriz = new Researcher(++auxId, "Beatriz", ufrj, new ArrayList<Topic>(
            List.of(swReuse, swTest, aoProg)));
        save(beatriz);
        Researcher suzana = new Researcher(++auxId, "Suzana", ufrgs, new ArrayList<Topic>(
            List.of(aoProg, modul, swReuse)));
        save(suzana);
        Researcher natasha = new Researcher(++auxId, "Natasha", ufrj, new ArrayList<Topic>(
            List.of(modul, swReuse, swQual, swProdLines)));
        save(natasha);
        Researcher pedro = new Researcher(++auxId, "Pedro", usp, new ArrayList<Topic>(
            List.of(aoProg, swArch)));
        save(pedro);
        Researcher carlos = new Researcher(++auxId, "Carlos", usp, new ArrayList<Topic>(
            List.of(swReuse, modul)));
        save(carlos);

        // Conferences
        Conference icse = new Conference("ICSE", new ArrayList<>(
            List.of(joao, ana, manoel, joana, miguel, beatriz)));
        save(icse);
        Conference fse = new Conference("FSE", new ArrayList<>(
            List.of(joao, ana, manoel, joana, miguel, beatriz)));
        save(fse);
        Conference sbes = new Conference("SBES", new ArrayList<>(
            List.of(joana, miguel, beatriz, suzana, natasha, pedro, carlos)));
        save(sbes);

        // Articles 
        auxId = 0;
        Article a1 = new Article(++auxId, "Coupling and Cohesion", joao, modul, sbes);
        sbes.addArticle(a1);
        save(a1);
        Article a2 = new Article(++auxId, "Design Patterns", beatriz, swReuse, fse);
        fse.addArticle(a2);
        save(a2);
        Article a3 = new Article(++auxId, "AspectJ", suzana, aoProg, fse);
        fse.addArticle(a3);
        save(a3);
        Article a4 = new Article(++auxId, "Feature Model", natasha, swProdLines, fse);
        fse.addArticle(a4);
        save(a4);
        Article a5 = new Article(++auxId, "Architecture Recovery", pedro, swArch, fse);
        fse.addArticle(a5);
        save(a5);
        Article a6 = new Article(++auxId, "Functional Testing", carlos, swTest, fse);
        fse.addArticle(a6);
        save(a6);
        Article a7 = new Article(++auxId, "COTs", beatriz, swReuse, icse);
        icse.addArticle(a7);
        save(a7);
        Article a8 = new Article(++auxId, "Pointcut", suzana, aoProg, icse);
        icse.addArticle(a8);
        save(a8);
        Article a9 = new Article(++auxId, "Product Derivation", natasha, swProdLines, icse);
        icse.addArticle(a9);
        save(a9);
        Article a10 = new Article(++auxId, "Architecture Conformance", pedro, swArch, icse);
        icse.addArticle(a10);
        save(a10);
        Article a11 = new Article(++auxId, "Structural Testing", carlos, swTest, icse);
        icse.addArticle(a11);
        save(a11);

        // Reviews
        a1.addReviewer(natasha);
        a1.updateReview(natasha, 2);
        a1.addReviewer(carlos);
        
        a2.addReviewer(suzana);
        a2.updateReview(suzana, 2);
        a2.addReviewer(ana);
        a2.updateReview(ana, 3);

        a3.addReviewer(joana);
        a3.updateReview(joana, -1);
        a3.addReviewer(beatriz);
        a3.updateReview(beatriz, 1);

        a4.addReviewer(joao);
        a4.updateReview(joao, 1);
        a4.addReviewer(manoel);
        a4.updateReview(manoel, 0);

        a5.addReviewer(joana);
        a5.updateReview(joana, -3);
        a5.addReviewer(miguel);
        a5.updateReview(miguel, -3);

        a6.addReviewer(manoel);
        a6.updateReview(manoel, -1);
        a6.addReviewer(beatriz);
        a6.updateReview(beatriz, 0);
    }
    
    public Conference getConference(String key) {
    	return this.allConferences.get(key);
    }
    
    public Researcher getResearcher(int key) {
    	return this.allResearchers.get(key);
    }
    
    public Article getArticle(int key) {
    	return this.allArticles.get(key);
    }

	public Map<String, Conference> getAllConferences() {
		return allConferences;
	}

	public Map<Integer, Article> getAllArticles() {
		return allArticles;
	}
	
	public void save(Conference conference) {
		this.allConferences.put(conference.getAcronym(), conference);
	}
	
	public void save(Researcher researcher) {
		this.allResearchers.put(researcher.getId(), researcher);
	}
	
	public void save(Article article) {
		this.allArticles.put(article.getId(), article);
	}
}
