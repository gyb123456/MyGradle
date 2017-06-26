package com;

import java.util.Date;

public class Student {
	  private Long id;
	  private String name;
	  private Date createdate;
	  private String no;
	  public String nickname;
	  public String aaa;
	public Long getId()
	{
	    return id;
	}
	public String getAaa() {
		return aaa;
	}
	public void setAaa(String aaa) {
		this.aaa = aaa;
	}
	public void setId(Long id)
	{
	    this.id = id;
	}
	public String getName()
	{
	    return name;
	}
	public void setName(String name)
	{
	    this.name = name;
	}
	public Date getCreatedate()
	{
	    return createdate;
	}
	public void setCreatedate(Date createdate)
	{
	    this.createdate = createdate;
	}
	public String getNo()
	{
	    return no;
	}
	public void setNo(String no)
	{
	    this.no = no;
	}
	public String getNickname()
	{
	    return nickname;
	}
	public void setNickname(String nickname)
	{
	    this.nickname = nickname;
	}
}
