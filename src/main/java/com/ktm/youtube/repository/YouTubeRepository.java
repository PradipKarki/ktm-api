package com.ktm.youtube.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ktm.youtube.model.YouTubePO;

public interface YouTubeRepository extends JpaRepository<YouTubePO, Long> {
		/* no custom method */
}
