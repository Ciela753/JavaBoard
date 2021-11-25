package main;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import service.BoardService;
import service.BoardServiceImpl;
import util.DBConn;
import vo.BoardVo;

public class Main{
	private BoardService boardService = new BoardServiceImpl();
	private Scanner scanner = new Scanner(System.in);
	public void execute() {
		while(true) {
			System.out.println("===========게시글 리스트==========");
			System.out.printf("%s%10s  %10s  %10s%n", "번호", "제목", "작성자", "작성일");
			
			List<BoardVo> list = boardService.getList(); 
			
			list.forEach(System.out::println);
			System.out.println("상세조회(글번호) w.등록 ");
			
			String input = scanner.nextLine();
			if(input.equalsIgnoreCase("w")) {
				// 글등록 폼 이동
				System.out.println("글 등록폼 이동");
				System.out.println("==========게시물 등록============");
				
				System.out.println("등록할 게시물의 제목 : ");
				String title = scanner.nextLine();
				
				System.out.println("등록할 게시물의 내용 : ");
				String content = scanner.nextLine();
				
				System.out.println("글쓴이 이름 : ");
				String writer = scanner.nextLine();
				
				boardService.register(new BoardVo(list.get(list.size()-1).getBno()+1, title, content, writer, "21/11/24"));
			}
			else {
				// 상세조회
				BoardVo vo = boardService.get(Long.parseLong((input)));
				System.out.println(vo);
				
				System.out.println("==========게시글 상세 페이지============");
				System.out.println("========="+ vo.getBno() +"==========");
				System.out.println("============="+vo.getTitle()+"============");
				System.out.println("============="+vo.getWriter()+"============");
				System.out.println("=                              =");
				System.out.println("=                              =");
				System.out.println("=    "+vo.getContent()+"=");
				System.out.println("=                              =");
				System.out.println("=                              =");
				System.out.println("================================");
				System.out.println("");
				System.out.println("1.수정 2.삭제 3.리스트");
				
				input = scanner.nextLine();
				
				switch (input) {
				case "1":
					System.out.println("제목 수정하기 : ");
					String title = scanner.nextLine();
					
					System.out.println("내용 수정하기 : ");
					String content = scanner.nextLine();
					vo.setTitle(title);
					vo.setContent(content);
					boardService.modify(vo);
					break;
				case "2":
					boardService.remove(vo.getBno());
					break;

				default:
					break;
				}
			}
		
		}
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.execute();
	}
}
