package model1;

public class Board {
	int bno;				// int not null auto_increment,
	String title;			// varchar(200) not null,
	String content;		// text null,
	String writer;		// varchar(50) not null,
	String regdate;		// timestamp not null default now(),
	int viewcnt;			// int default 0,
	int replaycnt;		// int default 0,
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public int getReplaycnt() {
		return replaycnt;
	}
	public void setReplaycnt(int replaycnt) {
		this.replaycnt = replaycnt;
	}
}
