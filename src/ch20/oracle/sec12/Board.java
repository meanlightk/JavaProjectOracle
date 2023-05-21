package ch20.oracle.sec12;

import java.util.Date;

import lombok.Data;

@Data
// lombok으로 Getter, Setter 생성
public class Board {

	private int bno;
	private String btitle;
	private String bcontent;
	private int bwriter;
	private Date bdate;

}
