/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ma.start;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Osikena
 */
public class Logic {
	
	public long generateaccountnumber(){
		ThreadLocalRandom random = ThreadLocalRandom.current();
		long accountnumber = random.nextLong(100_000_000_00L);
		return accountnumber;
	}
	
	public String date(){
		
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String get = date.format(now);
		return get;
	}
	
}
