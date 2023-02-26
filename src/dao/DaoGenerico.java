/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author hp
 */
public class DaoGenerico {

    public static DaoGenerico daoGenerico = null;
    private SessionFactory factory;
    private Transaction transaction;

    public DaoGenerico() {
        factory = HibernateUtil.getSessionFactory();
    }

    public static DaoGenerico getInstance() {
        if (daoGenerico == null) {
            daoGenerico = new DaoGenerico();
        }
        return daoGenerico;
    }

    public boolean inserir(Object o) {
        boolean retorno = false;
        Session sessao = factory.openSession();
        try {
            transaction = sessao.beginTransaction();
            sessao.save(o);
            transaction.commit();
            retorno = true;
        } catch (Exception e) {
            transaction.rollback();
            retorno=false;
            e.printStackTrace();
        }finally{
            sessao.close();
        }
        return retorno;
    }
}
