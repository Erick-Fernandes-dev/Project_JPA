package br.com.alura.testes;

import br.com.alura.dao.CategoriaDao;
import br.com.alura.dao.ProdutoDao;
import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Produto;
import br.com.alura.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {

        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto p = produtoDao.buscarPorId(1l);
        System.out.println(p.getPreco());

        System.out.println("errooooooooooooooo");

//        List<Produto> todos = produtoDao.buscarTodos();
//        List<Produto> todos = produtoDao.buscarPornome("Xiomi Redmi");
        List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
//        for (Produto p2 : todos) {
//            System.out.println(p2.getNome());
//        }
        todos.forEach(p2 -> System.out.println(p2.getNome()));

        BigDecimal precoDoProduto = produtoDao.buscaPrecoDoProdutoComNome("Xiomi Redmi");
        System.out.println("Preco do produto: " + precoDoProduto);

    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");

        Produto celular =
                new Produto(
                        "Xiomi Redmi",
                        "Muito legal",
                        new BigDecimal("800"),
                        celulares
                );

//        celular.setNome("Xiomi Redmi");
//        celular.setDescricao("Muito legal");
//        celular.setPreco(new BigDecimal("800"));

        //factory
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao dao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();
//        celulares.setNome("XPTO");

        dao.cadastrar(celular);
        categoriaDao.cadastrar(celulares);

        em.getTransaction().commit();
        em.close();
//        em.flush();
//        em.clear();

//        celulares = em.merge(celulares);
//        celulares.setNome("1234");
//
//        em.flush();
//
//        em.remove(celulares);
//        em.flush();
    }
}
