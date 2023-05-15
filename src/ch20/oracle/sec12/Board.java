package ch20.oracle.sec12;

import java.util.Date;

import lombok.Data;

@Data
public class Board {

	private int bno;
	private String btitle;
	private String bcontent;
	private int bweiter;
	private Date bdate;

}
