package org.mrstm.quoraapi.controllers;

import jakarta.validation.Valid;
import org.mrstm.quoraapi.dto.Like.LikeRequestDTO;
import org.mrstm.quoraapi.services.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/{entityType}/{entityId}/likes")
    public ResponseEntity<String> addLike(@PathVariable String entityType , @PathVariable int entityId , @RequestBody @Valid LikeRequestDTO likeRequestDTO) {
        Boolean res = likeService.addLike(entityType, entityId, likeRequestDTO.getUserId());
        return new ResponseEntity<String>(res ? "Liked" : "Something went wrong!", res ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
