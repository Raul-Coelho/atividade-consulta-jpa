package persister;

import mapeamento.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;

@Singleton
@Startup
public class App {

//    @Inject

    @PersistenceContext
    EntityManager em;
    Persister persister = new Persister();

    @PostConstruct
    private void init() {
//        persister.initPersistence(em);

//        consultaA(em);
        consultaB(em);
        consultaC(em);
        consultaD(em);
        consultaE(em);
        consultaF(em);
        consultaG(em);
    }


//CONSULTA TESTE
    private static void consultaA(EntityManager em) {

//        JPQL

//        String jpql = "SELECT a FROM Aluno a"; // entidade
//        TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
//        List<Aluno> lista = query.getResultList();
//        lista.forEach(
//                f -> System.out.println(f.getNome())
//        );


//        CRITERIA

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Aluno> criteria = builder.createQuery(Aluno.class);
        Root<Aluno> root = criteria.from(Aluno.class);
        criteria.select(root);

        TypedQuery<Aluno> query = em.createQuery(criteria);

        query.getResultList().forEach(q -> System.out.println(q));




    }

    //    COMECA AQUI

    //    Uma consulta que selecione todos os livros dos autores que não nasceram no dia 21/11/1982.
    private void consultaB(EntityManager em) {
        LocalDate d = LocalDate.of(1982, 11, 21);

//        JPQL

//        String jpql = "SELECT DISTINCT l FROM Livro l JOIN l.autores a WHERE a.dataNasc !=:d ";
//        em.createQuery(jpql, Livro.class).setParameter("d", d).getResultList().forEach(f -> System.out.println(f));


//        CRITERIA
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Livro> criteria = builder.createQuery(Livro.class);

        Root<Livro> root = criteria.from(Livro.class);
        Join<Autor, Livro> autorLivroJoin = root.join("autores", JoinType.LEFT);
        Predicate dataNasc = builder.notEqual(autorLivroJoin.get("dataNasc"),d);
        criteria.distinct(true).where(dataNasc);

        em.createQuery(criteria).getResultList().forEach(r -> System.out.println(r));
    }

    //    Uma consulta que selecione todos os professores que possuem Telefone e residem na rua “Que atividade fácil”.
    private void consultaC(EntityManager em) {

//        JPQL

//        String jpql = "SELECT p FROM Professor p WHERE p.telefones IS NOT EMPTY AND p.endereco.rua = 'Atividade Facil' ";
//        em.createQuery(jpql, Professor.class).getResultList().forEach(p -> System.out.println(p));

//        CRITERIA
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Professor> criteria = builder.createQuery(Professor.class);

        Root<Professor> root = criteria.from(Professor.class);
        Join<Professor, Telefone> join = root.join("telefones",JoinType.LEFT);
        Predicate nomeRua = builder.equal(root.get("endereco").get("rua"),"Atividade Facil");
        Predicate telefoneProf = builder.isNotNull(join.get("numero"));

        criteria.where(nomeRua,telefoneProf).distinct(true);

        em.createQuery(criteria).getResultList().forEach(r -> System.out.println(r));
    }

//    Uma classe, AlunoVO, que representa o nome, CPF e idade do Aluno. Crie uma
//    consulta, que retorna uma lista de todos os AlunoVO, selecionando todos os alunos
//    que pertencem a turma de 2019.1.

    private void consultaD(EntityManager em) {

//        JPQL

//        String jpql = "SELECT new mapeamento.AlunoVO(a.nome, a.idade, a.cpf) FROM Aluno a WHERE a.turma='2019.1'";
//        em.createQuery(jpql, AlunoVO.class).getResultList().forEach(a -> System.out.println(a));

//        CRITERIA

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<AlunoVO> criteria = builder.createQuery(AlunoVO.class);

        Root<Aluno> root = criteria.from(Aluno.class);
        Predicate predicate = builder.equal(root.get("turma"),"2019.1");

        criteria.multiselect(root.get("nome"), root.get("idade"),root.get("cpf")).where(predicate);
        em.createQuery(criteria).getResultList().forEach(a-> System.out.println("Aluno: "+a.getNome()));


    }

    //
//    Uma consulta que seleciona todas os Professores que possuem algum telefone
//    que termina em 8.
    private void consultaE(EntityManager em) {
//        JPQL

//        String jpql = "SELECT DISTINCT p FROM Professor p JOIN p.telefones t WHERE t.numero LIKE '%8'";
//        em.createQuery(jpql).getResultList().forEach(p -> System.out.println(p));


//        CRITERIA

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Professor> criteria = builder.createQuery(Professor.class);

        Root<Professor> root = criteria.from(Professor.class);
        Join<Professor, Telefone> join = root.join("telefones");

        Predicate telefone = builder.like(join.get("numero"),"%8");
        criteria.where(telefone);
        em.createQuery(criteria).getResultList().forEach(p-> System.out.println("Professor: "+p.getNome())
        );

    }

//    Uma consulta que seleciona todos os livros dos Autores da cidade de Cajazeiras e
//    tiveram seu lançamento entre os dias 01/01/2019 e 12/12/2019.

    private void consultaF(EntityManager em) {

        LocalDate d1 = LocalDate.of(2019,01,01);
        LocalDate d2 = LocalDate.of(2019,12,12);


//        JPQL

//        String jpql = "SELECT DISTINCT l FROM Livro l JOIN l.autores a WHERE l.lancamento BETWEEN :d1 AND :d2 AND a.endereco.cidade LIKE 'Cajazeiras'";
//        em.createQuery(jpql,Livro.class).setParameter("d1",d1).setParameter("d2",d2).getResultList().forEach(l -> System.out.println(l));

//        CRITERIA

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Livro> criteria = builder.createQuery(Livro.class);

        Root<Livro> root = criteria.from(Livro.class);
        Join<Livro,Autor> join = root.join("autores");

        Predicate cidade = builder.like(join.get("endereco").get("cidade"),"%Cajazeiras%");
        Predicate lancamento = builder.between(root.get("lancamento"),d1,d2);

        criteria.where(cidade,lancamento);
        em.createQuery(criteria).getResultList().forEach(l-> System.out.println("Livro: "+l.getTitulo()));

    }


//    Uma consulta que selecione os Livros dos Autores que começam com a letra “J”.
    private void consultaG(EntityManager em) {
//        JQPL

//        String jpql = "SELECT DISTINCT l FROM Livro l JOIN l.autores a WHERE a.nome LIKE 'J%'";
//        em.createQuery(jpql,Livro.class).getResultList().forEach(l -> System.out.println(l));

//        CRITERIA

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Livro> criteria = builder.createQuery(Livro.class);

        Root<Livro> root = criteria.from(Livro.class);
        Join<Livro,Autor> join = root.join("autores");

        Predicate predicate = builder.like(builder.lower(join.get("nome")),"j%");
        criteria.where(predicate);

        em.createQuery(criteria).getResultList().forEach(r-> System.out.println("Livro: "+r.getTitulo()));
    }


}
