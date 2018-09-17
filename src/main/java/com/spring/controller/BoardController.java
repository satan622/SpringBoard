package com.spring.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.domain.PageMaker;
import com.spring.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
   private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
   
   @Inject
   private BoardService service;
   
   @RequestMapping(value="/register",method=RequestMethod.GET)
   public void registerGET(BoardVO vo, Model model) throws Exception{ //  "/board/register"
	   logger.info("register_GET()......");
	   //return 생략: register.jsp이동
   }
   
//   @RequestMapping(value="/register",method=RequestMethod.POST)
//   public String registerPOST(BoardVO vo, Model model) throws Exception{ //  "/board/register"
//	   logger.info("register_POST()......");
//	   logger.info(vo.toString());
//	   service.regist(vo);//insert
//	  model.addAttribute("result", "success~~~~~~~~~~~");
//	//return "/board/success";
//	  return "redirect:/board/listAll";
//   }
   
   @RequestMapping(value="/register",method=RequestMethod.POST)
   public String registerPOST(BoardVO vo, RedirectAttributes rttr) throws Exception{ //  "/board/register"
	   logger.info("register_POST()......");
	   logger.info(vo.toString());
	   service.regist(vo);//insert
	   rttr.addFlashAttribute("msg","SUCCESS");
	   return "redirect:/board/listAll";
   }
   
   @RequestMapping(value="/listAll", method=RequestMethod.GET)
   public void listAll(Model model) throws Exception{
	   logger.info("listAll_GET()......");
	   model.addAttribute("list", service.listAll());
	   //return "/board/listAll"; 생략, listAll.jsp
   }
   
   //listAll.jsp에서 해당 "TITLE"을 눌렀을때
   @RequestMapping(value="/read", method=RequestMethod.GET)
   public void read(@RequestParam("bno") int bno, Model model)throws Exception{
	   logger.info("read_GET().......");
	   model.addAttribute(service.read(bno));//변수: boardVO
	   //model.addAttribute("result",service.read(bno));
	   //return /board/read; read.jsp
   }
   //"Modify" 버튼을 눌렀을때 동작:원래 데이터를 읽어와서 Model전송
   @RequestMapping(value="/modify", method=RequestMethod.GET)
   public void modifyGET(int bno, Model model) throws Exception{
	   logger.info("modifyGET().......");
	   model.addAttribute(service.read(bno));//modify.jsp
   }
   //실제 수정작업 : modify.jsp안에서 "SAVE"을 눌렀을때 동작
   @RequestMapping(value="/modify", method=RequestMethod.POST)
   public String modifyPOST(BoardVO bo, RedirectAttributes rttr) throws Exception{
	   logger.info("modifyPOST().......");
	   service.modify(bo);
	   rttr.addFlashAttribute("msg","ModifyPost Success");
	   return "redirect:/board/listAll";
   }
   
   @RequestMapping(value="/remove", method=RequestMethod.POST)
   public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception{
	   logger.info("remove_POST().....");
	   service.remove(bno);
	   rttr.addFlashAttribute("msg", "REMOVE SUCCESS");
	return "redirect:/board/listAll";
	   
   }
   //하나의 페이지로 보여질 글의 수 및 몇개 페이지인지 나타내기
   @RequestMapping(value="/listCri", method=RequestMethod.GET)
   public void listAll(Criteria cri, Model model) throws Exception{
	   logger.info("listCri......");
	   model.addAttribute("list", service.listCriteria(cri));
   }
   
   //화면 하단에 보여질 페이지 정보: 목록 데이터를 모델에 저장 & PageMaker를 구성해서 모델에 담는 작업
   @RequestMapping(value="/listPage", method=RequestMethod.GET)
   public void listPage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception{
	   logger.info(cri.toString());
	   model.addAttribute("list", service.listCriteria(cri));
	   PageMaker pageMaker = new PageMaker();
	   pageMaker.setCri(cri);
//	   pageMaker.setTotalCount(13100); 전체 글릐 개수 
	   pageMaker.setTotalCount(service.listCountCriteria(cri));
	   
	   model.addAttribute("pageMaker", pageMaker);
	   //listPage.jsp로 전달
   }
   
   //listPage.jsp에서 해당 "TITLE"을 눌렀을 때
   //목록 페이지에서 특정 글을 클릭했을 때 조회 페이지로 이동한 후 다시 목록 페이지로 가기 위한 설계
   @RequestMapping(value="/readPage", method=RequestMethod.GET)
   public void read(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model)throws Exception{
	   logger.info("readPage_GET().......");
	   model.addAttribute(service.read(bno));//변수: boardVO
	   //readPage.jsp
   }
   
   //readPage.jsp에서 "Modify" 눌렀을 때(.btn-warning) 동작
   @RequestMapping(value="/modifyPage", method=RequestMethod.GET)
   public void modifyPagingGET(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
	   model.addAttribute(service.read(bno)); //modifyPage.jsp
   }
   
   //modifyPage.jsp에서 "SAVE" 눌렀을 때(.btn-warning) 동작: 실제 수정 작업
   @RequestMapping(value="/modifyPage", method=RequestMethod.POST)
   public String modifyPagingPOST(BoardVO bo, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) throws Exception {
	   logger.info("modifyPagingPOST........");
	   service.modify(bo);
	   rttr.addAttribute("page", cri.getPage());
	   rttr.addAttribute("perPageNum", cri.getPerPageNum());
	   rttr.addFlashAttribute("msg", "SUCCESS");
	   
	   return "redirect:/board/listPage";
   }
   
   @RequestMapping(value="/removePage", method=RequestMethod.POST)
   public String remove(@RequestParam("bno") int bno, Criteria cri, RedirectAttributes rttr) throws Exception{
	   service.remove(bno);
	   rttr.addAttribute("page", cri.getPage());
	   rttr.addAttribute("perPageNum", cri.getPerPageNum());
	   rttr.addFlashAttribute("msg", "SUCCESS");
	   
	   return "redirect:/board/listPage";
   }
   
}











