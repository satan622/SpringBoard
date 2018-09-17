package com.spring.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.BoardVO;
import com.spring.domain.PageMaker;
import com.spring.domain.SearchCriteria;
import com.spring.service.BoardService;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {
	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);

	@Inject
	private BoardService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listPage(SearchCriteria cri, Model model) throws Exception {
		logger.info(cri.toString());
		model.addAttribute("list", service.listSearchCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listSearchCountCriteria(cri));

		model.addAttribute("pageMaker", pageMaker);
	}

	// listPage.jsp에서 해당 "TITLE"을 눌렀을 때
	// 목록 페이지에서 특정 글을 클릭했을 때 조회 페이지로 이동한 후 다시 목록 페이지로 가기 위한 설계
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, Model model)
			throws Exception {
		logger.info("readPage_GET");
		model.addAttribute(service.read(bno));// 변수: boardVO
		// readPage.jsp
	}

	// remove
	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		service.remove(bno);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());

		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/sboard/list";
	}

	// readPage.jsp에서 "Modify" 눌렀을 때(.btn-warning) 동작
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPagingGET(@RequestParam("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, Model model)
			throws Exception {
		model.addAttribute(service.read(bno)); // modifyPage.jsp
	}

	// modifyPage.jsp에서 "SAVE" 눌렀을 때(.btn-warning) 동작: 실제 수정 작업
	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagingPOST(BoardVO bo, @ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rttr)
			throws Exception {
		logger.info("modifyPagingPOST........");
		service.modify(bo);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());

		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/sboard/list";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVO vo, Model model) throws Exception { // "/sboard/register"
		logger.info("register_GET()......");
		// return 생략: register.jsp이동
	}
	
	//글 등록 폼에서 완료 버튼 눌렀을 시
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO vo, RedirectAttributes rttr) throws Exception { // "/sboard/register"
		logger.info("register_POST()......");
		logger.info(vo.toString());
		service.regist(vo);// insert
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/sboard/list";
	}
	
	@RequestMapping("/getAttach/{bno}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("bno") Integer bno) throws Exception{
		return service.getAttach(bno);
	}
	
}
