package persisterQuestao2;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class App {
    
    @PersistenceContext
    EntityManager em;
    persister.Persister persister = new persister.Persister();
    @PostConstruct
    private void init(){
    	persister.initPersistence(em);
    }
    
}
