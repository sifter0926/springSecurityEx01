package org.pgm.securitydemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.pgm.securitydemo.config.auth.PrincipalDetails;
import org.pgm.securitydemo.domain.Board;
import org.pgm.securitydemo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("insert")
    public String insert() {
        return "/board/register";
    }
    @PostMapping("/insert")
    public String insert(Board board,
                         @AuthenticationPrincipal PrincipalDetails principal) {
        log.info("insert board controller: ", principal.getUser());
        //board.setWriter(principal.getUsername());
        boardService.insert(board,principal.getUser() );
        return "redirect:/board/list";
    }
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("lists", boardService.list());
        return "/board/list";
    }

    @GetMapping({"/view","modify"})
    public void view(@RequestParam("num") Long num, Model model) {
        model.addAttribute("board", boardService.findById(num));
    }
    @PostMapping("/update")
    public String update(Board board, @AuthenticationPrincipal PrincipalDetails principal) {
        log.info("update board controller: ", board);
        boardService.update(board, principal.getUser());
        return "redirect:/board/view?num="+board.getNum();
    }
    @GetMapping("/delete")
    public String delete(@RequestParam Long num) {
        boardService.delete(num);
        return "redirect:/board/list";
    }



}
