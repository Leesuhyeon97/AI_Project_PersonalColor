package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.service.BoardService;
import com.example.demo.service.MemberService;

import com.example.demo.vo.BoardVO;
import com.example.demo.vo.MemberVO;
import com.example.demo.vo.ScrapVO;


@Controller
public class BoardController {


	
	@Autowired
	BoardService boardService;

	//글목록 가져오기
	@RequestMapping("boardList")
	public ModelAndView boardList() {
		//System.out.println("boardList() 호출됨");
		List<BoardVO> list=boardService.boardList();
		ModelAndView mav=new ModelAndView("boardList");
		mav.addObject("boardList", list);
		//System.out.println(list.size());
		return mav;
	}
	
	
	//글쓰기 화면 제공
	
	@RequestMapping("boardWriteForm")
	public String boardWriteForm() {
		return "boardWriteForm";
	}
	
	//글등록
	
	@RequestMapping("boardWrite")
	public RedirectView boardWrite(BoardVO boardVO,MultipartFile file) {
//		System.out.println(file);
		String fileName=file.getOriginalFilename();
		if(!fileName.equals("")) {
			boardVO.setFileName(fileName);
			try {

				file.transferTo(new File(PathVar.FIND_DRIVE+fileName));

			} catch (IllegalStateException | IOException e) {
				
				e.printStackTrace();
			}
			
		}
		//System.out.println(boardVO);	
		if(boardVO.getId()=="error" || boardVO.getNo()==-1 ||
				boardVO.getTitle()=="error"||boardVO.getContent()=="error")
			{
				System.out.println("error");
				return new RedirectView("boardWriteForm");
			}else {
				boardService.boardWrite(boardVO);
			}
		return new RedirectView("boardList");
	}
	
	//글 상세보기
	@RequestMapping("viewArticle")
	public String viewArticle(@RequestParam int no,HttpSession session ) {
		//System.out.println(no+"번글보기");
		BoardVO boardVO=boardService.viewArticle(no);
		//System.out.println(boardVO);
		session.setAttribute("article", boardVO);
		return "viewArticle";
	}
	
	
	
	@RequestMapping("delete")
	@ResponseBody
	public String delete(@RequestParam String id,HttpSession session) {
		BoardVO parentVO=(BoardVO) session.getAttribute("article");
			//System.out.println("delete 호출");
			if(parentVO.getId().equals(id)) {
				//System.out.println("서비스호출");
				boardService.delete(parentVO.getNo());
				return "ok";
			}else {
				//System.out.println("서비스호출안됨");
				return "error";
				
			}
		
	}
	
	@RequestMapping("updateArticleForm")
	public String updateForm() {
		return "updateArticleForm";
	}
	
	@RequestMapping("updateArticle")
	@ResponseBody
	public RedirectView update(@RequestParam int no,String title,String content,MultipartFile file,BoardVO updateVO) {
		//System.out.println(updateVO);
		updateVO.setContent(content);
		updateVO.setTitle(title);
		String fileName=file.getOriginalFilename();
			if(!fileName.equals("")) {
				updateVO.setFileName(fileName);
				try {
	
					file.transferTo(new File(PathVar.LOCAL_DRIVE+fileName));
	
				} catch (IllegalStateException | IOException e) {
					
					e.printStackTrace();
				}
				
			}
			
			boardService.update(updateVO);
			return new RedirectView("boardList");
	}
	
	@RequestMapping("search")
	@ResponseBody
	public List<BoardVO> search(@RequestParam String searchType,String keyword,BoardVO boardVO) {
		//System.out.println(searchType+":"+keyword);
	boardVO.setSearchType(searchType);
	boardVO.setKeyword(keyword);
	
		
	List<BoardVO> searchList=boardService.search(boardVO);
		//System.out.println(searchList);
		return searchList;
	}
	
	@RequestMapping("scrapArticle")
	@ResponseBody
	public String scrap(@RequestParam int no,String id,String cookieId ,BoardVO boardVO,ScrapVO scrapVO) {
		//System.out.println(no+":"+id+":"+bid);
		boardVO.setId(id);
		boardVO.setNo(no);
		boardService.selectScrap(boardVO);
		//System.out.println(boardService.selectScrap(boardVO));
		//System.out.println(boardService.selectScrap(boardVO).get(0));
		scrapVO.setBid(id);
		scrapVO.setNo(no);
		scrapVO.setId(cookieId);
		scrapVO.setContent(boardService.selectScrap(boardVO).get(0).getContent());
		scrapVO.setFileName(boardService.selectScrap(boardVO).get(0).getFileName());
		scrapVO.setTitle(boardService.selectScrap(boardVO).get(0).getTitle());
		
		boardService.insertScrap(scrapVO);
		return "ok";
		
	}
	
	@RequestMapping("scrapList")
	public ModelAndView scrapList(HttpSession session,ScrapVO scrapVO) {
		//System.out.println("scrapList() 호출됨");
		//System.out.println(session.getAttribute("id"));
		String id= (String)session.getAttribute("id");
		scrapVO.setId(id);
		List<ScrapVO> scrapList=boardService.scrapList(scrapVO);
		//System.out.println(scrapList);
		ModelAndView mav=new ModelAndView("scrapList");
		mav.addObject("scrapList", scrapList);
		return mav;
	}
	
	//글 상세보기
		@RequestMapping("viewScrapArticle")
		public String viewScrapArticle(@RequestParam int no,HttpSession session ) {
			//System.out.println(no+"번글보기");
			ScrapVO scrapVO=boardService.viewScrapArticle(no);		
			session.setAttribute("scrapArticle", scrapVO);
			return "viewScrapArticle";
		}
		
		@RequestMapping("deleteScrap")
		@ResponseBody
		public String deleteScrap(@RequestParam int no) {
			boardService.deleteScrap(no);
			return "ok";
		}
	
	
	
	/*
	 * //답글쓰기 화면제공
	 * 
	 * @RequestMapping("replyWriteForm") public String replyWriteForm() { return
	 * "replyWriteForm"; }
	 * 
	 * 
	 * //답글등록
	 * 
	 * @RequestMapping("replyWrite") 
	 * public RedirectView replyWrite(@RequestParam
	 * int parentNo, BoardVO replyVO, HttpSession session) {
	 * 
	 * BoardVO parentVO=(BoardVO) session.getAttribute("article");
	 * //System.out.println(parentVO); //System.out.println(replyVO);
	 * if(parentVO.getNo()==parentNo) { replyVO.setNo(parentNo);
	 * replyVO.setGrp(parentVO.getGrp()); replyVO.setSeq(parentVO.getSeq()+1);
	 * replyVO.setLvl(parentVO.getLvl()+1);
	 * 
	 * boardService.replyWrite(replyVO);
	 *  return new RedirectView("boardList"); }else
	 * { return new RedirectView("error"); } }
	 */

}
