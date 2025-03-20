package com.yedam.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CharacterExe {
	public static void main(String[] args) {
		// 외부 파일 정보 읽기
		Scanner scn = null;
		try {
			scn = new Scanner(new FileInputStream("c:/temp/message.txt"));
			while (true) { // 모든 라인 반복
				String msg = scn.nextLine(); // 한 라인 읽어들이기
				String[] msgAry = msg.split(" "); // 공백을 기준으로 문자를 나눔. //message.txt에 공백이 아닌 . 이면 "."으로, :이면 ":"으로 구분 가능
				System.out.println("코드: " + msgAry[0] + ", 이름: " + msgAry[1] + ", 가격: " + msgAry[2]); // 읽은 라인 구분하여 쓰기
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {

		}
		scn.close();
		System.out.println("end of prog.");
	}

	static void write() {
		try {
			// 출력스트림(문자기반)
			Writer writer = new FileWriter("c:/temp/data.txt");
			writer.write('a');
			writer.write('b');
			writer.write('c');

			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void read() {
		// 입력스트림(문자기반)
		try {
			Reader reader = new FileReader("d:/Dev/git/hello_world/HellowWorld/src/com/yedam/io/StreamExe.java");
			while (true) {
				int data = reader.read();
				if (data == -1) {
					break;
				}
				System.out.println((char) data);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void write2() {
		Scanner scn = new Scanner(System.in);
		// 입력값을 파일에 출력.
		try {
			Writer writer = new FileWriter("c:/temp/message.txt");
			while (true) {
				System.out.println("입력 >> ");
				String msg = scn.nextLine();
				if (msg.equals("quit"))
					break;
				writer.write(msg + "\n"); // 불러올때 "공백값" 넣어서 저장.
				writer.flush();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		scn.close();
	}
}
