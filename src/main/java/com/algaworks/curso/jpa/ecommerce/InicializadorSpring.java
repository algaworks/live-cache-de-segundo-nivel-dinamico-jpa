package com.algaworks.curso.jpa.ecommerce;

import com.algaworks.curso.jpa.ecommerce.config.JpaConfig;
import com.algaworks.curso.jpa.ecommerce.model.Categoria;
import com.algaworks.curso.jpa.ecommerce.model.Entidade;
import com.algaworks.curso.jpa.ecommerce.model.Produto;
import com.algaworks.curso.jpa.ecommerce.repository.Produtos;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InicializadorSpring {

    private static final Integer PRIMARY_KEY = 1;

    public static void estudarCache(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        EntityManager entityManager3 = entityManagerFactory.createEntityManager();
        EntityManager entityManager4 = entityManagerFactory.createEntityManager();


        /* ******************************************************
        Terceira parte da LIVE

        log("EM1 - Consultando todos os produtos.");
        entityManager1
                .createQuery("select p from Produto p", Produto.class)
//                .setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS)
//                .setHint("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS)
                .getResultList();
        log(" --- ");

        log("EM2 - Consultando todos os produtos.");
        entityManager2
                .createQuery("select p from Produto p", Produto.class)
//                .setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS)
//                .setHint("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS)
                .getResultList();
        log(" --- ");
         ****************************************************** */

        /* ******************************************************
        Segunda parte da LIVE

        entityManager2.setProperty(
                "javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);

        log("EM1 - Consultando todas os categorias... e incluindo no cache");
        entityManager1
                .createQuery("select c from Categoria c", Categoria.class)
//                .setHint("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS)
                .getResultList();
        log(" --- ");

        log("EM2 - Consultando somente uma categoria.");
        Map<String, Object> propertiesCategoria2 = new HashMap<>();
//        propertiesCategoria2.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        propertiesCategoria2.put("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS);
        entityManager2.find(Categoria.class, PRIMARY_KEY, propertiesCategoria2);
        log(" --- ");
         ****************************************************** */

        /* ******************************************************
        Primeira parte da LIVE

        log("EM1 - Consultando somente uma categoria... buscando na base e incluindo no cache.");
        Map<String, Object> propertiesCategoria1 = new HashMap<>();
//        propertiesCategoria1.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        propertiesCategoria1.put("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS);
        entityManager1.find(Categoria.class, PRIMARY_KEY, propertiesCategoria1);
        log(" --- ");

        log("EM2 - Consultando somente uma categoria... buscando não mais no cache, agora no banco");
        Map<String, Object> propertiesCategoria2 = new HashMap<>();
        propertiesCategoria2.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        propertiesCategoria2.put("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS);
        entityManager2.find(Categoria.class, PRIMARY_KEY, propertiesCategoria2);
        log(" --- ");

        log("EM3 - Consultando somente uma categoria... buscando no cache");
        Map<String, Object> propertiesCategoria3 = new HashMap<>();
//        propertiesCategoria3.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        propertiesCategoria3.put("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS);
        entityManager3.find(Categoria.class, PRIMARY_KEY, propertiesCategoria3);
        log(" --- ");
         ****************************************************** */




        /*
        entityManager1.setProperty(
                "javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        entityManager1.setProperty(
                "javax.persistence.cache.storeMode", CacheStoreMode.BYPASS);

        log("EM1 - Consultando somente uma categoria.");
        Map<String, Object> propertiesCategoria1 = new HashMap<>();
//        propertiesCategoria1.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        propertiesCategoria1.put("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS);
        entityManager1.find(Categoria.class, PRIMARY_KEY, propertiesCategoria1);
        log(" --- ");

        log("EM1 - Consultando somente um produto.");
        Map<String, Object> propertiesProduto1 = new HashMap<>();
//        propertiesProduto1.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        propertiesProduto1.put("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS);
        entityManager1.find(Produto.class, PRIMARY_KEY, propertiesProduto1);
        log(" --- ");

        log("EM1 - Consultando todas os categorias.");
        entityManager1
                .createQuery("select c from Categoria c", Categoria.class)
//                .setHint("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS)
                .getResultList();
        log(" --- ");

        log("EM1 - Consultando todos os produtos.");
        entityManager1
                .createQuery("select p from Produto p", Produto.class)
//                .setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS)
//                .setHint("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS)
                .getResultList();
        log(" --- ");
        */

        entityManager1.close();
        entityManager2.close();
        entityManager3.close();
        entityManager4.close();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext();

        applicationContext.register(JpaConfig.class);

        applicationContext.scan(Entidade.class.getPackageName(),
                Produtos.class.getPackageName());

        applicationContext.refresh();

        EntityManagerFactory entityManagerFactory = applicationContext
                .getBean(EntityManagerFactory.class);

        estudarCache(entityManagerFactory);

        entityManagerFactory.close();

        applicationContext.close();

        log("Fim!");
    }

    private static void log(Object msg) {
        System.out.println("[LOG " + System.currentTimeMillis() + "] " + msg);
    }
}
