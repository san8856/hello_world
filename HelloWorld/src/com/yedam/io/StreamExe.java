package com.yedam.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamExe {
	public static void main(String[] args) {
		//입력+출력 => 복사.
		try {
			InputStream is = new FileInputStream("c:/temp/imagee.JPG");
			OutputStream os = new FileOutputStream("c:/temp/imagee1.JPG");
			
			byte[] buf = new byte[100]; //바이트 배열로 크기만큼 한번에 읽고 쓰기.
			while(true) {
				int data = is.read(buf);
				if(data == -1) {
					break;
				}
				os.write(buf);
				os.flush();
			}
			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("end of prog.");
}	//end of main.
	

	
	
static void write() {
	// 출력스트림(byte)
			 try {
				OutputStream os = new FileOutputStream("c:/temp/data.bin");
				os.write(10);
				os.write(20);
				os.write(30);
				os.flush();
				os.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	}	

static void read() {
	//입력스트림(byte)
			try {
				InputStream is = new FileInputStream("c:/temp/data.bin");
				while(true) {
					int data = is.read();
					if(data == -1) {
						break;
					}
					System.out.println(data);	
				}
				is.close();			
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
}
}
