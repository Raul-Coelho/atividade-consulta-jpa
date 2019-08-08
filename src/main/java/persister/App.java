package persister;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import mapeamento.Aluno;
import mapeamento.AlunoVO;
import mapeamento.Autor;
import mapeamento.Endereco;
import mapeamento.Livro;
import mapeamento.Professor;
import mapeamento.Professor_;
import mapeamento.Telefone;

@Singleton
@Startup
public class App implements Serializable{

//    @Inject
    
    @PersistenceContext
	EntityManager em;
    Persister persister = new Persister();
    @PostConstruct
    private void init(){
//    	persister.initPersistence(em);
        
//        a1(em);
//        b1(em);
//        c1(em);
//        d1(em);
//        e1(em);
//        f1(em);

//        a2(em);
//        b2(em);
//        c2(em);
//        d2(em);
//        e2(em);
//        f2(em);
        
    }
    //Uma consulta que selecione todos os livros dos autores que não nasceram no dia 21/11/1982.
    private void a1(EntityManager em) {
        LocalDate d = LocalDate.of(1982, 11, 21);
        String jpql = "SELECT DISTINCT l FROM Livro l JOIN l.autores a WHERE a.dataNasc!=:d";
        em.createQuery(jpql, Livro.class).setParameter("d", d).getResultList().forEach(l->System.out.println(l));
    }
    //Uma consulta que selecione todos os professores que possuem Telefone e residem na rua “Que atividade fácil”.
    private void b1(EntityManager em) {
        String jpql = "select p from Professor p where p.telefones IS NOT EMPTY AND p.endereco.rua='Que atividade fácil'";
        em.createQuery(jpql, Professor.class).getResultList().forEach(p->System.out.println(p));
    }
    //Uma classe, AlunoVO, que representa o nome, CPF e idade do Aluno. Crie uma consulta, que retorna uma lista de todos os AlunoVO, selecionando todos os alunos que pertencem a turma de 2019.1.
    private void c1(EntityManager em) {
        String psql = "select new mapeamento.AlunoVO(a.nome, a.cpf, a.idade) from Aluno a where a.turma='2019.1'";
        em.createQuery(psql, AlunoVO.class).getResultList().forEach(a->System.out.println(a));
    }
//    Uma consulta que seleciona todas os Professores que possuem algum telefone que termina em 8.
    private void d1(EntityManager em) {
        String psql = "select distinct p from Professor p join p.telefones t where t.numero like '%8'";
        em.createQuery(psql, Professor.class).getResultList().forEach(p->System.out.println(p));
    }
    
//    Uma consulta que seleciona todos os livros dos Autores da cidade de Cajazeiras e tiveram seu lançamento entre os dias 01/01/2019 e 12/12/2019.
    private void e1(EntityManager em) {
        LocalDate data1 = LocalDate.of(2019, 1, 1);
        LocalDate data2 = LocalDate.of(2019, 12, 12);
        String psql = "select distinct l from Livro l join l.autores a where l.lancamento between :data1 and :data2 and a.endereco.cidade like 'Cajazeiras'";
        em.createQuery(psql, Livro.class).setParameter("data1", data1).setParameter("data2", data2).getResultList().forEach(l->System.out.println(l));
    }
//    Uma consulta que selecione os Livros dos Autores que começam com a letra “J”.
    private void f1(EntityManager em) {
        String psql = "select distinct l from Livro l join l.autores a where a.nome like 'J%'";
        em.createQuery(psql, Livro.class).getResultList().forEach(l->System.out.println(l));
    }
    
