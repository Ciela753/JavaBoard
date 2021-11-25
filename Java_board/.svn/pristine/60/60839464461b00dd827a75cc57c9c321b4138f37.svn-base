package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import util.DBConn;
import vo.BoardVo;

public class BoardDao {
	private DataSource dataSource;
	//db커넥션 생성
	private Connection connection = DBConn.getConnection();
	{
		// 1. conn 취득
		// 2. DML문장 생성 > 실행
		// 3. (Select) ResultSet 결과집합을 취득
	}
	
	public static void main(String[] args) throws SQLException {
//		List<String> list = new ArrayList<String>();
//		list.add("사과");
//		list.add("바나나");
//		list.add("딸기");
//		list.add("포도");
//		list.add("키위");
//		
//		Iterator<String> itr = list.iterator();
//		
//		while(itr.hasNext())
//			System.out.println(itr.next());
		BoardVo vo = new BoardVo(null, "제목2", "내용내용2", "작성자", null);
		String sql = "INSERT INTO TBL_BOARD (BNO, title, CONTENT, WRITER) "
				+ "VALUES (SEQ_BOARD.NEXTVAL, " + vo.getTitle() + ", '" + vo.getContent() + "', '" + vo.getWriter() + "')";
		
		Statement stmt = new BoardDao().connection.createStatement(); 
		stmt.executeUpdate(sql);
		
		PreparedStatement pstmt = new BoardDao().connection.prepareStatement(
				"INSERT INTO TBL_BOARD (BNO, title, CONTENT, WRITER) "
				+ "VALUES(SEQ_BOARD.NEXTVAL,?,?,?)");
		
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getWriter());
		pstmt.executeUpdate();
	}
	
	//게시글 목록 출력
	public List<BoardVo> list() {
		//출력할 결과를 담을 빈 목록 생성
		List<BoardVo> list = new ArrayList<>();
		try {
			//문장 생성
			Statement stmt = connection.createStatement();
			//쿼리문의 결과집합 취득
			ResultSet rs = stmt.executeQuery("SElECT \r\n"+
			"BNO, TITLE, CONTENT, WRITER, REGDATE\r\n" +
			"FROM JAVABOARD.TBL_BOARD\r\n"+
			"WHERE BNO>0\r\n"+
			"ORDER BY 1 DESC");
			
			//rs에 담긴 객체의 개수 만큼 반복
			while(rs.next()) {
				int idx = 1;
				//rs에 담긴 객체를 가져와서 차례대로 변수에 저장 
				Long bno = rs.getLong(idx++);
				String title = rs.getString(idx++);
				String content = rs.getString(idx++);
				String writer = rs.getString(idx++);
				String regDate = rs.getString(idx++);
				
				//인스턴스 생성
				BoardVo boardVo = new BoardVo(bno, title, content, writer, regDate);
				//빈 리스트에 인스턴스 저장.
				list.add(boardVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//인스턴스가 담긴 목록 리턴
		return list;
		
	}
	
	//create
	public Long insert(BoardVo boardVo) {
		
		Long bno = null;
		try {
			connection.setAutoCommit(false);
			
			//커넥션 취득한 쿼리문의 실행결과인 결과집합 취득
			ResultSet rs = connection.prepareStatement("SELECT SEQ_BOARD.NEXTVAL FROM DUAL").executeQuery();
			//커서 이동
			rs.next();
			//결과 집합 rs에서 첫번째 컬럼 값을 가져온다.
			bno = rs.getLong(1);
			
			//bno에 글 작성(이클립스 -> db)
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO JAVABOARD.TBL_BOARD (BNO, TITLE, CONTENT, WRITER, REGDATE) VALUES (?, ?, ?, ?, ?)");
			int idx = 1;
			pstmt.setLong(idx++, bno);
			pstmt.setString(idx++, boardVo.getTitle());
			pstmt.setString(idx++, boardVo.getContent());
			pstmt.setString(idx++, boardVo.getWriter());
			pstmt.setString(idx++, boardVo.getRegDate());
			
			//db의 쿼리 실행
			pstmt.executeUpdate();
			//db커밋
			connection.commit();
			//자동 커밋 모드 켜기
			connection.setAutoCommit(true);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return bno;
	}
	
	//read. bno번호로 상세조회
	public BoardVo read(Long bno) {
		//vo타입 참조변수 선언
		BoardVo boardVo = null;
		try {
			//커넥션을 취득하여 쿼리문을 실행
			PreparedStatement pstmt = connection.prepareStatement("SELECT BNO, TITLE, CONTENT, WRITER, REGDATE\r\n"+
						"FROM JAVABOARD.TBL_BOARD\r\n"+
						"WHERE BNO =?");
			//첫번째 인자 ?에 bno값 저장 
			pstmt.setLong(1, bno);
			//bno값이 저장된 쿼리의 결과집합 취득 
			ResultSet rs = pstmt.executeQuery();
			
			//rs에 값이 있다면(하나의 결과집합만 가져와야하므로 참거짓으로 구분)
			//쿼리문으로 가져온 결과값을 vo인스턴스로 저장
			if(rs.next()) {
				int idx=1;
				boardVo = new BoardVo(
						rs.getLong(idx++),
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getString(idx++)
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//인스턴스 그대로 리턴
		return boardVo;
	}
	
	//파라미터로 받아온 BoardVo타입 객체 수정
	public void update(BoardVo boardVo) {
		try {
			//실행할 쿼리문을 pstmt에 저장
			PreparedStatement pstmt = connection.prepareStatement("UPDATE TBL_BOARD SET\r\n"+
					" TITLE = ?, \r\n" +
					" CONTENT = ?\r\n" +
					"WHERE BNO = ?");
			int idx = 1;
			//쿼리문 파라미터에 차례대로 대입
			pstmt.setString(idx++, boardVo.getTitle());
			pstmt.setString(idx++, boardVo.getContent());
			pstmt.setLong(idx++, boardVo.getBno());
			
			//쿼리문 실행하여 db업데이트
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//파라미터로 받아온 bno로 글삭제
	public void delete(Long bno) {
		try {
			//쿼리문 실행
			PreparedStatement pstmt = connection.prepareStatement("DELETE JAVABOARD.TBL_BOARD WHERE BNO=?");
			int idx = 1;
			//첫번재 인자에 파라미터로 넘어온 bno값 저장
			pstmt.setLong(idx++, bno);
			
			//인자값이 저장된 쿼리문 실행
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
