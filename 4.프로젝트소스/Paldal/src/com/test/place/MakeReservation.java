package com.test.place;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class MakeReservation {
	
public void ReservationNow() throws Exception {
		
		File file = new File("D:\\resource\\reservation.dat");
	     FileWriter writer = new FileWriter(file,true);
		Scanner scan = new Scanner(System.in);
		int temp = scan.nextInt();
		writer.write("name"+"■");
		writer.write( "팔달월드"+"■");
		writer.write(8000*temp + "원■");
		writer.write(temp + "명■");
		Calendar cal = Calendar.getInstance();		
		SimpleDateFormat time = new SimpleDateFormat("yyyy.MM.dd.HH.mm");
		String writetime = time.format(cal.getTime());
		writer.write(writetime+ "■");
		System.out.print("점수 : ");
		writer.write(scan.nextLine()+"\n");
		writer.close();
		
	}

}