    /*
    * Com CRITEREA
    *
    */
    
    
    
    
    //Uma consulta que selecione todos os livros dos autores que não nasceram no dia 21/11/1982.
    private void a2(EntityManager em) {
        LocalDate d = LocalDate.of(1982, 11, 21);
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Livro> criteria = builder.createQuery(Livro.class);
        
        Root<Livro> root = criteria.from(Livro.class);
        Join<Livro, Autor> join = root.join("autores");
        
        Predicate predicate = builder.notEqual(join.get("dataNasc"), d);
        
        criteria.select(root).where(predicate).distinct(true);
        em.createQuery(criteria).getResultList().forEach(l->System.out.println(l));
    }
    //Uma consulta que selecione todos os professores que possuem Telefone e residem na rua “Que atividade fácil”.
    private void b2(EntityManager em) {
        String jpql = "select p from Professor p where p.telefones IS NOT EMPTY AND p.endereco.rua='Que atividade fácil'";
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Professor> criteria = builder.createQuery(Professor.class);
        Root<Professor> from = criteria.from(Professor.class);
        Join<Professor, Endereco> join = from.join("endereco");
        Predicate equal = builder.equal(join.get("rua"), "Que atividade fácil");
        Predicate notEmpty = builder.isNotEmpty(from.get("telefones"));
        Predicate and = builder.and(equal, notEmpty);
        criteria.select(from).where(and);
        em.createQuery(criteria).getResultList().forEach(p->System.out.println(p));
    }
    //Uma classe, AlunoVO, que representa o nome, CPF e idade do Aluno. Crie uma consulta, que retorna uma lista de todos os AlunoVO, selecionando todos os alunos que pertencem a turma de 2019.1.
    private void c2(EntityManager em) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<AlunoVO> criteria = builder.createQuery(AlunoVO.class);
        Root<Aluno> from = criteria.from(Aluno.class);
        CompoundSelection<AlunoVO> construct = builder.construct(
                AlunoVO.class, 
                from.get("nome"), 
                from.get("cpf"), 
                from.get("idade")
        );
        Predicate equal = builder.equal(from.get("turma"), "2019.1");
        criteria.select(construct).where(equal);
        em.createQuery(criteria).getResultList().forEach(a->System.out.println(a));
    }
//    Uma consulta que seleciona todas os Professores que possuem algum telefone que termina em 8.
    private void d2(EntityManager em) {
        String psql = "select distinct p from Professor p join p.telefones t where t.numero like '%8'";
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Professor> criteria = builder.createQuery(Professor.class);
        Root<Professor> from = criteria.from(Professor.class);
        Join<Professor, Telefone> join = from.join("telefones");
        Predicate like = builder.like(join.get("numero"), "%8");
        criteria.select(from).distinct(true).where(like);
        em.createQuery(criteria).getResultList().forEach(p->System.out.println(p));
    }
//    Uma consulta que seleciona todos os livros dos Autores da cidade de Cajazeiras e tiveram seu lançamento entre os dias 01/01/2019 e 12/12/2019.
    private void e2(EntityManager em) {
        LocalDate data1 = LocalDate.of(2019, 1, 1);
        LocalDate data2 = LocalDate.of(2019, 12, 12);
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Livro> criteria = builder.createQuery(Livro.class);
        Root<Livro> from = criteria.from(Livro.class);
        Join<Livro, Autor> join = from.join("autores");
        Join<Object, Object> join1 = join.join("endereco");
        Predicate between = builder.between(from.get("lancamento"), data1, data2);
        Predicate like = builder.like(join1.get("cidade"), "Cajazeiras");
        Predicate and = builder.and(between, like);
        criteria.select(from).distinct(true).where(and);
        em.createQuery(criteria).getResultList().forEach(l->System.out.println(l));
    }
//    Uma consulta que selecione os Livros dos Autores que começam com a letra “J”.
    private void f2(EntityManager em) {
//        String psql = "select distinct l from Livro l join l.autores a where a.nome like 'J%'";
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Livro> criteria = builder.createQuery(Livro.class);
        Root<Livro> from = criteria.from(Livro.class);
        Join<Livro, Autor> join = from.join("autores");
        Predicate like = builder.like(join.get("nome"), "J%");
        criteria.select(from).distinct(true).where(like);
        em.createQuery(criteria).getResultList().forEach(l->System.out.println(l));
    }


}
