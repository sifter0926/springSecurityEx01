package org.pgm.securitydemo.controller;

import org.pgm.securitydemo.config.auth.PrincipalDetails;
import org.pgm.securitydemo.domain.Board;
import org.pgm.securitydemo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        boardService.insert(board,principal.getUser() );
        return "redirect:/board/list";
    }


}
