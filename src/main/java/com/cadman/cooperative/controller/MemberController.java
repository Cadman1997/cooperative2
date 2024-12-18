package com.cadman.cooperative.controller;


import com.cadman.cooperative.model.Member;
import com.cadman.cooperative.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/register")

    public ResponseEntity<?> registerMember(@RequestBody Member newMember) {
        Optional<Member> existingMember = memberRepository.findByEmail(newMember.getEmail());
        if (existingMember.isPresent()) {
            return ResponseEntity.badRequest().body("Email already in use. Please use a different email.");
        }
        String newMemberId = generateMemberId();
        newMember.setMemberId(newMemberId);
        Member savedMember = memberRepository.save(newMember);
        return ResponseEntity.ok(savedMember);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@RequestBody Member loginRequest) {
        Optional<Member> member = memberRepository.findByMemberIdAndPassword(loginRequest.getMemberId(), loginRequest.getPassword());
        if (member.isPresent()) {
            return ResponseEntity.ok("Login successful. Welcome, " + member.get().getName() + "!");
        }
        return ResponseEntity.badRequest().body("Invalid Membership ID or password.");
    }

    private String generateMemberId() {
        Member lastMember = memberRepository.findTopByOrderByIdDesc();
        int nextId = (lastMember != null) ? Integer.parseInt(lastMember.getMemberId().substring(3)) + 1 : 1;
        return String.format("OGC%03d", nextId);
    }
}
