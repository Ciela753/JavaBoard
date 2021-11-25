package service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.BoardDao;
import vo.BoardVo;
import util.DBConn;

public class BoardServiceImpl implements BoardService {
	BoardDao boardDao = new BoardDao();
	private List<BoardVo> list = new ArrayList<BoardVo>();
	
	{
		list.add(new BoardVo(1L, "제목", "내용", "작성자", "21/11/24"));
		list.add(new BoardVo(2L, "제목", "내용", "작성자", "21/11/24"));
		list.add(new BoardVo(3L, "제목", "내용", "작성자", "21/11/24"));
	}
	

	@Override
	public void register(BoardVo boardVo) {
		boardDao.insert(boardVo);
	}

	@Override
	public BoardVo get(Long bno) {
		return boardDao.read(bno);
	}

	@Override
	public void modify(BoardVo boardVo) {
		boardDao.update(boardVo);
	}

	@Override
	public void remove(Long bno) {
		boardDao.delete(bno);
	}

	@Override
	public List<BoardVo> getList() {
		return boardDao.list();
	}
}
