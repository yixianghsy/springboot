package hsy.com.elasticSearch.domain;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 功能描述：文章对象，
 *
 * <p> 创建时间：May 1, 2018 8:22:45 PM </p> 
 *
 *@作者 小D课堂  小D
 */
@Document(indexName = "blog", type = "article")
public class Article implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String title;
	
	private String summary;
	
	private String content;
	
	private int pv;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}
	
	

}
