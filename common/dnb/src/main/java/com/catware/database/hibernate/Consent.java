package com.catware.database.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name = "Consent")
@org.hibernate.annotations.Table(appliesTo = "Consent")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Consent implements Serializable {

	@Id
	@Column
	private String psuid;
	
	@Column
	private String consentid;

	public String getPsuid() {
		return psuid;
	}

	public void setPsuid(String psuid) {
		this.psuid = psuid;
	}

	public String getConsentid() {
		return consentid;
	}

	public void setConsentid(String consentid) {
		this.consentid = consentid;
	}

    @Transient
    @Transactional
	public void persist() {
      	Session session = getSession();
    	Transaction transaction = session.beginTransaction();
      	session.saveOrUpdate(this);
      	transaction.commit();
      	//session.flush();
      	//session.close();
	}
		
    @Transient
    @Transactional
	public static Consent find(String id) {
    	Session session = getSession();
    	Transaction transaction = session.beginTransaction();
    	Consent consent = session.find(Consent.class, id);
    	transaction.commit();
    	session.close();
    	return consent;
    }
		
    @Transient
    @Transactional
    public static Session getSession() {
    	  Configuration configuration = new Configuration().configure("hibernate.cfg.xml").configure();
    	  SessionFactory factory = configuration.buildSessionFactory();
    	  Session session = null;
          if (session == null) {
               session = factory.getCurrentSession();
          }

          if (session != null) {
              if (!session.isOpen()) {
                  session = factory.openSession();
              }
          }
          return session;
    }
}
