package persisterQuestao2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;

import mapeamento.questão2.Area;
import mapeamento.questão2.Escritor;
import mapeamento.questão2.Publicacao;
import mapeamento.questão2.Revisor;



public class Persister2 {
    
    public Persister2() {}
    
    public void initPersistence(EntityManager em) {
        
        List<Area> area = new ArrayList<>();

        Area area1 = new Area("indústria");
        Area area2 = new Area("TI");
        Area area3 = new Area("Agronegocio");
        Area area4 = new Area("Financias");
        Area area5 = new Area("Ciencias"); 
        Area area6 = new Area("Exatas"); 

        Publicacao publicacao1 = new Publicacao(1,"Sondagem Industrial",Arrays.asList(area1));
        Publicacao publicacao2 = new Publicacao(2,"Java beans",Arrays.asList(area6, area2));
        Publicacao publicacao3 = new Publicacao(3,"O agronegócio da soja nos contextos mundial e brasileiro",Arrays.asList(area3));
        Publicacao publicacao4 = new Publicacao(4,"Javai?",Arrays.asList(area4));
        Publicacao publicacao5 = new Publicacao(5,"Projeto de Ciência para o Brasil",Arrays.asList(area5));
        
        area1.setPublicacao(publicacao1);
        area2.setPublicacao(publicacao2);
        area3.setPublicacao(publicacao3);
        area4.setPublicacao(publicacao4);
        area5.setPublicacao(publicacao5);
        area6.setPublicacao(publicacao2);
        
        Escritor escritor1 = new Escritor(5,Arrays.asList(publicacao1, publicacao5),1,"Jose",LocalDate.of(1997, 1, 1));
        Escritor escritor3 = new Escritor(2,Arrays.asList(publicacao2),3,"Carlos",LocalDate.of(1999, 1, 1));
        Escritor escritor4 = new Escritor(7,Arrays.asList(publicacao3),7,"Caio",LocalDate.of(1994, 1, 1));
        Escritor escritor5 = new Escritor(2,Arrays.asList(publicacao4),9,"Maria",LocalDate.of(1995, 5, 1));
        
        Revisor revisor1 = new Revisor("7.7",Arrays.asList(publicacao1, publicacao5),2,"Joao",LocalDate.of(1996, 5, 6));
        Revisor revisor3 = new Revisor("5.7",Arrays.asList(publicacao2),6,"Vitoria",LocalDate.of(1998, 5, 6));
        Revisor revisor4 = new Revisor("6.7",Arrays.asList(publicacao3),8,"Pedro",LocalDate.of(1999, 5, 6));
        Revisor revisor5 = new Revisor("9.7",Arrays.asList(publicacao4),10,"Katarina",LocalDate.of(1991, 5, 6));
        
        publicacao1.setEscritor(escritor1);
        publicacao1.setRevisor(revisor1);
        
        publicacao2.setEscritor(escritor3);
        publicacao2.setRevisor(revisor3); 
        
        publicacao3.setEscritor(escritor4);
        publicacao3.setRevisor(revisor4);
        
        publicacao4.setEscritor(escritor5);
        publicacao4.setRevisor(revisor5);
        
        publicacao5.setEscritor(escritor1);
        publicacao5.setRevisor(revisor1);
        
        em.persist(area1);
        em.persist(area2);
        em.persist(area3);
        em.persist(area4);
        em.persist(area5);
        
        em.persist(publicacao1);
        em.persist(publicacao2);
        em.persist(publicacao3);
        em.persist(publicacao4);
        em.persist(publicacao5);
    }
    
}
