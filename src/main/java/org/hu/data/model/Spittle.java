package org.hu.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Spittle {
	private Spittle(){};
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="message")
	private String message;
	
	@Column(name="postedTime")
	private Date postedTime;
	
	@ManyToOne
	@JoinColumn(name="spitter")
	private Spitter spitter;
	
	public Spittle(Long id, String message, Date postedTime, Spitter spitter){
		this.id = id;
		this.message = message;
		this.postedTime = postedTime;
		this.spitter = spitter;
	}

	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public Date getPostedTime() {
		return postedTime;
	}

	public Spitter getSpitter() {
		return spitter;
	}
}
